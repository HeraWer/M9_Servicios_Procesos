package threads;

public class Liebre extends Thread {

	public static int casilla = 1; // Casilla que avanzan la Tortuga
	private int maxCasilla = 0; // Maximo casillas que puede moverse para terminar la carrera
	private int duerme = 0;
	private int gran_salto = 9;
	private int resbalon_grande = 12;
	private int pequeño_salto = 1;
	private int resbalon_pequeño = 2;
	public boolean ganador = false;

	public void run() {
		
		/*
		 * While para las 100 casillas si en 100 casillas no a sido capaz de llegar a la meta no es valido.
		 */
		while (maxCasilla != 100) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Dados que tira la liebre para avanzar o retroceder casillas
			int dados = (int) (Math.random() * 100) + 1;
			// RESBALON GRANDE
			if (dados >= 1 && dados <= 10) {
				casilla = casilla - resbalon_grande;
				if (casilla < 1) {
					casilla = 1;
				}

				// DUERME
			} else if (dados >= 11 && dados <= 30) {
				casilla = casilla + duerme;
				if (casilla > 70) {
					casilla = 70;
				}

				// GRAN SALTO
			} else if (dados >= 31 && dados <= 50) {
				casilla = casilla + gran_salto;
				if (casilla > 70) {
					casilla = 70;
				}

				// RESBALON PEQUEÑO
			} else if (dados >= 51 && dados <= 70) {
				casilla = casilla - resbalon_pequeño;
				if (casilla < 1) {
					casilla = 1;
				}

				// PEQUEÑO SALTO
			} else if (dados >= 71 && dados <= 100) {
				casilla = casilla + pequeño_salto;
				if (casilla > 70) {
					casilla = 70;
				}
			}
			System.out.println("Posicion L: " + casilla);

			/*
			 * Aqui lo que hago es que cuando llega la casilla 70 es que la liebre a llegado a la meta
			 * entonces significa que a ganado. Paro el thread de la tortuga para que no siga corriendo y compruebo si ella tambien a llegado a la misma vez a la meta.
			 * Si a llegado imprimo un empate si no a llegado imprimo que la liebre a ganado
			 */
			if (casilla == 70) {
				Salida.t.stop();
				
				try {
					// Me aseguro que el otro thread se a parado para determinar el final de la carrera con el join
					Salida.t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Tortuga.casilla == 70) {
					System.out.println("\n EMPATE");
				} else {
					System.out.println("\n La Liebre a llegado a la meta y ha ganado!");
				}
				break;
			}
			maxCasilla++;
		}
	}

}
