package threads;

public class Salida {

	static Liebre l = new Liebre();
	static Tortuga t = new Tortuga();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Comienza la carrera entre la Liebre y la Tortuga");
		l.start();
		t.start();
		
		l.join();
		t.join();
		
		if(Liebre.casilla == 70 && Tortuga.casilla == 70) {
			System.out.println("EMPATE");
		}
	}

}
