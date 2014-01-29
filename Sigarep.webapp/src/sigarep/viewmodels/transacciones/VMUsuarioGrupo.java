package sigarep.viewmodels.transacciones;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;

import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.herramientas.mensajes;


import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioUsuarioGrupo;

//import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUsuarioGrupo {	
	@WireVariable
	private Usuario usuarioSeleccionado;
	@WireVariable
	private Grupo grupoSeleccionado;	
	@WireVariable
	private List<Usuario> listaUsuario = new LinkedList<Usuario>();
	@WireVariable
	private List<Grupo> listaGrupoPertenece = new LinkedList<Grupo>();
	@WireVariable
	private List<Grupo> listaGrupoNoPertenece = new LinkedList<Grupo>();
	@WireVariable
	private ServicioGrupo sg;
	@WireVariable
	private ServicioUsuario su;
	@WireVariable
	private ServicioUsuarioGrupo serviciousuariogrupo;
	@WireVariable
	ServicioSolicitudApelacion serviciosolicitudapelacion;
	@Wire
	private String nombreUsuario="";
	
	
	UsuarioGrupoPK usuarioGrupoPK = new UsuarioGrupoPK();
	UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	
	@WireVariable 
	private List<UsuarioGrupo> listadoUsuarioGrupo = new LinkedList<UsuarioGrupo>();
	@WireVariable 
	private List<UsuarioGrupo> listadoUsuarioGrupoNO = new LinkedList<UsuarioGrupo>();
	
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

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
	
	public List<UsuarioGrupo> getListadoUsuarioGrupo() {
		return listadoUsuarioGrupo;
	}

	public void setListadoUsuarioGrupo(List<UsuarioGrupo> listadoUsuarioGrupo) {
		this.listadoUsuarioGrupo = listadoUsuarioGrupo;
	}

	public List<UsuarioGrupo> getListadoUsuarioGrupoNO() {
		return listadoUsuarioGrupoNO;
	}

	public void setListadoUsuarioGrupoNO(List<UsuarioGrupo> listadoUsuarioGrupoNO) {
		this.listadoUsuarioGrupoNO = listadoUsuarioGrupoNO;
	}
	
	public List<Grupo> getListaGrupoPertenece() {
		return listaGrupoPertenece;
	}

	public void setListaGrupoPertenece(List<Grupo> listaGrupoPertenece) {
		this.listaGrupoPertenece = listaGrupoPertenece;
	}
	
	public List<Grupo> getListaGrupoNoPertenece() {
		return listaGrupoNoPertenece;
	}

	public void setListaGrupoNoPertenece(List<Grupo> listaGrupoNoPertenece) {
		this.listaGrupoNoPertenece = listaGrupoNoPertenece;
	}

	@Init
	public void init() {
		// initialization code
		buscarUsuario();
	}
	
	@Command
	@NotifyChange({"nombreUsuario","listaGrupoPertenece", "listaGrupoNoPertenece" })
	public void mostrarUsuarioSeleccionado(){
		listaGrupoPertenece = sg.listadoGrupoPerteneceUsuario(usuarioSeleccionado.getNombreUsuario());
		listaGrupoNoPertenece = sg.listadoGrupoNoPerteneceUsuario(usuarioSeleccionado.getNombreUsuario());
		System.out.println(listaGrupoPertenece.size()+"tamañoSI");
		System.out.println(listaGrupoNoPertenece.size()+"tamañoNO");
	}
	
	@Command
	@NotifyChange({"nombreGrupo","descripcionGrupo"})
	public void mostrarGrupoSeleccionado(){
		
	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarUsuario() {
		listaUsuario = su.listadoUsuario();
		System.out.println(listaUsuario.size());
	}

	// Metodo que busca las sanciones y las carga en el combobox de sanciones
	@Command
	@NotifyChange({ "listaGrupo" })
	public void buscarGrupo() {
		//listaGrupo = sg.listadoGrupo();
	}

	@Command
	 @NotifyChange({})
	public void registrarUsuarioGrupo() {
//		if(nombreUsuario.equals("") || nombreGrupo.equals("") || descripcionGrupo.equals(""))
//				msjs.advertenciaLlenarCampos();
//		else {
			//usuarioGrupoPK.setIdGrupo();
			//usuarioGrupoPK.setNombreUsuario(nombreUsuario);

			//usuarioGrupo.setId(usuarioGrupoPK);
			//usuarioGrupo.setUsuario();
			//usuarioGrupo.setGrupo();
			//usuarioGrupo.setEstatus(true);
//			try {
//				serviciousuariogrupo.guardar(usuarioGrupo);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			msjs.informacionRegistroCorrecto();
//			limpiar();
//		}
	}
	 
	 @Command
	 @NotifyChange({"nombreGrupo", "nombreUsuario", "descripcionGrupo","listaUsuario","listaGrupo"})
	public void limpiar() {
		
		//buscarSancionados();
	}
	
}