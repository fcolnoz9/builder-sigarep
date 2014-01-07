package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.data.maestros.MenuItem;
import sigarep.modelos.servicio.maestros.ServicioEnlaceIntere;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMenlace {
	 
	@WireVariable ServicioEnlaceIntere servicioei;
	public  List<EnlaceInteres> listaEnlace;
	private AImage imag;
	private List<MenuItem> menuItems;
	
	public AImage getImag() {
		return imag;
	}

	public void setImag(AImage imag) {
		this.imag = imag;
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

@Init
	public void init(){
	Enlaces();
}
	
	 
	public void Enlaces(){ 
		listaEnlace =servicioei.listadoEnlaceIntere();
		menuItems = new ArrayList<MenuItem>();
		MenuItem m1_Lv1 = new MenuItem("",null,"",1);
		//menuItems.add(m1_Lv1);
		for  (int i=0;i < listaEnlace.size(); i++){
			
			if (listaEnlace.get(i).getImagen().getTamano() > 0){
				try {
					imag = new AImage(listaEnlace.get(i).getImagen().getNombreArchivo(),listaEnlace.get(i).getImagen().getContenidoArchivo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			else
				imag = null;
		
		
			
			m1_Lv1.addChild(new MenuItem("",imag,listaEnlace.get(i).getDireccionEnlace(),2));
		}
		menuItems.add(m1_Lv1);
	}
	
	
//	public void VMenlace() {
//		getAllMenus();
//		
//	}
	
		
	public List<MenuItem> getAllMenus() {
				return new ArrayList<MenuItem>(menuItems);
	}
	
	
	
}
