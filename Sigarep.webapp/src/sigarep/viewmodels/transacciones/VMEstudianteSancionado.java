package sigarep.viewmodels.transacciones;

import java.util.Date;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zhtml.Messagebox;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;

import org.zkoss.zul.Intbox;

import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Window;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.InstanciaApeladaFiltros;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.maestros.TipoMotivoFiltros;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoFiltros;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;

import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;

//import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudianteSancionado {
	@WireVariable
	private String cedula;
	@WireVariable
	private Estudiante estudiante;
	@WireVariable
	private String primerNombre;
	@WireVariable
	private String segundoNombre;
	@WireVariable
	private String primerApellido;
	@WireVariable
	private String segundoApellido;
	@WireVariable
	private String sexo;
	@WireVariable
	private Date fechaNacimiento;
	@WireVariable
	private String telefono;
	@WireVariable
	private String sancion;
	@WireVariable
	private String lapso;
	@WireVariable
	private String email;
	@WireVariable
	private LapsoAcademico lapsoAcademico;
	@WireVariable
	private String nombrePrograma;
	
	

	private mensajes mensajeAlUsuario = new mensajes();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioEstudiante servicioestudiante;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private String nombreSancion;
	@WireVariable
	private Date annoIngreso;
	@WireVariable
	private float indiceGrado;
	@WireVariable
	private String lapsosAcademicosRP;
	@WireVariable
	private Integer unidadesCursadas;
	@WireVariable
	private Integer unidadesAprobadas;
	@WireVariable
	private SancionMaestro sancionMaestro;
	@WireVariable
	private Integer semestre;
	@WireVariable
	private String programa;

	@Wire
	private Textbox txtCedula;
	@Wire
	private Textbox txtPrimerNombre;
	@Wire
	private Textbox txtSegundoNombre;
	@Wire
	private Textbox txtPrimerApellido;
	@Wire
	private Textbox txtSegundoApellido;
	@Wire
	private Textbox txtTelefono;
	@Wire
	private Textbox txtEmail;
	@Wire
	private Intbox intAnnoIngreso;
	@Wire
	private Textbox txtIndiceGrado;
	@Wire
	private Textbox txtLapsoAcademico;
	@Wire
	private Textbox txtUnidadCursadas;
	@Wire
	private Textbox txtUnidadAprobadas;
	@Wire
	private Textbox txtLapsosAcademicosRP;
	@Wire
	private Textbox txtPeriodoSancion;
	@Wire
	private Textbox txtSexo;
	@Wire
	private Textbox txtPrograma;
	@Wire
	private Combobox cmbLapso;
	@Wire
	private Combobox cmbSancion;
	@Wire
	private Datebox dtbFechaNacimiento;
	@Wire
	private Datebox	dtbAnnoIngreso;
	
	private List<ProgramaAcademico> listaPrograma;
	private List<LapsoAcademico> listaLapso;
	private List<SancionMaestro> listaSancion;
	private EstudianteSancionado estudianteSeleccionado;
	private List<EstudianteSancionado> listaSancionados = new LinkedList<EstudianteSancionado>();
	private List<EstudianteSancionado> listaEstudianteSancionado;

	private EstudianteSancionadoFiltros filtros = new EstudianteSancionadoFiltros();
	EstudianteSancionadoPK estudianteSancionadoPK = new EstudianteSancionadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	private List<EstudianteSancionado> lista = new LinkedList<EstudianteSancionado>();
	@Wire
	private Datebox dbfecha;
	@Wire
	private Radiogroup rgEleccion;
	@Wire
	private Radio rbTodos;
	@Wire
	private Radio rbSolicitud;
	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	

	 @NotifyChange({ "filtros" })
	    public EstudianteSancionadoFiltros getFiltros() {
			return filtros;
		}

		public void setFiltros(EstudianteSancionadoFiltros filtros) {
			this.filtros = filtros;
		}

	
	public List<EstudianteSancionado> getListaEstudianteSancionado() {
		return listaEstudianteSancionado;
	}

	public void setListaEstudianteSancionado(List<EstudianteSancionado> listaEstudianteSancionado) {
		this.listaEstudianteSancionado = listaEstudianteSancionado;
	}
	


	public String getPrimerNombre() {
		return primerNombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public Textbox getTxtPrograma() {
		return txtPrograma;
	}

	public void setTxtPrograma(Textbox txtPrograma) {
		this.txtPrograma = txtPrograma;
	}

	public Date getAnnoIngreso() {
		return annoIngreso;
	}

	public void setAnnoIngreso(Date annoIngreso) {
		this.annoIngreso = annoIngreso;
	}

	public float getIndiceGrado() {
		return indiceGrado;
	}

	public void setIndiceGrado(float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}

	public String getLapsosAcademicosRP() {
		return lapsosAcademicosRP;
	}

	public void setLapsosAcademicosRp(String lapsosAcademicosRP) {
		this.lapsosAcademicosRP = lapsosAcademicosRP;
	}
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Integer getUnidadesCursadas() {
		return unidadesCursadas;
	}

	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}

	public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}

	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}

	public SancionMaestro getSancionMaestro() {
		return sancionMaestro;
	}

	public void setSancionMaestro(SancionMaestro sancionMaestro) {
		this.sancionMaestro = sancionMaestro;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaLapso(List<LapsoAcademico> ListaLapso) {
		this.listaLapso = ListaLapso;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
	
	public EstudianteSancionado getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(
			EstudianteSancionado estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public Datebox getDbfecha() {
		return dbfecha;
	}

	public void setDbfecha(Datebox dbfecha) {
		this.dbfecha = dbfecha;
	}
	
	public Radiogroup getRgEleccion() {
		return rgEleccion;
	}

	public void setRgEleccion(Radiogroup rgEleccion) {
		this.rgEleccion = rgEleccion;
	}
	
	public Radio getRbTodos() {
		return rbTodos;
	}

	public void setRbTodos(Radio rbTodos) {
		this.rbTodos = rbTodos;
	}

	public Radio getRbSolicitud() {
		return rbSolicitud;
	}

	public void setRbSolicitud(Radio rbSolicitud) {
		this.rbSolicitud = rbSolicitud;
	}
	
	private String cedulaFiltro;
	private String nombreRFiltro;
	private String apellidoFiltro;
	private String sancionFiltro;
	private String lapsoFiltro;
	
	
	
	public String getCedulaFiltro() {
		return cedulaFiltro;
	}

	public void setCedulaFiltro(String cedulaFiltro) {
		this.cedulaFiltro = cedulaFiltro;
	}

	public String getNombreRFiltro() {
		return nombreRFiltro;
	}

	public void setNombreRFiltro(String nombreRFiltro) {
		this.nombreRFiltro = nombreRFiltro;
	}

	public String getApellidoFiltro() {
		return apellidoFiltro;
	}

	public void setApellidoFiltro(String apellidoFiltro) {
		this.apellidoFiltro = apellidoFiltro;
	}

	public String getSancionFiltro() {
		return sancionFiltro;
	}

	public void setSancionFiltro(String sancionFiltro) {
		this.sancionFiltro = sancionFiltro;
	}

	public String getLapsoFiltro() {
		return lapsoFiltro;
	}

	public void setLapsoFiltro(String lapsoFiltro) {
		this.lapsoFiltro = lapsoFiltro;
	}


	@Init
	public void init() {
		// initialization code
		buscarLapsoAcademico();
		buscarSancion();
		buscarSancionados();
	}
	
	public List<EstudianteSancionado> getListaSancionados() {
		return listaSancionados;
	}

	public void setListaSancionados(List<EstudianteSancionado> listaSancionados) {
		this.listaSancionados = listaSancionados;
	}
	
	@Command
	@NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "sexo"
		  ,"primerNombre","segundoNombre","primerApellido","segundoApellido","unidadesCursadas", "programa" 
		  ,"semestre","lapsosAcademicosRP","telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso"})
	public void mostrarSeleccionado(){
		 EstudianteSancionado miSanc = getEstudianteSeleccionado();
		 cedula = miSanc.getId().getCedulaEstudiante();
		 primerNombre = miSanc.getEstudiante().getPrimerNombre();
		 segundoNombre = miSanc.getEstudiante().getSegundoNombre();
		 primerApellido = miSanc.getEstudiante().getPrimerApellido();
		 segundoApellido = miSanc.getEstudiante().getSegundoApellido();
		 sexo = miSanc.getEstudiante().getSexo();
		 fechaNacimiento = miSanc.getEstudiante().getFechaNacimiento();
		 telefono = miSanc.getEstudiante().getTelefono();
		 email = miSanc.getEstudiante().getEmail();
		 annoIngreso = miSanc.getEstudiante().getAnioIngreso();
		 programa = miSanc.getEstudiante().getProgramaAcademico().getNombrePrograma();
		 indiceGrado = miSanc.getIndiceGrado();
		 lapsoAcademico = miSanc.getLapsoAcademico();
		 //estudianteSancionado.setLapsosAcademicosRp(lapsosAcademicosRP); //ni idea de esta, Yelitza y Amanda modificarán la vista
		 sancionMaestro = miSanc.getSancionMaestro();
		 unidadesAprobadas = miSanc.getUnidadesAprobadas();
		 unidadesCursadas = miSanc.getUnidadesCursadas();
		 semestre = miSanc.getSemestre();
	}
	
	@Command
	@NotifyChange({"cedula", "primerNombre", "primerApellido", "sancionMaestro", "lapsoAcademico", "listaSancionados"})
	public void buscarSancionados(){
		listaSancionados = servicioestudiantesancionado.buscarTodos();
	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapsoAcademico() {
		listaLapso = serviciolapsoacademico.listadoLapsoAcademico();
	}

	// Metodo que busca las sanciones y las carga en el combobox de sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void buscarSancion() {
		listaSancion = serviciosancionmaestro.listadoSanciones();
	}

	@Command
	 @NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionados"
		  ,"segundoApellido", "sexo","programa"})
	public void registrarEstudianteSancionado() {
		if(cedula.equals("") || primerNombre.equals("") || segundoNombre.equals("") || primerApellido.equals("")
				|| segundoApellido.equals("") || telefono.equals("") || email.equals("")
				|| sexo.equals("") || programa.equals("") 
			    || fechaNacimiento.equals("") || lapsoAcademico.equals("") || unidadesCursadas.equals("") 
			    || unidadesAprobadas.equals(null) || annoIngreso.equals("")  ||sancionMaestro.equals("")
			    || semestre.equals(""))
			mensajeAlUsuario.advertenciaLlenarCampos();
		else {
			if(getListaEstudianteSancionado().size()!=0){
				   System.out.println("ESTATUS"+getListaEstudianteSancionado().get(0).getEstatus());
				   Messagebox.show("Ya Existe un Lapso Activo", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
			   }
			   else{
				   
			estudianteSancionadoPK.setCedulaEstudiante(cedula);
			estudianteSancionadoPK.setCodigoLapso(lapsoAcademico.getCodigoLapso());
			estudianteSancionado.setId(estudianteSancionadoPK);
			estudianteSancionado.setLapsoAcademico(lapsoAcademico);
			estudianteSancionado.setEstudiante(estudiante);
			estudianteSancionado.setIndiceGrado(indiceGrado);
		 	estudianteSancionado.setSancionMaestro(sancionMaestro);
			estudianteSancionado.setUnidadesAprobadas(unidadesAprobadas);
			estudianteSancionado.setUnidadesCursadas(unidadesCursadas);
			estudianteSancionado.setSemestre(semestre);
			estudianteSancionado.setEstatus(true);
			try {
				servicioestudiantesancionado.guardar(estudianteSancionado);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
		}
	}
	 
	 @Command
	 @NotifyChange({"cedula" ,"primerNombre" ,"segundoNombre", "primerApellido", "segundoApellido"
		  ,"sexo" ,"fechaNacimiento" ,"telefono" ,"email" ,"annoIngreso", "nombreSancion", "programa"
		 })
	 public void buscarEstudiante(){
		 if (cedula.equals(""))
			 mensajeAlUsuario.advertenciaLlenarCampos();
				
		 else {
			 estudiante = new Estudiante();
			 estudiante = servicioestudiante.buscarEstudiante(cedula);
			 primerNombre = estudiante.getPrimerNombre();
			 segundoNombre = estudiante.getSegundoNombre();
			 primerApellido = estudiante.getPrimerApellido();
			 segundoApellido = estudiante.getSegundoApellido();
			 sexo = estudiante.getSexo();
			 fechaNacimiento = estudiante.getFechaNacimiento();
			 telefono = estudiante.getTelefono();
			 email = estudiante.getEmail();
			 annoIngreso = estudiante.getAnioIngreso();
             programa = estudiante.getProgramaAcademico().getNombrePrograma();
		 }		 
	 }

	 	@Command
		 @NotifyChange({"cedula", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
			  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
			  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
			  , "semestre", "lapsosAcademicosRP","listaSancionados"})
		public void eliminarEstudianteSancionado(){
		  if (cedula == null)
			  mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		  else {
			try {
				servicioestudiantesancionado.eliminar(estudianteSeleccionado.getId());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			msjs.informacionEliminarCorrecto();
			limpiar();
		  }
		}
	 
	 @Command
	 @NotifyChange({"cedula", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
		  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
		  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
		  , "semestre", "lapsosAcademicosRP","listaSancionados"})
	public void limpiar() {
		primerNombre = "";
		segundoNombre = "";
		primerApellido = "";
		segundoApellido = "";
		telefono = "";
		email = "";
		lapsosAcademicosRP = "";
		nombreSancion= "";
		sexo = "";
		programa = null;
		cedula = "";
		indiceGrado = 0;
		fechaNacimiento = null;
		lapsoAcademico = null;
		unidadesCursadas = null;
		unidadesAprobadas = null;
		annoIngreso = null;
		sancionMaestro = null;
		semestre = null;
		buscarSancionados();
	}
	
	@Command
	 @NotifyChange({"listaSancion"})
	public SancionMaestro objetoComboSancion() {
		return sancionMaestro;
	}
	
	@Command
	 @NotifyChange({"listaLapso"})
	public LapsoAcademico objetoComboLapso() {
		return lapsoAcademico;
	}
	
	@Command
	@NotifyChange({"cedula", "nombre", "apellido","sancion", "lapso"})
	public void showModal(){
  		Window registrar = (Window)Executions.createComponents(
        		"/WEB-INF/sigarep/vistas/transacciones/ListadoSancionInicial.zul", null, null);
		registrar.setMaximizable(true);
		registrar.doModal();	
  	}
	
	@Command
	@NotifyChange({ "listaEstudianteSancionado" })
	public void listadoEstudianteSancionado() {
		listaEstudianteSancionado = servicioestudiantesancionado.listadoEstudianteSancionado();
	}
	
//
//	 // Método que busca y filtra todos loe estudiantes sancionados
// 	@Command
// 	@NotifyChange({ "listaEstudianteSancionado" })
// 	public void filtros() {
// 		listaEstudianteSancionado = servicioestudiantesancionado.buscarEstudianteSancionado(filtros);
// 	}
	
	@Command
	@NotifyChange({"lista","cedula","nombre","apellido","sancion", "lapso"})
	public void filtros(){
		lista = servicioestudiantesancionado.buscarEstudianteSancionadofiltros(cedula, primerNombre, segundoApellido, sancion, lapso);
				}
 	
}