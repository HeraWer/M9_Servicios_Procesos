package threads;

public class Tren extends Thread {

	public Tren(String nombre) {
		this.setName(nombre);
	}

	public void run() {
		// Se calcula random el tiempo de espera de cada ejecucion
		int pausa = (int) (Math.random() * 5000) + 500;
		do {
			try {
				// El thread se espera lo calculado anteriormente
				Thread.sleep(pausa);
				/*
				 * Llamamos a los metodos sincronizados para añadir o quitar elementos de los almacenes
				 */
				Main.almacenHerramientas.sacarStock(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
}
