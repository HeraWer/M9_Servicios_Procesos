package threads;

public class Herrero extends Thread {

	public Herrero(String nombre) {
		this.setName(nombre);
	}

	public void run() {
		do {
			try {
				// Se calcula random el tiempo de espera de cada ejecucion
				int pausa = (int) (Math.random() * 200) + 2000;
				// El thread se espera lo calculado anteriormente
				Thread.sleep(pausa);
				/*
				 * Llamamos a los metodos sincronizados para añadir o quitar elementos de los almacenes
				 */
				Main.almacenHierro.sacarStock(2);
				Main.almacenHerramientas.añadirStock(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
}
