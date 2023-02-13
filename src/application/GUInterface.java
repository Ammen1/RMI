package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GUInterface extends Remote{
	public String Insert(String name,int id,String department,String gender,int age,int year) throws RemoteException;
	public String Update(String name,int id,String department,String gender,int age,int year) throws RemoteException;
	public ArrayList<String> Search(int id)throws RemoteException;
	
}
