package Pt1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServidorTCP_Palindrom {

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
		String entrada;
		String salida;
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
				entrada = dis.readUTF();
				
				// Comprobamos si el string introducido es palindromo i escribimos el resultado
				if (esPalindromo(entrada)) {
					salida = "La frase es palindroma";
				} else {
					salida = "La frase no es palindroma";
				}
				dos.writeUTF(salida);
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

	public static boolean esPalindromo(String cadena) {
		// Convertimos a minuscula y quitamos espacios, comas y puntos
		cadena = cadena.toLowerCase().replace(" ", "").replace(".", "").replace(",", "");
		// Invertir la cadena, y si es igual que la original entonces son palíndromos
		String invertida = new StringBuilder(cadena).reverse().toString();
		return invertida.equals(cadena);
	}

}
