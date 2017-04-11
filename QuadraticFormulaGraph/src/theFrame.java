import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yohan on 4/11/2017.
 */
public class theFrame extends JFrame implements ActionListener {

    public JTextField aInput, bInput, cInput;
    public drawPanel topPanel;

    public theFrame() {
        //FRAME STUFF
        super("Quadratic Formula to Graph");
        setSize(400, 500);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //PANEL WITH GRAPHICS
        topPanel = new drawPanel();
        add(topPanel, BorderLayout.PAGE_START);

        drawButtons();
        drawInputLines();


        setVisible(true);
    }

    private void drawButtons() {
        JButton showLinesButton = new JButton("Show Lines");
        showLinesButton.setPreferredSize(new Dimension(150, 20));
        showLinesButton.addActionListener(this);
        showLinesButton.setActionCommand("drawLines");
        add(showLinesButton, BorderLayout.LINE_START);

        JButton CalculateButton = new JButton("Update Graph");
        CalculateButton.setPreferredSize(new Dimension(150, 20));
        CalculateButton.addActionListener(this);
        CalculateButton.setActionCommand("Calculate");
        add(CalculateButton, BorderLayout.LINE_END);
    }

    private void drawInputLines() {
        //Text area for Input Lines
        JPanel textAreas = new JPanel();
        add(textAreas, BorderLayout.CENTER);
        textAreas.setLayout(new BorderLayout());

        //Input Lines
        aInput = new JTextField("A variable", 1);
        aInput.setPreferredSize(new Dimension(150, 20));
        textAreas.add(aInput, BorderLayout.PAGE_START);
        bInput = new JTextField("B variable", 1);
        bInput.setPreferredSize(new Dimension(150, 20));
        textAreas.add(bInput, BorderLayout.CENTER);
        cInput = new JTextField("C variable", 1);
        cInput.setPreferredSize(new Dimension(150, 20));
        textAreas.add(cInput, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Calculate") {
            calculateProcedures();
        }else if(e.getActionCommand() == "drawLines") {
            theProgram.doOrDoNotLines();
            topPanel.repaintThis();
        }
    }

    private void calculateProcedures() { //Does the Update graph procedures
        theProgram.A = Double.parseDouble(aInput.getText());
        theProgram.B = Double.parseDouble(bInput.getText());
        theProgram.C = Double.parseDouble(cInput.getText());
        theProgram.calculateYPoint();
        topPanel.repaintThis();
    }
}
