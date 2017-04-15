import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by Yohan on 4/14/2017.
 */
public class JCalcFrame extends JFrame implements ActionListener {

    private String firstBatch = "", secondBatch = "", actionType = "";
    private int step = 0;
    private JTextField displayPanel;


    JCalcFrame() {
        super("JCalculator");
        setSize(210, 410);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        //the panel that displays.
        displayPanel = new JTextField("", 1);
        displayPanel.setBounds(10, 10, 170, 50);
        displayPanel.setEditable(false);
        add(displayPanel);

        //Initialize the buttons
        initButtons();

        setVisible(true);

    }

    private void initButtons() { //Initialises the number buttons
        JButton[] numberButton = new JButton[9];

        int currentNumber = 1;
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                numberButton[currentNumber - 1] = new JButton("" + currentNumber);
                numberButton[currentNumber - 1].setBounds(10 + (x * 60), 70 + (y * 60), 50, 50);
                add(numberButton[currentNumber - 1]);
                numberButton[currentNumber - 1].setActionCommand(String.valueOf(currentNumber));
                numberButton[currentNumber - 1].addActionListener(this);
                currentNumber++;
            }
        }

        //---/ All the buttons /---//
        JButton addButton = new JButton("+");
        addButton.setBounds(10, 250, 50, 50);
        addButton.setActionCommand("+");
        addButton.addActionListener(this);
        add(addButton);

        JButton subButton = new JButton("-");
        subButton.setBounds(70, 250, 50, 50);
        subButton.setActionCommand("-");
        subButton.addActionListener(this);
        add(subButton);

        JButton multiButton = new JButton("x");
        multiButton.setBounds(130, 250, 50, 50);
        multiButton.setActionCommand("x");
        multiButton.addActionListener(this);
        add(multiButton);

        JButton dividButton = new JButton("/");
        dividButton.setBounds(10, 310, 50, 50);
        dividButton.setActionCommand("/");
        dividButton.addActionListener(this);
        add(dividButton);

        JButton resultButton = new JButton("=");
        resultButton.setBounds(70, 310, 50, 50);
        resultButton.setActionCommand("=");
        resultButton.addActionListener(this);
        add(resultButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "1":
                addNumber(1);
                break;
            case "2":
                addNumber(2);
                break;
            case "3":
                addNumber(3);
                break;
            case "4":
                addNumber(4);
                break;
            case "5":
                addNumber(5);
                break;
            case "6":
                addNumber(6);
                break;
            case "7":
                addNumber(7);
                break;
            case "8":
                addNumber(8);
                break;
            case "9":
                addNumber(9);
                break;
            case "+":
                gotoSecondBatch("+");
                break;
            case "-":
                gotoSecondBatch("-");
                break;
            case "x":
                gotoSecondBatch("x");
                break;
            case "/":
                gotoSecondBatch("/");
                break;
            case "=":
                showResult();
                break;
        }
    }

    private void showResult() {
        double result = 0;
        DecimalFormat format = new DecimalFormat("#.####");
        switch(actionType) {
            case "+":
                result = Double.parseDouble(firstBatch) + Double.parseDouble(secondBatch);
                break;
            case "-":
                result = Double.parseDouble(firstBatch) - Double.parseDouble(secondBatch);
                break;
            case "x":
                result = Double.parseDouble(firstBatch) * Double.parseDouble(secondBatch);
                break;
            case "/":
                result = Double.parseDouble(firstBatch) / Double.parseDouble(secondBatch);
                break;
        }
        displayPanel.setText("" + format.format(result));
        actionType = "";
        firstBatch = "";
        secondBatch = "";
        step = 0;
    }

    private void gotoSecondBatch(String Type) {
        if(step == 0) {
            actionType = Type;
            step = 1;
            updatePanel();
        }
    }

    private void updatePanel() {
        displayPanel.setText(firstBatch + " " + actionType + " " + secondBatch);
    }

    private void addNumber(int number) { //Add number to displayPanel
        if(step == 0) {
            firstBatch += "" + number;
            displayPanel.setText(firstBatch);
        }else{
            secondBatch += "" + number;
            displayPanel.setText(secondBatch);
        }

        updatePanel();
    }
}
