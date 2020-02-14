package thread;

public class Main {

	private static int count = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Recorremos un while para decir cuantas veces iniciaremos el Thread,
		 * hasta 5 veces y le pasamos el contador para saber el hilo que se ejecuta.
		 */
		while (count < 6) {
			HolaMundo hi = new HolaMundo("Hola Mundo", count);
			hi.start();
			count++;
		}

	}

}
