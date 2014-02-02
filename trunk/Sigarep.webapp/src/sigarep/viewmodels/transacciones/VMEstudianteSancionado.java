package sigarep.viewmodels.transacciones;

import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;

import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioNoticia;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;



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
	 

	//Variables para los filtros
	private String cedulaFiltro="";
	private String nombreFiltro="";
	private String apellidoFiltro="";
	private String sancionFiltro="";

	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioEstudiante servicioestudiante;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable 
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	private String nombreSancion;
	private Date annoIngreso;
	private float indiceGrado;
	private String lapsosAcademicosRP;
	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
	private SancionMaestro sancionMaestro;
	private Asignatura asignaturas;
	private Integer semestre;
	private ProgramaAcademico programa;
	
	private AsignaturaEstudianteSancionado asignaturaestudiantesancionado;
	private List<AsignaturaEstudianteSancionado> listaasignatura;

	private Integer periodoSancion;
	private Asignatura asignaturaseleccionado;
	private SancionMaestro sancionSeleccionada;
	private List<ProgramaAcademico> listaPrograma;
	private List<SancionMaestro> listaSancion;
	private EstudianteSancionado estudianteSeleccionado;
	private List<EstudianteSancionado> listaEstudianteSancionado;
	private List<Asignatura> listaAsignaturas;
	private List<AsignaturaEstudianteSancionado> listaAsignaturaListBox = new LinkedList<AsignaturaEstudianteSancionado>();
	private List<AsignaturaEstudianteSancionado> asignatura; //de vanessaa

	EstudianteSancionadoPK estudianteSancionadoPK = new EstudianteSancionadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	private List<EstudianteSancionado> listaSancionado;
	
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

	public void setlistaAsignaturas(List<Asignatura> listaTipoMotivo) {
		this.listaAsignaturas = listaAsignaturas;
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

	public List<AsignaturaEstudianteSancionado> getListaAsignaturaListBox() {
		return listaAsignaturaListBox;
	}

	public void setListaAsignaturaListBox(
			List<AsignaturaEstudianteSancionado> listaAsignaturaListBox) {
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
 	

	
	
	@Init
	public void init(){
		buscarLapsoAcademico();
		buscarSancion();
		buscarSancionados();
		buscarProgramas();
	}
	
	@Command
	@NotifyChange({ "listaAsignaturas", "listaAsignaturaListBox", "asignaturaseleccionado" })
	public void agregarAsignatura(
			@BindingParam("listBoxAsignaturas") Listbox listBoxAsignaturas) {

		AsignaturaEstudianteSancionado asignaturaLista = new AsignaturaEstudianteSancionado();
		asignaturaLista.setAsignatura(asignaturaseleccionado);
		listaAsignaturaListBox.add(asignaturaLista);
	
	//System.out.println(listaAsignaturaListBox);
	}
	
	@Command
	public void seleccionarSancion(@BindingParam("parametro1") Groupbox groupBoxAsignaturas,@BindingParam("parametro2")Textbox textboxlapsoConsecutivo1,@BindingParam("parametro3")Textbox textboxlapsoConsecutivo2,@BindingParam("parametro4")Label lbllapsoConsecutivo ){
		if (sancionMaestro.getNombreSancion().equalsIgnoreCase("RP")){
			groupBoxAsignaturas.setVisible(false);
			textboxlapsoConsecutivo1.setVisible(true);
			textboxlapsoConsecutivo2.setVisible(true);
			lbllapsoConsecutivo.setVisible(true);
		}else{
			groupBoxAsignaturas.setVisible(true);
			textboxlapsoConsecutivo1.setVisible(false);
			textboxlapsoConsecutivo2.setVisible(false);
			lbllapsoConsecutivo.setVisible(false);
		}
	}
	
	@NotifyChange({"listaPrograma"})
	private void buscarProgramas() {
		listaPrograma = servicioprogramaacademico.buscarPrograma("");
	}

	@Command
	@NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "sexo"
		  ,"primerNombre","segundoNombre","primerApellido","segundoApellido","unidadesCursadas", "programa" 
		  ,"semestre","lapsosAcademicosRP","telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","periodoSancion"})
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
		 programa = miSanc.getEstudiante().getProgramaAcademico();
		 indiceGrado = miSanc.getIndiceGrado();
		 lapsoAcademico = miSanc.getLapsoAcademico();
		 sancionMaestro = miSanc.getSancionMaestro();
		 unidadesAprobadas = miSanc.getUnidadesAprobadas();
		 unidadesCursadas = miSanc.getUnidadesCursadas();
		 semestre = miSanc.getSemestre();
		 periodoSancion= miSanc.getPeriodoSancion();
	}
	
	@Command
	@NotifyChange({"listaSancionado"})
	public void buscarSancionados(){
		listaSancionado = servicioestudiantesancionado.listadoEstudianteSancionado();
	}

	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapsoAcademico() {
		lapsoAcademico = serviciolapsoacademico.buscarLapsoActivo();
	}

	// Metodo que busca las sanciones y las carga en el combobox de sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void buscarSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
	}


	@Command
	@NotifyChange({ "listaAsignaturas" })
	public void buscarAsignaturas() {
		listaAsignaturas =  servicioAsignatura.buscarAsignaturasPorPrograma(programa.getIdPrograma());

	}

	@Command
	 @NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionado"
		  ,"segundoApellido", "sexo","programa","periodoSancion","semestre"})
	public void registrarEstudianteSancionado() {
		if (serviciolapsoacademico.encontrarLapsoActivo() == null)
			mensajeAlUsuario.ErrorLapsoActivoNoExistente();
		else{
			lapsoAcademico = serviciolapsoacademico.encontrarLapsoActivo();
			if (cedula==null || cedula.equals("") || primerNombre==null ||primerNombre.equals("") || segundoNombre==null ||segundoNombre.equals("") 
					|| primerApellido==null || segundoApellido.equals("") 
					|| telefono==null ||telefono.equals("") || email==null ||email.equals("")|| sexo==null ||sexo.equals("") 
					|| programa==null ||programa.equals("") || fechaNacimiento==null ||fechaNacimiento.equals("") 
					||  unidadesCursadas==null 
				    || unidadesAprobadas==null || annoIngreso==null || annoIngreso.equals("")  
				    || sancionMaestro==null ||sancionMaestro.equals("")
				    || semestre==null  || periodoSancion==null)
				mensajeAlUsuario.advertenciaLlenarCampos();
			else{
				if (sancionMaestro == null)
					mensajeAlUsuario.advertenciaLlenarCampos();
				else{
					if (sancionMaestro.getNombreSancion().equalsIgnoreCase("RP")){
						if (lapsoConsecutivo1 == null || lapsoConsecutivo1.equals("") || lapsoConsecutivo2 == null || lapsoConsecutivo2.equals(""))
							mensajeAlUsuario.advertenciaLlenarCampos();
						else
							estudianteSancionado.setLapsosAcademicosRp(lapsoConsecutivo1 + ": " + lapsoConsecutivo2);
					}
					else{
						//Lo que se debe hacer si la sancion es RR
						//Validar que la lista de asignaturas no este vacia
					}
				}
				Estudiante estudiante = new Estudiante(cedula,annoIngreso, email, true, fechaNacimiento, primerApellido,
														primerNombre, segundoApellido, segundoNombre, sexo, telefono, programa);
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
	 @NotifyChange({"cedula" ,"indiceGrado" ,"lapsoAcademico", "sancionMaestro", "unidadesAprobadas"
		  ,"primerNombre","segundoNombre","primerApellido","unidadesCursadas" ,"semestre","lapsosAcademicosRP"
		  ,"telefono","email","fechaNacimiento","lapsoAcademico","annoIngreso","listaSancionado"
		  ,"segundoApellido", "sexo", "periodoSancion"})
	 public void buscarEstudiante(){
		 Estudiante estudiante = new Estudiante();
		 estudiante = servicioestudiante.buscarEstudiante(cedula);
		 if (estudiante != null && !estudiante.equals("")){
			 primerNombre = estudiante.getPrimerNombre();
			 segundoNombre = estudiante.getSegundoNombre();
			 primerApellido = estudiante.getPrimerApellido();
			 segundoApellido = estudiante.getSegundoApellido();
			 sexo = estudiante.getSexo();
			 fechaNacimiento = estudiante.getFechaNacimiento();
			 telefono = estudiante.getTelefono();
			 email = estudiante.getEmail();
			 annoIngreso = estudiante.getAnioIngreso();
             programa = estudiante.getProgramaAcademico();
             
             EstudianteSancionadoPK estudianteSancionadoPK = new EstudianteSancionadoPK(lapsoAcademico.getCodigoLapso(), cedula);
             EstudianteSancionado estudianteSancionado = servicioestudiantesancionado.buscar(estudianteSancionadoPK);
             if (estudianteSancionado != null){
            	 annoIngreso = estudianteSancionado.getEstudiante().getAnioIngreso();
        		 indiceGrado = estudianteSancionado.getIndiceGrado();
        		 lapsoAcademico = estudianteSancionado.getLapsoAcademico();
        		 sancionMaestro = estudianteSancionado.getSancionMaestro();
        		 unidadesAprobadas = estudianteSancionado.getUnidadesAprobadas();
        		 unidadesCursadas = estudianteSancionado.getUnidadesCursadas();
        		 semestre = estudianteSancionado.getSemestre();
        		 periodoSancion = estudianteSancionado.getPeriodoSancion();
             }
		 }else{
			 estudianteNoEncontrado();
		 }
	 }

	 	@Command
		@NotifyChange({"cedula", "primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
			  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
			  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
			  , "semestre", "lapsosAcademicosRP","listaSancionado"})
		public void eliminarEstudianteSancionado(){
		  if (cedula==null ||cedula.equals("") )
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
		  , "semestre", "lapsosAcademicosRP","listaSancionado","periodoSancion","listaAsignaturaListBox"})
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
		unidadesCursadas = null;
		unidadesAprobadas = null;
		annoIngreso = null;
		sancionMaestro = null;
		semestre = null;
		periodoSancion=null;
		buscarSancionados();
		listaAsignaturaListBox.clear();
	}
	 
	 @Command
	 @NotifyChange({"primerNombre", "segundoNombre", "primerApellido", "segundoApellido"
		  , "sexo", "fechaNacimiento", "telefono", "email", "annoIngreso", "nombreSancion", "programa"
		  , "indiceGrado", "lapsoAcademico", "sancionMaestro", "unidadesAprobadas", "unidadesCursadas"
		  , "semestre", "lapsosAcademicosRP","listaSancionado","periodoSancion","listaAsignaturaListBox"})
	public void estudianteNoEncontrado() {
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
		indiceGrado = 0;
		fechaNacimiento = null;
		unidadesCursadas = null;
		unidadesAprobadas = null;
		annoIngreso = null;
		sancionMaestro = null;
		semestre = null;
		buscarSancionados();
		periodoSancion=null;
		//listaAsignaturaListBox.clear();
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
	
	@Command
	@NotifyChange("listaSancionado")
	public void filtros(){
		listaSancionado = servicioestudiantesancionado.buscarEstudianteSancionadofiltros(cedulaFiltro, nombreFiltro, apellidoFiltro, sancionFiltro);
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

	public List<AsignaturaEstudianteSancionado> getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(List<AsignaturaEstudianteSancionado> asignatura) {
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

	

	


	
//	 else if ( servicioestudiante.buscarEstudiante(cedula)== null )
//	 mensajeAlUsuario.advertenciaNoExiteCedula();
	
}