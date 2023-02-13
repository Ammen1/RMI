package application;	

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.YearMonth;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Client extends Application {
	private static String MYSQL_JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/JDBCrmi";
	private static String MYSQL_DB_USER = "root";
	private static String MYSQL_DB_USER_PASSWORD = "";
//	private static String SQL_QUERY = "Select * from students";
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox box = new VBox();
			GridPane gp = new GridPane();
			
			
			Label idLabel = new Label("ID");
			TextField idField = new TextField();
			Label nameLabel = new Label("Name");
			TextField nameField = new TextField();
			Label departmentLabel = new Label("Department");
			TextField departmentField = new TextField();
			Label genderLabel = new Label("Gender");
			TextField genderField = new TextField();
			Label ageLabel = new Label("Age");
			TextField ageField = new TextField();
			Label yearLabel = new Label("Year");
			TextField yearField = new TextField();
			
			
			Button insertButton = new Button("Insert");
			Button updateButton = new Button("Update");
			Button searchButton = new Button("Search");
			
			gp.add(idLabel, 0, 0);
			gp.add(idField, 1, 0);
			gp.add(nameLabel, 0, 1);
			gp.add(nameField, 1, 1);
			gp.add(departmentLabel, 0, 2);
			gp.add(departmentField, 1, 2);
			gp.add(genderLabel, 0, 3);
			gp.add(genderField, 1, 3);
			gp.add(ageLabel,0,4);
			gp.add(ageField, 1, 4);
			gp.add(yearLabel, 0, 5);
			gp.add(yearField, 1, 5);
			gp.add(insertButton, 0, 6);
			gp.add(updateButton, 1, 6);
			gp.add(searchButton, 2, 6);
			
			insertButton.setId("insertButton");
			updateButton.setId("updateButton");
			searchButton.setId("searchButton");
			idLabel.setId("idLabel");
			nameLabel.setId("nameLabel");
			departmentLabel.setId("departmentLabel");
			genderLabel.setId("genderLabel");
			ageLabel.setId("ageLabel");
			yearLabel.setId("yearLabel");
			
			
			gp.setHgap(10);
			gp.setVgap(10);
			
			gp.setPadding(new Insets(30, 30, 30, 10));
			gp.setAlignment(Pos.CENTER);
			box.setAlignment(Pos.CENTER_RIGHT);
			
			box.getChildren().add(gp);
//			System.out.println("hello world");
			Scene scene = new Scene(box,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			box.setId("box");
			
			insertButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						Connection connection = DriverManager.getConnection(MYSQL_DB_URL,MYSQL_DB_USER,"");
						Class.forName(MYSQL_JDBC_DRIVER_CLASS);
						Statement statement = connection.createStatement();
						
						int id = Integer.parseInt(idField.getText());
						String name = nameField.getText();
						String depertment = departmentField.getText();
						String gender = genderField.getText();
						int age =Integer.parseInt(ageField.getText());
						int year =Integer.parseInt(yearField.getText()) ;
						
						GUInterface databaseInterface = (GUInterface) Naming.lookup("rmi://localhost:1099/db");
						String result = databaseInterface.Insert(name, id, depertment, gender, age, year);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
				}
			});
			
			updateButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			public void handle(ActionEvent arg0) {
				
			try {
				Connection connection = DriverManager.getConnection(MYSQL_DB_URL,MYSQL_DB_USER,"");
				Class.forName(MYSQL_JDBC_DRIVER_CLASS);
				Statement statement = connection.createStatement();
				
				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				String depertment = departmentField.getText();
				String gender = genderField.getText();
				int age =Integer.parseInt(ageField.getText());
				int year =Integer.parseInt(yearField.getText()) ;
				
				GUInterface databaseInterface = (GUInterface) Naming.lookup("rmi://localhost:2099/db");
				String result = databaseInterface.Update(name, id, depertment, gender, age, year);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			}});
			
			searchButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						Connection connection = DriverManager.getConnection(MYSQL_DB_URL,MYSQL_DB_USER,"");
						Class.forName(MYSQL_JDBC_DRIVER_CLASS);
						Statement statement = connection.createStatement();
						int id = Integer.parseInt(idField.getText());
						
						GUInterface databaseInterface = (GUInterface) Naming.lookup("rmi://localhost:1099/db");
						ArrayList result = databaseInterface.Search(id);
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
