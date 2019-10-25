package calculadora;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteTCP_Calculadora {

	public static void main(String[] args) throws RemoteException {

		RMICalcInterface calc = null;
		try {
			System.out.println("Localizando registro de objetos remotos...");
			Registry registry = LocateRegistry.getRegistry("192.168.41.219", 5555); // CREAMOS UN REGISTRO HACIA LA IP DEL SERVIDOR EN EL PUERTO 5555
			System.out.println("Obteniendo el stub del objeto remoto...");
			calc = (RMICalcInterface) registry.lookup("Calculadora"); // AQUI ESTA LEYENDO EL REGISTRO CREADO EN EL SERVIDOR "CALCULADORA"
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (calc != null) {
			System.out.println("Realizando operaciones con el objeto remoto...");

			try {
				System.out.println("Sumando 2 y 2 : " + calc.suma(2, 2));
				System.out.println("Restando 99 y 45 : " + calc.resta(99, 45));
				System.out.println("Multiplicando 125 por : 3 " + calc.multip(125, 3));
				System.out.println("Diviendo 10 entre 5 : " + calc.div(10, 5));
				System.out.println("Elevando 2 a 3 : " + calc.potencia(2, 3));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Terminado");
		}
	}

}
