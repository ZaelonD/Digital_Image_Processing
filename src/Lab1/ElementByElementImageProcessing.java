package Lab1;

public class ElementByElementImageProcessing {
    public static void main(String[] args) {
        // Выбираем имплементацию логарифмического преобразования
        TransformationLogic transformationLogic = new LogarithmicTransformationImpl("resources/image.png");
        // Запускаем программу
        transformationLogic.transform();
    }
}
