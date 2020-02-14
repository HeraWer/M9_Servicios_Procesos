package thread;

public class Main {

	public static int entero = 0;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		/*
		 * Declaramos los objectos de la clase thread.
		 */
		Variable v1 = new Variable();
		Variable v2 = new Variable();
		Variable v3 = new Variable();
		Variable v4 = new Variable();
		
		/*
		 * Iniciamos cada uno de los objetos.
		 */
		v1.start();
		v2.start();
		v3.start();
		v4.start();
		
		/*
		 * Se espera a que todos los thread se hayan acabado.
		 */
		v1.join();
		v2.join();
		v3.join();
		v4.join();

		/*
		 * Finalmente imprimimos la variable.
		 */
		System.out.println(entero);
	}

}
