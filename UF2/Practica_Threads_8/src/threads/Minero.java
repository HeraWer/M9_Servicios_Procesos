package threads;

public class Minero extends Thread {

	static Herrero h = new Herrero();

	public void run() {
		do {
			try {
				int pausa = (int) (Math.random() * 1000) + 100;
				Thread.sleep(pausa);
				sumarHierro();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

	public void sumarHierro() throws InterruptedException {
		if (Main.almacenHierro < 10) {
			Main.guardarHierro(1);
			System.err.println("MINERO");
			System.out.println("HIERROS: " + Main.almacenHierro);
			System.out.println("HERRAMIENTAS: " + Main.almacenHerramientas);
		}
	}
}
