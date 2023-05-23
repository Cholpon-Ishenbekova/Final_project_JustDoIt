package com.example.justtodoit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    private int sessiontime = 1500;
    private int elapsetime = sessiontime * 1000;
    private int h = elapsetime/3600000;
    private int m = (elapsetime/60000) % 60;
    private int s = (elapsetime/1000) % 60;
    boolean startClicked = false;

    String h_string = String.format("%02d", h);
    String m_string = String.format("%02d", m);
    String s_string = String.format("%02d", s);

    Timer timer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            if(elapsetime != 0){
                elapsetime = elapsetime - 1000;
            }
        }
    });

//    @FXML
//    void handleDoneButton(MouseEvent event) {
//
//    }
//    @FXML
//    private void handlePomoButton() {
//        time1.setVisible(true);
//        time2.setVisible(false);
//        pomoBtn.setStyle("-fx-background-color: #94bbe9");
//    }
//
//    @FXML
//    private void handleBreakButton() {
//        time1.setVisible(false);
//        time2.setVisible(true);
//        breakBtn.setStyle("-fx-background-color: #94bbe9");
//    }

    @FXML
    void initialize() {
        time1.setText(h_string + ":" + m_string + ":" + s_string);

    }

}

