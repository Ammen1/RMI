module StudentRegRMI {
	requires javafx.controls;
	
		requires java.sql;
		requires mysql.connector.j;
		requires javafx.graphics;
		requires java.rmi;
	
	opens application to javafx.graphics, javafx.fxml;
}
