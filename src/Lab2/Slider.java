package Lab2;

import javax.swing.*;

public class Slider {
    static final int MIN_VALUE = 1;
    static int INIT_VALUE = 5;
    static final int MAX_VALUE = 11;
    private final JSlider slider;

    public Slider() {
        this.slider = new JSlider(MIN_VALUE, MAX_VALUE, INIT_VALUE);
        slider.setMajorTickSpacing(MAX_VALUE);
        slider.setMinorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
    }

    public JSlider getSlider() {
        return slider;
    }

    public int getInitValue() {
        return INIT_VALUE;
    }
}