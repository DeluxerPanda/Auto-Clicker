package se.deluxerpanda.click;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
    @FXML
    private Text labelinfotid;
    @FXML
    private Text Startandstoptext;
    @FXML
    private Text textmusskaklicka;
    @FXML
    private Text Banner_text;
    @FXML
    private TextField timme;
    @FXML
    private TextField minuter;
    @FXML
    private TextField sekunder;
    @FXML
    private Button onstart;
    @FXML
    private Button onstop;
    @FXML
    private Button resetknapp;
    // The timer
    private Timer timer;

    // The countdown task
    private CountdownTask task;

    static int X = 0;
    static int Y = 0;
    public static boolean stop_mus_chek = false;
    @FXML
    public void onstart(ActionEvent Event) throws LineUnavailableException {

        try{

            if (timme.getText().isEmpty()){timme.setText("0");}
            if (minuter.getText().isEmpty()){minuter.setText("0");}
            if (sekunder.getText().isEmpty()){sekunder.setText("0");}

            onstart.setDisable(true);
            onstart.setVisible(false);

            onstop.setDisable(false);
            onstop.setVisible(true);
            Startandstoptext.setText("Stop");
            // Stop the old timer if it is running
            onstop(new ActionEvent());

            // Create a new timer
            timer = new Timer();

            // Create a new countdown task
            task = new CountdownTask();

            // Schedule the countdown task to run every 1000 milliseconds (1 second)
            timer.schedule(task, 0, 1000);
        } catch (Exception e) {
            timer.cancel();
            // Set the timer and task to null
            timer = null;
            task = null;
            labelinfotid.setFill(Color.RED);
            labelinfotid.setText("Något gick snett");
            onstart.setDisable(false);
            onstart.setVisible(true);
            Startandstoptext.setText("Start");
            onstop.setDisable(true);
            onstop.setVisible(false);

        }
    }

    public void onstop(ActionEvent Event) {
        // Cancel the old timer if it exists
        if (timer != null) {
            timer.cancel();
            // Set the timer and task to null
            timer = null;
            task = null;
            labelinfotid.setFill(Color.GREEN);
            labelinfotid.setText("Klar");
            onstart.setDisable(false);
            onstart.setVisible(true);

            onstop.setDisable(true);
            onstop.setVisible(false);
            Startandstoptext.setText("Start");
        }


    }

    // The countdown task class
    private class CountdownTask extends TimerTask {
        int TextNummerTimme = Integer.parseInt(timme.getText());
        int TextNummerMinuter = Integer.parseInt(minuter.getText());
        int TextNummerSekunder = Integer.parseInt(sekunder.getText());

        int counter_sek = TextNummerSekunder;
        int counter_min = TextNummerMinuter;
        int counter_tim = TextNummerTimme;
        public void run() {

            if (counter_sek > 0) {
                counter_sek--;
            } else if (counter_min > 0) {
                counter_sek = 59;
                counter_min--;

            } else if (counter_tim > 0) {
                counter_tim--;
                counter_min = 59;
            }
            labelinfotid.setFill(Color.GOLD);
            labelinfotid.setText(counter_tim + "H," + counter_min + "M," + counter_sek + "S");
            int count_dow = counter_sek + counter_min + counter_tim;
            if (count_dow == 0) {
                 click();
                onstop(new ActionEvent());
            }
        }
    }


    public void getNumberX(){
            java.awt.Point originalLocation = java.awt.MouseInfo.getPointerInfo().getLocation();
            X = (int) originalLocation.getX();
            Y = (int) originalLocation.getY();
        onstart.setVisible(true);
        timme.setVisible(true);
        minuter.setVisible(true);
        sekunder.setVisible(true);
        labelinfotid.setVisible(true);
        Startandstoptext.setVisible(true);
        resetknapp.setVisible(true);
        Banner_text.setVisible(true);
        textmusskaklicka.setVisible(false);
        Startandstoptext.setText("Start");
        stop_mus_chek = true;

    }
    // klicka aotonatisk
    public static void click() {
        try {
            java.awt.Robot robot = new java.awt.Robot();
            robot.mouseMove(X, Y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resetknapp(ActionEvent Event) {
        stop_mus_chek = false;
        onstart.setVisible(false);
        timme.setVisible(false);
        minuter.setVisible(false);
        sekunder.setVisible(false);
        labelinfotid.setVisible(false);
        Startandstoptext.setVisible(false);
        resetknapp.setVisible(false);
        Banner_text.setVisible(false);
        textmusskaklicka.setVisible(true);
        Startandstoptext.setText("Start");
        onstop(new ActionEvent());
    }
}
