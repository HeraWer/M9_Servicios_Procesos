package threads;

public class Comprador extends Thread {

	/*
	 * Metodo para mirar cuantas entradas va a comprar cada comprador.
	 */
	public int inputQuantity() {
		int result = (int) (Math.random() * 5 + 1);
		return result;
	}

	/*
	 * El run de los hilos.
	 */
	public void run() {

		int quantityTickets = inputQuantity();
		/*
		 * Recorremos tantas veces como entradas vaya a comprar.
		 */
		for (int i = 0; i < quantityTickets; i++) {

			Entrada.buyTickets();
		}
	}
}
