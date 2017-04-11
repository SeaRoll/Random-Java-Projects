import javax.swing.*;
import java.awt.*;

/**
 * Created by Yohan on 4/10/2017.
 */
public class theProgram extends JFrame {

    public static int[] YPoint;
    public static double A = 0, B = 0, C = 0;
    public static int zoomAmountX = 1, zoomAmountY = 1;
    public static boolean drawLines = true;

    public static void main(String[] args) {
        YPoint = new int[100];
        calculateYPoint();

        //initialize the frame
        theFrame frame = new theFrame();
    }

    public static void doOrDoNotLines() { //Method, which draws or hides lines.
        if(drawLines) {
            drawLines = false;
        }else{
            drawLines = true;
        }
    }

    public static void calculateYPoint() { //Calculate Y Point
        for(int x = -50; x < 50; x++) {
            YPoint[x + 50] = (int)((-A * ( x * x)) - (B * x) - C);
        }
    }

}
