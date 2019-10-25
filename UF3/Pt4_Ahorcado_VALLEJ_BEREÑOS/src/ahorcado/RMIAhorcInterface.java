package ahorcado;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIAhorcInterface extends Remote{
	
	public int largoPalabra() throws RemoteException;
	
	public String devolverPalabra(String letra) throws RemoteException;
	
	public boolean finalizar() throws RemoteException;

}
