module me.jord.rebuilt2048 {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.jord.rebuilt2048 to javafx.fxml;
    exports me.jord.rebuilt2048;
}