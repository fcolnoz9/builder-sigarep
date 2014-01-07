package sigarep.viewmodels.reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;




import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.CategoryModel;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.reportes.ChartDataAsignaturasMayorCantidadSancionados;
import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.reportes.ServicioListaAsignaturasMayorCantidadSancionados;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMasignaturasMayorCantidadSancionados {

	CategoryModel model;
	String type;
	
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable ServicioListaAsignaturasMayorCantidadSancionados servicioListaAsignaturasMayor;
	@WireVariable ServicioLapsoAcademico spp;
	@WireVariable ServicioProgramaAcademico servicioprogramaacademico;
	
	//***********************************DECLARACION DE LISTAS*************************
	@WireVariable ListaAsignaturasMayorCantidadSancionados listaMayorSancionados;
	private  List<ListaAsignaturasMayorCantidadSancionados> listaAsigMayor= new LinkedList<ListaAsignaturasMayorCantidadSancionados>();
	private List<ProgramaAcademico> listaComboPrograma;
	private List<LapsoAcademico> listaComboLapsoFinal;
	private List<LapsoAcademico> listaLapsoAcademico;
	
	//***********************************DECLARACION DE LAS VARIABLES*************************
	@WireVariable private String nombreAsignatura;
	@WireVariable private Integer cantidadSancionados;
	private Integer idPrograma;
	private String nombrePrograma;
	private String codigoLapso;
	private String codigoLapsoI;
	private String codigoLapsoF;
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO*************************
	private LapsoAcademico lapsoAcademico;
	private LapsoAcademico lapsoAcademicoFinal;
	private ProgramaAcademico programaAcademico;
	
	//***********************************DECLARACION DE LA VARIABLE CONEXION PARA REPORTE*************************
	private Connection con;
	
	Map<String, Object> parametros = new HashMap<String, Object>();
	
	//*******METODO DE INICIALIZACION*******
	@Init
	public void init() {
		// prepare chart data
		type = "column";
		model = ChartDataAsignaturasMayorCantidadSancionados.getModel();
		buscarProgramaAcademico();
		buscarLapso();
		buscarLapsoAcademicoFinal();
	}
	//*******FIN DEL METODO*******
	
	//==================================METODOS SET Y GET====================================
	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}
	
	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	
	public String getCodigoLapsoI() {
		return codigoLapsoI;
	}

	public void setCodigoLapsoI(String codigoLapsoI) {
		this.codigoLapsoI = codigoLapsoI;
	}
	
	public String getCodigoLapsoF() {
		return codigoLapsoF;
	}

	public void setCodigoLapsoF(String codigoLapsoF) {
		this.codigoLapsoF = codigoLapsoF;
	}
	
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public CategoryModel getModel() {
		return model;
	}
	
	public String getType(){
		return type;
	}
	public ProgramaAcademico getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
		this.programaAcademico = programaAcademico;
	}
	
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	public LapsoAcademico getLapsoAcademicoFinal() {
		return lapsoAcademicoFinal;
	}

	public void setLapsoAcademicoFinal(LapsoAcademico lapsoAcademicoFinal) {
		this.lapsoAcademicoFinal = lapsoAcademicoFinal;
	}

	
	public List<ProgramaAcademico> getListaComboPrograma() {
		return listaComboPrograma;
	}

	public void setListaComboPrograma(List<ProgramaAcademico> listaComboPrograma) {
		this.listaComboPrograma = listaComboPrograma;
	}
	
	public List<LapsoAcademico> getListaLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}

	public List<LapsoAcademico> getListaComboLapsoFinal() {
		return listaComboLapsoFinal;
	}

	public void setListaComboLapsoFinal(List<LapsoAcademico> listaComboLapsoFinal) {
		this.listaComboLapsoFinal = listaComboLapsoFinal;
	}

	public ListaAsignaturasMayorCantidadSancionados getListaMayorSancionados() {
		return listaMayorSancionados;
	}

	public void setListaMayorSancionados(ListaAsignaturasMayorCantidadSancionados listaMayorSancionados) {
		this.listaMayorSancionados = listaMayorSancionados;
	}

	public List<ListaAsignaturasMayorCantidadSancionados> getListaAsig() {
		return listaAsigMayor;
	}

	public void setListaAsig(List<ListaAsignaturasMayorCantidadSancionados> listaAsig) {
		this.listaAsigMayor = listaAsig;
	}
	//===============================FIN DE LOS METODOS SET Y GET==============================
	
	
	@GlobalCommand("configChanged") 
	@NotifyChange("type")
	public void onConfigChanged(
			@BindingParam("type")String type){
		this.type = type;
	}
	
	
	//@@@@@@@@@@@@@@@@@METODO PARA CARGAR COMBOS TANTO DE PROGRAMA COMO LAPSO ACADEMICO@@@@@@@@@@@@@@@@@@@
	@Command
	@NotifyChange({ "listaComboPrograma" })
	public void buscarProgramaAcademico() {
		setListaComboPrograma(servicioprogramaacademico.listadoProgramas());
	}
	@Command
	 @NotifyChange({"listaComboPrograma"})
	public ProgramaAcademico objetoComboPrograma() {
		return programaAcademico;
		
	}
	@Command
	@NotifyChange({ "listaComboLapsoFinal" })
	public void buscarLapsoAcademicoFinal() {
		setListaComboLapsoFinal(spp.listadoLapsoAcademico());
	}
	@Command
	 @NotifyChange({"listaComboLapsoFinal"})
	public LapsoAcademico objetoComboLapsoFinal() {
		return lapsoAcademicoFinal;
		
	}
	
	@Command
	@NotifyChange({ "listaLapsoAcademico" })
	public void buscarLapso() {
		setListaLapsoAcademico(spp.listadoLapsoAcademico());
	}
	@Command
	 @NotifyChange({"listaLapsoAcademico"})
	public LapsoAcademico objetoComboLapso() {
		return lapsoAcademico;
		
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@FIN DEL METODO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	
	//################METODO DE CONSULTA CON PARAMETROS################
	@Command
	@NotifyChange({ "listaAsig" })
	public void buscarListaAsigMayorSancionados(ListaAsignaturasMayorCantidadSancionados listaMayorSancionados) {
		listaAsigMayor= servicioListaAsignaturasMayor.buscarAsignaturasSancionados(programaAcademico.getIdPrograma(),lapsoAcademico.getCodigoLapso(), lapsoAcademicoFinal.getCodigoLapso());
	}
	//#####################FIN DEL METODO###############################
	
	
	
	// ###############METODO PARA IMPRIMIR REPORTE#################
	@Command
	public void GenerarReporteAsigMayor() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/SIGAREP-BD","postgres","postgres");
			con.setAutoCommit(false);
		} catch (ClassNotFoundException el) {
			el.printStackTrace();
		}
		
		try {
			String reporte= "C:/Reportes/RpAsignaturasMayorSancionados.jrxml";
			
			parametros.put("varprograma",programaAcademico.getIdPrograma());
			parametros.put("varcod1",lapsoAcademico.getCodigoLapso()); //Parametro del reporte
			parametros.put("varcod2",lapsoAcademicoFinal.getCodigoLapso());
			JasperReport jasperReport= JasperCompileManager.compileReport(reporte);
			JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parametros,con);
		
			JasperViewer.viewReport(jasperPrint);
			con.close();
		} catch (JRException e){
			e.printStackTrace();
		}
	}
	//#####################FIN DEL METODO##########################
}
