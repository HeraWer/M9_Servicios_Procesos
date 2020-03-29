package thread;

import java.io.IOException;

public class ThreadEx3 implements Runnable {

	@Override
	public void run() {
		try {
			Process proceso = Runtime.getRuntime().exec("cmd /c sort lista.txt > orden.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
