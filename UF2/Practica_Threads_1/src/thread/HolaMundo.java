package thread;

public class HolaMundo extends Thread {

	/*
	 * Creamos una variable privada con su constructor al que le pasaremos unos datos.
	 */
	private String word;
	private int countThread;

	public HolaMundo(String word, int countThread) {
		super();
		this.word = word;
		this.countThread = countThread;
	}
	
	/*
	 * Imprimimos el word y el numero del hilo al ejecutar el Thread.
	 */
	public void run() {
		
		System.out.println(word + " - Numero del hilo: " + countThread);
	}

}
