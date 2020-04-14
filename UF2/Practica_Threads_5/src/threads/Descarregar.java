package threads;

public class Descarregar  extends Thread {
	private Pistola arma;
    private int cartucho;

    public Descarregar(Pistola arma, int cartucho) {
        this.arma = arma;
        this.cartucho = cartucho;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            arma.disparar(i);
            System.out.println("Descarregar #" + this.cartucho
                               + " bala: " + i);
        }
    }

}
