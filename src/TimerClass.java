import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.swing.*;
import java.util.Observable;

/**
 * Created by Selicean Titus on 05/05/2017.
 */
public class TimerClass extends Observable {

    Timer timer;
    private int timeLimit;
    private static int currentTime;
    private boolean running;

    public TimerClass(int timeLimit) {
        timer = new Timer(1000, e -> {
            if (currentTime <= timeLimit) {
                setChanged();
                notifyObservers(currentTime);
                currentTime++;
            } else {
                setChanged();
                notifyObservers("Done");
                timer.stop();
                running = false;
            }
        });
        this.timeLimit = timeLimit;
        currentTime = -1;
    }

    public void start(){
        currentTime = 0;
        running = true;
        timer.start();
    }

    public void reset(){
        currentTime = 0;
    }

    public boolean running(){
        return running;
    }
}
