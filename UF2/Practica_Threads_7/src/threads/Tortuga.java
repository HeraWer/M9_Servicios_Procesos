package threads;

public class Tortuga extends Thread {

	public static int casilla = 1; // Casilla que avanzan la Tortuga
	private int maxCasilla = 0; // Maximo casillas que puede moverse para terminar la carrera
	private int avance_rapido = 3;
	private int resbalon = 6;
	private int avance_lento = 1;
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
			int dados = (int) (Math.random() * 100) + 1;
			// RESBALON
			if (dados >= 1 && dados <= 20) {
				casilla = casilla - resbalon;
				if (casilla < 1) {
					casilla = 1;
				}

				// AVANCE LENTO
			} else if (dados >= 21 && dados <= 50) {
				casilla = casilla + avance_lento;
				if (casilla > 70) {
					casilla = 70;
				}

				// AVANCE RAPIDO
			} else if (dados >= 51 && dados <= 100) {
				casilla = casilla + avance_rapido;
				if (casilla > 70) {
					casilla = 70;
				}
			}
			System.out.println("Posicion T: " + casilla);
			
			
			/*
			 * Aqui lo que hago es que cuando llega la casilla 70 es que la tortuga a llegado a la meta
			 * entonces significa que a ganado. Paro el thread de la tortuga para que no siga corriendo y compruebo si ella tambien a llegado a la misma vez a la meta.
			 * Si a llegado imprimo un empate si no a llegado imprimo que la tortuga a ganado
			 */
			if (casilla == 70) {
				Salida.l.stop();
				
				try {
					// Me aseguro que el otro thread se a parado para determinar el final de la carrera con el join
					Salida.l.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Liebre.casilla == 70) {
					System.out.println("\n EMPATE");
				} else {
					System.out.println("\n La Tortuga a llegado a la meta y ha ganado!");
				}
				break;
			}
			maxCasilla++;
		}
	}
}
