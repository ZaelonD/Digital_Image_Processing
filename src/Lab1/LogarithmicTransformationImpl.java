package Lab1;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LogarithmicTransformationImpl implements TransformationLogic {
    private final Mat image;
    private final GUI gui;
    private final Slider slider;

    public LogarithmicTransformationImpl(String path) {
        // Инициализируем библиотеку openCV
        OpenCVLibrary.init();
        // Преобразовываем изображение в полутоновое
        this.image = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE);
        // Создаем ползунок
        this.slider = new Slider();
        // Создаем графический интерфейс для просмотра изображения
        this.gui = new GUI(slider);
    }

    // Функция преобразования
    @Override
    public void transform() {
        // Рисуем начальное изображение
        draw(slider.getInitValue());

        // Объект changeListener запускается тогда, когда положение ползунка изменилось
        slider.getSlider().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                //if (!source.getValueIsAdjusting())
                    draw(source.getValue()); // Получаем значение ползунка
            }
        });
    }

    private static Mat logarithmicTransform(Mat image, double c) {
        // Создаем результирющую матрицу соответствующего размера и типом массива,
        // используемого для хранения данных
        Mat result = new Mat(image.size(), image.type());
        // Применение логарифмического преобразования к каждому пикселю
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                // Значение яркости исходного изображения каждого пикселя
                double r = image.get(y, x)[0];
                // Формула логарифмического преобразования s = c*log(1 + r),
                // где s зачение яркости обработанного изображения
                double s = c * Math.log(1 + r);
                // Записываем каждый преобразованный пиксель в результирующую матрицу
                result.put(y, x, s);
            }
        }
        return result;
    }

    private void draw(int c) {
        Mat result = logarithmicTransform(image, c);
        gui.setImage(result);
    }
}