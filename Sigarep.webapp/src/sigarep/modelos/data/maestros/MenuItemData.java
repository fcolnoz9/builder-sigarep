package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

public class MenuItemData {

	private static List<MenuItem> menus = new ArrayList<MenuItem>();
	static {

 		MenuItem m1_Lv1 = new MenuItem("","","",1);
		MenuItem m1_Lv2 = new MenuItem("","/imagenes/portal-principal/sistemaico.png","http://www.yahoo.com",2);
		MenuItem m1_Lv3 = new MenuItem("","/imagenes/portal-principal/logoIteratorico.png","www.google.com",3);
		MenuItem m1_Lv4 = new MenuItem("","/imagenes/portal-principal/sitecico.png","http://www.facebook.com",4);
		MenuItem m1_Lv5 = new MenuItem("","/imagenes/portal-principal/sistemaico.png","http://www.hotmail.com",2);
		
		
		m1_Lv1.addChild(m1_Lv2);
		m1_Lv1.addChild(m1_Lv3);
		m1_Lv1.addChild(m1_Lv4);
		m1_Lv1.addChild(m1_Lv5);
		
		
		menus.add(m1_Lv1);

		
		
	}

	public static List<MenuItem> getAllMenus() {
		return new ArrayList<MenuItem>(menus);
	}

}
