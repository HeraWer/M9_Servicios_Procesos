package ahorcado;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteTCP_Ahorcado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RMIAhorcInterface calc = null;
		try {
			System.out.println("Localizando registro de objetos remotos...");
			Registry registry = LocateRegistry.getRegistry("192.168.41.219", 5555); // CREAMOS UN REGISTRO HACIA LA IP DEL SERVIDOR EN EL PUERTO 5555
			System.out.println("Obteniendo el stub del objeto remoto...");
			calc = (RMIAhorcInterface) registry.lookup("Calculadora"); // AQUI ESTA LEYENDO EL REGISTRO CREADO EN EL SERVIDOR "CALCULADORA"
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (calc != null) {
			System.out.println("Realizando operaciones con el objeto remoto...");

			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Terminado");
		}
	}
	}

