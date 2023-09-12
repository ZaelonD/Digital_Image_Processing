package Lab1;

public class ElementByElementImageProcessing {
    public static void main(String[] args) {
        // Выбираем имплементацию логарифмического преобразования
        TransformationLogic transformationLogic = new LogarithmicTransformationImpl();
        // Запускаем программу
        transformationLogic.transform();
    }
}
