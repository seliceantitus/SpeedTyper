import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by Selicean Titus on 05/05/2017.
 */

public class View  {
    private JFrame frame;
    private JPanel mainPanel;

    private JLabel timerLabel;
    private JLabel currentWord;
    private JLabel wordCheck;

    private JTextField wordInput;

    private JTextArea results;

    private JButton reset;

    private KeyListener keyListener;

    public View() {
        frame = new JFrame("Speed type test");
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        initComponents();
        putComponents();
    }

    private void initComponents(){
        timerLabel = new JLabel("Time : ");
        timerLabel.setVisible(true);

        currentWord = new JLabel("Press space to begin");
        currentWord.setFont(new Font("Calibri", Font.PLAIN, 25));

        wordCheck = new JLabel(
                "<html>" +
                        "You will see here if the word entered is " +
                        "<font color = 'green'> correct </font>" +
                        "or <font color = 'red'> wrong </font></html>"
        );

        wordInput = new JTextField(15);
        wordInput.setVisible(true);
        wordInput.setFont(new Font("Calibri", Font.PLAIN, 25));
        wordInput.setHorizontalAlignment(JTextField.CENTER);

        results = new JTextArea(4, 100);
        results.setEditable(false);
        results.setBackground(frame.getBackground());
        results.setVisible(false);

        reset = new JButton("Try again");
        reset.setVisible(false);
    }

    private void putComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 5 ,6, 5);
        gbc.weighty = 1;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(timerLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(currentWord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(wordCheck, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.PAGE_END;
        mainPanel.add(wordInput, gbc);

        frame.add(mainPanel);
    }

    public void show() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(350, 170);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setCurrentWordBackground(boolean isCorrect) {
        if (isCorrect) {
            wordCheck.setForeground(new Color(20, 155, 45));
            wordCheck.setText("Correct!");
        } else {
            wordCheck.setForeground(new Color(239, 88, 74));
            wordCheck.setText("Wrong!");
        }
    }

    public void setCurrentWord(String word) {
        currentWord.setText(word);
    }

    public String getWordInput() {
        return wordInput.getText();
    }

    public void attachInputListener(KeyListener keyListener) {
        this.keyListener = keyListener;
        wordInput.addKeyListener(keyListener);
    }

    public void detachInputListener(){
        wordInput.removeKeyListener(keyListener);
    }

    public void attachReset(ActionListener actionListener){
        reset.addActionListener(actionListener);
    }

    public void clearInput() {
        wordInput.setText("");
    }

    public void stop(){
        wordInput.setEditable(false);
        wordInput.setVisible(false);
        currentWord.setVisible(false);
        wordCheck.setVisible(false);
        detachInputListener();
    }

    public void setStats(String stats){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5 ,5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;

        results.setVisible(true);
        results.setFont(new Font("Calibri", Font.BOLD, 14));
        results.setText(stats);
        mainPanel.add(results, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        reset.setVisible(true);
        mainPanel.add(reset, gbc);
    }

    public void resetComponents() {
        mainPanel.removeAll();
        initComponents();
        putComponents();
    }

    public void setTimerLabel(int currentTime){
        timerLabel.setText("Time : " + currentTime);
    }

    public void setTimerLabel(String text){
        timerLabel.setText(text);
    }

}
