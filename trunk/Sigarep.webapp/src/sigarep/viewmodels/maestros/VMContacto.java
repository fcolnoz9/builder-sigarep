package sigarep.viewmodels.maestros;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ContactoSigarep;
import sigarep.modelos.servicio.maestros.ServicioContactoSigarep;

/**
 * Clase VMContacto: Clase ViewModels 
relacionada con el Maestro Contacto. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 19/12/2013
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMContacto {
	// -----------------Servicios----------------------------
	@WireVariable
	ServicioContactoSigarep serviciocontactosigarep;
	// -----------------Variables contactoSigarep -----------
	private Integer id_contacto;
	private String quienesSomos = "";
	private String twitter = "";
	private String facebook = "";
	private String telefonoContacto = "";
	private String direccion = "";
	private String nombreEmisor = "";
	private String correoEmisor = "";
	private String telefonoEmisor = "";
	private String consulta = "";
	private String correoContacto = "";
	private String claveCorreoContacto = "";
	private String servidorEntrantePop3 = "";
	private String puertoEntradaPop3 = "";
	private String servidorSalidaSmtp = "";
	private String puertoSalidaSmtp = "";

	// -----------------Variables Objeto----------------------
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private ContactoSigarep contactoSigarep;

	// Métodos Set y Get
	public String getQuienesSomos() {
		return quienesSomos;
	}

	public void setQuienesSomos(String quienesSomos) {
		this.quienesSomos = quienesSomos;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefono) {
		this.telefonoContacto = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getCorreoEmisor() {
		return correoEmisor;
	}

	public void setCorreoEmisor(String correoEmisor) {
		this.correoEmisor = correoEmisor;
	}

	public String getTelefonoEmisor() {
		return telefonoEmisor;
	}

	public void setTelefonoEmisor(String telefonoEmisor) {
		this.telefonoEmisor = telefonoEmisor;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getClaveCorreoContacto() {
		return claveCorreoContacto;
	}

	public void setClaveCorreoContacto(String claveCorreoContacto) {
		this.claveCorreoContacto = claveCorreoContacto;
	}

	public String getServidorEntrantePop3() {
		return servidorEntrantePop3;
	}

	public void setServidorEntrantePop3(String servidorEntrantePop3) {
		this.servidorEntrantePop3 = servidorEntrantePop3;
	}

	public String getPuertoEntradaPop3() {
		return puertoEntradaPop3;
	}

	public void setPuertoEntradaPop3(String puertoEntradaPop3) {
		this.puertoEntradaPop3 = puertoEntradaPop3;
	}

	public String getServidorSalidaSmtp() {
		return servidorSalidaSmtp;
	}

	public void setServidorSalidaSmtp(String servidorSalidaSmtp) {
		this.servidorSalidaSmtp = servidorSalidaSmtp;
	}

	public String getPuertoSalidaSmtp() {
		return puertoSalidaSmtp;
	}

	public void setPuertoSalidaSmtp(String puertoSalidaSmtp) {
		this.puertoSalidaSmtp = puertoSalidaSmtp;
	}

	// Fin Métodos Set y Get

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param Ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		cargarContacto();
	}

	/**
	 * guardarContacto : Guarda el registro completo, el command indica a las variables el
	 * cambio que se hará en el objeto.
	 * 
	 * @param Ninguno
	 * @return 
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "twitter", "facebook",
			"telefonoContacto", "direccion", "claveCorreoContacto",
			"servidorEntrantePop3", "puertoEntradaPop3", "servidorSalidaSmtp",
			"puertoSalidaSmtp" })
	public void guardarContacto() {
		if (correoContacto == null || correoContacto.equals("")) {
			mensajeAlUsuario.advertenciaIngresarCorreo();
		} else {
			ContactoSigarep contactoSigarep = new ContactoSigarep(id_contacto,
					quienesSomos, correoContacto, twitter, facebook,
					telefonoContacto, direccion, claveCorreoContacto,
					servidorEntrantePop3, servidorSalidaSmtp,
					puertoEntradaPop3, puertoSalidaSmtp, true);
			serviciocontactosigarep.guardar(contactoSigarep);
			mensajeAlUsuario.informacionRegistroCorrecto();
		}
	}

	/**
	 * cargarContacto
	 * 
	 * @param Ninguno
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "twitter", "facebook",
			"telefonoContacto", "direccion", "nombreEmisor", "telefonoEmisor",
			"correoEmisor", "consulta", "claveCorreoContacto",
			"servidorEntrantePop3", "puertoEntradaPop3", "servidorSalidaSmtp",
			"puertoSalidaSmtp" })
	public void cargarContacto() {
		if (serviciocontactosigarep.buscarContactoSigarep().size() > 0 ) {
			contactoSigarep = serviciocontactosigarep.buscarContactoSigarep().get(0);
			id_contacto = contactoSigarep.getIdContacto();
			quienesSomos = contactoSigarep.getQuienesSomos();
			correoContacto = contactoSigarep.getCorreoContacto();
			twitter = contactoSigarep.getTwitter();
			facebook = contactoSigarep.getFacebook();
			telefonoContacto = contactoSigarep.getTelefonoContacto();
			direccion = contactoSigarep.getDireccionContacto();
			claveCorreoContacto = contactoSigarep.getClaveCorreo();
			servidorEntrantePop3 = contactoSigarep.getServidorEntrantePop3();
			puertoEntradaPop3 = contactoSigarep.getPuertoEntradaPop3();
			servidorSalidaSmtp = contactoSigarep.getServidorSalidaSmtp();
			puertoSalidaSmtp = contactoSigarep.getPuertoSalidaSmtp();
		}
	}

	/**
	 * enviarCorreoContactanos.
	 * 
	 * @param Ninguno
	 * @return envía el correo con el mensaje/consulta al sistema.
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({ "correoEmisor", "nombreEmisor", "telefonoEmisor",
			"consulta" })
	public void enviarCorreoContactanos() {
		if (correoEmisor == null || nombreEmisor == null
				|| telefonoEmisor == null || consulta == null) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} 
		else if(!mensajeAlUsuario.errorValidarCorreo(correoEmisor)){}
		else {			
			serviciocontactosigarep.sendEmailContactanos(correoEmisor, nombreEmisor,telefonoEmisor, consulta);
			mensajeAlUsuario.informacionCorreoEnviado();
			limpiar();
		}
	}

	/**
	 * limpiar
	 * 
	 * @param Ninguno
	 * @return Limpiar cada una de las cajas de texto de la vista
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "twitter", "facebook",
			"telefonoContacto", "direccion", "nombreEmisor", "telefonoEmisor",
			"correoEmisor", "consulta", "claveCorreoContacto",
			"servidorEntrantePop3", "puertoEntradaPop3", "servidorSalidaSmtp",
			"puertoSalidaSmtp" })
	public void limpiar() {
		quienesSomos = null;
		correoContacto = null;
		twitter = null;
		facebook = null;
		telefonoContacto = null;
		direccion = null;
		nombreEmisor = null;
		telefonoEmisor = null;
		correoEmisor = null;
		consulta = null;
		claveCorreoContacto = null;
		servidorEntrantePop3 = null;
		puertoEntradaPop3 = null;
		servidorSalidaSmtp = null;
		puertoSalidaSmtp = null;
	}

	/**
	 * limpiarContactanos
	 * 
	 * @param Ninguno
	 * @return Limpiar cada una de las cajas de texto de la vista
	 * @throws No dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "nombreEmisor", "telefonoEmisor", "correoEmisor",
			"consulta" })
	public void limpiarContactanos() {
		nombreEmisor = null;
		telefonoEmisor = null;
		correoEmisor = null;
		consulta = null;
	}

	/**
	 * Cerrar Ventana :  Cierra el .zul 
asociado al VM. 
	 * 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "direccion", "twitter",
			"facebook", "telefonoContacto" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana) {
		boolean condicion = false;
		if (quienesSomos != null || correoContacto != null || twitter != null
				|| facebook != null || telefonoContacto != null
				|| direccion != null || nombreEmisor != null
				|| telefonoEmisor != null || correoEmisor != null
				|| consulta != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);
	}

}
