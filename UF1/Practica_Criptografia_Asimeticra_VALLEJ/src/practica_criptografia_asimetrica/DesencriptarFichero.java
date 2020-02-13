package practica_criptografia_asimetrica;

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class DesencriptarFichero implements Constantes {
	public static void main(String[] args) throws Exception {
		// Pedimos el fichero a desencriptar
		// y fichero de clave privada a usar
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Indique fichero a desencriptar:");
		String fichero_encriptado = teclado.readLine();
		if (!new File(fichero_encriptado).exists()) {
			System.out.println("El fichero " + fichero_encriptado + " no existe");
			return;
		}
		if (!fichero_encriptado.toLowerCase().endsWith(".crypto")) {
			System.out.println("La extension de los" + "ficheros encriptados debe ser .crypto");
			return;
		}
		String fichero_desencriptado = fichero_encriptado.substring(0,
				fichero_encriptado.length() - ".crypto".length());
		System.out.print("Indique que fichero tiene la" + " clave privada a usar:");
		String fichero_privada = teclado.readLine();
		System.out.print("Indique password con que se " + "encripto el fichero " + fichero_privada + ":");
		char[] password;
		password = teclado.readLine().toCharArray();

		// Recuperamos la clave privada
		System.out.println("Recuperando clave privada...");
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		FileInputStream fis = new FileInputStream(fichero_privada);
		byte[] buffer = new byte[TAMANO_SALT_BYTES];
		fis.read(buffer);
		PBEKeySpec clave_pbe_spec = new PBEKeySpec(password);
		SecretKey clave_pbe = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(clave_pbe_spec);
		PBEParameterSpec param_pbe_spec = new PBEParameterSpec(buffer, ITERACIONES_PBE);
		Cipher descifrador_pbe = Cipher.getInstance("PBEWithMD5AndDES");
		descifrador_pbe.init(Cipher.DECRYPT_MODE, clave_pbe, param_pbe_spec, sr);
		buffer = new byte[fis.available()];
		fis.read(buffer);
		buffer = descifrador_pbe.doFinal(buffer);
		PKCS8EncodedKeySpec clave_privada_spec = new PKCS8EncodedKeySpec(buffer);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey clave_privada = kf.generatePrivate(clave_privada_spec);
		System.out.println("Clave secreta recuperada");

		// Generamos el fichero desencriptado
		DataInputStream dis = new DataInputStream(new FileInputStream(fichero_encriptado));
		FileOutputStream fos = new FileOutputStream(fichero_desencriptado);

		// 1. Recuperamos la clave de sesion
		System.out.println("Generando el fichero desencriptado...");
		int longitud = dis.readInt();
		buffer = new byte[longitud];
		dis.read(buffer);

		// 2. Desencriptamos la clave de sesion
		Cipher descifrador_rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		descifrador_rsa.init(Cipher.DECRYPT_MODE, clave_privada, sr);
		buffer = descifrador_rsa.doFinal(buffer);
		SecretKeySpec clave_sesion = new SecretKeySpec(buffer, "Blowfish");

		// 3. recuperamos el IV
		byte[] IV = new byte[TAMANO_IV_BYTES];
		dis.read(IV);
		IvParameterSpec iv_spec = new IvParameterSpec(IV);

		// 4. Desencriptamos y vamos generando el
		// fichero desencriptado
		System.out.println("Guardando " + fichero_encriptado + " en el fichero encriptado " + fichero_desencriptado);
		Cipher cifrador_fichero = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
		cifrador_fichero.init(Cipher.DECRYPT_MODE, clave_sesion, iv_spec, sr);
		CipherOutputStream cos = new CipherOutputStream(fos, cifrador_fichero);
		int b = dis.read();
		while (b != -1) {
			cos.write(b);
			b = dis.read();
		}
		dis.close();
		cos.close();
		fos.close();
		System.out.println("Fichero desencriptado correctamente");
	}
}
