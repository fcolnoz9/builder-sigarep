package sigarep.viewmodels.seguridad;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.hibernate.Session;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import sigarep.herramientas.EnviarCorreo;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUsuario {

	@WireVariable
	private ServicioUsuario su;

	@WireVariable
	private ServicioGrupo sg;

	private String nombreUsuario;
	private String correo;
	private String clave;
	private String confirmarcontrasenia;
	private String nombreCompleto;
	private String estado;
	private ListModelList<Grupo> modeloGrupo;
	List<Grupo> listGrupo;
	private List<Usuario> listaUsuario;
	private Usuario usuarioSeleccionado;
	
	@WireVariable
	private String correoLogin;

	private mensajes msjs = new mensajes();
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ListModelList<Grupo> getModeloGrupo() {
		return modeloGrupo;
	}

	public void setModeloGrupo(ListModelList<Grupo> modeloGrupo) {
		this.modeloGrupo = modeloGrupo;
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

	public String getConfirmarcontrasenia() {
		return confirmarcontrasenia;
	}

	public void setConfirmarcontrasenia(String confirmarcontrasenia) {
		this.confirmarcontrasenia = confirmarcontrasenia;
	}

	public List<Grupo> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<Grupo> listGrupo) {
		this.listGrupo = listGrupo;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	@Command
	@NotifyChange({ "nombreUsuario", "clave","confirmarcontrasenia", "correo",
			"listaUsuario" })
	public void guardarUsuario() {
		if (nombreUsuario=="" || clave==""|| correo=="") {
			msjs.advertenciaLlenarCampos();
		} else {
			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setClave(clave);
			usuario.setCorreo(correo);
//			usuario.setNombreCompleto(nombreCompleto); //esto se lo debería traer de la vista, ojo JM.
			usuario.setEstatus(true);
			su.guardarUsuario(usuario);
			msjs.informacionRegistroCorrecto();
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
		listaUsuario = su.buscarUsuario(nombreUsuario);
	}

	// Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia", "confirmarcontrasenia","correo","listaUsuario"})
	public void limpiar() {
		nombreUsuario = "";
		clave = "";
		confirmarcontrasenia = "";
		correo = "";
		buscarUsuario();
	}

	// Metodo que elimina una actividad tomando en cuenta el idActividad
	@Command
	@NotifyChange({ "listaUsuario" })
	public void eliminarUsuario() {
		su.eliminar(getUsuarioSeleccionado().getNombreUsuario());
		msjs.informacionEliminarCorrecto();
		limpiar();
	}

	// permite tomar los datos del objeto usaurioseleccionado
	@Command
	@NotifyChange({ "nombreUsuario", "fechaCreacion","correo" })
	public void mostrarSeleccionado() {
		nombreUsuario = getUsuarioSeleccionado().getNombreUsuario();
		correo = getUsuarioSeleccionado().getCorreo();
		
	}
	@Command
	@NotifyChange({ "listaUsuario" })
	public void pasepase() {
		System.out.println("");
	}
	
	@Command
	@NotifyChange({ "correoLogin" })
	public void recuperarContrasenna() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("-1");
		if (correoLogin=="")
			Messagebox.show("Debe llenar los campos", "Información",Messagebox.OK, Messagebox.EXCLAMATION);
		else {
			List<Usuario> listaUsuarios = su.listadoUsuario();
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
					enviar.sendEmail(usuario.getCorreo(), usuario.getClave());
					Messagebox.show("Te hemos enviado un email con tu contraseña.","Información", Messagebox.OK, Messagebox.INFORMATION);
				}
				else
					Messagebox.show("Usuario o correo e-mail no registrados","Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
}