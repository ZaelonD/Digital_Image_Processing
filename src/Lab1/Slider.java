package Lab1;

import javax.swing.*;

public class Slider {
    static final int MIN_VALUE = 0;
    static int INIT_VALUE = 50;
    static final int MAX_VALUE = 100;
    private final JSlider slider;

    public Slider() {
        this.slider = new JSlider(MIN_VALUE, MAX_VALUE, INIT_VALUE);
    }

    public JSlider getSlider() {
        return slider;
    }

    public int getInitValue() {
        return INIT_VALUE;
    }
}