module Assignment2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires org.junit.jupiter.api;

	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens application.Controller to javafx.graphics, javafx.fxml, javafx.base;
	opens application.Model to javafx.graphics, javafx.fxml, javafx.base;
	opens application.Exception to javafx.graphics, javafx.fxml, javafx.base;

}
