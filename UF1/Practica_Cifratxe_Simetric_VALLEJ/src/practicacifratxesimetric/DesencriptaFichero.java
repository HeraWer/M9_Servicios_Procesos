package practicacifratxesimetric;

/* DESCRIPCION: Programa que desencripta un fichero  
 *              encriptado por EncriptaFichero 
 */ 
 
import java.io.*; 
import java.security.*; 
import javax.crypto.*; 
import javax.crypto.spec.*; 
 
public class DesencriptaFichero 
{ 
 public static final int ITERACIONES = 1024; 
 public static final int TAMANO_BUFFER = 1024; 
 
 public static void main (String args[]) throws Exception 
        { 
         // Comprobacion de argumentos 
         if (args.length<2 || args.length>3) 
            { 
             System.out.println("Indique <password> " 
                + " <fichero_encriptado> [<fichero_plano>]" 
                + " como argumento"); 
             return; 
            } 
         if (!args[1].endsWith(".des")) 
            { 
             System.out.println("Los ficheros encriptados" 
                      + " deben tener la extension .des"); 
             return; 
            } 
             
         // Abrimos los ficheros 
         System.out.print("Abriendo fichero..."); 
         DataInputStream fichero_encriptado = 
         new DataInputStream(new FileInputStream(args[1])); 
         FileOutputStream fichero_plano; 
         if (args.length==2) 
            fichero_plano = new FileOutputStream( 
                args[1].substring(0,args[1].length()-4)); 
         else 
            fichero_plano = new FileOutputStream(args[2]); 
          
         // Generamos una clave secreta a partir 
         // del password 
         System.out.print("\rGenerando clave secreta"); 
         PBEKeySpec objeto_password =  
             new PBEKeySpec(args[0].toCharArray()); 
         SecretKeyFactory skf =  
                SecretKeyFactory.getInstance( 
                                 "PBEWithMD5AndDES"); 
         SecretKey clave_secreta =  
                skf.generateSecret(objeto_password); 
 
          
         // Leemos los parametros encoded 
         int longitud_encoded = 
              fichero_encriptado.readInt(); 
         byte[] encoded = new byte[longitud_encoded]; 
         fichero_encriptado.read(encoded); 
         AlgorithmParameters ap = 
               AlgorithmParameters.getInstance( 
               "PBEWithMD5AndDES"); 
         ap.init(encoded); 
          
         // Creamos el cifrador 
         Cipher cifrador =  
              Cipher.getInstance("PBEWithMD5andDES"); 
         cifrador.init(Cipher.DECRYPT_MODE 
              ,clave_secreta,ap); 
              
         // Desencriptamos el contenido del fichero 
         // encriptado y lo pasamos al fichero plano 
         System.out.print("\rDesencriptando fichero..."); 
         byte[] buffer = new byte[TAMANO_BUFFER]; 
         int bytes_leidos =  
              fichero_encriptado.read(buffer); 
         while (bytes_leidos>0) 
               { 
                fichero_plano.write(cifrador.update( 
                                  buffer,0,bytes_leidos)); 
                bytes_leidos =  
                     fichero_encriptado.read(buffer); 
               } 
         fichero_plano.write(cifrador.doFinal()); 
         // Cerramos los ficheros 
         fichero_encriptado.close(); 
         fichero_plano.close(); 
         System.out.println("\rHecho                   "); 
        } 
 
}
