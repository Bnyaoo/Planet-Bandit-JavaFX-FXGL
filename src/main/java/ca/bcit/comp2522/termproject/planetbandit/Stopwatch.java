package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

    static int interval;
    static Timer timer;


    private static void setInterval() {
        if (interval == 1) {
            timer.cancel();
        }
        --interval;
    }

    public static int getInterval() {
        return interval;
    }



    public static void startTimer() {
        final int timerx = 100;
        final int timery = 50;

        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = 10;
        System.out.println(10);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval();
                System.out.println(getInterval());
            }
        }, delay, period);
    }

}
