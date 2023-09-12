package Lab1;

import org.opencv.core.Core;

public class OpenCVLibrary {
    public static void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
