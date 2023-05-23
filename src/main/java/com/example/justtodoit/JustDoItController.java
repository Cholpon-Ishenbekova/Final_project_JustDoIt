package com.example.justtodoit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

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
    private Label index;

    @FXML
    private Label time1;

    @FXML
    private Label time2;
    @FXML
    private Button pomoBtn;

    @FXML
    private Button breakBtn;

    private List<Task> taskList = new ArrayList<Task>();


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

    @FXML
    private void handleAddButton(ActionEvent event) {
        String newTask = tasksLabel.getText();
        ImageView doneImage = new ImageView(doneBtn.getImage());
        ImageView removeImage = new ImageView(removeBtn.getImage());
        Task newTaskObject = new Task(newTask, doneImage, removeImage);
        taskList.add(newTaskObject);
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        taskList.clear();
    }

    public static class Task {
        private String taskLabel;
        private ImageView doneButton;
        private ImageView removeButton;

        public Task(String taskLabel, ImageView doneButton, ImageView removeButton) {
            this.taskLabel = taskLabel;
            this.doneButton = doneButton;
            this.removeButton = removeButton;
        }

        // Getter methods for taskLabel, doneButton, removeButton
    }

//    public Button getAddBtn() {
//        return addBtn;
//    }


//    public Button getClearBtn() {
//        return clearBtn;
//    }
//
//    public void addListiner(){
//        addBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Task task = new Task();
//                listTasks.add(tasksLabel);
//
//            }
//        });
//    }
}

