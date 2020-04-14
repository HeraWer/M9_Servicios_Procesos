package threads;

public class Pistola {
	private int cartucho;
    private boolean enposicio = true;

    public synchronized void disparar(int cartucho) {
        while (enposicio == false) {
            try {
                // Esperar a apuntar
                wait();
            } catch (InterruptedException e) { }
        }
        enposicio = false;
        notifyAll();
    }
 
    public synchronized void apuntar() {
        while (enposicio == true) {
            try {
                // Esperar a disparar
                wait();
            } catch (InterruptedException e) { }
        }
        enposicio = true;
        notifyAll();
    }

}
