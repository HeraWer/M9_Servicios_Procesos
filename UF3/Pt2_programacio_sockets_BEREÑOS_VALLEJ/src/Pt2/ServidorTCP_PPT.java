package Pt2;

import java.io.*;
import java.net.*;
import java.util.Random;

class ServidorTCP_PPT {
	public static void main(String args[]) {
		int contadorServidor = 0;
		int contadorCliente = 0;
		// Primero indicamos la dirección IP local
		try {
			System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
		} catch (UnknownHostException uhe) {
			System.err.println("No puedo saber la dirección IP local : " + uhe);
		}
		// Abrimos un "Socket de Servidor" TCP en el puerto 1235.
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(1235);
		} catch (IOException ioe) {
			System.err.println("Error al abrir el socket de servidor : " + ioe);
			System.exit(-1);
		}
		int entrada;
		String salida = null;
		// Bucle que dura hasta que acabe el juego
		try {
			boolean repetir = true;
			System.out.println("Juego listo... Esperando cliente.");
			while (repetir) {
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
				System.out.println(entrada);
				// Generamos la eleccion del servidor y elegimos ganador
				int eleccionServidor = generarEleccion();
				salida = elegirGanador(entrada, eleccionServidor);
				// Sumamos al marcador del ganador
				String[] ganador = salida.split(":");
				if (ganador[1].equals("Cliente")) {
					contadorCliente++;
				} else if (ganador[1].equals("Servidor")) {
					contadorServidor++;
				}
				// Devolvemos el resultado de la jugada
				dos.writeUTF(salida);

				// Cerramos los streams
				dis.close();
				dos.close();
				sckt.close();
				// Registramos en salida estandar
				System.out.println(
						"Cliente = " + direcc + ":" + puerto + "\tEntrada = " + entrada + "\tSalida = " + salida);
				// Cerramos el bucle si alguno de los jugadores llega a 3
				if (contadorCliente == 3 || contadorServidor == 3) {
					repetir = false;
				}
			}
		} catch (Exception e) {
			System.err.println("Se ha producido la excepción : " + e);
		}
	}

	// Metodo que calcula el numero de eleccion de la maquina de forma aleatoria
	public static int generarEleccion() {
		// Piedra es 1, Papel es 2 y Tijera es 3
		int eleccionServidor = new Random().nextInt(3) + 1;
		return eleccionServidor;
	}

	// Metodo que recibe el numero de eleccion del jugador y el servidor y devuelve
	// el ganador
	public static String elegirGanador(int eleccionCliente, int eleccionServidor) {

		String resultado = "Error. Eleccion no valida: ";

		if (eleccionCliente == eleccionServidor) {
			resultado = "Empate: ";

		} else if (eleccionCliente == 1) {
			if (eleccionServidor == 2) {
				resultado = "Papel gana a piedra. Ganador:Servidor";
			} else if (eleccionServidor == 3) {
				resultado = "Piedra gana a tijeras. Ganador:Cliente";
			}

		} else if (eleccionCliente == 2) {
			if (eleccionServidor == 1) {
				resultado = "Papel gana a piedra. Ganador:Cliente";
			} else if (eleccionServidor == 3) {
				resultado = "Tijeras gana a papel. Ganador:Servidor";
			}

		} else if (eleccionCliente == 3) {
			if (eleccionServidor == 1) {
				resultado = "Piedra gana a tijeras. Ganador:Servidor";
			} else if (eleccionServidor == 2) {
				resultado = "Tijeras gana a papel. Ganador:Cliente";
			}
		}
		return resultado;
	}
}
