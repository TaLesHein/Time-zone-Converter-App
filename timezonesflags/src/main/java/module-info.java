module project {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens project to javafx.fxml;
    exports project;
    exports project.entities;
}
