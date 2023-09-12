package Lab1;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JFrame window = new JFrame();
    JLabel screen = new JLabel();
    private final MatOfByte buf;

    public GUI() throws HeadlessException {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        buf = new MatOfByte();
    }

    public void setImage(Mat image) {
        Imgcodecs.imencode(".jpg", image, buf);
        ImageIcon ic = new ImageIcon(buf.toArray());
        screen.setIcon(ic);
        window.getContentPane().add(screen);
        window.pack();
    }
}
