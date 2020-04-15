package threads;

public class Tren extends Thread {
	
	static Minero m = new Minero();
	static Herrero h = new Herrero();

	public void run() {
		int pausa = (int) (Math.random() * 5000) + 500;
		do {
			try {
				Thread.sleep(pausa);
				buscarHerramientas();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
	
	public void buscarHerramientas() throws InterruptedException {
		if(Main.almacenHerramientas >= 5) {
			Main.guardarHerramienta(-5);
			System.err.println("TREN");
			System.out.println("HIERROS: " + Main.almacenHierro);
			System.out.println("HERRAMIENTAS: " + Main.almacenHerramientas);
		}
	}
}
