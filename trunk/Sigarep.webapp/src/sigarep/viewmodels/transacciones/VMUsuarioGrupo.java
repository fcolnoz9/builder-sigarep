package sigarep.viewmodels.transacciones;

import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

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
	private List<Grupo> listaGrupo = new LinkedList<Grupo>();
	@WireVariable
	private ServicioGrupo sg;
	@WireVariable
	private ServicioUsuario su;
	@WireVariable
	private ServicioUsuarioGrupo serviciousuariogrupo;
	@WireVariable
	ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private String nombreUsuario;
	@WireVariable
	private String nombreGrupo;
	@WireVariable
	private String descripcionGrupo;
	
	UsuarioGrupoPK usuarioGrupoPK = new UsuarioGrupoPK();
	UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo

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

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Grupo> getListaGrupo() {
		return listaGrupo;
	}

	public void setListaGrupo(List<Grupo> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}

	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}

	public UsuarioGrupoPK getUsuarioGrupoPK() {
		return usuarioGrupoPK;
	}

	public void setUsuarioGrupoPK(UsuarioGrupoPK usuarioGrupoPK) {
		this.usuarioGrupoPK = usuarioGrupoPK;
	}

	public UsuarioGrupo getUsuarioGrupo() {
		return usuarioGrupo;
	}

	public void setUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		this.usuarioGrupo = usuarioGrupo;
	}

	@Init
	public void init() {
		// initialization code
		buscarUsuario();
		buscarGrupo();
	}
	
	@Command
	@NotifyChange({"nombreUsuario"})
	public void mostrarUsuarioSeleccionado(){
		 Usuario miUsuario = getUsuarioSeleccionado();
		 nombreUsuario = miUsuario.getNombreUsuario();
	}
	
	@Command
	@NotifyChange({"nombreGrupo","descripcionGrupo"})
	public void mostrarGrupoSeleccionado(){
		 Grupo miGrupo = getGrupoSeleccionado();
		 nombreGrupo = miGrupo.getNombre();
		 descripcionGrupo = miGrupo.getDescripcion();
	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarUsuario() {
		listaUsuario = su.listadoUsuario();
	}

	// Metodo que busca las sanciones y las carga en el combobox de sanciones
	@Command
	@NotifyChange({ "listaGrupo" })
	public void buscarGrupo() {
		listaGrupo = sg.listadoGrupo();
	}

	@Command
	 @NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionados"
		  ,"segundoApellido", "sexo","programa"})
	public void registrarUsuarioGrupo() {
		if(nombreUsuario.equals("") || nombreGrupo.equals("") || descripcionGrupo.equals(""))
				msjs.advertenciaLlenarCampos();
		else {
			usuarioGrupoPK.setIdGrupo(getGrupoSeleccionado().getIdGrupo());
			usuarioGrupoPK.setNombreUsuario(nombreUsuario);

			usuarioGrupo.setId(usuarioGrupoPK);
			usuarioGrupo.setUsuario(getUsuarioSeleccionado());
			usuarioGrupo.setGrupo(getGrupoSeleccionado());
			usuarioGrupo.setEstatus(true);
			try {
				serviciousuariogrupo.guardar(usuarioGrupo);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			msjs.informacionRegistroCorrecto();
			limpiar();
		}
	}
	 
	 @Command
	 @NotifyChange({"nombreGrupo", "nombreUsuario", "descripcionGrupo","listaUsuario","listaGrupo"})
	public void limpiar() {
		 nombreGrupo ="";
		 nombreUsuario = "";
		 descripcionGrupo ="";
		//buscarSancionados();
	}
	
}