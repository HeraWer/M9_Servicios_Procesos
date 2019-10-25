package calculadora;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorTCP_Calculadora implements RMICalcInterface { // IMPLEMENTO LA INTERFACE CREADA ANTERIORMENTE
	
	// DEFINIR LOS METODOS AL IMPLEMENTAR LA INTERFACE

	public int suma(int a, int b) throws RemoteException {
		System.out.println("Sumando " + a + " y " + b + "...");
		return (a + b);
	}

	public int resta(int a, int b) throws RemoteException {
		System.out.println("Restando " + a + " y " + b + "...");
		return (a - b);
	}

	public int multip(int a, int b) throws RemoteException {
		System.out.println("Multiplicando " + a + " por " + b + "...");
		return (a * b);
	}

	public int div(int a, int b) throws RemoteException {
		System.out.println("Dividiendo " + a + " entre " + b + "...");
		return (a / b);
	}

	@Override
	public int potencia(int a, int b) throws RemoteException {
		System.out.println("Elevando " + a + " a la potencia " + b + "...");
		return (int) Math.pow(a, b);
	}

	public static void main(String[] args) {
		
		// CREAMOS UN REGISTRO DE OBJETOS REMOTOS

		System.out.println("Creando el registro de objetos remotos...");
		Registry reg = null;
		
		// ABRIMOS EL REGISTRO EN EL PUERTO 5555

		try {
			reg = LocateRegistry.createRegistry(5555);
		} catch (Exception e) {
			System.out.println("Error: No se ha podido crear el registro");
			e.printStackTrace();
		}
		
		//CREAMOS EL OBJETO SERVIDOR Y LO INSCRIBIMOS EN EL REGISTRO.

		System.out.println("Creando el objeto servidor e inscribiendolo en el registro...");
		ServidorTCP_Calculadora serverObject = new ServidorTCP_Calculadora();
		
		//FINALMENTE LE DAMOS UN NOMBRE AL REGISTRO "CALCULADORA" POR EL CUAL EL CLIENTE PODRA ENTRAR Y RESOLVER SUS OPERACIONES.

		try {
			reg.rebind("Calculadora", (RMICalcInterface) UnicastRemoteObject.exportObject(serverObject, 0));
		} catch (Exception e) {
			System.out.println("Error: No se ha podido inscribir el objeto servidor");
			e.printStackTrace();
		}
	}
}
