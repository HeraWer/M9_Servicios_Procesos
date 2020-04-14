package threads;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 * Inicializamos la array con 100 posiciones y sus valores a 0
		 */
		initializeArray();

		/*
		 * 10 hilos distintos de 10 compradores diferentes.
		 */
		Comprador c = new Comprador();
		c.start();
		Comprador c1 = new Comprador();
		c1.start();
		Comprador c2 = new Comprador();
		c2.start();
		Comprador c3 = new Comprador();
		c3.start();
		Comprador c4 = new Comprador();
		c4.start();
		Comprador c5 = new Comprador();
		c5.start();
		Comprador c6 = new Comprador();
		c6.start();
		Comprador c7 = new Comprador();
		c7.start();
		Comprador c8 = new Comprador();
		c8.start();
		Comprador c9 = new Comprador();
		c9.start();

		/*
		 * Estos join son para esperar que todos los hilos acaben y a si poder imprimir
		 * un resultado final
		 */
		c.join();
		c1.join();
		c2.join();
		c3.join();
		c4.join();
		c5.join();
		c6.join();
		c7.join();
		c8.join();
		c9.join();

		System.out.println(Entrada.entradas);
	}

	/*
	 * Metodo para inicializar la array
	 */
	public static void initializeArray() {
		for (int i = 0; i < 100; i++) {
			Entrada.entradas.add(0);
		}
		System.out.println(Entrada.entradas);
	}

}
