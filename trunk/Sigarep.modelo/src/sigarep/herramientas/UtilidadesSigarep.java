package sigarep.herramientas;

import java.net.URL;

/**
 * Clase de Utilidades del Sistema Sigarep
 * 
 * @since Sabado 14-12-2013 01:53 PM.
 * @author Jorge Chaviel
 * @version 1.0 Sabado 14-12-2013 01:53 PM.
 * 
 */
public class UtilidadesSigarep {

	/**
	 * 
	 * @return El directorio actual de trabajo
	 */
	public static String obtenerDirectorio() {
		URL rutaURL = UtilidadesSigarep.class.getProtectionDomain()
				.getCodeSource().getLocation();
		String ruta = rutaURL.getPath();
		return ruta.substring(1, ruta.indexOf(".metadata"));
	}

}
