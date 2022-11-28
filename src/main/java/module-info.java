module com.example.databasesql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databasesql to javafx.fxml;
    exports com.example.databasesql.model;
    opens com.example.databasesql.model to javafx.fxml;
    exports com.example.databasesql.client;
    opens com.example.databasesql.client to javafx.fxml;
    exports com.example.databasesql.Server;
    opens com.example.databasesql.Server to javafx.fxml;
    exports com.example.databasesql.Controllers;
    opens com.example.databasesql.Controllers to javafx.fxml;
}