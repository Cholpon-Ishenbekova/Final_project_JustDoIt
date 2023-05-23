package com.example.justtodoit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

import java.util.EventListener;
import java.util.Timer;

public class JustDoItController {

    @FXML
    private Button addBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private ImageView doneBtn;

    @FXML
    private ImageView removeBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Label tasksLabel;

    @FXML
    private Label time1;

    @FXML
    private Label time2;
    @FXML
    private Button pomoBtn;

    @FXML
    private Button breakBtn;

    private int sessionTime = 1500;
    private int elapsedSeconds = sessionTime;
    private Timeline timer;
    private boolean startClicked = false;

    public void initialize() {
        updateTimeLabel();

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (elapsedSeconds > 0) {
                elapsedSeconds--;
                updateTimeLabel();
            } else {
                stopTimerInZero();
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);

    }

    @FXML
    private void handleStartButton() {
        if (!startClicked) {
            startBtn.setText("STOP");
            startClicked = true;
            timer.play();
        } else {
            startBtn.setText("START");
            startClicked = false;
            timer.pause();
        }
    }

    @FXML
    private void handleResetButton() {
        timer.stop();
        elapsedSeconds = sessionTime;
        updateTimeLabel();
        startBtn.setText("START");
        startClicked = false;
    }

    private void updateTimeLabel() {
        int hours = elapsedSeconds / 3600;
        int minutes = (elapsedSeconds % 3600) / 60;
        int seconds = elapsedSeconds % 60;

        String hString = String.format("%02d", hours);
        String mString = String.format("%02d", minutes);
        String sString = String.format("%02d", seconds);

        time1.setText(hString + ":" + mString + ":" + sString);
    }

    private void stopTimerInZero() {
        timer.stop();
        elapsedSeconds = sessionTime;
        updateTimeLabel();
        startBtn.setText("START");
        startClicked = false;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Session End");
        alert.setHeaderText("Nice Session");

        ButtonType continueButtonType = new ButtonType("Continue");
        ButtonType cancelButtonType = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(continueButtonType, cancelButtonType);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == continueButtonType) {
                showAlert("Keep Going");
            } else if (buttonType == cancelButtonType) {
                showAlert("Don't Give Up");
            }
        });
    }

    private void showAlert(String message) {
        Alert alert1 = new Alert(AlertType.INFORMATION);
        alert1.setTitle("Action Taken");
        alert1.setHeaderText(null);
        alert1.setContentText("You chose to: " + message);
        alert1.showAndWait();
    }


}

