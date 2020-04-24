package threads;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
	static List<Minero> mineros = new ArrayList<>();
	static List<Herrero> herreros = new ArrayList<>();
	
	static Tren t = new Tren("TREN");
	static Almacen almacenHierro = new Almacen("Hierro");
	static Almacen almacenHerramientas = new Almacen("Herramientas");
	
	static int numMineros, numHerreros, numTren;

	/*
	 * Main que pide los trabajadores que habran y los ejecuta 1 a 1 en un for
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Se preguntan los mineros que habra
		numMineros = Integer.parseInt(JOptionPane.showInputDialog("Cuantos mineros habra?"));
		for(int i = 0 ; i < numMineros; i++) {
			mineros.add(new Minero("MINERO " + i));
		}
		
		// Se preguntan los herreros que habra
		numHerreros = Integer.parseInt(JOptionPane.showInputDialog("Cuantos herreros habra?"));
		for(int i = 0 ; i < numHerreros; i++) {
			herreros.add(new Herrero("HERRERO " + i));
		}
		
		/*
		 * Se ejecutan los threads
		 */
		for (Minero minero : mineros) {
			minero.start();
		}
		for (Herrero herrero : herreros) {
			herrero.start();
		}
		t.start();
	}
}
