package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

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
