package threads;

public class Herrero extends Thread {
	
	static Minero m = new Minero();
	static Tren t = new Tren();

	public void run() {
		do {
			try {
				int pausa = (int) (Math.random() * 200) + 2000;
				Thread.sleep(pausa);
				sumarHerramientas();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}
	
	public void sumarHerramientas() throws InterruptedException {
		if(Main.almacenHerramientas < 10 && Main.almacenHierro >= 2) {
			Main.guardarHierro(-2);
			Main.guardarHerramienta(1);
			System.err.println("HERERO");
			System.out.println("HIERROS: " + Main.almacenHierro);
			System.out.println("HERRAMIENTAS: " + Main.almacenHerramientas);
		}
	}
}
