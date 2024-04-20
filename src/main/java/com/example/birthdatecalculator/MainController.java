package com.example.birthdatecalculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField missionTF, sphereTF, happinessTF, psyTF, philTF, codeTF;

    @FXML
    protected void calculate() {
        LocalDate date = datePicker.getValue();

        //mission
        int mission = mission(date);
        missionTF.setText(String.valueOf(mission));

        //sphere
        int sphere = toSingleDigit(mission + mission - 1);
        sphereTF.setText(String.valueOf(sphere));

        //happiness
        int happiness = date.getYear() > 1999 ? toSingleDigit(calculateYear(date)) - 1 : toSingleDigit(calculateYear(date));
        happinessTF.setText(String.valueOf(happiness));

        //psy
        psyTF.setText(String.valueOf(toSingleDigit(happiness + 6)));

        //phil
        philTF.setText(String.valueOf(toSingleDigit(happiness + 6 + 1)));

        //code
        codeTF.setText(calculateCode(date));
    }

    @FXML
    private void createCalendar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calendar.fxml"));
        Parent parent = fxmlLoader.load();
        CalendarController calendarController = fxmlLoader.getController();

        Scene calendarScene = new Scene(parent, 600, 500);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(calendarScene);
        stage.showAndWait();
    }

    @FXML
    protected void initialize() {
        datePicker.setValue(LocalDate.of(1969, 8, 10));
    }

    public int mission(LocalDate date) {
        int result = 0;
        //day
        result += calculateDay(date);

        //month
        result += calculateMonth(date);

        //year
        result += calculateYear(date);

        //final
        while (result > 9) {
            int r1 = result % 10;
            int r2 = result / 10;
            result = r1 + r2;
        }

        return result;
    }

    public int calculateDay(LocalDate date) {
        int day = date.getDayOfMonth();
        int d1 = day % 10;
        int d2 = day / 10;
        return d1 + d2;
    }

    public int calculateMonth(LocalDate date) {
        int month = date.getMonthValue();
        int m1 = month % 10;
        int m2 = month / 10;
        return m1 + m2;
    }

    public int calculateYear(LocalDate date) {
        int result = 0;
        int year = date.getYear();
        String yearStr = "" + year;
        for (int i = 0; i < yearStr.length(); i++) {
            result += Integer.parseInt(yearStr.charAt(i) + "");
        }
        return result;
    }

    //for two-digit numbers only
    public int toSingleDigit(int i) {
        while (i > 9) {
            int i1 = i % 10;
            int i2 = i / 10;
            i = i1 + i2;
        }
        return i;
    }

    public LocalDate getBirthDate() {
        return datePicker.getValue();
    }

    private String calculateCode(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        int day = calculateDay(date);
        int month = calculateMonth(date);
        int year = toSingleDigit(calculateYear(date));
        int lastDigit = toSingleDigit(day + month + year);
        sb.append(day).append(month).append(year).append(lastDigit);
        return sb.toString();
    }
}