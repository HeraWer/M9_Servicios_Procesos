package thread;

public class Variable extends Thread {

	/*
	 * Modificamos el metodo run para incrementar el entero del main.
	 */
	public void run() {
		for (int i = 0; i < 5000; i++) {
			Main.entero++;
		}
	}
}
