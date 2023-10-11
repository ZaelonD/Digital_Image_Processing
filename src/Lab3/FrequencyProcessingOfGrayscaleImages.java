package Lab3;

public class FrequencyProcessingOfGrayscaleImages {
    public static void main(String[] args) {
        ButterworthLowPassFilterImpl butterworthLowPassFilterImpl = new ButterworthLowPassFilterImpl("resources/image.png");
        butterworthLowPassFilterImpl.filtrate();
    }
}
