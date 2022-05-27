module com.ap.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.ap.final_project to javafx.fxml;
    exports com.ap.final_project;
}