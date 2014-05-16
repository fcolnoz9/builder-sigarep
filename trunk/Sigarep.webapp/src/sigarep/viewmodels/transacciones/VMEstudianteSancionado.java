package sigarep.viewmodels.transacciones;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;


/**
 *Clase VMEstudianteSancionado
 *ViewModel para la interfaz RegistrarSancionado.zul
 * @author Builder
 * @version 1.0
 * @since 20/12/13
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudianteSancionado {
	 
	private String cedula;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;	
	private String segundoApellido;	
	private String sexo;	
	private Date fechaNacimiento;
	private String telefono;
	private String sancion;	
	private String lapso;	
	private String email;
	private LapsoAcademico lapsoAcademico;
	private String lapsoConsecutivo1;
	private String lapsoConsecutivo2;
	private String lbllapsoConsecutivo;
	private String nombrePrograma;
	private Date annoIngreso;
	private float indiceGrado;
	private String lapsosAcademicosRP;
	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
	private SancionMaestro sancionMaestro;
	private Asignatura asignaturas;
	private Integer semestre;
	private Integer periodoSancion;
	//Variables para los filtros
	private String cedulaFiltro="";
	private String nombreFiltro="";
	private String apellidoFiltro="";
	private String sancionFiltro="";
	private String nombreSancion;
	private ProgramaAcademico programa;
	private Asignatura asignaturaseleccionado;
	private LapsoAcademico lapsoActivo;
	private SancionMaestro sancionSeleccionada;
	private EstudianteSancionado estudianteSeleccionado;
	private AsignaturaEstudianteSancionado asignaturaestudiantesancionado;
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico; //Servicio para el Lapso Academico
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico; //Servicio para el programa academico
	@WireVariable
	private ServicioEstudiante servicioestudiante; //Servicio para el estudiante
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro; //Servicio para La sanciona maestro
	@WireVariable 
	private ServicioEstudianteSancionado servicioestudiantesancionado; //Servicio para el estudiante sancionado
	@WireVariable
	private ServicioAsignatura servicioAsignatura; //Servicio para el servicio asignatura
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion; //Servicio para el servicio asignatura
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado; //Servicio para Laasignatura estudiante sancionado
	private List<AsignaturaEstudianteSancionado> listaasignatura; //Lista de asignaturas
	private List<ProgramaAcademico> listaPrograma; //Listado de programas
	private List<SancionMaestro> listaSancion; //Listado de sanciones
	private List<EstudianteSancionado> listaEstudianteSancionado; //Listado de estudiantes sancionados
	private List<Asignatura> listaAsignaturas; //Listado de asignaturas
	private Set<AsignaturaEstudianteSancionado> listaAsignaturaListBox = new HashSet<AsignaturaEstudianteSancionado>();
	private Set<AsignaturaEstudianteSancionado> asignatura;  
	private List<EstudianteSancionado> listaSancionado; 
	EstudianteSancionadoPK estudianteSancionadoPK = new EstudianteSancionadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	
	//Métodos setters y getters
	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
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
	
	public ProgramaAcademico getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaAcademico programa) {
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

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
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
	
	public List<EstudianteSancionado> getListaSancionado() {
		return listaSancionado;
	}

	public void setListaSancionado(List<EstudianteSancionado> listaSancionado) {
		this.listaSancionado = listaSancionado;
	}
	public List<Asignatura> getlistaAsignaturas() {
		return listaAsignaturas;
	}
	
	public ServicioAsignatura getServicioAsignatura() {
		return servicioAsignatura;
	}

	public void setServicioAsignatura(ServicioAsignatura servicioAsignatura) {
		this.servicioAsignatura = servicioAsignatura;
	}

	public Asignatura getAsignaturaseleccionado() {
		return asignaturaseleccionado;
	}

	public void setAsignaturaseleccionado(Asignatura asignaturaseleccionado) {
		this.asignaturaseleccionado = asignaturaseleccionado;
	}

	public Set<AsignaturaEstudianteSancionado> getListaAsignaturaListBox() {
		return listaAsignaturaListBox;
	}

	public void setListaAsignaturaListBox(
			Set<AsignaturaEstudianteSancionado> listaAsignaturaListBox) {
		this.listaAsignaturaListBox = listaAsignaturaListBox;
	}

	public Asignatura getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(Asignatura asignaturas) {
		this.asignaturas = asignaturas;
	}

	public AsignaturaEstudianteSancionado getAsignaturaestudiantesancionado() {
		return asignaturaestudiantesancionado;
	}

	public void setAsignaturaestudiantesancionado(
			AsignaturaEstudianteSancionado asignaturaestudiantesancionado) {
		this.asignaturaestudiantesancionado = asignaturaestudiantesancionado;
	}

	public List<AsignaturaEstudianteSancionado> getListaasignatura() {
		return listaasignatura;
	}

	public void setListaasignatura(
			List<AsignaturaEstudianteSancionado> listaasignatura) {
		this.listaasignatura = listaasignatura;
	}
	public String getLapsoConsecutivo1() {
		return lapsoConsecutivo1;
	}

	public void setLapsoConsecutivo1(String lapsoConsecutivo1) {
		this.lapsoConsecutivo1 = lapsoConsecutivo1;
	}

	public String getLapsoConsecutivo2() {
		return lapsoConsecutivo2;
	}

	public void setLapsoConsecutivo2(String lapsoConsecutivo2) {
		this.lapsoConsecutivo2 = lapsoConsecutivo2;
	}
	
	public String getCedulaFiltro() {
		return cedulaFiltro;
	}

	public void setCedulaFiltro(String cedulaFiltro) {
		this.cedulaFiltro = cedulaFiltro;
	}

	public String getNombreFiltro() {
		return nombreFiltro;
	}

	public void setNombreFiltro(String nombreFiltro) {
		this.nombreFiltro = nombreFiltro;
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

	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}

	public Set<AsignaturaEstudianteSancionado> getAsignatura() {
		return asignatura;
	}

	public void SetAsignatura(Set<AsignaturaEstudianteSancionado> asignatura) {
		this.asignatura = asignatura;
	}

	public SancionMaestro getSancionSeleccionada() {
		return sancionSeleccionada;
	}

	public void setSancionSeleccionada(SancionMaestro sancionSeleccionada) {
		this.sancionSeleccionada = sancionSeleccionada;
	}

	public String getLbllapsoConsecutivo() {
		return lbllapsoConsecutivo;
	}

	public void setLbllapsoConsecutivo(String lbllapsoConsecutivo) {
		this.lbllapsoConsecutivo = lbllapsoConsecutivo;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	
	// fin del metodo get y set
	
	//Comienzo Otros Metodos
	@Wire("#winRegistrarSancionados")//para conectarse a la ventana con el ID
	Window ventana;
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			 @ContextParam(ContextType.BINDER) final Binder binder){
		Selectors.wireComponents(view, this, false);
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();
		if(lapsoActivo==null)
			mensajeAlUsuario.advertenciaLapsoAcademicoNoActivo(ventana);
		else{
			buscarLapsoAcademico();
			buscarSancion();
			buscarSancionados();
			buscarProgramaA();
		}
	}
	
	/** AgregarAsignatura
	 * @param  combo con las asignaturas, lista con asignaturas
	 * @return nada
	 */
	@Command
	@NotifyChange({ "listaAsignaturas", "listaAsignaturaListBox",
			"asignaturaseleccionado", "asignaturaLista" })
	public void agregarAsignatura(
			@BindingParam("comboitem") Combobox comboItem,
			@BindingParam("listBoxAsignaturas") Listbox listBoxAsignaturas) {
		if(comboItem.getValue().equals("")){
			mensajeAlUsuario.advertenciaIngresarAsignatura();
		}
		else {
			AsignaturaEstudianteSancionado asignaturaLista = new AsignaturaEstudianteSancionado();
			asignaturaLista.setAsignatura(asignaturaseleccionado);
			listaAsignaturas.remove(comboItem.getSelectedItem().getIndex());
			comboItem.setValue("");
			listaAsignaturaListBox.add(asignaturaLista);
		}
	}
	
	/** Seleccionar Sancion
	 * @param  no tiene ningun parametro
	 * @return 
	 */
	@Command
	@NotifyChange({ "sancionMaestro", "listaAsignaturas","programa", "listaAsignaturaListBox" })
	public void seleccionarSancion(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("comboAsignaturas") Combobox comboAsignaturas) {
		if (sancionMaestro.getIdSancion() ==1 ) {
			groupBoxAsignaturas.setVisible(false);
			textboxlapsoConsecutivo1.setVisible(true);
			textboxlapsoConsecutivo2.setVisible(true);
			lbllapsoConsecutivo.setVisible(true);
		} else {
			groupBoxAsignaturas.setVisible(true);
			textboxlapsoConsecutivo1.setVisible(false);
			textboxlapsoConsecutivo2.setVisible(false);
			lbllapsoConsecutivo.setVisible(false);
			buscarAsignaturas(comboAsignaturas);
		}

	}

	/** buscar Programas
	 * @param  
	 * @return lista de programa
	 */
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPrograma(nombrePrograma);
	}


	/** mostrar Seleccionado
	 * @param  cedula, indiceGrado, lapsoAcademico,
			sancionMaestro,unidadesAprobadas, sexo, primerNombre,
			segundoNombre, primerApellido, segundoApellido,
			unidadesCursadas, programa, listaAsignaturas, semestre,
			lapsosAcademicosRP, telefono, email, fechaNacimiento,
			lapsoAcademico,annoIngreso, periodoSancion,
			listaAsignaturaListBox
	 * @return lista de programa
	 */
	@Command
	@NotifyChange({ "cedula", "indiceGrado", "lapsoAcademico",
			"sancionMaestro", "unidadesAprobadas", "sexo", "primerNombre",
			"segundoNombre", "primerApellido", "segundoApellido",
			"unidadesCursadas", "programa", "listaAsignaturas", "semestre",
			"lapsosAcademicosRP", "telefono", "email", "fechaNacimiento",
			"lapsoAcademico", "annoIngreso", "periodoSancion","lapsoConsecutivo1",
			"lapsoConsecutivo2", "listaAsignaturaListBox" })
	public void mostrarSeleccionado(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("comboAsignaturas") Combobox comboAsignaturas,
			@BindingParam("textboxCedula") Textbox textboxCedula) {
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
		programa = miSanc.getEstudiante().getProgramaAcademico();
		indiceGrado = miSanc.getIndiceGrado();
		lapsoAcademico = miSanc.getLapsoAcademico();
		sancionMaestro = miSanc.getSancionMaestro();
		unidadesAprobadas = miSanc.getUnidadesAprobadas();
		unidadesCursadas = miSanc.getUnidadesCursadas();
		semestre = miSanc.getSemestre();
		periodoSancion = miSanc.getPeriodoSancion();
		textboxCedula.setReadonly(true);
		seleccionarSancion(groupBoxAsignaturas, textboxlapsoConsecutivo1,
				textboxlapsoConsecutivo2, lbllapsoConsecutivo, comboAsignaturas);
		if (sancionMaestro.getIdSancion()==2) {
			listaAsignaturaListBox = miSanc
					.getAsignaturaEstudianteSancionados();
		}
		else{
			asignatura = miSanc.getAsignaturaEstudianteSancionados();
			
			String palabra = miSanc.getLapsosAcademicosRp();
				String separador = ":";
			if( !palabra.equals("")){
		        String[] palabraArray = palabra.split(separador);
				lapsoConsecutivo1 = palabraArray[0];
				if(palabraArray.length>1)
				lapsoConsecutivo2 = palabraArray[1];
			}
		}
}
	
	/** buscar Sancionados
	 * @param  
	 * @return lista de sancion
	 */
	@Command
	@NotifyChange({ "listaSancionado" })
	public void buscarSancionados() {
		listaSancionado = servicioestudiantesancionado
				.listadoEstudianteSancionado();
	}

	/** buscar Lapso Academico
	 * @param  
	 * @return lista de programa
	 */
	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapsoAcademico() {
		lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo();
	}

	/** buscar Sancion
	 * @param  
	 * @return lista de de sancion
	 */
	@Command
	@NotifyChange({ "listaSancion" })
	public void buscarSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
	}
	

	/** buscar Asignaturas
	 * @param  IdPrograma
	 * @return lista de de asignaturas, programas
	 */
	@Command
	@NotifyChange({ "listaAsignaturas","programa", "listaAsignaturaListBox" })
	public void buscarAsignaturas(@BindingParam("comboAsignaturas") Combobox comboAsignaturas) {
		comboAsignaturas.setText("");
		if(estudianteSeleccionado!=null){
			listaAsignaturas = servicioAsignatura.listadoAsignaturaNoPerteneceEstudiante(estudianteSeleccionado, programa);
			if(listaAsignaturas.size()==0){
				listaAsignaturaListBox = new HashSet<AsignaturaEstudianteSancionado>();
				if(estudianteSeleccionado.getAsignaturaEstudianteSancionados().size() != servicioAsignatura.buscarAsignaturasPorPrograma(programa).size())
					listaAsignaturas =  servicioAsignatura.buscarAsignaturasPorPrograma(programa);
				else listaAsignaturas = new LinkedList<Asignatura>();
			}
			else listaAsignaturaListBox = estudianteSeleccionado.getAsignaturaEstudianteSancionados();
		}	
		else {
			listaAsignaturaListBox = new HashSet<AsignaturaEstudianteSancionado>();
			listaAsignaturas =  servicioAsignatura.buscarAsignaturasPorPrograma(programa);
		}	
	}
	
	
	

	/** registrarEstudianteSancionado
	 * @return No devuelve ningun valor.
	 * @throws las Excepciones ocurren cuando se quiera registrar un estudiante 
	 */
	@Command
	 @NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionado"
		  ,"segundoApellido", "sexo","programa","periodoSancion","semestre", "lapsoConsecutivo1", "lapsoConsecutivo2"})
	public void registrarEstudianteSancionado(
			@BindingParam("asignaturaSancionado") List<Listitem> asignaturas,
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("textboxCedula") Textbox textboxCedula){

		boolean estudienteRR = false;
		boolean llenarCamposDeLapsoConsecutivo = false;
		lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo();
		if (cedula == null  || primerNombre == null
				|| segundoNombre == null
				||  primerApellido == null
				||  telefono == null
				||  email == null 
				|| sexo == null ||  programa == null
			    || fechaNacimiento == null
				|| unidadesCursadas == null
				|| unidadesAprobadas == null || annoIngreso == null
				|| sancionMaestro == null
				|| semestre == null
				|| periodoSancion == null)
			mensajeAlUsuario.advertenciaLlenarCampos();
		else if(!mensajeAlUsuario.errorValidarCorreo(email)){}
		else {
			estudianteSancionado = new EstudianteSancionado();
			//-------- Validacion de las fechas 
			if (fechaNacimiento != null && annoIngreso != null) {
				if (fechaNacimiento.compareTo(annoIngreso) > 0) {
					mensajeAlUsuario.errorRangoFechas();
					fechaNacimiento = null;
				} else if (sancionMaestro == null || sancionMaestro.equals("")) 
					mensajeAlUsuario.advertenciaLlenarCampos();
				else{
					if (sancionMaestro.getIdSancion()==1) {
						if (lapsoConsecutivo1 == null || lapsoConsecutivo2 == null || lapsoConsecutivo1.equals("") || lapsoConsecutivo2.equals("")){
							mensajeAlUsuario.advertenciaLlenarCampos();
							llenarCamposDeLapsoConsecutivo = true;
						}
						else estudianteSancionado.setLapsosAcademicosRp(lapsoConsecutivo1+ ": " + lapsoConsecutivo2);
					} else {
						estudienteRR = true;
						if (asignaturas.size() == 0) {
							mensajeAlUsuario.advertenciaIngresarAsignatura();
							estudienteRR = false;
						} 
						else estudienteRR = true;
					}
					
					if(estudienteRR == true || llenarCamposDeLapsoConsecutivo == false){
						Estudiante estudiante = new Estudiante(cedula, annoIngreso,
								email, true, fechaNacimiento, primerApellido,
								primerNombre, segundoApellido, segundoNombre, sexo,
								telefono, programa);
						servicioestudiante.guardarEstudiante(estudiante);
						estudianteSancionadoPK.setCedulaEstudiante(cedula);
						estudianteSancionadoPK.setCodigoLapso(lapsoAcademico.getCodigoLapso());
						estudianteSancionado.setId(estudianteSancionadoPK);
						estudianteSancionado.setLapsoAcademico(lapsoAcademico);
						estudianteSancionado.setEstudiante(estudiante);
						estudianteSancionado.setIndiceGrado(indiceGrado);
						estudianteSancionado.setSancionMaestro(sancionMaestro);
						estudianteSancionado.setUnidadesAprobadas(unidadesAprobadas);
						estudianteSancionado.setUnidadesCursadas(unidadesCursadas);
						estudianteSancionado.setPeriodoSancion(periodoSancion);
						estudianteSancionado.setSemestre(semestre);
						estudianteSancionado.setEstatus(true);
					}					
					// try {
					if (estudienteRR == true) {
						String condicion1 = "";
						boolean pase = false;
						for (Listitem miAsignaturasacion : asignaturas) {
							condicion1 = ((Textbox) (miAsignaturasacion.getChildren().get(1)).getFirstChild()).getValue();
							if(condicion1.equals("") || condicion1 == null ){
								pase = false;
								break;
							}else {
								pase = true;
							}
						}
						
						if(pase){
							for (AsignaturaEstudianteSancionado asignaturaEstudianteSancionado : (servicioestudiantesancionado.buscar(estudianteSeleccionado.getId()).getAsignaturaEstudianteSancionados())) {
								servicioasignaturaestudiantesancionado.eliminarAsignaturaEstudianteSancionadoFisicamente(cedula, asignaturaEstudianteSancionado.getAsignatura().getCodigoAsignatura());
							}
							for (Listitem miAsignaturasacion : asignaturas) {
								String nombreAsignatura = ((Listcell) miAsignaturasacion.getChildren().get(0)).getLabel();
								String condicion = ((Textbox) (miAsignaturasacion.getChildren().get(1)).getFirstChild()).getValue();
								Asignatura asignaturaSacion = new Asignatura();
								asignaturaSacion = servicioAsignatura.buscarAsignaturaNombreAndProgramaAcademico(nombreAsignatura, estudianteSancionado.getEstudiante().getProgramaAcademico());
								AsignaturaEstudianteSancionadoPK asignaturaEstudianteSancionadoPK = new AsignaturaEstudianteSancionadoPK();
								asignaturaEstudianteSancionadoPK.setCedulaEstudiante(cedula);
								asignaturaEstudianteSancionadoPK.setCodigoAsignatura(asignaturaSacion.getCodigoAsignatura());
								asignaturaEstudianteSancionadoPK.setCodigoLapso(lapsoAcademico.getCodigoLapso());
								AsignaturaEstudianteSancionado asignaturaEstudianteSancionado = new AsignaturaEstudianteSancionado();
								asignaturaEstudianteSancionado.setId(asignaturaEstudianteSancionadoPK);
								asignaturaEstudianteSancionado.setCondicionAsignatura(Integer.valueOf(condicion));
								asignaturaEstudianteSancionado.setEstudianteSancionado(estudianteSancionado);
								asignaturaEstudianteSancionado.setAsignatura(asignaturaSacion);
								estudianteSancionado.addAsignaturaEstudianteSancionado(asignaturaEstudianteSancionado);
								servicioestudiantesancionado.guardar(estudianteSancionado);
							}
							mensajeAlUsuario.informacionRegistroCorrecto();
							limpiar(groupBoxAsignaturas, textboxlapsoConsecutivo1, textboxlapsoConsecutivo2, lbllapsoConsecutivo, textboxCedula);
						}else{ 
							mensajeAlUsuario.advertenciaLlenarCampos();
						}
					} else if (llenarCamposDeLapsoConsecutivo == false)  {
						if(estudianteSeleccionado!=null) {
							for (AsignaturaEstudianteSancionado asignaturaEstudianteSancionado : (servicioestudiantesancionado.buscar(estudianteSeleccionado.getId()).getAsignaturaEstudianteSancionados())) {
								servicioasignaturaestudiantesancionado.eliminarAsignaturaEstudianteSancionadoFisicamente(cedula, asignaturaEstudianteSancionado.getAsignatura().getCodigoAsignatura());
							}
						}
						servicioestudiantesancionado.guardar(estudianteSancionado);
						mensajeAlUsuario.informacionRegistroCorrecto();
						limpiar(groupBoxAsignaturas, textboxlapsoConsecutivo1, textboxlapsoConsecutivo2, lbllapsoConsecutivo, textboxCedula);
					}
				}
			}
		}
	}
	
	/**
	 * buscarEstudiante
	 * 
	 * @param
	 * @return Ninguno
	 */
	@Command
	@NotifyChange({ "cedula", "indiceGrado", "lapsoAcademico",
		"sancionMaestro", "unidadesAprobadas", "sexo", "primerNombre",
		"segundoNombre", "primerApellido", "segundoApellido",
		"unidadesCursadas", "programa", "listaAsignaturas", "semestre",
		"lapsosAcademicosRP", "telefono", "email", "fechaNacimiento",
		"lapsoAcademico", "annoIngreso", "periodoSancion","lapsoConsecutivo1",
		"lapsoConsecutivo2", "listaAsignaturaListBox" })
	public void buscarEstudiante(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("comboAsignaturas") Combobox comboAsignaturas){

		try {
			
			EstudianteSancionado miSanc = servicioestudiantesancionado.buscarEstudianteSancionadoLapsoActual(cedula);
				if (miSanc != null) {
					
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
					programa = miSanc.getEstudiante().getProgramaAcademico();
					indiceGrado = miSanc.getIndiceGrado();
					lapsoAcademico = miSanc.getLapsoAcademico();
					sancionMaestro = miSanc.getSancionMaestro();
					unidadesAprobadas = miSanc.getUnidadesAprobadas();
					unidadesCursadas = miSanc.getUnidadesCursadas();
					semestre = miSanc.getSemestre();
					periodoSancion = miSanc.getPeriodoSancion();
					seleccionarSancion(groupBoxAsignaturas, textboxlapsoConsecutivo1,
							textboxlapsoConsecutivo2, lbllapsoConsecutivo, comboAsignaturas);
					if (sancionMaestro.getIdSancion()==2) {
						listaAsignaturaListBox = miSanc
								.getAsignaturaEstudianteSancionados();
					}
					else{
						asignatura = miSanc.getAsignaturaEstudianteSancionados();
						
						String palabra = miSanc.getLapsosAcademicosRp();
							String separador = ":";
						if( !palabra.equals("")){
					        String[] palabraArray = palabra.split(separador);
					   		
							lapsoConsecutivo1 = palabraArray[0];
							if(palabraArray.length>1)
							lapsoConsecutivo2 = palabraArray[1];
						}
					}		
				} else {
					estudianteNoEncontrado(groupBoxAsignaturas, textboxlapsoConsecutivo1, textboxlapsoConsecutivo2, lbllapsoConsecutivo);
				}
			
		} catch (Exception e) {
		}
	}
	 
	/**
	 * eliminarEstudianteSancionado
	 * 
	 * @param cedula
	 *            , primerNombre,segundoNombre,primerApellido, segundoApellido ,
	 *            sexo, fechaNacimiento, telefono, email, annoIngreso,
	 *            nombreSancion, programa , indiceGrado, lapsoAcademico,
	 *            sancionMaestro, unidadesAprobadas,unidadesCursadas , semestre,
	 *            lapsosAcademicosRP,listaSancionado
	 * @return No devuelve ningun valor
	 * @throws Debe
	 *             seleccionar un registro para poder eliminarlo
	 */
	@Command
	@NotifyChange({ "cedula", "primerNombre", "segundoNombre",
			"primerApellido", "segundoApellido", "sexo", "fechaNacimiento",
			"telefono", "email", "annoIngreso", "nombreSancion", "programa",
			"indiceGrado", "lapsoAcademico", "sancionMaestro",
			"unidadesAprobadas", "unidadesCursadas", "semestre",
			"lapsosAcademicosRP", "listaSancionado" })
	public void eliminarEstudianteSancionado(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("textboxCedula") Textbox textboxCedula) {

		if (cedula == null || cedula.equals("")) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else if (serviciosolicitudapelacion
				.buscarEstudianteSancionadoxSolicitud(cedula) != null) {
			mensajeAlUsuario.advertenciaNoELiminar();
		} else {
			try {
				servicioestudiantesancionado.eliminar(estudianteSeleccionado.getId());
			} catch (Exception e) {
			}
			mensajeAlUsuario.informacionEliminarCorrecto();
			limpiar(groupBoxAsignaturas, textboxlapsoConsecutivo1,
					textboxlapsoConsecutivo2, lbllapsoConsecutivo, textboxCedula);
		}
	}
	 
	 
	 	/** limpiar
		 * @param Groupbox,label, Textbox
		 * @return Ninguno
		 */	
	@Command
	@NotifyChange({ "cedula", "primerNombre", "segundoNombre",
			"primerApellido", "segundoApellido", "sexo", "fechaNacimiento",
			"telefono", "email", "annoIngreso", "nombreSancion", "programa",
			"indiceGrado", "lapsosAcademicosRP", "sancionMaestro",
			"unidadesAprobadas", "unidadesCursadas", "semestre",
			"lapsosAcademicosRP", "listaSancionado", "periodoSancion",
			"listaAsignaturaListBox", "lapsoConsecutivo1", "lapsoConsecutivo2",
			"cedulaFiltro", "nombreFiltro", "apellidoFiltro", "sancionFiltro",
			"asignatura", "init", "estudianteSancionado"})
	public void limpiar(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo,
			@BindingParam("textboxCedula") Textbox textboxCedula) {
		primerNombre = null;
		segundoNombre = null;
		primerApellido = null;
		segundoApellido = null;
		telefono = null;
		email = null;
		lapsosAcademicosRP = null;
		nombreSancion = null;
		sexo = null;
		programa = null;
		cedula = null;
		indiceGrado = 0;
		fechaNacimiento = null;
		unidadesCursadas = null;
		unidadesAprobadas = null;
		annoIngreso = null;
		sancionMaestro = null;
		semestre = null;
		periodoSancion = null;
		buscarSancionados();
		listaAsignaturaListBox.clear();
		lapsoConsecutivo1 = null;
		lapsoConsecutivo2 = null;
		estudianteSancionado = null;
		cedulaFiltro = "";
		nombreFiltro = "";
		apellidoFiltro = "";
		sancionFiltro = "";
		textboxlapsoConsecutivo1.setVisible(false);
		textboxlapsoConsecutivo2.setVisible(false);
		lbllapsoConsecutivo.setVisible(false);
		groupBoxAsignaturas.setVisible(false);
		textboxCedula.setReadonly(false);
	}
		 
	/** estudianteNoEncontrado
	 * @param Groupbox,label, Textbox
	 * @return Ninguno
	 */	
	 @Command
	 @NotifyChange({"primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
		  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
		  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
		  , "semestre", "lapsosAcademicosRP","listaSancionado","periodoSancion"
		  ,"listaAsignaturaListBox","lapsoConsecutivo1","lapsoConsecutivo1"
		  ,"cedulaFiltro","nombreFiltro","apellidoFiltro","sancionFiltro","asignatura"})
	public void estudianteNoEncontrado(
			@BindingParam("parametro1") Groupbox groupBoxAsignaturas,
			@BindingParam("parametro2") Textbox textboxlapsoConsecutivo1,
			@BindingParam("parametro3") Textbox textboxlapsoConsecutivo2,
			@BindingParam("parametro4") Label lbllapsoConsecutivo) {
			primerNombre = null;
			segundoNombre = null;
			primerApellido = null;
			segundoApellido = null;
			telefono = null;
			email = null;
			lapsosAcademicosRP = null;
			nombreSancion= null;
			sexo = null;
			programa = null;
			indiceGrado = 0;
			fechaNacimiento = null;
			unidadesCursadas = null;
			unidadesAprobadas = null;
			annoIngreso = null;
			sancionMaestro = null;
			semestre = null;
			buscarSancionados();
			periodoSancion=null;
			listaAsignaturaListBox.clear();
			lapsoConsecutivo1= null;
			lapsoConsecutivo2= null;
			cedulaFiltro= "";
			nombreFiltro= "";
			apellidoFiltro= "";
			sancionFiltro= "";
			groupBoxAsignaturas.setVisible(false);
			textboxlapsoConsecutivo1.setVisible(false);
			textboxlapsoConsecutivo2.setVisible(false);
			lbllapsoConsecutivo.setVisible(false);	
	}
	 
	@Command
	@NotifyChange({"cedula", "nombre", "apellido","sancion", "lapso"})
	public void showModal(){
 		Window registrar = (Window)Executions.createComponents(
       		"/WEB-INF/sigarep/vistas/transacciones/ListadoSancionInicial.zul", null, null);
		registrar.setMaximizable(true);
		registrar.doModal();	
 	}
	
	/** Método que trae todos los registros en una lista de sancionados
	 * @parameters lista de sancionados
	 * @throws No dispara ninguna excepcion.
	   */
	@Command
	@NotifyChange({ "listaEstudianteSancionado" })
	public void listadoEstudianteSancionado() {
		listaEstudianteSancionado = servicioestudiantesancionado
				.listadoEstudianteSancionado();
	}
	
	@Command
	@NotifyChange("listaSancionado")
	public void filtros() {
		listaSancionado = servicioestudiantesancionado
				.buscarEstudianteSancionadofiltros(cedulaFiltro, nombreFiltro,
						apellidoFiltro, sancionFiltro);
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
	@NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionado"
		  ,"segundoApellido", "sexo","programa","periodoSancion","semestre"})
	
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(cedula != null  || primerNombre != null
				|| segundoNombre != null
				||  primerApellido != null
				||  telefono != null
				||  email != null 
				|| sexo != null ||  programa != null
			    || fechaNacimiento != null
				|| unidadesCursadas != null
				|| unidadesAprobadas != null || annoIngreso != null
				|| sancionMaestro != null
				|| semestre != null
				|| periodoSancion != null || lapsoConsecutivo1 != null || lapsoConsecutivo2 != null)
			condicion = true;
		
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}

}//fin VMEstudianteSancionado