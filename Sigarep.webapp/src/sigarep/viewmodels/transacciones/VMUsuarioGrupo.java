package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelArray;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.Usuario;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioUsuarioGrupo;
import sigarep.modelos.servicio.maestros.ServicioGrupo;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioUsuario;

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
	private ServicioGrupo serviciogrupo;
	@WireVariable
	private ServicioUsuario serviciousuario;
	@WireVariable
	private ServicioUsuarioGrupo serviciousuariogrupo;
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
		 System.out.println("plop");
	}
	
	@Command
	@NotifyChange({"nombreGrupo","descripcionGrupo"})
	public void mostrarGrupoSeleccionado(){
		 Grupo miGrupo = getGrupoSeleccionado();
		 nombreGrupo = miGrupo.getNombre();
		 descripcionGrupo = miGrupo.getDescripcion();
		 System.out.println("plop2");
	}
	
//	@Command
//	@NotifyChange({"cedula", "primerNombre", "primerApellido", "sancionMaestro", "lapsoAcademico", "listaSancionados"})
//	public void buscarUsuariosGrupos(){
//		listaSancionados = servicioestudiantesancionado.buscarTodos();
//	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarUsuario() {
		listaUsuario = serviciousuario.listadoUsuario();
	}

	// Metodo que busca las sanciones y las carga en el combobox de sanciones
	@Command
	@NotifyChange({ "listaGrupo" })
	public void buscarGrupo() {
		listaGrupo = serviciogrupo.listadoGrupo();
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
//	 
//	 @Command
//	 @NotifyChange({"cedula" ,"primerNombre" ,"segundoNombre", "primerApellido", "segundoApellido"
//		  ,"sexo" ,"fechaNacimiento" ,"telefono" ,"email" ,"annoIngreso", "nombreSancion", "programa"
//		 })
//	 public void buscarEstudiante(){
//		 if (cedula.equals(""))
//				Messagebox.show("Debe ingresar una cedula", "Advertencia",
//						Messagebox.OK, Messagebox.EXCLAMATION);
//		 else {
//			 estudiante = new Estudiante();
//			 estudiante = servicioestudiante.buscarEstudiante(cedula);
//			 primerNombre = estudiante.getPrimerNombre();
//			 segundoNombre = estudiante.getSegundoNombre();
//			 primerApellido = estudiante.getPrimerApellido();
//			 segundoApellido = estudiante.getSegundoApellido();
//			 sexo = estudiante.getSexo();
//			 fechaNacimiento = estudiante.getFechaNacimiento();
//			 telefono = estudiante.getTelefono();
//			 email = estudiante.getEmail();
//			 annoIngreso = estudiante.getAnioIngreso();
//             programa = estudiante.getProgramaAcademico().getNombrePrograma();
//		 }		 
//	 }
//
//	 	@Command
//		 @NotifyChange({"cedula", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
//			  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
//			  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
//			  , "semestre", "lapsosAcademicosRP","listaSancionados"})
//		public void eliminarEstudianteSancionado(){
//		  if (cedula == null)
//			Messagebox.show(
//					"Cedula de estudiante no encontrada, no se pudo eliminar",
//					"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//		  else {
//			try {
//				servicioestudiantesancionado.eliminar(estudianteSeleccionado.getId());
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			msjs.informacionEliminarCorrecto();
//			limpiar();
//		  }
//		}
	 
	 @Command
	 @NotifyChange({"nombreGrupo", "nombreUsuario", "descripcionGrupo","listaUsuario","listaGrupo"})
	public void limpiar() {
		 nombreGrupo ="";
		 nombreUsuario = "";
		 descripcionGrupo ="";
		//buscarSancionados();
	}
	
}