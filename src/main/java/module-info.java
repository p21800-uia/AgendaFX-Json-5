module uia.com.agenda.agendafxjson {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;


    opens uia.com.agenda.agendafxjson to javafx.fxml;
    exports uia.com.agenda.agendafxjson;
}