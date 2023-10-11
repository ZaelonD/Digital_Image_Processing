package Lab3;

import javax.swing.*;

public class Slider {
    // Минимальное значение ползунка
    static final int MIN_VALUE = 1;
    // Начальное значение ползунка
    static int INIT_VALUE = 5;
    // Максимальное значение ползунка
    static final int MAX_VALUE = 11;
    private final JSlider slider;

    public Slider() {
        // Создание ползунка
        this.slider = new JSlider(MIN_VALUE, MAX_VALUE, INIT_VALUE);
        // Крайнее положение
        slider.setMajorTickSpacing(MAX_VALUE);
        // Шаг ползунка
        slider.setMinorTickSpacing(2);
        // Делаем привязку ползунка к делениям
        slider.setSnapToTicks(true);
        // Делаем деления ползунка видимыми
        slider.setPaintTicks(true);
    }

    public JSlider getSlider() {
        return slider;
    }

    public int getInitValue() {
        return INIT_VALUE;
    }
}