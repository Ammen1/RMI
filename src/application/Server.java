package application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Server extends UnicastRemoteObject implements GUInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6540729878053402954L;

	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(2099);
		Server server = new Server();
		registry.rebind("db", server);
		System.out.println("Server up and running...");
//		
	}

	@Override
	public String Insert(String name, int id, String department, String gender, int age, int year)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCrmi","root","");
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO student values('"+id+"','"+name+"','"+department+"','"+gender+"','"+age+"','"+year+"')";
			statement.execute(sql);
			return "record inseted successfully";
		}catch (Exception e) {
			return (e.toString());	
		}
	}

	@Override
	public String Update(String name, int id, String department, String gender, int age, int year)
			throws RemoteException {
		// TODO Auto-generated method stub
//		return null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCrmi","root","");
			Statement statement = connection.createStatement();
			String sql = "UPDATE student set Name ='"+name+"',Gender='"+gender+"',Department='"+department+"',Age='"+age+"',Year='"+year+"'";
			statement.executeUpdate(sql);
			return "record updated successfully";
		}catch (Exception e) {
			return (e.toString());
		}
	}

	@Override
	public ArrayList<String> Search(int id) throws RemoteException {
		// TODO Auto-generated method stub
//		return null;
		ArrayList<String> student = new ArrayList<String>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBCrmi","root","");
			Statement statement = connection.createStatement();
			String sql ="select * from student where id = '"+id+"'";
			ResultSet resultSet;
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				student.add("Name:"+resultSet.getString("name"));
				student.add("Gender:"+resultSet.getString("gender"));
				student.add("Department:"+resultSet.getString("department"));
				student.add("Age:"+resultSet.getString("age"));
				student.add("Year:"+resultSet.getString("year"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	}