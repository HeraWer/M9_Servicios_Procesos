package practicacifratxesimetric;

public class MainClass {

	public static void main(String[] args) {

		// Creamos el array String para pasar los parametros password y fichero
		String[] credencialesEncriptar = {"patata", "fichero_encriptado.txt"}; 
		//String[] credencialesDesencriptar = {"patata", "fichero_encriptado.txt.des"};
		
		// Dentro del try/catch llamamos al main y le pasamos el array
		try {
			EncriptaFichero.main(credencialesEncriptar);
			//DesencriptaFichero.main(credencialesDesencriptar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
