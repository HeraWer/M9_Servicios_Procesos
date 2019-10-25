package Pt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class ClienteTCP {
	public static void main(String args[]) {
// Leemos el primer parámetro, donde debe ir la dirección
// IP del servidor
		InetAddress direcc = null;
		try {
			direcc = InetAddress.getByName("192.168.41.225");
		} catch (UnknownHostException uhe) {
			System.err.println("Host no encontrado : " + uhe);
			System.exit(-1);
		}
// Puerto que hemos usado para el servidor
		int puerto = 1234;
// Para cada uno de los argumentos...
		for (int n = 1; n < 2; n++) {
			Socket sckt = null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try {
// Convertimos el texto en número
				String frase = "Ala";
// Creamos el Socket
				sckt = new Socket(direcc, puerto);
// Extraemos los streams de entrada y salida
				dis = new DataInputStream(sckt.getInputStream());
				dos = new DataOutputStream(sckt.getOutputStream());
// Lo escribimos
				dos.writeUTF(frase);
// Leemos el resultado final
				String resultado = dis.readUTF();
// Indicamos en pantalla
				System.out.println("Solicitud = " + frase + "\tResultado = " + resultado);
// y cerramos los streams y el socket
				dis.close();
				dos.close();
			} catch (Exception e) {
				System.err.println("Se ha producido la excepción : " + e);
			}
			try {
				if (sckt != null)
					sckt.close();
			} catch (IOException ioe) {
				System.err.println("Error al cerrar el socket : " + ioe);
			}
		}
	}
}