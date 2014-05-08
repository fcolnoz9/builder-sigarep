package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase MenuItemData (Se utiliza para mostrar un arreglo de menu de enlace de interes)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 15/01/2014
 * @last 08/05/2014
 */

public class MenuItemData {

	private static List<MenuItem> menus = new ArrayList<MenuItem>();
	static {
		
	}

	/**
	 * getAllMenus
	 * 
	 * @param 
	 * @return el arreglo de menu de enlace de interes
	 */
	public static List<MenuItem> getAllMenus() {
		return new ArrayList<MenuItem>(menus);
	}

}
