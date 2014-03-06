package sigarep.viewmodels.maestros;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.EnviarCorreo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ContactoSigarep;
import sigarep.modelos.servicio.maestros.ServicioContactoSigarep;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMContacto {

	@WireVariable
	ServicioContactoSigarep serviciocontactosigarep;
	private Integer id_contacto;
	private String quienesSomos;
	private String correoContacto;
	private String twitter;
	private String facebook;
	private String telefonoContacto;
	private String direccion;
	private String nombreEmisor;
	private String correoEmisor;
	private String telefonoEmisor;
	private String consulta;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private ContactoSigarep contactoSigarep;

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

	@Init
	public void init() {
		cargarContacto();
	}

	/**
	 * cargarContacto
	 * 
	 * @param quienesSomos
	 *            , correoContacto, twitter, facebook, telefono, direccion
	 * @return No devuelve ningun valor
	 * @throws Debe
	 *             introducir un correo
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "direccion", "twitter",
			"facebook", "telefonoContacto" })
	public void cargarContacto() {
		if (serviciocontactosigarep.buscarContactoSigarep().size() > 0) {
			contactoSigarep = serviciocontactosigarep.buscarContactoSigarep().get(0);
			id_contacto = contactoSigarep.getIdContacto();
			quienesSomos = contactoSigarep.getQuienesSomos();
			correoContacto = contactoSigarep.getCorreoContacto();
			twitter = contactoSigarep.getTwitter();
			facebook = contactoSigarep.getFacebook();
			telefonoContacto = contactoSigarep.getTelefonoContacto();
			direccion = contactoSigarep.getDireccionContacto();
		}
	}

	/**
	 * guardarContacto
	 * 
	 * @param quienesSomos
	 *            , correoContacto, twitter, facebook, telefono, direccion
	 * @return No devuelve ningun valor
	 * @throws Debe
	 *             introducir un correo
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "direccion", "twitter",
			"facebook", "telefonoContacto" })
	public void guardarContacto() {
		if (correoContacto == null || correoContacto.equals("")) {
			mensajeAlUsuario.advertenciaIngresarCorreo();
		} else {
			ContactoSigarep contactoSigarep = new ContactoSigarep(id_contacto,
					quienesSomos, correoContacto, twitter, facebook,
					telefonoContacto, direccion);
			serviciocontactosigarep.guardar(contactoSigarep);
			mensajeAlUsuario.informacionRegistroCorrecto();
		}
	}

	/**
	 * limpiar
	 * 
	 * @param quienesSomos
	 *            , correoContacto, twitter, facebook, telefono, direccion
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "twitter", "facebook",
			"telefono", "direccion", "nombreEmisor", "telefonoEmisor",
			"correoEmisor", "consulta" })
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
	}

	/**
	 * limpiarContactanos
	 * 
	 * @param nombreEmisor
	 *            , telefonoEmisor, correoEmisor, consulta
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
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
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "quienesSomos", "correoContacto", "direccion", "twitter",
			"facebook", "telefono" })
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

	/**
	 * enviarCorreoContactanos.
	 * 
	 * @param Ninguno
	 * @return envía el correo con el mensaje/consulta al sistema.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({ "correo", "nombre", "telefono", "consulta" })
	public void enviarCorreoContactanos() {
		EnviarCorreo enviar = new EnviarCorreo();
		enviar.sendEmailContactanos(correoEmisor, nombreEmisor, telefonoEmisor,
				consulta);
		mensajeAlUsuario.informacionCorreoEnviado();
		limpiar();
	}
}
