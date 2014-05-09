package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.data.maestros.MenuItem;
import sigarep.modelos.servicio.maestros.ServicioEnlaceInteres;

/**
 * Clase VMenlace
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 19/12/2013
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEnlace {
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioEnlaceInteres servicioenlacesinteres;
	//-----------------Variables Lista----------------------
	public List<EnlaceInteres> listaEnlace;
	//-----------------Variables Enlace -----------------
	private AImage imag;
	private List<MenuItem> menuItems;
	
	// Métodos Set y Get

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
	
	public List<MenuItem> getAllMenus() {
		return new ArrayList<MenuItem>(menuItems);
	}// Fin de los métodos set y get

	/**
	 * inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno
	 * @return código de inicialización
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		Enlaces();
	}

	/** 
	 * Enlaces.
	 * 
	 * @param Ninguno 
	 * @return Ninguno. 
	 * @throws No dispara ninguna excepcion. 
	 */
	public void Enlaces() {
		listaEnlace = servicioenlacesinteres.listadoEnlaceInteres();
		menuItems = new ArrayList<MenuItem>();
		MenuItem m1_Lv1 = new MenuItem("", null, "", 1);
		// menuItems.add(m1_Lv1);
		for (int i = 0; i < listaEnlace.size(); i++) {

			if (listaEnlace.get(i).getImagen().getTamano() > 0) {
				try {
					imag = new AImage(listaEnlace.get(i).getImagen()
							.getNombreArchivo(), listaEnlace.get(i).getImagen()
							.getContenidoArchivo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			} else
				imag = null;

			m1_Lv1.addChild(new MenuItem("", imag, listaEnlace.get(i)
					.getDireccionEnlace(), 2));
		}
		menuItems.add(m1_Lv1);
	}

}
