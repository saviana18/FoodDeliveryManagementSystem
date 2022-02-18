module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.fooddelivery to javafx.fxml, javafx.base;
    exports org.fooddelivery;
    exports org.fooddelivery.presentation;
    opens org.fooddelivery.presentation to javafx.fxml;
    opens org.fooddelivery.bll;
}