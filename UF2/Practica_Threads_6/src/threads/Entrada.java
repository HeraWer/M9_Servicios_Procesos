package threads;

import java.util.ArrayList;
import java.util.List;

public class Entrada extends Thread {

	static List<Integer> entradas = new ArrayList<>(); // Array de 100 posiciones de las entradas

	/*
	 * Metodo que se encarga de comprar las entradas mirando si la posicion de la
	 * entrada no esta comprada, es decir, que su valor es = 0 si es igual = 1 no
	 * compra la entrada y vuelve a llamar al metodo. con el synchronized podemos
	 * sincronizar los hilos que estan llamando a este metodo para que se queden
	 * esperando y a si que no nos salgan que hay una misma entrada vendida 2 veces.
	 * Es decir que la posicion de una entrada sea = 2
	 */
	public synchronized static void buyTickets() {
		int entrada = (int) (Math.random() * 100);
		if (entradas.get(entrada) == 1) {
			buyTickets();

		} else {
			entradas.set(entrada, entradas.get(entrada) + 1);
		}

	}
}
