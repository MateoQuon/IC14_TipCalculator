package edu.miracosta.cs112.mquon.ic14_tipcalculator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class HelloApplication extends Application {

    // Define fields for each of the nodes that change or
    // are interacted with by the user

    private TextField billAmountTF = new TextField();
    private Label tipPercentLabel = new Label("15%");
    private Slider tipPercentSlider = new Slider(0, 30, 15); //min, max, default
    private TextField tipAmountTF = new TextField();
    private TextField totalAmountTF = new TextField();
    private Button clearButton = new Button("Clear");
    private Button calculateButton = new Button("Calculate");

    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();

        // Lets add "Bill Amount" label to gridPane
        gridPane.add(new Label("Bill Amount"), 0, 0);
        gridPane.add(billAmountTF, 1, 0);

        gridPane.add(tipPercentLabel, 0, 1);
        gridPane.add(tipPercentSlider, 1, 1);

        gridPane.add(new Label("Tip Amount"), 0, 2);
        tipAmountTF.setEditable(false);
        tipAmountTF.setFocusTraversable(false); // cannot be "tabbed"
        //tipAmountTF.setDisable(true);
        gridPane.add(tipAmountTF, 1, 2);

        gridPane.add(new Label("Total Amount"), 0, 3);
        totalAmountTF.setEditable(false);
        totalAmountTF.setFocusTraversable(false);
        gridPane.add(totalAmountTF, 1, 3);

        // Use an HBox to add two nodes into one gridbox
        HBox hBox = new HBox(clearButton, calculateButton);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        gridPane.add(hBox, 1, 4);

        // Set the horizontal gap AND vertical gap of GridPane (affects all Nodes inside)
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        // Set alignment to center
        gridPane.setAlignment(Pos.CENTER);
        // Configure the slider (tick marks)
        tipPercentSlider.setShowTickMarks(true);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setBlockIncrement(5);
        tipPercentSlider.setSnapToTicks(true);
        tipPercentSlider.setMajorTickUnit(5);

        // Wire-up the clearButton with the clear() method using a lambda expression
        // Parameter of setOnAction method will be a new, anonymous,
        // inner class that implements the handle() method
        // handle() method will call our clear() method
        // Lambda event: actionEvent -> clear()
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                clear();
            }
        }); // this inner class can be substituted with e -> clear()
        calculateButton.setOnAction(e -> calculate());

        // Listener is an interface, watches for changes in a node.
        tipPercentSlider.valueProperty().addListener((obsVal, oldVal, newVal) -> calculate());
        billAmountTF.textProperty().addListener((obsVal, oldBal, newVal) -> calculate());



        Scene scene = new Scene(gridPane, 320, 240);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }
    // Clear method - will be "wired up" to the clearButton
    public void clear(){
        billAmountTF.clear();
        tipAmountTF.clear();
        totalAmountTF.clear();
        // Set slider to 15%
        tipPercentSlider.setValue(15);
        // Return focus back to billAmountTF
        billAmountTF.requestFocus();
    }
    // Calculate method will be "wired up" to calculateButton ,billAmountTF, and tipPercentSlider
    public void calculate() {
        tipPercentLabel.setText((int)tipPercentSlider.getValue() + "%");
        if (billAmountTF.getText().isEmpty()) {

            return;
        }
        double billAmount = Double.parseDouble(billAmountTF.getText());
        double tipPercent = tipPercentSlider.getValue();

        double tipAmount = billAmount * (tipPercent/100);
        double totalAmount = tipAmount + billAmount;
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        tipAmountTF.setText(currency.format(tipAmount));
        totalAmountTF.setText(currency.format(totalAmount));

    }

    public static void main(String[] args) {
        launch();
    }
}
/*
Event-Driven Programming:
-wire up certain nodes to method calls, using lambda expressions.
-
Lambda Expression ( -> ): a shortcut for code
-Syntax:  parameter -> method call
 */