package threads;

public class Almacen {

	public static final int maxCapacidad = 10;
	private int stock;
	private String nombreAlmacen;

	public Almacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
		this.stock = 0;
	}
	
	// Metodo sincronizado de añadir elementos al almacen tanto hierro o herramientas.
	public synchronized void añadirStock(int in) throws InterruptedException {
		
		/*
		 * Si el stock que va a introducir el herrero o el minero supera la capacidad del almacen este se espera.
		 */
		if((this.stock + in) > maxCapacidad) {
			//this.stock = maxCapacidad;
			System.err.println(Thread.currentThread().getName() + " se espera");
			wait();
		}else {
			this.stock += in;
			System.out.println("Se han añadido " + in  + " unidades de " +  nombreAlmacen + " al almacen. Hay un total de " + stock + " en el almacen" );
			// Al añadir elementos a los almacenes este se suma y se notifica a todos los demas thread que ha habido movimiento.
			notifyAll();
		}
	}
	
	// Metodo sincronizado de sacar elementos al almacen tanto hierro o herramientas.
	public synchronized void sacarStock(int out) throws InterruptedException {
		
		/*
		 * Si el stock que va a retirar el herrero o el tren no es suficiente para generar una herramienta o para que el tren se lleve las herramientas este se espera.
		 */
		if((this.stock - out) < 0) {
			//this.stock = 0;
			System.err.println(Thread.currentThread().getName() + " se espera");
			wait();
		}else {
			this.stock -= out;
			System.out.println("Se han sacado " + out + " unidades de " + nombreAlmacen + ".Quedan " + stock + " unidades.");
			// Al poder quitar elementos de los almacen este se resta y se notifica a todos los demas thread que ha habido movimiento.
			notifyAll();
		}
	}
	
	
	

}
