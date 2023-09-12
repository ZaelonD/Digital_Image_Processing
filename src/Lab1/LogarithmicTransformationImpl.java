package Lab1;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

public class LogarithmicTransformationImpl implements TransformationLogic {
    private final String inputImagePath;

    public LogarithmicTransformationImpl(String path) {
        OpenCVLibrary.init();
        this.inputImagePath = path;
    }

    @Override
    public void transform() {
        Mat image = Imgcodecs.imread(inputImagePath, Imgcodecs.IMREAD_GRAYSCALE);
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
    private static Mat applyLogarithmicTransformation(Mat image) {
        Mat result = new Mat(image.size(), image.type());

        // Параметр для настройки интенсивности преобразования (можно изменить)
        double c = 100;

        // Применение логарифмического преобразования к каждому пикселю
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                double pixelValue = image.get(y, x)[0];
                double newValue = c * Math.log(1 + pixelValue);
                result.put(y, x, newValue);
            }
        }

        return result;
    }
}