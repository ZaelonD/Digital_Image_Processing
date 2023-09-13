package Lab2;

public class SpatialFilteringOfGrayscaleImages {
    public static void main(String[] args) {
        // Выбираем имплементацию медианной фильтрации изображения
        FilteringLogic filteringLogic = new MedianImageFilteringImpl();
        // Запуск программы
        filteringLogic.filtrate();
    }
}
