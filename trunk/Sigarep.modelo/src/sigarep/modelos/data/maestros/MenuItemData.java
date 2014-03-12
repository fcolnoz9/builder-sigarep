package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

public class MenuItemData {

	private static List<MenuItem> menus = new ArrayList<MenuItem>();
	static {
		
	}

	public static List<MenuItem> getAllMenus() {
		return new ArrayList<MenuItem>(menus);
	}

}
