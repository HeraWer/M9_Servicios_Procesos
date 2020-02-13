package practica_criptografia_asimetrica;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class EncriptarFichero implements Constantes {
	
	public static void main(String[] args) throws Exception {
		
		// Pedimos el fichero a encriptar
		// y fichero de clave publica a usar
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Indique fichero a encriptar:");
		String fichero_encriptar = teclado.readLine();
		if (!new File(fichero_encriptar).exists()) {
			System.out.println("El fichero " + fichero_encriptar + " no existe");
			return;
		}
		String fichero_encriptado = fichero_encriptar + ".crypto";
		System.out.print("Indique que fichero tiene la" + " clave publica a usar:");
		String fichero_publica = teclado.readLine();

		// Recuperamos la clave publica
		FileInputStream fis = new FileInputStream(fichero_publica);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		X509EncodedKeySpec clave_publica_spec = new X509EncodedKeySpec(buffer);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey clave_publica = kf.generatePublic(clave_publica_spec);

		// Generamos el fichero encriptado
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		fis = new FileInputStream(fichero_encriptar);
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero_encriptado));
		
		// 1. Generamos una clave de sesion
		System.out.println("Generando clave de sesion...");
		KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
		kg.init(TAMANO_CLAVE_SESION, sr);
		SecretKey clave_sesion = (SecretKey) kg.generateKey();
		
		// 2. Guardamos la clave de sesion
		// encriptada en el fichero
		System.out.println("Guardando la clave de sesion encriptada...");
		Cipher cifrador_rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cifrador_rsa.init(Cipher.ENCRYPT_MODE, clave_publica, sr);
		buffer = cifrador_rsa.doFinal(clave_sesion.getEncoded());
		dos.writeInt(buffer.length);
		dos.write(buffer);
		
		// 3. Generamos un IV aleatorio
		byte[] IV = new byte[TAMANO_IV_BYTES];
		sr.nextBytes(IV);
		IvParameterSpec iv_spec = new IvParameterSpec(IV);
		dos.write(IV);
		
		// 4. Guardamos los datos encriptados
		// en el fichero
		System.out.println("Guardando " + fichero_encriptar + " en el fichero encriptado" + fichero_encriptado);
		Cipher cifrador_fichero = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
		cifrador_fichero.init(Cipher.ENCRYPT_MODE, clave_sesion, iv_spec, sr);
		CipherOutputStream cos = new CipherOutputStream(dos, cifrador_fichero);
		int b = fis.read();
		while (b != -1) {
			cos.write(b);
			b = fis.read();
		}
		fis.close();
		cos.close();
		dos.close();
		System.out.println("Fichero encriptado correctamente");
	}
}
