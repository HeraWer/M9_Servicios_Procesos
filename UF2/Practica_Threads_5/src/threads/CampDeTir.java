package threads;

public class CampDeTir {
	public static void main(String[] args) {
        Pistola arma = new Pistola();
        Carregar c = new Carregar(arma, 1);
        Descarregar d = new Descarregar(arma, 1);
        c.start();
        d.start();
    }

}
