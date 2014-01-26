package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.List;

import java.util.List;

import javax.swing.JOptionPane;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.servicio.maestros.ServicioEnlaceInteres;

/**
 * CargarEstudiante por XML UCLA DCYT Sistemas de Información.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMenlaceInteres {
	@WireVariable
	ServicioEnlaceInteres servicioenlacesinteres;
	private Integer idEnlace;
	private Integer idEnlaceFiltro;
	private String nombreEnlace;
	private String nombreEnlaceFiltro;
	private String direccionEnlace;
	private String direccionEnlaceFiltro;
	private String descripcion;
	private Boolean estatus;
	private Archivo imagen = new Archivo();
	private AImage imagenes;
	private Media media;
	private List<EnlaceInteres> listaEnlaces;
	private EnlaceInteres enlaceSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@Wire
	Textbox txtnombre_enlace;
	@Wire
	Window ventana;

	// Getters and Setters
	public Integer getIdEnlace() {
		return idEnlace;
	}

	public void setIdEnlace(Integer idEnlace) {
		this.idEnlace = idEnlace;
	}

	public Integer getIdEnlaceFiltro() {
		return idEnlaceFiltro;
	}

	public void setIdEnlaceFiltro(Integer idEnlaceFiltro) {
		this.idEnlaceFiltro = idEnlaceFiltro;
	}

	public String getNombreEnlace() {
		return nombreEnlace;
	}

	public void setNombreEnlace(String nombreEnlace) {
		this.nombreEnlace = nombreEnlace;
	}

	public String getNombreEnlaceFiltro() {
		return nombreEnlaceFiltro;
	}

	public void setNombreEnlaceFiltro(String nombreEnlaceFiltro) {
		this.nombreEnlaceFiltro = nombreEnlaceFiltro;
	}

	public String getDireccionEnlace() {
		return direccionEnlace;
	}

	public void setDireccionEnlace(String direccionEnlace) {
		this.direccionEnlace = direccionEnlace;
	}

	public String getDireccionEnlaceFiltro() {
		return direccionEnlaceFiltro;
	}

	public void setDireccionEnlaceFiltro(String direccionEnlaceFiltro) {
		this.direccionEnlaceFiltro = direccionEnlaceFiltro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Archivo getImagen() {
		return imagen;
	}

	public void setImagen(Archivo imagen) {
		this.imagen = imagen;
	}

	public AImage getImagenes() {
		return imagenes;
	}

	public void setImagenes(AImage imagenes) {
		this.imagenes = imagenes;

	}

	public List<EnlaceInteres> getListaEnlaces() {
		return listaEnlaces;
	}

	public void setListaEnlaces(List<EnlaceInteres> listaEnlaces) {
		this.listaEnlaces = listaEnlaces;
	}

	public EnlaceInteres getEnlaceSeleccionado() {
		return enlaceSeleccionado;
	}

	public void setEnlaceSeleccionado(EnlaceInteres enlaceSeleccionado) {
		this.enlaceSeleccionado = enlaceSeleccionado;
	}

	// fin Getters and Setters

	/**
	 * inicialización
	 * 
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepcion.
	 */

	@Init
	public void init() {
		imagen = new Archivo();
		buscarEnlaceInteres();
	}

	/**
	 * Guardar Enlace
	 * 
	 * @param guardar
	 * @return Guarda el registro completo, el command indica a las variables el
	 *         cambio que se hará en el objeto.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "idEnlace", "nombreEnlace", "direccionEnlace",
			"descripcion", "estatus", "imagenes", "listaEnlaces" })
	public void guardar() {
		if (nombreEnlace == null || direccionEnlace == null
				|| descripcion == null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else if (imagen.getTamano() < 1)
			mensajeAlUsuario.advertenciaCargarImagen();
		else {
			EnlaceInteres enlace = new EnlaceInteres(idEnlace, nombreEnlace,
					direccionEnlace, descripcion, true, imagen);
			servicioenlacesinteres.guardarEnlace(enlace);
			limpiar();
			mensajeAlUsuario.informacionRegistroCorrecto();
		}

	}

	/**
	 * Carga de imágenes
	 * 
	 * @param cargarImagen
	 * @return Permite la carga de imágenes. utiliza Archivo del paquete
	 *         herramientas.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange("imagenes")
	public void cargarImagen(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		if (media != null) {
			if (media instanceof org.zkoss.image.Image) {
				imagen.setNombreArchivo(media.getName());
				imagen.setTipo(media.getContentType());
				imagen.setContenidoArchivo(media.getByteData());

				imagenes = (AImage) media;
			} else {
				Messagebox.show("El archivo: " + media
						+ " no es una imagen valida", "Error", Messagebox.OK,
						Messagebox.ERROR);
			}
		}
	}

	/**
	 * Limpiar.
	 * 
	 * @param limpiar
	 * @return inicializa las cajas de texto
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "idEnlace", "nombreEnlace", "direccionEnlace",
			"descripcion", "estatus", "imagenes" })
	public void limpiar() {
		nombreEnlace = null;
		direccionEnlace = null;
		descripcion = null;
		idEnlace = null;
		media = null;
		imagenes = null;
		imagen = new Archivo();
		buscarEnlaceInteres();
	}

	/**
	 * Buscar enlaces de interés
	 * 
	 * @param buscarEnlacesInteres
	 * @return Busca todos los registros. Inicializa el código.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaEnlaces" })
	private void buscarEnlaceInteres() {
		listaEnlaces = servicioenlacesinteres.listadoEnlaceInteres();
	}

	/**
	 * Buscar enlaces de interés por nombre
	 * 
	 * @param buscarEnlaceFiltroNombreEnlace
	 * @return Permite la búsqueda por nombre en el filtro, en
	 *         ActualizarEnlace.zul,viene de VMenlaceInteres
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "listaEnlaces" })
	public void buscarEnlaceFiltroNombreEnlace() {
		listaEnlaces = servicioenlacesinteres
				.buscarEnlacesNombre(nombreEnlaceFiltro);
	}

	/**
	 * Buscar enlaces de interés por dirección
	 * 
	 * @param buscarEnlaceFiltroDireccionEnlace
	 * @return Permite la búsqueda por dirección en el filtro, en
	 *         ActualizarEnlace.zul,viene de VMenlaceInteres
	 * @throws No
	 *             dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({ "listaEnlaces" })
	public void buscarEnlaceFiltroDireccionEnlace() {
		listaEnlaces = servicioenlacesinteres
				.buscarEnlacesDireccion(direccionEnlaceFiltro);
	}

	/**
	 * Eliminar un enlace
	 * 
	 * @param eliminarEnlaceSeleccionado
	 * @return Elimina un enlace fisicamente.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "nombreEnlace", "direccionEnlace", "descripcion",
			"listaEnlaces", "imagenes" })
	public void eliminarEnlaceSeleccionado() {
		if (nombreEnlace == null || direccionEnlace == null
				|| descripcion == null || imagen.getTamano() < 1) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			servicioenlacesinteres.eliminar(idEnlace);
			mensajeAlUsuario.informacionEliminarCorrecto();
			limpiar();
		}

	}

	/**
	 * Mostrar Enlace
	 * 
	 * @param mostrarEnlace
	 * @return muestra en el área de datos el registro seleccionado.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "idEnlace", "nombreEnlace", "direccionEnlace",
			"descripcion", "estatus", "imagenes", "imagen" })
	public void mostrarEnlace() {
		idEnlace = enlaceSeleccionado.getIdEnlace();
		nombreEnlace = enlaceSeleccionado.getNombreEnlace();
		direccionEnlace = enlaceSeleccionado.getDireccionEnlace();
		descripcion = enlaceSeleccionado.getDescripcion();
		imagen = enlaceSeleccionado.getImagen();
		if (enlaceSeleccionado.getImagen().getTamano() > 0) {
			try {
				imagenes = new AImage(enlaceSeleccionado.getImagen()
						.getNombreArchivo(), enlaceSeleccionado.getImagen()
						.getContenidoArchivo());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		} else {
			imagenes = null;
		}
	}
}// fin VMenlaceInteres.

