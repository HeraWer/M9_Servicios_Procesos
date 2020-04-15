package threads;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
	static int almacenHierro = 0;
	static int almacenHerramientas = 0;
	static List<Minero> mineros = new ArrayList<>();
	static List<Herrero> herreros = new ArrayList<>();
	
	static Tren t = new Tren();
	
	static int numMineros, numHerreros, numTren;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		numMineros = Integer.parseInt(JOptionPane.showInputDialog("Cuantos mineros habra?"));
		for(int i = 0 ; i < numMineros; i++) {
			mineros.add(new Minero());
		}
		
		numHerreros = Integer.parseInt(JOptionPane.showInputDialog("Cuantos herreros habra?"));
		for(int i = 0 ; i < numHerreros; i++) {
			herreros.add(new Herrero());
		}
		
		for (Minero minero : mineros) {
			minero.start();
		}
		for (Herrero herrero : herreros) {
			herrero.start();
		}
		t.start();
	}

	
	public synchronized static void guardarHierro(int hierro) {
		almacenHierro += hierro;
	}
	
	public synchronized static void guardarHerramienta(int herramienta) {
		almacenHerramientas += herramienta;
	}
	
/*	public synchronized static void despertarMinero() {
		for (Minero minero : mineros) {
			if(minero.getState() == State.WAITING) {
				minero.notify();
			}
		}
	}
	
	public synchronized static void despertarHerrero() {
		for (Herrero herrero: herreros) {
			if(herrero.getState() == State.WAITING) {
				herrero.notify();
			}
		}
	}
	
	public synchronized static void despertarTren() {
				t.notify();
		}*/
}
