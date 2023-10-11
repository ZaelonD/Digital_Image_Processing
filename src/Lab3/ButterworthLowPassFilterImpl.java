package Lab3;

import Lab1.OpenCVLibrary;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class ButterworthLowPassFilterImpl implements FrequencyProcessingLogic {
    private final Mat grayImage;
    private final GUI gui;
    private final Slider slider;

    public ButterworthLowPassFilterImpl(String path) {
        // Инициализируем библиотеку openCV
        OpenCVLibrary.init();
        // Преобразовываем изображение в полутоновое
        this.grayImage = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE);
        // Создаем ползунок
        this.slider = new Slider();
        // Создаем графический интерфейс для просмотра изображения
        this.gui = new GUI(slider);
    }

    @Override
    public void filtrate() {
        // Рисуем начальное изображение
        draw(slider.getInitValue());
        // Объект changeListener запускается тогда, когда положение ползунка изменилось
        slider.getSlider().addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            draw(source.getValue()); // Получаем значение ползунка
        });
    }

    private Mat applyButterworthLowPassFilter(Mat grayImage, int cutoffFrequency) {

        // Преобразование Фурье
        Mat complexImage = new Mat(grayImage.size(), CvType.CV_32F);
        grayImage.convertTo(complexImage, CvType.CV_32F);
        Core.dft(complexImage, complexImage);

        // Создание фильтра Баттерворта
        Mat butterworthFilter = createButterworthLowPassFilter(complexImage.size(), cutoffFrequency);

        // Применение фильтра к частотному представлению
        Core.mulSpectrums(complexImage, butterworthFilter, complexImage, 0);

        // Обратное преобразование Фурье
        Core.idft(complexImage, complexImage);

        // Получение результата
        Mat filteredImage = new Mat();
        Core.normalize(complexImage, filteredImage, 0, 255, Core.NORM_MINMAX);
        filteredImage.convertTo(filteredImage, CvType.CV_8U);

        return filteredImage;
    }

    private Mat createButterworthLowPassFilter(Size size, int cutoffFrequency) {
        Mat filter = Mat.ones(size, CvType.CV_32F);
        Point center = new Point(size.width / 2, size.height / 2);
        double n = 2.0;
        for (int y = 0; y < size.height; y++) {
            for (int x = 0; x < size.width; x++) {
                double distance = Math.sqrt(Math.pow(y - center.y, 2) + Math.pow(x - center.x, 2));
                double value = 1 / (1 + Math.pow(distance / cutoffFrequency, 2 * n));
                filter.put(y, x, value);
            }
        }
        return filter;
    }

    // Функция перерисовки изображения
    private void draw(int cutoffFrequency) {
        Mat result = applyButterworthLowPassFilter(grayImage, cutoffFrequency);
        gui.setImage(result);
    }
}