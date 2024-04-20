package com.example.birthdatecalculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CalendarController {
    @FXML
    private Pane printPane;

    @FXML
    private ImageView background;

    @FXML
    private Label monthLabel;

    @FXML
    private Label   d11, d12, d13, d14, d15, d16, d17,
                    d21, d22, d23, d24, d25, d26, d27,
                    d31, d32, d33, d34, d35, d36, d37,
                    d41, d42, d43, d44, d45, d46, d47,
                    d51, d52, d53, d54, d55, d56, d57,
                    d61, d62, d63, d64, d65, d66, d67;

    @FXML
    private Label   p11, p12, p13, p14, p15, p16, p17,
                    p21, p22, p23, p24, p25, p26, p27,
                    p31, p32, p33, p34, p35, p36, p37,
                    p41, p42, p43, p44, p45, p46, p47,
                    p51, p52, p53, p54, p55, p56, p57,
                    p61, p62, p63, p64, p65, p66, p67;

    @FXML
    private Label   g11, g12, g13, g14, g15, g16, g17,
                    g21, g22, g23, g24, g25, g26, g27,
                    g31, g32, g33, g34, g35, g36, g37,
                    g41, g42, g43, g44, g45, g46, g47,
                    g51, g52, g53, g54, g55, g56, g57,
                    g61, g62, g63, g64, g65, g66, g67;

    @FXML
    private Rectangle   r11, r12, r13, r14, r15, r16, r17,
                        r21, r22, r23, r24, r25, r26, r27,
                        r31, r32, r33, r34, r35, r36, r37,
                        r41, r42, r43, r44, r45, r46, r47,
                        r51, r52, r53, r54, r55, r56, r57,
                        r61, r62, r63, r64, r65, r66, r67;

    @FXML
    private TextField yearTF;

    @FXML
    private ComboBox<String> monthCB;

    @FXML
    private DatePicker bdayPicker;

    private MainController mainController;

    private List<Label> dayCells;
    private List<Label> pCells;
    private List<Label> gCells;
    private List<Rectangle> rectangles;

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        mainController = loader.getController();

        bdayPicker.setValue(mainController.getBirthDate());
        LocalDate currentDate = LocalDate.now();
        ObservableList<String> months = FXCollections.observableArrayList("январь", "февраль", "март", "апрель", "май",
                "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь");
        monthCB.setItems(months);
        monthCB.setValue(months.get(currentDate.getMonthValue() - 1));
        yearTF.setText(String.valueOf(currentDate.getYear()));

        dayCells = List.of(d11, d12, d13, d14, d15, d16, d17,
                            d21, d22, d23, d24, d25, d26, d27,
                            d31, d32, d33, d34, d35, d36, d37,
                            d41, d42, d43, d44, d45, d46, d47,
                            d51, d52, d53, d54, d55, d56, d57,
                            d61, d62, d63, d64, d65, d66, d67);

        pCells = List.of(p11, p12, p13, p14, p15, p16, p17,
                p21, p22, p23, p24, p25, p26, p27,
                p31, p32, p33, p34, p35, p36, p37,
                p41, p42, p43, p44, p45, p46, p47,
                p51, p52, p53, p54, p55, p56, p57,
                p61, p62, p63, p64, p65, p66, p67);

        gCells = List.of(g11, g12, g13, g14, g15, g16, g17,
                g21, g22, g23, g24, g25, g26, g27,
                g31, g32, g33, g34, g35, g36, g37,
                g41, g42, g43, g44, g45, g46, g47,
                g51, g52, g53, g54, g55, g56, g57,
                g61, g62, g63, g64, g65, g66, g67);

        rectangles = List.of(r11, r12, r13, r14, r15, r16, r17,
                r21, r22, r23, r24, r25, r26, r27,
                r31, r32, r33, r34, r35, r36, r37,
                r41, r42, r43, r44, r45, r46, r47,
                r51, r52, r53, r54, r55, r56, r57,
                r61, r62, r63, r64, r65, r66, r67);

        clearCalendar();

//        Image image = new Image(Main.class.getResource("/com/example/birthdatecalculator/img2.jpg").toString());
//        background.setImage(image);
    }

    @FXML
    private void print() {
        Printer printer = Printer.getDefaultPrinter();

        PageLayout pageLayout = printer.createPageLayout(Paper.A5, PageOrientation.LANDSCAPE,
                Printer.MarginType.HARDWARE_MINIMUM);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        job.getJobSettings().setPageLayout(pageLayout);
        if (job != null) {
            job.printPage(printPane);
            job.endJob();
        }
    }

    @FXML
    private void calculate() {
        monthLabel.setText(monthCB.getValue());
        int monthValue = 0;
        switch (monthCB.getValue()) {
            case "январь":
                monthValue = 1;
                break;
            case "февраль":
                monthValue = 2;
                break;
            case "март":
                monthValue = 3;
                break;
            case "апрель":
                monthValue = 4;
                break;
            case "май":
                monthValue = 5;
                break;
            case "июнь":
                monthValue = 6;
                break;
            case "июль":
                monthValue = 7;
                break;
            case "август":
                monthValue = 8;
                break;
            case "сентябрь":
                monthValue = 9;
                break;
            case "октябрь":
                monthValue = 10;
                break;
            case "ноябрь":
                monthValue = 11;
                break;
            default:
                monthValue = 12;
        }

        LocalDate currentDate = LocalDate.of(Integer.parseInt(yearTF.getText()), monthValue, 1);
        displayCalendar(currentDate);
    }

    public void clearCalendar() {
        dayCells.forEach(cell -> cell.setVisible(false));
        pCells.forEach(cell -> cell.setVisible(false));
        gCells.forEach(cell -> cell.setVisible(false));
        rectangles.forEach(r -> r.setVisible(false));
    }

    public void displayCalendar(LocalDate date) {
        clearCalendar();

        int dayOfWeek = date.getDayOfWeek().getValue();
        for (int i = 0; i < date.lengthOfMonth(); i++) {
            Label dayCell = dayCells.get(i + dayOfWeek - 1);
            dayCell.setText(String.valueOf(i + 1));
            dayCell.setVisible(true);

            Label gCell = gCells.get(i + dayOfWeek - 1);
            int generalValue = calculateGeneralValue(date);
            gCell.setText(String.format("О: %d", generalValue));
            changeLabelColor(generalValue, gCell);
            gCell.setVisible(true);

            Label pCell = pCells.get(i + dayOfWeek - 1);
            int privateValue = calculatePrivateValue(date);
            pCell.setText(String.format("Л: %d", privateValue));
            changeLabelColor(privateValue, pCell);
            pCell.setVisible(true);

            date = date.plusDays(1);

            if (i + 1 == 10 || i + 1 == 20 || i + 1 == 30) {
                rectangles.get(i + dayOfWeek - 1).setVisible(true);
            }
        }
    }

    public void changeLabelColor(int value, Label label) {
        switch (value) {
            case 3 : label.setTextFill(Color.GREEN); break;
            case 8 : label.setTextFill(Color.BLUE); break;
            case 6 : label.setTextFill(Color.DARKMAGENTA); break;
            default: label.setTextFill(Color.BLACK);
        }
    }

    public int calculateGeneralValue(LocalDate currentDate) {
        return mainController.mission(currentDate);
    }

    public int calculatePrivateValue(LocalDate currentDate) {
        System.out.println("--------------------------");
        System.out.println(currentDate.toString());
        System.out.println(bdayPicker.getValue().toString());
        LocalDate birthDate = bdayPicker.getValue();
        int result = 0;
        result += birthDate.getDayOfMonth() % 10;
        System.out.println(result);
        result += birthDate.getDayOfMonth() / 10;
        System.out.println(result);
        result += birthDate.getMonthValue() % 10;
        System.out.println(result);
        result += birthDate.getMonthValue() / 10;
        System.out.println(result);
        result += calculateGeneralValue(currentDate);
        System.out.println(result);
        return mainController.toSingleDigit(result);
    }
}
