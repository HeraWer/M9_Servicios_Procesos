package threads;

public class Minero extends Thread {

	public Minero(String nombre) {
		this.setName(nombre);
	}
	

	public void run() {
		do {
			try {
				// Se calcula random el tiempo de espera de cada ejecucion
				int pausa = (int) (Math.random() * 1000) + 100;
				// El thread se espera lo calculado anteriormente
				Thread.sleep(pausa);
				/*
				 * Llamamos a los metodos sincronizados para añadir o quitar elementos de los almacenes
				 */
				Main.almacenHierro.añadirStock(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
}
