package Lab2;

import Lab1.OpenCVLibrary;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.util.Arrays;

public class MedianImageFilteringImpl implements FilteringLogic {

    private final Mat image;
    private final GUI gui;
    private final Slider slider;

    public MedianImageFilteringImpl(String path) {
        // Инициализируем библиотеку openCV
        OpenCVLibrary.init();
        // Преобразовываем изображение в полутоновое
        this.image = Imgcodecs.imread(path, Imgcodecs.IMREAD_GRAYSCALE);
        // Создаем ползунок
        this.slider = new Slider();
        // Создаем графический интерфейс для просмотра изображения
        this.gui = new GUI(slider);
    }

    // Функция фильтрации
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

    // Метод реализации медианной фильтрации
    public static Mat applyMedianFilter(Mat image, int smoothingAreaSize) {
        // Создаем результирющую матрицу соответствующего размера и типом массива,
        // используемого для хранения данных
        Mat filteredImage = new Mat(image.size(), image.type());
        // Находим центр окрестности
        int halfSmoothingAreaSize = smoothingAreaSize / 2;
        // Проходим циклами по изображению
        for (int x = halfSmoothingAreaSize; x < image.rows() - halfSmoothingAreaSize; x++) {
            for (int y = halfSmoothingAreaSize; y < image.cols() - halfSmoothingAreaSize; y++) {
                // Создаем пустой фильтр (для удобства представим его в виде массива, а не матрицы)
                int[] values = new int[smoothingAreaSize * smoothingAreaSize];
                // Проходимся циклами по окрестности
                for (int i = 0; i < smoothingAreaSize; i++) {
                    for (int j = 0; j < smoothingAreaSize; j++) {
                        // Записываем значения каждого пикселя окрестности в фильтр
                        values[i * smoothingAreaSize + j] = (int) image.get(x - halfSmoothingAreaSize + i, y - halfSmoothingAreaSize + j)[0];
                    }
                }
                // Считаем медиану
                int median = findMedian(values);
                // Перезаписываем значение пикселя в определенной точке изображения
                filteredImage.put(x, y, median);
            }
        }
        // Возвращаем отфильтрованное изображение
        return filteredImage;
    }

    // Метод для вычисления медианы
    public static int findMedian(int[] values) {
        // Копируем значения фильтра в новый массив
        int[] sortedValues = values.clone();
        // Сортируем по возрастанию
        Arrays.sort(sortedValues);
        // Вычисляем медиану в зависимости от четности / нечетности кол-ва элементов в фильтре
        return values.length % 2 == 0 ? (sortedValues[values.length / 2] + sortedValues[(values.length - 1) / 2]) / 2
                : sortedValues[values.length / 2];
    }

    // Функция перерисовки изображения
    private void draw(int smoothingAreaSize) {
        Mat result = applyMedianFilter(image, smoothingAreaSize);
        gui.setImage(result);
    }
}
