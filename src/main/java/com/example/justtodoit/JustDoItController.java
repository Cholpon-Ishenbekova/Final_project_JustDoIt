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
import javafx.scene.layout.VBox;

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
        Task newTask = createTask();
        taskList.add(newTask);
        tasksContainer.getChildren().add(newTask.getTaskPane());
    }

    @FXML
    private void handleClearButton(ActionEvent event) {
        taskList.clear();
        tasksContainer.getChildren().clear();
    }

    private Task createTask() {
        Label taskLabel = new Label("Enter Task");
        ImageView doneButton = new ImageView(new Image("/Images/check-mark-button_2705.png"));
        ImageView removeButton = new ImageView(new Image("/Images/cross-mark_274c.png"));

        doneButton.setOnMouseClicked(event -> markTaskAsDone(doneButton));
        removeButton.setOnMouseClicked(event -> removeTask(removeButton));

        return new Task(taskLabel, doneButton, removeButton);
    }

    private void markTaskAsDone(ImageView doneButton) {
        Label taskLabel = (Label) doneButton.getUserData();
        taskLabel.getStyleClass().add("task-done");
    }

    private void removeTask(ImageView removeButton) {
        VBox taskPane = (VBox) removeButton.getUserData();
        tasksContainer.getChildren().remove(taskPane);

        // Remove the task from the taskList if needed
        for (Task task : taskList) {
            if (task.getTaskPane() == taskPane) {
                taskList.remove(task);
                break;
            }
        }
    }

    public static class Task {
        private Label taskLabel;
        private ImageView doneButton;
        private ImageView removeButton;
        private VBox taskPane;


        public Task(Label taskLabel, ImageView doneButton, ImageView removeButton) {
            this.taskLabel = taskLabel;
            this.doneButton = doneButton;
            this.removeButton = removeButton;

            initializeTaskPane();
        }

        private void initializeTaskPane() {
            VBox taskPane;
            taskPane = new VBox(5);
            taskPane.getChildren().addAll(taskLabel, doneButton, removeButton);
            taskPane.getStyleClass().add("task-pane");

            // Store references to the label and buttons in the user data
            doneButton.setUserData(taskLabel);
            removeButton.setUserData(taskPane);
        }

        public VBox getTaskPane() {
            return taskPane;
        }

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

