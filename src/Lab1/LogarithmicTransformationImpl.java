package Lab1;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

public class LogarithmicTransformationImpl implements TransformationLogic {
    private final String inputImagePath;
    private Mat image;

    public LogarithmicTransformationImpl(String path) {
        // Инициализируем библиотеку openCV
        OpenCVLibrary.init();
        // Получаем путь до изображения
        this.inputImagePath = path;
        // Преобразовываем изображение в полутоновое
        this.image = Imgcodecs.imread(inputImagePath, Imgcodecs.IMREAD_GRAYSCALE);
    }

    @Override
    public void transform() {
        while (true) {
            // Получение значения передвижения ползунка (с)
            // Параметр для настройки интенсивности преобразования (можно изменить)

        }
    }

    //    public static void main(String[] args) {
//        // Загрузка библиотеки OpenCV
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // Загрузка полутонового изображения
//        String inputImagePath = "resources/image.jpg";
//        Mat image = Imgcodecs.imread(inputImagePath, Imgcodecs.IMREAD_GRAYSCALE);
//
//        // Применение логарифмического преобразования
//        Mat result = applyLogarithmicTransformation(image);
//
//        // Отображение и сохранение результата
//        HighGui.imshow("Logarithmic Transformation", result);
//        HighGui.waitKey(0);
//
//        String outputImagePath = "resources/output.jpg";
//        Imgcodecs.imwrite(outputImagePath, result);
//
//        System.out.println("Изображение успешно преобразовано и сохранено в " + outputImagePath);
//    }
//
    private static Mat applyLogarithmicTransformation(Mat image, double c) {
        Mat result = new Mat(image.size(), image.type());
        // Применение логарифмического преобразования к каждому пикселю
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                // Значение яркостей исходного изображения в каждой точке
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
}