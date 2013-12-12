package sigarep.viewmodels.maestros;

import java.util.List;
import sigarep.modelos.data.maestros.MenuItem;
import sigarep.modelos.data.maestros.MenuItemData;;



public class VMenlace {
	 
	private List<MenuItem> menuItems;

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public VMenlace() {
		menuItems = MenuItemData.getAllMenus();
		
	}
}
