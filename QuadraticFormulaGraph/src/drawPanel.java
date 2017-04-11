import javax.swing.*;
import java.awt.*;

/**
 * Created by Yohan on 4/11/2017.
 */
public class drawPanel extends JPanel {

    public drawPanel() {
        //Just set Sizes
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paintComponent(Graphics g) {

        //DRAW BACKGROUNDS STUFF
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 400);

        g.setColor(Color.WHITE);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(200, 0, 200, 400);
        //---------------------------------------------//

        //DRAW LINES & POINTS
        for(int x = -50; x < 50; x++) { //DRAW POINTS & LINES
            g.drawLine((x * theProgram.zoomAmountX) + 200, (theProgram.YPoint[x + 50] * theProgram.zoomAmountY) + 200, (x * theProgram.zoomAmountX) + 200, (theProgram.YPoint[x + 50] * theProgram.zoomAmountY) + 200);

            if(theProgram.drawLines == true && x < 49) { //LINES
                g.drawLine((x * theProgram.zoomAmountX) + 200, (theProgram.YPoint[x + 50] * theProgram.zoomAmountY) + 200, ((x + 1) * theProgram.zoomAmountX) + 200, (theProgram.YPoint[x + 51] * theProgram.zoomAmountY) + 200);
            }
        }
    }

    public void repaintThis() {
        repaint();
    }
}
