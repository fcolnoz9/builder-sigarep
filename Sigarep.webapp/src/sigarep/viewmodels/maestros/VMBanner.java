package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.Archivo;
import sigarep.modelos.data.maestros.Banner;
import sigarep.modelos.servicio.maestros.ServicioBanner;


/** View Models de Banner.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 * @since 20/12/2013
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMBanner {

	@WireVariable ServicioBanner servicioBanner;
	private Integer idImagen;
	private String descripcion;
	private String enlace;
	private Date fechaVencimiento;
	private String titulo;
	private Boolean estatus;
	private String tituloFiltro;
	private String enlaceFiltro;
	private List<Banner> listadoBanner;
	private Banner bannerSeleccionado;
	private Archivo fotoBanner = new Archivo();
	private Media media;
	private AImage imagenBanner;
	MensajesAlUsuario mensajeAlUsuario= new MensajesAlUsuario();
	private int indiceBanner = 0;
	private AImage contenidoBanner;
	private Archivo fotoBannerPortal = new Archivo();
	
	@Wire("#winActualizarBanner")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	//Metodos Get y Set de la clase 
	
	public Integer getIdImagen() {
		return idImagen;
	}
	
	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	
	public String getTituloFiltro() {
		return tituloFiltro;
	}

	public void setTituloFiltro(String tituloFiltro) {
		this.tituloFiltro = tituloFiltro;
	}

	public String getEnlaceFiltro() {
		return enlaceFiltro;
	}

	public void setEnlaceFiltro(String enlaceFiltro) {
		this.enlaceFiltro = enlaceFiltro;
	}

	public List<Banner> getListadoBanner() {
		return listadoBanner;
	}
	
	public void setListadoBanner(List<Banner> listadoBanner) {
		this.listadoBanner = listadoBanner;
	}
	
	public Banner getBannerSeleccionado() {
		return bannerSeleccionado;
	}

	public void setBannerSeleccionado(Banner bannerSeleccionado) {
		this.bannerSeleccionado = bannerSeleccionado;
	}
	
	public AImage getImagenBanner() {
		return imagenBanner;
	}

	public void setImagenBanner(AImage imagenBanner) {
		this.imagenBanner = imagenBanner;
	}
	
	public AImage getContenidoBanner() {
		return contenidoBanner;
	}

	public void setContenidoBanner(AImage contenidoBanner) {
		this.contenidoBanner = contenidoBanner;
	}

	public Archivo getFotoBannerPortal() {
		return fotoBannerPortal;
	}

	public void setFotoBannerPortal(Archivo fotoBannerPortal) {
		this.fotoBannerPortal = fotoBannerPortal;
	}
	
	//Fin de los Metodos Get y Set de la clase 

	/**Inicialización
	 * @param init
	 * @return Carga de Variables y metodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Init
	public void init(){
        //initialization code
		fechaVencimiento= null;
		titulo= null;
		enlace= null;
		tituloFiltro= "";
		enlaceFiltro= "";
		descripcion= null; 
		media= null;
		fotoBanner= new Archivo();
		imagenBanner = null;
		buscarTodosLosBanner();
		cambiarImagen();
    }
	
		// Metodos de la clase

		@Command
		@NotifyChange({"listadoBanner", "tituloFiltro","enlaceFiltro"})
		public void filtrosBanner(){
			listadoBanner =servicioBanner.buscarFiltroBanner(tituloFiltro,enlaceFiltro);
		}
	
		
		/** Guardar Datos del Banner.
		 	* @param Ninguno
		 	* @return Objeto Banner Guardado
		 	* @throws No dispara ninguna excepcion.
		 */

		@Command // Permite manipular la propiedad de ViewModel
		@NotifyChange({"descripcion","enlace","fechaVencimiento","titulo","estatus","imagenBanner","listadoBanner"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es los atributos de la pantalla se va a colocar en blanco al guardar!!
		public void guardarBanner(){
			if (descripcion == null||enlace == null||titulo == null)
				mensajeAlUsuario.advertenciaLlenarCampos();
			else if (fotoBanner.getTamano() < 1)
				mensajeAlUsuario.advertenciaCargarImagen();
			else{
					Banner ban = new Banner (idImagen,descripcion,enlace,fechaVencimiento,titulo,fotoBanner,true);
					servicioBanner.guardarBanner(ban);
					mensajeAlUsuario.informacionRegistroCorrecto();
					limpiarBanner();
			}
		}
		
		
		/** Eliminar Banner.
	 		* @param Integer idImagen
	 		* @return Objeto Banner Eliminado
	 		* @throws No dispara ninguna excepcion.
	 	*/
		@SuppressWarnings("unchecked")
		@Command
		@NotifyChange({"listadoBanner","descripcion","fechaVencimiento","enlace","titulo","imagenBanner"})
		public void eliminarImagenBanner(@ContextParam(ContextType.BINDER) final Binder binder){
			if(titulo==null || enlace==null || descripcion==null || fechaVencimiento==null || fotoBanner.getTamano() < 1) {
				mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
			} else {
				Messagebox.show("¿Desea eliminar el registro realmente?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
						Messagebox.QUESTION,new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
							case YES:
								//if you call super.delete here, since original zk event is not control by binder
								//the change of viewmodel will not update to the ui.
								//so, I post a delete to trigger to process it in binder controll.
								//binder.postCommand("limpiar", null);
								servicioBanner.eliminarBanner(idImagen);
								mensajeAlUsuario.informacionEliminarCorrecto();
								binder.postCommand("limpiarBanner", null);
							case NO:
						
								binder.postCommand("limpiarBanner", null);
						}
					}
				});		
			}
		}
		
	
		
		/** Limpiar Banner.
	 		* @param Ninguno
	 		* @return Limpiar cada una de las cajas de texto de la vista
	 		* @throws No dispara ninguna excepcion.
	 	*/
		
		@Command
		@NotifyChange({"descripcion","fechaVencimiento","enlace","titulo","imagenBanner","bannerSeleccionado","listadoBanner","tituloFiltro","enlaceFiltro"})
		public void limpiarBanner(){
			idImagen= null; 
			descripcion=null;
			 enlace=null;
			 titulo=null;
			 tituloFiltro= "";
			 enlaceFiltro= "";
			 media= null;
			 imagenBanner= null;
			 fotoBanner = new Archivo();
			 fechaVencimiento= null;//new Date();
			 bannerSeleccionado= null;
			 buscarTodosLosBanner();
		}
		
		
        /** Buscar Banner.
	 		* @param Ninguno
	 		* @return Todos los Banner en la lista que están en estatus TRUE en la Base de Datos
	 		* @throws No dispara ninguna excepcion.
	 	*/
        
		@Command
		@NotifyChange({"titulo","descripcion","idImagen","enlace","fechaVencimiento","listadoBanner","imagenBanner"})
		public void buscarTodosLosBanner(){
			listadoBanner =servicioBanner.buscarTodosBanner();
		}
		
		/** Mostrar Registro Seleccionado.
	 		* @param Ninguno
	 		* @return Llena cada una de las cajas de texto con los datos del registro seleccionado en la lista
	 		* @throws No dispara ninguna excepcion.
	 	*/
		
		@Command
		@NotifyChange({"descripcion","enlace","fechaVencimiento","titulo","imagenBanner"})
		public void mostrarSeleccionado(){
			idImagen= getBannerSeleccionado().getIdImagen();
			titulo=getBannerSeleccionado().getTitulo();
			descripcion=getBannerSeleccionado().getDescripcion();
			enlace=getBannerSeleccionado().getEnlace();
			fechaVencimiento=getBannerSeleccionado().getFechaVencimiento();
			fotoBanner=getBannerSeleccionado().getFotoBanner();
			if (bannerSeleccionado.getFotoBanner().getTamano() > 0){
				try {
					imagenBanner = new AImage(bannerSeleccionado.getFotoBanner().getNombreArchivo(), bannerSeleccionado.getFotoBanner().getContenidoArchivo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			else
				imagenBanner = null;
		}
	
		
		/** Cargar Imagen del Banner.
	 		* @param Ninguno
	 		* @return Muestra Imagen Seleccionada
	 		* @throws No dispara ninguna excepcion.
	 	*/
		@Command
		@NotifyChange("imagenBanner")
		public void cargarImagenBanner(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
			media = event.getMedia();
			if (media != null) {
				if (media instanceof org.zkoss.image.Image) {
					fotoBanner.setNombreArchivo(media.getName());
					fotoBanner.setTipo(media.getContentType());
					fotoBanner.setContenidoArchivo(media.getByteData());
			
					imagenBanner = (AImage) media;
					//Messagebox.show("Archivo: " + imagen.getHeight(), "Informacion", Messagebox.OK, Messagebox.INFORMATION);
				} else {
					Messagebox.show("El archivo: "+media+" no es una imagen valida", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			} 
			else
				mensajeAlUsuario.advertenciaCargarImagen();//Messagebox.show("Debe introducir una imagen", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
		/** Cambiar Imagen en el Portal (Carrusel).
 		* @param Ninguno
 		* @return Muestra las imagenes de Banner en el portal en forma de carrusel
 		* @throws No dispara ninguna excepcion.
 		*/
		
		//Metodo que reordena la lista
		@Command
		@NotifyChange({"listadoBanner","fotoBannerPortal"})
		public void cambiarImagen(){				
			if(listadoBanner.size() > 0){
				fotoBannerPortal=listadoBanner.get(indiceBanner).getFotoBanner();
				indiceBanner= indiceBanner+1;
				if (indiceBanner==listadoBanner.size()){
					indiceBanner=0;
				}
			}
		}
		
		/**
		 * Cerrar Ventana
		 * 
		 * @param binder
		 * @return cierra el .zul asociado al VM
		 * @throws No
		 *             dispara ninguna excepcion.
		 */
		@SuppressWarnings("unchecked")
		@Command
		@NotifyChange({"listadoBanner","descripcion","fechaVencimiento","enlace","titulo","imagenBanner"})
		public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
				
			if(titulo != null || enlace != null || descripcion != null || fechaVencimiento != null || fotoBanner.getTamano() > 1) 
			{
				Messagebox.show("¿Realmente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
						Messagebox.QUESTION,new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
							case YES:
									ventana.detach();
						
						}
					}
				});		
			}
			else{
			Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
						Messagebox.QUESTION,new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
							case YES:
									ventana.detach();
						
						
						}
					}
				});		
			}
		}	
}
