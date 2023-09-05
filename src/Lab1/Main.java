package Lab1;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Version " + Core.VERSION);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread("resources/image.jpg");
        Mat imgEmpty = new Mat(img.size(), img.type());
        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", imgEmpty, buf);
        ImageIcon ic = new ImageIcon(buf.toArray());
        screen.setIcon(ic);
        window.getContentPane().add(screen);
        window.pack();
    }
}
