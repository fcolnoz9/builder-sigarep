package sigarep.viewmodels.maestros;

import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import sigarep.herramientas.EnviarCorreo;
import sigarep.modelos.data.maestros.Usuario;
import sigarep.modelos.servicio.maestros.ServicioUsuario;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMusuario {

	@WireVariable 
	ServicioUsuario serviciousuario;

	private String nombreUsuario;
	private String contrasenia;
	private String confirmarcontrasenia;
	private String correo;
	private Boolean estatus;
	private Date fechaCreacion;
	private Date fechaEliminacion;
	@WireVariable
	private String correoLogin;
	
	

	private List<Usuario> listaUsuario;
	private Usuario usuarioSeleccionado;

	// Metodos GETS Y SETS
	

	public String getConfirmarcontrasenia() {
		return confirmarcontrasenia;
	}

	public void setConfirmarcontrasenia(String confirmarcontrasenia) {
		this.confirmarcontrasenia = confirmarcontrasenia;
	}

	// Fin de los metodos gets y sets
	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	public String getCorreoLogin() {
		return correoLogin;
	}

	public void setCorreoLogin(String correoLogin) {
		this.correoLogin = correoLogin;
	}

	// OTROS METODOS
	// Metodos que perimite guardar una Actividad
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia","confirmarcontrasenia", "correo",
			"listaUsuario" })
	public void guardarUsuario() {
		if (nombreUsuario.equals("") || contrasenia.equals("")|| correo.equals("")) {
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			//Actividad actividad = new Actividad(id_actividad, nombre,
			//		descripcion, imagen, true);
			//servicioactividad.guardar(actividad);
			Date fecha= new Date();
			Usuario usuario = new Usuario(nombreUsuario, contrasenia, correo, true, fecha);
			serviciousuario.guardar(usuario);
			Messagebox.show("Se ha Registrado Correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	@Init
	public void init() {
		// initialization code
		buscarUsuario();
	}

	// Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarUsuario() {
		listaUsuario = serviciousuario.buscarUsuario(nombreUsuario);
	}

	// Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia", "confirmarcontrasenia","correo" })
	public void limpiar() {
		nombreUsuario = "";
		contrasenia = "";
		confirmarcontrasenia = "";
		correo = "";
		buscarUsuario();
	}

	// Metodo que elimina una actividad tomando en cuenta el idActividad
	@Command
	@NotifyChange({ "listaUsuario" })
	public void eliminarUsuario() {
		serviciousuario.eliminar(getUsuarioSeleccionado().getNombreUsuario());
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
	}

	// permite tomar los datos del objeto usaurioseleccionado
	@Command
	@NotifyChange({ "nombreUsuario", "fechaCreacion","correo" })
	public void mostrarSeleccionado() {
		nombreUsuario = getUsuarioSeleccionado().getNombreUsuario();
		fechaCreacion = getUsuarioSeleccionado().getFechaCreacion();
		correo = getUsuarioSeleccionado().getCorreo();
		
	}
	@Command
	@NotifyChange({ "listaUsuario" })
	public void pasepase() {
		System.out.println("pase");
	}
	
	@Command
	@NotifyChange({ "correoLogin" })
	public void recuperarContrasenna() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("-1");
		if (correoLogin=="")
			Messagebox.show("Debe llenar los campos", "Información",Messagebox.OK, Messagebox.EXCLAMATION);
		else {
			List<Usuario> listaUsuarios = serviciousuario.listadoUsuario();
				Usuario usuarioAux = new Usuario();
				for (int i = 0; i < listaUsuarios.size(); i++) {
					usuarioAux = listaUsuarios.get(i);
					if (usuarioAux.getCorreo() != null) 
						if (usuarioAux.getCorreo().equals(correoLogin) || usuarioAux.getNombreUsuario().equals(correoLogin)){
							usuario = usuarioAux;
						}
				}
				if (usuario.getNombreUsuario()!="-1") {
					EnviarCorreo enviar = new EnviarCorreo();
					enviar.sendEmail(usuario.getCorreo(), usuario.getContrasenia());
					Messagebox.show("Te hemos enviado un email con tu contraseña.","Información", Messagebox.OK, Messagebox.INFORMATION);
				}
				else
					Messagebox.show("Usuario o correo e-mail no registrados","Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
}
