package Lab3;

import Lab1.OpenCVLibrary;
import Lab2.Slider;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

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

    }
}
