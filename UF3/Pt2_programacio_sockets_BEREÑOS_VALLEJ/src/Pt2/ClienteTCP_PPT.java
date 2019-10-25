package Pt2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP_PPT {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		// Leemos el primer parámetro, donde debe ir la dirección
		// IP del servidor
		InetAddress direcc = null;
		try {
			direcc = InetAddress.getByName("192.168.40.189");
		} catch (UnknownHostException uhe) {
			System.err.println("Host no encontrado : " + uhe);
			System.exit(-1);
		}
		// Puerto que hemos usado para el servidor
		int puerto = 1235;
		// Para cada uno de los argumentos...
		int contadorCliente = 0;
		int contadorServidor = 0;
		for (int n = 1; n < 1000; n++) {
			Socket sckt = null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try {
				// Convertimos el texto en número
				boolean comprobar = true;
				int opcion = 0;
				// COMPRUEBO QUE LA OPCION INTRODUCIDA ES CORRECTAR SIN AFECTAR AL SERVIDOR, AUNQUE EL SERVIDOR TAMBIEN LA COMPRUEBA
				while(comprobar) {
				System.out.println("Elige las siguentes opciones:");
				System.out.println("1 - Piedra");
				System.out.println("2 - Papel");
				System.out.println("3 - Tijera");
					if(lector.hasNextInt()) {
						opcion = lector.nextInt();
						comprobar = false;
					}else {
						System.out.println("No has escrito una opcion correcta, volviendo a las opciones...");
						lector.next();
					}
				}
				
				// Creamos el Socket
				sckt = new Socket(direcc, puerto);
				// Extraemos los streams de entrada y salida
				dis = new DataInputStream(sckt.getInputStream());
				dos = new DataOutputStream(sckt.getOutputStream());
				// Lo escribimos
				dos.writeInt(opcion);
				// Leemos el resultado final
				// AQUI MIRO CON LA ULTIMA PALABRA QUIEN GANO LA ULTIMA RONDA Y A SI HAGO UN CONTADOR PARA SALIR DEL BUCLE Y TERMINAR EL JUEGO
				String resultado = dis.readUTF();
				String[] vector = resultado.split(":");
				if(vector[1].equals("Cliente")) {
					contadorCliente++;
				}else if(vector[1].equals("Servidor")){
					contadorServidor++;
				}
				// Indicamos en pantalla
				System.out.println(resultado);
				// y cerramos los streams y el socket
				dis.close();
				dos.close();
				// AQUI MIRO QUIEN LLEGO PRIMERO A TRES Y LE DIGO QUIEN A SIDO EL GANADOR
				if(contadorCliente == 3) {
					System.out.println("Eres el ganador");
					break;
				}else if(contadorServidor == 3) {
					System.out.println("Te gano la maquina");
					break;
				}
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
