package sigarep.viewmodels.transacciones;

import java.util.LinkedList;
import java.util.List;
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
	private List<Grupo> listaGrupo = new LinkedList<Grupo>();
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
	
	@Wire private List<UsuarioGrupo> listadoUsuarioGrupo;
	@Wire private List<UsuarioGrupo> listadoUsuarioGrupoNO;
	
	
	

	@Init
	public void init() {
		// initialization code
		buscarUsuario();
		
	}
	
	@Command
	@NotifyChange({"nombreUsuario","listadoUsuarioGrupo","listadoUsuarioGrupo"})
	public void mostrarUsuarioSeleccionado(){
		//listadoUsuarioGrupo=serviciousuariogrupo.buscarPorUsuario(nombreUsuario);
		//listadoUsuarioGrupoNO=serviciousuariogrupo.buscarPorUsuarioNO(nombreUsuario);
		//listadoUsuarioGrupo.get(0).getGrupo().getUsuariosGrupos();
		listadoUsuarioGrupo = usuarioSeleccionado.getUsuariosGrupos();
		System.out.println(listadoUsuarioGrupo.size()+"tamaño");
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