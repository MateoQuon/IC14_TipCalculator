module edu.miracosta.cs112.mquon.ic14_tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.mquon.ic14_tipcalculator to javafx.fxml;
    exports edu.miracosta.cs112.mquon.ic14_tipcalculator;
}