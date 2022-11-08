package com.seekerscloud.pos.controller;

import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        setDateAndTime();
    }

    private void setDateAndTime() {
        // set Date
  /*      Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        lblDate.setText(formattedDate);*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //===================
    }

}
