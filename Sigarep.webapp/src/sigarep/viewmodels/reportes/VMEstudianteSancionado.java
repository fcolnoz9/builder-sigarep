package sigarep.viewmodels.reportes;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.EstudianteSancionado;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioReporteEstudianteSancionado;
/**VM Reporte Estudiante Sancionado
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEstudianteSancionado {
	 
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioReporteEstudianteSancionado servicioreporteestudiantesancionado;
	@WireVariable
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable
	private ProgramaAcademico programaAcademico = new ProgramaAcademico();
	@WireVariable
	private TipoMotivo tipoMotivo = new TipoMotivo();
	@WireVariable
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	@WireVariable
	private SancionMaestro sancionMaestro = new SancionMaestro();
	@WireVariable
	private InstanciaApelada instanciaApelada = new InstanciaApelada();
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<LapsoAcademico> listaLapsoAcademico;
	private List<SancionMaestro> listaSancion;
	private List<InstanciaApelada> listaInstanciaApelada;
	public String programa = "Informatica";
	private LapsoAcademico objLapso;
	private SancionMaestro objSancion;
	private ProgramaAcademico  objprograma;
	private TipoMotivo objtipoMotivo;
	private InstanciaApelada objinstanciaApelada;
	private String objsexo;
	private List<EstudianteSancionado> listaE = new LinkedList<EstudianteSancionado>();
	//Parametros para la Tira Sql
	private String parametroLapsoAcademico;
	private String parametroTipoSancion;
	private String parametroInstanciaApelada;
	private String parametroMotivo;
	private String parametroProgramaAcademico;
	private String parametroSexo;
	private ListModelList<String> cmbSexo;//Lista para llenar el combo de sexo
	// SETS Y GETS
	public SancionMaestro getObjSancion() {
		return objSancion;
	}
	public void setObjSancion(SancionMaestro objSancion) {
		this.objSancion = objSancion;
	}
	public ProgramaAcademico getObjprograma() {
		return objprograma;
	}
	public void setObjprograma(ProgramaAcademico objprograma) {
		this.objprograma = objprograma;
	}
	public LapsoAcademico getObjLapso() {
		return objLapso;
	}
	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}
	public TipoMotivo getObjtipoMotivo() {
		return objtipoMotivo;
	}
	public void setObjtipoMotivo(TipoMotivo objtipoMotivo) {
		this.objtipoMotivo = objtipoMotivo;
	}
	public InstanciaApelada getObjinstanciaApelada() {
		return objinstanciaApelada;
	}
	public void setObjinstanciaApelada(InstanciaApelada objinstanciaApelada) {
		this.objinstanciaApelada = objinstanciaApelada;
	}
	public InstanciaApelada getInstanciaApelada() {
		return instanciaApelada;
	}
	public void setInstanciaApelada(InstanciaApelada instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}
	public ProgramaAcademico getProgramaAcademico() {
		return programaAcademico;
	}
	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
	}
	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}
	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	public SancionMaestro getSancionMaestro() {
		return sancionMaestro;
	}
	public void setSancionMaestro(SancionMaestro sancionMaestro) {
		this.sancionMaestro = sancionMaestro;
	}
	public List<InstanciaApelada> getListaInstanciaApelada() {
		return listaInstanciaApelada;
	}
	public void setListaInstanciaApelada(List<InstanciaApelada> listaInstanciaApelada) {
		this.listaInstanciaApelada = listaInstanciaApelada;
	}
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}
	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}
	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}
	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
	public List<EstudianteSancionado> getListaE() {
		return listaE;
	}
	public void setListaE(List<EstudianteSancionado> listaE) {
		this.listaE = listaE;
	}
	public ListModelList<String> getCmbSexo() {
		cmbSexo.add("F");
		cmbSexo.add("M");
		cmbSexo.add("Todos");
		return cmbSexo;
	}
	public void setCmbSexo(ListModelList<String> cmbSexo) {
		this.cmbSexo = cmbSexo;
	}
	public String getParametroLapsoAcademico() {
		return parametroLapsoAcademico;
	}
	public void setParametroLapsoAcademico(String parametroLapsoAcademico) {
		this.parametroLapsoAcademico = parametroLapsoAcademico;
	}
	public String getParametroTipoSancion() {
		return parametroTipoSancion;
	}
	public void setParametroTipoSancion(String parametroTipoSancion) {
		this.parametroTipoSancion = parametroTipoSancion;
	}
	public String getParametroMotivo() {
		return parametroMotivo;
	}
	public void setParametroMotivo(String parametroMotivo) {
		this.parametroMotivo = parametroMotivo;
	}
	public String getParametroInstanciaApelada() {
		return parametroInstanciaApelada;
	}
	public void setParametroInstanciaApelada(String parametroInstanciaApelada) {
		this.parametroInstanciaApelada = parametroInstanciaApelada;
	}
	public String getParametroProgramaAcademico() {
		return parametroProgramaAcademico;
	}
	public void setParametroProgramaAcademico(String parametroProgramaAcademico) {
		this.parametroProgramaAcademico = parametroProgramaAcademico;
	}
	public String getParametroSexo() {
		return parametroSexo;
	}
	public void setParametroSexo(String parametroSexo) {
		this.parametroSexo = parametroSexo;
	}
	public String getObjsexo() {
		return objsexo;
	}
	public void setObjsexo(String objsexo) {
		this.objsexo = objsexo;
	}
	//FINAL SETS GETS
	@Init
	public void init() {
		// initialization code
		buscarTipoMotivo();
		buscarProgramaA();
		buscarActivoLapso();
		listadoSancion();
		listadoInstancia();
		cmbSexo = new ListModelList<String>();
	}
	// Metodo que busca un motivo partiendo por su titulo
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTipoMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
		TipoMotivo mot = new TipoMotivo(null,"Todos", null,"Todos",false);
		listaTipoMotivo.add(listaTipoMotivo.size()/*0*/, mot);
	}
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public TipoMotivo objCmbtipoMotivo() {
		return objtipoMotivo;

	}
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
		ProgramaAcademico prog = new ProgramaAcademico(null, "Todos",null);
		listaPrograma.add(listaPrograma.size(),prog);
	}
	@Command
	@NotifyChange({ "listaPrograma" })
	public ProgramaAcademico objCmbprograma() {
		return objprograma;

	}
	// Método que trae todos los registros en una lista de Lapso Academicos
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarActivoLapso() {
		listaLapsoAcademico = serviciolapsoacademico.listadoLapsoAcademico();
		LapsoAcademico lap = new LapsoAcademico("Todos", null, null, null);
		listaLapsoAcademico.add(listaLapsoAcademico.size(),lap);
	}
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;

	}
	// Método que trae todos los registros en una lista de sanciones
	@Command
	@NotifyChange({ "listaSancion" })
	public void listadoSancion() {
		listaSancion = serviciosancionmaestro.listaTipoSanciones();
		SancionMaestro san = new  SancionMaestro(null,"Todos", null, "Todos");
		listaSancion.add(listaSancion.size(),san);
	}
	@Command
	@NotifyChange({ "listaSancion" })
	public SancionMaestro objCmbSancion() {
		return objSancion;

	}
	// Metodo que muestra la lista de todas las instancias
		@Command
		@NotifyChange({ "listaInstanciaApelada" })
		public void listadoInstancia() {
			listaInstanciaApelada = servicioInstanciaApelada.listadoInstanciaApelada();
			InstanciaApelada ins = new InstanciaApelada(null,"Todos", null, "Todos", null);
			listaInstanciaApelada.add(listaInstanciaApelada.size(),ins);
		}
		@Command
		@NotifyChange({ "listaInstanciaApelada" })
		public InstanciaApelada objCmbinstanciaApelada() {
			return objinstanciaApelada;

		}

	@Command
	@NotifyChange({ "listaE" })
	public void buscarEstudianteSancionado() {
		if(objinstanciaApelada==null|| objLapso==null || objprograma==null || objSancion.getDescripcion()==null || objsexo==null || objtipoMotivo==null){
	        Clients.showNotification("Hola que hace");
		}
		else{
			configurarParametro1();
			configurarParametroSancion();
			configurarParametroInstanciaApelada();
			configurarParametroMotivo();
			configurarParametroProgramaAcademico();
			configurarParametroSexo();
			listaE = servicioreporteestudiantesancionado.buscarTodosSancionado(parametroLapsoAcademico,parametroTipoSancion,parametroInstanciaApelada,
					 parametroMotivo,parametroProgramaAcademico,parametroSexo);
		}
		
	}
	// Metodo que limpia todos los campos
	@Command
	@NotifyChange({ "programaAcademico", "tipoMotivo", "lapsoAcademico",
			"sancionMaestro", "instanciaApelada","sexo" })
	public void limpiarE() {
		programaAcademico = new ProgramaAcademico();
		tipoMotivo = new TipoMotivo();
		lapsoAcademico = new LapsoAcademico();
		sancionMaestro = new SancionMaestro();
		instanciaApelada = new InstanciaApelada();
	}
	@NotifyChange({ "parametroLapsoAcademico" })
	@Command
	public String configurarParametro1() // parametro codigo Lapso
	{
		if (objLapso.getCodigoLapso() == "Todos") {
			parametroLapsoAcademico = "sap.codigo_lapso";
		} else {
			parametroLapsoAcademico = "'" + objLapso.getCodigoLapso() + "'";
		}
		return parametroLapsoAcademico;
	}
	@NotifyChange({ "parametroTipoSancion" })// parametro Tipo sancion
	@Command
	public String configurarParametroSancion() {
		if (objSancion.getNombreSancion() == "Todos") {
			parametroTipoSancion = "esa.id_sancion";
		} else {
			parametroTipoSancion = "'" + objSancion.getIdSancion() + "'";
		}
		return parametroTipoSancion;
	}

	@NotifyChange({ "parametroInstanciaApelada" })// ParametroInstanciaApeldada
	@Command
	public String configurarParametroInstanciaApelada() {
		if (objinstanciaApelada.getDescripcion() == "Todos") {
			parametroInstanciaApelada = "sap.id_instancia_apelada";
		} else {
			parametroInstanciaApelada = "'"
					+ objinstanciaApelada.getIdInstanciaApelada() + "'";
		}
		return parametroInstanciaApelada;
	}

	@NotifyChange({ "parametroMotivo" })// ParametroMotivo
	@Command
	public String configurarParametroMotivo() {
		if (objtipoMotivo.getDescripcion() == "Todos") {
			parametroMotivo = "mot.id_tipo_motivo";
		} else {
			parametroMotivo = "'" + objtipoMotivo.getIdTipoMotivo() + "'";
		}
		return parametroMotivo;
	}
	@NotifyChange({ "parametroProgramaAcademico" })// ParametroprogramaAcademico
	@Command
	public String configurarParametroProgramaAcademico() {
		if (objprograma.getNombrePrograma() == "Todos") {
			parametroProgramaAcademico = "es.id_programa";
		} else {
			parametroProgramaAcademico = "'" + objprograma.getIdPrograma()
					+ "'";
		}
		return parametroProgramaAcademico;
	}
	@NotifyChange({ "parametroSexo" })
	// Parametro Sexo
	@Command
	public String configurarParametroSexo() {
		if (objsexo.equals("Todos")) {
			parametroSexo = "es.sexo";
		} else {
			parametroSexo = "'" + objsexo + "'";
		}
		return parametroSexo;
	}
}
