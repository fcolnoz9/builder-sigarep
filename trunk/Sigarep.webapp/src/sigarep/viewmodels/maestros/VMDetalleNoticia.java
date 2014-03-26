package sigarep.viewmodels.maestros;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import java.io.IOException;
import java.util.List;
import org.zkoss.image.AImage;
import sigarep.herramientas.Archivo;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.servicio.maestros.ServicioNoticia;


/**
 * Clase VMDetalleNoticia
 * 
 * @author BUILDER
 * @version 1.0
 * @since 19/12/2013
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleNoticia {
	// -----------------Servicios-------------------------
	@WireVariable
	ServicioNoticia servicionoticia;
	// -----------------Variables Noticia-----------------
	private String titulo;
	private String contenido;
	private String enlaceNoticia;
	private Integer idNoticia;
	// -----------------Variables Lista----------------------
	private List<Noticia> listaNoticia1;
	// -----------------Variables Objeto---------------------
	private Archivo fotoNoticia = new Archivo();
	private AImage imagen;
	private Noticia noticiaSeleccionada1;
	// -----------------Variables Modal----------------------
	@Wire("#modalDialog")
	private Window win;

	// Métodos Set y Get
	public AImage getImagen() {
		return imagen;
	}

	public void setImagen(AImage imagen) {
		this.imagen = imagen;
	}

	public Integer getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}

	public Archivo getFotoNoticia() {
		return fotoNoticia;
	}

	public void setFotoNoticia(Archivo fotoNoticia) {
		this.fotoNoticia = fotoNoticia;
	}

	public String getEnlaceNoticia() {
		return enlaceNoticia;
	}

	public void setEnlaceNoticia(String enlaceNoticia) {
		this.enlaceNoticia = enlaceNoticia;
	}

	public List<Noticia> getListaNoticia() {
		return listaNoticia1;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setListaNoticia(List<Noticia> listaNoticia) {
		this.listaNoticia1 = listaNoticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	String ruta = UtilidadesSigarep.obtenerDirectorio();

	// Fin Métodos Set y Get
	
	/**
	 * inicialización
	 * 
	 * @param  Component view
	 * @return código de inicialización
	 * @throws No
	 * IOException e, e1
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("noticiaSeleccionada") Noticia v6) {
		Selectors.wireComponents(view, this, false);
		this.noticiaSeleccionada1 = v6;
		this.titulo = noticiaSeleccionada1.getTitulo();
		this.contenido = noticiaSeleccionada1.getContenido();
		this.enlaceNoticia = noticiaSeleccionada1.getEnlaceNoticia();
		this.fotoNoticia = noticiaSeleccionada1.getFotoNoticia();
		try {
			this.imagen = fotoNoticia.getAImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (imagen == null) {
				imagen = new AImage(
						ruta
	                     + "/Sigarep.webapp/WebContent/imagenes/iconos/imagen-no-disponible.png");
				fotoNoticia.setAImage(imagen);
			} else {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}

}
