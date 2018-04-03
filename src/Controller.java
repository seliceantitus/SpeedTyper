import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Selicean Titus on 05/05/2017.
 */
public class Controller implements Observer {

    View view;
    TimerClass timer;

    int totalWords = 0;
    int wrongWords = 0;
    int keyStrokes = 0;

    public Controller() {
        view = new View();
        timer = new TimerClass(60);
        start();
    }

    private void start() {
        timer.addObserver(this);
        view.show();
        view.attachInputListener(new KeyListener() {
            String word;
            boolean isTimerInitialized = false;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_SPACE:
                        totalWords++;
                        if (!isTimerInitialized) {
                            isTimerInitialized = true;
                            timer.start();
                        }
                        String input = view.getWordInput().trim();
                        if (!input.equals(word))
                            wrongWords++;
                        view.setCurrentWordBackground(input.equals(word));
                        view.clearInput();
                        word = Words.getRandomWord(new ArrayList<>()).toLowerCase();
                        view.setCurrentWord(word);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if ((keyCode > 64 && keyCode < 91) || (keyCode > 96 && keyCode < 123))
                    if (KeyEvent.getKeyText(keyCode).length() == 1)
                        keyStrokes++;

                switch (keyCode){
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_SPACE:
                        view.clearInput();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private String generateResult() {
        int correctWords = totalWords - wrongWords;
        double accuracy = (correctWords * 100) / totalWords;
        return "You got " + totalWords + " words per minute, out of which\n" +
                (totalWords - wrongWords) + " correct\n" +
                wrongWords + " incorrect\n" +
                accuracy + "% as overall accuracy\n"
                ;
    }

    private void reset(){
        totalWords = 0;
        wrongWords = 0;
        keyStrokes = 0;
        view.resetComponents();
        timer.reset();
        start();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            int time = Integer.parseInt(arg.toString());
            view.setTimerLabel(time);
        } catch(Exception e){
            view.setTimerLabel("Time's up");
            view.stop();
            view.setStats(generateResult());
            view.attachReset(e1 -> {
                reset();
            });
        }
    }

    public static void main(String[] args){
        Controller controller = new Controller();
    }
}
