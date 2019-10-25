package calculadora;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMICalcInterface extends Remote{
	
	// CREO UNA INTERFACE PARA DECLARAR LOS METODOS DE LAS DIFERENTES OPERACIONES DE LAS CALCULADORAS
	
	public int suma(int a, int b) throws RemoteException;
	
	public int resta(int a, int b) throws RemoteException;
	
	public int multip(int a, int b) throws RemoteException;
	
	public int div(int a, int b) throws RemoteException;
	
	public int potencia(int a, int b) throws RemoteException;
	
}
