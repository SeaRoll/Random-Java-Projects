import javax.swing.*;
import java.awt.*;

/**
 * Created by Yohan on 4/10/2017.
 */
public class theProgram extends JFrame {

    private static int[] YPoint;
    private static double A = 1, B = 2, C = 0;
    private static int zoomAmountX = 1, zoomAmountY = 1;
    private static boolean drawLines = true;

    public theProgram() { //The Frame
        super("Graph");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        calculateYPoint();
    }

    public static void main(String[] args) {
        //Input all the variables
        inputVars();

        //initialize the frame
        theProgram frame = new theProgram();
    }

    private static void inputVars() {
        String varA = JOptionPane.showInputDialog("Variable for A: ");
        String varB = JOptionPane.showInputDialog("Variable for B: ");
        String varC = JOptionPane.showInputDialog("Variable for C: ");

        if(!varA.equals("") && !varB.equals("") && !varC.equals("")) {
            A = Integer.parseInt(varA);
            B = Integer.parseInt(varB);
            C = Integer.parseInt(varC);
        }else{
            System.out.println("//WARNING//\nOne of the strings were empty!");
            inputVars();
        }
    }

    private void calculateYPoint() { //Calculate Y Point
        YPoint = new int[100];
        for(int x = -50; x < 50; x++) {
            YPoint[x + 50] = (int)((-A * ( x * x)) - (B * x) - C);
        }
    }

    public void paint(Graphics g) { //Paint the frame
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 400);

        g.setColor(Color.WHITE);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(200, 0, 200, 400);

        for(int x = -50; x < 50; x++) { //DRAW POINTS & LINES
            g.drawLine((x * zoomAmountX) + 200, (YPoint[x + 50] * zoomAmountY) + 200, (x * zoomAmountX) + 200, (YPoint[x + 50] * zoomAmountY) + 200);

            if(drawLines == true && x < 49) { //LINES
                g.drawLine((x * zoomAmountX) + 200, (YPoint[x + 50] * zoomAmountY) + 200, ((x + 1) * zoomAmountX) + 200, (YPoint[x + 51] * zoomAmountY) + 200);
            }
        }
    }

}
