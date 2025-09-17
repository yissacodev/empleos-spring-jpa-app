package web.mvc.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	/* Esta clase guarda un archivo y aniade un caracer random para evitar errores de ambiguedad
	 * 
	 */
	 
	public static String guardarArchivo( MultipartFile multipart, String ruta ) {
		//Obtener el nombre originar del archivo
		String nombreOriginal = multipart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");		//Reemplazar los espacios por guiones
		nombreOriginal = randomChar() + "-" + nombreOriginal;	//Generar un caracter aleatorio y sucederle un -
		try {
			//Formar el nombre del arhcivo para guardarlo den el dico duro
			File imageFile = new File(ruta + nombreOriginal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			
			//Guardar fisicamente el archivo
			multipart.transferTo(imageFile);
			
			return nombreOriginal;
		} catch ( IOException e) {
			System.out.println( "Error: " + e.getMessage());
			return null;
		}
	}
	
	//Generar carater aleatorio
	private static char randomChar() {
        Random r = new Random();
        return (char)(r.nextInt(26) + 'A');
    }
}
