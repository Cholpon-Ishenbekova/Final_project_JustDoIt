package com.example.justtodoit;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;


public class JustDoItController {

    @FXML
    private Button addBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button startBtn;

    @FXML
    private TextField taskField;

    @FXML
    private Label time1;

    @FXML
    private Pane pane;


    private List<Task> taskList = new ArrayList<Task>();

    @FXML
    private VBox tasksContainer;


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
        String task = taskField.getText();
        Label newTaskLabel = new Label();
        newTaskLabel.textProperty().bind(Bindings.concat((tasksContainer.getChildren().size() + 1), ". ", task));
        newTaskLabel.setStyle("-fx-padding: 5px 0;");
        tasksContainer.getChildren().add(newTaskLabel);
        taskField.clear();
        tasksContainer.setPrefHeight(tasksContainer.getPrefHeight() + newTaskLabel.getBoundsInLocal().getHeight() + 10);
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        tasksContainer.getChildren().clear();
        tasksContainer.setPrefHeight(148);
    }



}

