package com.example.justtodoit;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Task {
    private Label tasksLabel;
    private ImageView doneBtn;
    private ImageView removeBtn;

    public Task(Label tasksLabel, ImageView doneBtn, ImageView removeBtn) {
        this.tasksLabel = tasksLabel;
        this.doneBtn = doneBtn;
        this.removeBtn = removeBtn;
    }
}

