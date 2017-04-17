import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Yohan
 */
public class frame extends JFrame implements ActionListener {

    JTextField textField;
    JTextArea textArea;
    public String filePath = null;

    public frame() {
        super("Document Searcher");
        setSize(350, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        //TextField
        textField = new JTextField(1);
        textField.setBounds(10, 10, 160, 22);
        textField.setBorder(BorderFactory.createLineBorder(Color.black));

        //Add FilepathButton
        JButton enterButton = new JButton("File");
        enterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        enterButton.setBackground(Color.white);
        enterButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        enterButton.setHorizontalTextPosition(AbstractButton.CENTER);
        enterButton.setMnemonic(KeyEvent.VK_ENTER);
        enterButton.setActionCommand("filepath");
        enterButton.addActionListener(this);
        enterButton.setBounds(175, 10, 80, 22);

        //Add SearchButton
        JButton searchButton = new JButton("Search");
        searchButton.setBorder(BorderFactory.createLineBorder(Color.black));
        searchButton.setBackground(Color.white);
        searchButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        searchButton.setHorizontalTextPosition(AbstractButton.CENTER);
        searchButton.setMnemonic(KeyEvent.VK_ENTER);
        searchButton.setActionCommand("search");
        searchButton.addActionListener(this);
        searchButton.setBounds(260, 10, 75, 22);

        //TextArea
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea.setBounds(10, 40, 325, 100);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        add(enterButton);
        add(searchButton);
        add(textField);
        add(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("filepath".equals(e.getActionCommand())) {
            filePath = getFilePath();
            textField.setText(filePath);
        }

        if ("search".equals(e.getActionCommand())) {
            googleAPI.doubleSearch(filePath);
        }
    }

    //Get Filepath
    private String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        int option = chooser.showOpenDialog(chooser);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String fPath = selectedFile.getAbsolutePath();
            return fPath;
        }

        return "null";
    }
}
