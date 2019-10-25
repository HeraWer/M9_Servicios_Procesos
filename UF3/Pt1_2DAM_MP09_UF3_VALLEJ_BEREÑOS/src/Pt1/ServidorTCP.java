package Pt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class ServidorTCP {
	public static void main(String args[]) {
// Primero indicamos la dirección IP local
		try {
			System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
		} catch (UnknownHostException uhe) {
			System.err.println("No puedo saber la dirección IP local : " + uhe);
		}
// Abrimos un "Socket de Servidor" TCP en el puerto 1234.
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(1234);
		} catch (IOException ioe) {
			System.err.println("Error al abrir el socket de servidor : " + ioe);
			System.exit(-1);
		}
		int entrada;
		long salida;
// Bucle infinito
		while (true) {
			try {
// Esperamos a que alguien se conecte a nuestro Socket
				Socket sckt = ss.accept();
// Extraemos los Streams de entrada y de salida
				DataInputStream dis = new DataInputStream(sckt.getInputStream());
				DataOutputStream dos = new DataOutputStream(sckt.getOutputStream());
// Podemos extraer información del socket
// Nº de puerto remoto
				int puerto = sckt.getPort();
// Dirección de Internet remota
				InetAddress direcc = sckt.getInetAddress();
// Leemos datos de la peticion
				entrada = dis.readInt();
// Calculamos resultado
				salida = (long) entrada * (long) entrada;
// Escribimos el resultado
				dos.writeLong(salida);
// Cerramos los streams
				dis.close();
				dos.close();
				sckt.close();
// Registramos en salida estandard
				System.out.println(
						"Cliente = " + direcc + ":" + puerto + "\tEntrada = " + entrada + "\tSalida = " + salida);
			} catch (Exception e) {
				System.err.println("Se ha producido la excepción : " + e);
			}
		}
	}
}