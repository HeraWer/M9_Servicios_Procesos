package thread;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ThreadEx3 tex3 = new ThreadEx3();
		Thread t = new Thread(tex3);
		
		t.setDaemon(true);
		t.start();
		
		System.out.println("Ordenando...");
		sc.next();
	}
}
