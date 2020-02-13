package practica_criptografia_asimetrica;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class CrearClaves implements Constantes {

	public static void main(String[] args) throws Exception {

		// Generamos las claves publica/privada
		SecureRandom sr = new SecureRandom(); // Genera un numero random cryptographically
		sr.setSeed(new Date().getTime());
		System.out.println("Generando claves...");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); // Genera una clave publica o privada
		kpg.initialize(TAMANO_CLAVE_RSA, sr);
		KeyPair par_claves = kpg.generateKeyPair(); // Genera un simple titular de las claves privadas o publicas
		System.out.println("Claves generadas");
		
		// Generamos el fichero de la clave publica
		System.out.print("Indique fichero para" + " la clave publica:");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in)); // Iniciamos el BufferedReader para escribir en el fichero sin sobrescribir
		String fichero_publica;
		fichero_publica = teclado.readLine();
		FileOutputStream fos = new FileOutputStream(fichero_publica); // FileOutputStream para escribir en el fichero
		fos.write(par_claves.getPublic().getEncoded());
		fos.close();
		System.out.println("Fichero con clave publica generado");
		
		// Generamos el fichero de clave privada
		System.out.print("Indique fichero para la clave privada:");
		String fichero_privada; // Hacemos los mismo que el de fichero de clave publica
		fichero_privada = teclado.readLine();
		System.out.print("La clave privada debe estar encriptada, indique password con la que encriptarla:");
		char[] password; // Aqui almacenamos la contraseña para la clave privada
		password = teclado.readLine().toCharArray();
		
		// Encriptamos con un PBE
		byte[] salt = new byte[TAMANO_SALT_BYTES]; // Hacemos un array de Bytes
		sr.nextBytes(salt);
		PBEKeySpec clave_pbe = new PBEKeySpec(password); // Encriptamos la contraseña
		SecretKey clave_secreta_pbe = SecretKeyFactory.getInstance("PBEWithMD5AndDES") // Instancia el algorismo de encriptacion de la clave secreta
				.generateSecret(clave_pbe);
		PBEParameterSpec pbe_param = new PBEParameterSpec(salt, ITERACIONES_PBE); // Setea paramatros usados para la contraseña encriptada
		Cipher cifrador_pbe = Cipher.getInstance("PBEWithMD5AndDES"); // Instanciamos el cifrador con el algorismo de encriptacion
		cifrador_pbe.init(Cipher.ENCRYPT_MODE, clave_secreta_pbe, pbe_param);
		byte[] clave_privada_cifrada = cifrador_pbe.doFinal(par_claves.getPrivate().getEncoded());
		fos = new FileOutputStream(fichero_privada); // Escribimos en el fichero lo encriptado.
		fos.write(salt);
		fos.write(clave_privada_cifrada);
		fos.close();
		System.out.println("Fichero con clave privada generado");
	}
}
