package sigarep.viewmodels.seguridad;

import java.io.IOException;
import java.util.Date;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.maestros.Persona;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.servicio.maestros.ServicioPersona;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;

/**
* Clase VMRespaldarBaseDatos : Clase ViewModels relacionada con el Maestro Usuario.
*
* @author Equipo Builder
* @version 1.0
* @since 20/12/2014
* @last 10/05/2014
*/

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEditarPerfilUsuario {
	
	//-----------------Servicios----------------------------
	@WireVariable
	ServicioUsuario serviciousuario;
	@WireVariable
	ServicioPersona serviciopersona;
	//-----------------Variables Usuario ------------------
	private String cedula = "";
	private String nombre = "";
	private String apellido = "";
	private String correo = "";
	private String telefono = "";
	private Date fechaCreacion;
	//---------Variables de control------------------------
	String ruta = UtilidadesSigarep.obtenerDirectorio();
	//-----------------Variables Objeto--------------------
	private Persona persona = new Persona();
	private Archivo fotoUsuario = new Archivo();
	private AImage imagenUsuario;
	VMUtilidadesDeSeguridad seguridad = new VMUtilidadesDeSeguridad();
	MensajesAlUsuario mensajesAlusuario = new MensajesAlUsuario(); //para llamar a los diferentes mensajes de dialogo
	
	// Métodos Set y Get 
	public Archivo getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(Archivo fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	
	public AImage getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(AImage imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	//Fin Métodos Set y Get
	
	/**
	* Init. Código de inicialización.
	* @param Ninguno.
	* @return Objetos inicializados.
	* @throws No dispara ninguna excepción.
	*
	*/
	
	@Init
	public void init() {
		// initialization code
		buscarUsuario();
	}
	
	/**
	* actualizarFotoPerfilUsuario
	*
	* @param Ninguno
	* @return Actualiza la foto de perfil del usuario cuando éste la cambia con sesión activa.
	* @throws No dispara ninguna excepción.
	*/
	@GlobalCommand
    public void actualizarFotoPerfilUsuario(){
    	BindUtils.postGlobalCommand(null, null, "cargarFotoImagen", null);
    }
	
	/**
	* Buscar Usuario. Inicializa el código.
	*
	* @param Ninguno
	* @return Busca el usuario que ha iniciado sesión en la tabla de usuarios, el command 
	* indica a las variables el cambio que se hará en el objeto.
	* @throws No dispara ninguna excepcion.
	*/
	
	@Command
	@NotifyChange({"correo","fechaCreacion","nombre","apellido","telefono","fotoUsuario","imagenUsuario"})
	public void buscarUsuario() {
		Persona persona = serviciopersona.buscarPersonaNombreUsuario(seguridad.getUsuario().getUsername());
		this.persona = persona;
		this.cedula = persona.getCedulaPersona();
		this.correo = persona.getUsuario().getCorreo();
		this.fechaCreacion = persona.getUsuario().getFechaCreacion();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.telefono = persona.getTelefono();
		try {
			imagenUsuario = persona.getUsuario().getFoto().getAImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	/** Guarda la información de perfil del usuario.
	 * @param ninguno.
	 * @return Guarda el perfil del usuario, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws Dispara un excepción si no existe la ruta de la foto de perfil por defecto.
	 */
	
	@Command
	@NotifyChange({"imagenUsuario","nombre","apellido", "correo","telefono"})
	public void guardarPerfilUsuario() {
		Usuario usuario = new Usuario();
		usuario = this.persona.getUsuario();
		if(nombre.equals("") || apellido.equals("") || correo.equals("") || telefono.equals("")){
			mensajesAlusuario.advertenciaLlenarCampos();
		}
		else if(!mensajesAlusuario.errorValidarCorreo(correo)){}
		else
		{
			this.persona.setNombre(nombre);
			this.persona.setApellido(apellido);
			usuario.setNombreCompleto(this.persona.getNombre() + " " +this.persona.getApellido());
			this.persona.setTelefono(telefono);
			usuario.setCorreo(correo);
			if(imagenUsuario == null){
				try {
					imagenUsuario = new AImage(ruta+"/Sigarep.webapp/WebContent/imagenes/iconos/usuario.png");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			fotoUsuario.setNombreArchivo(imagenUsuario.getName());
			fotoUsuario.setTipo(imagenUsuario.getContentType());
			fotoUsuario.setContenidoArchivo(imagenUsuario.getByteData());
			usuario.setFoto(fotoUsuario);
			serviciopersona.guardar(this.persona);
			serviciousuario.guardarUsuario(usuario);
			mensajesAlusuario.informacionRegistroCorrecto();
		}	
	}
	
	/** Elimina la imagen de perfil del usuario.
	 * @param ninguno.
	 * @return Elimina la imagen del usuario, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange("imagenUsuario")
	public void removeImage() {
		imagenUsuario = null;
	}

	/**
	* Carga de la imágen del usuario. Permite la carga de imágen de perfil del usuario,
	* utiliza Archivo del paquete herramientas.
	* @param @ContextParam(ContextType.BIND_CONTEXT) BindContext ctx
	* @return ninguno.
	* @throws No dispara ninguna excepción.
	*
	*/
	
	@Command
	@NotifyChange({"imagenUsuario","fotoUsuario"})
	public void upload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			fotoUsuario.setNombreArchivo(media.getName());
			fotoUsuario.setTipo(media.getContentType());
			fotoUsuario.setContenidoArchivo(media.getByteData());
			int tamanhoImagen = media.getByteData().length;
			int ancho = 500;
			if (media instanceof Image) {
				if (tamanhoImagen > ancho * 1024) {
					mensajesAlusuario.advertenciaTamannoImagen(500);
				} else {
					imagenUsuario = (AImage) media;// Initialize the bind object to show image in zul page and Notify it also
				}
			} else {
				mensajesAlusuario.advertenciaCargarImagen();
			}
		}
	}
	
	/** Limpiar todos los campos de texto de información del usuario
	 * @param ninguno.
	 * @return ninguno, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	@NotifyChange({ "imagenUsuario", "nombre", "apellido", "correo", "telefono" })
	public void limpiarPerfilUsuario() {
		nombre = "";
		apellido = "";
		telefono =  "";
		correo = "";
		if(persona.getUsuario().getFoto()!=null)
		try {
			imagenUsuario = this.persona.getUsuario().getFoto().getAImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Cerrar Ventana
	 * @param ventana
	 * @return cierra el EditarPerfilUsuario.zul asociado al VMEditarPerfilUsuario
	 * @throws No dispara ninguna excepcion.
	*/
	
	@Command
	@NotifyChange({"nombre","apellido", "correo","telefono"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		mensajesAlusuario.confirmacionCerrarVentanaSimple(ventana,true);		
	}
}// fin VMEditarPerfilUsuario.
