package sigarep.viewmodels.reportes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.transacciones.RecaudoEntregado;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMModalInformeCU {
	@Wire("#modalDialog")
	private Window window;
	private String apellidos;
	private String nombres;
	private String codigoLapso;
	private String programa;
	private Date fecha_comision;
	private String observacion_comision;
	private String sugerencia;
	private String codigo_sesion;
	private Date fecha_d;
	private String observacion_consejo_decanato;
	private String veredicto;
	private String sancion;
	private Date fecha_ingreso;
	private int semestre;
	private int unidades_cursadas;
	private int unidades_aprobadas;
	private int unidades_reprobadas;
	private float indice_grado;
	
	private String cedula;

	private List<RecaudoEntregado> listaRecaudos1 = new LinkedList<RecaudoEntregado>();
	private List<RecaudoEntregado> listaRecaudos2 = new LinkedList<RecaudoEntregado>();

	
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();			

	
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
	String ruta="/WEB-INF/sigarepReportes/informes/estructurados/RpInformeConsejoUniversitario.jasper";
	
	//Metodos GET y SET
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Date getFecha_comision() {
		return fecha_comision;
	}

	public void setFecha_comision(Date fecha_comision) {
		this.fecha_comision = fecha_comision;
	}

	public String getObservacion_comision() {
		return observacion_comision;
	}

	public void setObservacion_comision(String observacion_comision) {
		this.observacion_comision = observacion_comision;
	}

	public String getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(String sugerencia) {
		this.sugerencia = sugerencia;
	}

	public String getCodigo_sesion() {
		return codigo_sesion;
	}

	public void setCodigo_sesion(String codigo_sesion) {
		this.codigo_sesion = codigo_sesion;
	}

	public Date getFecha_d() {
		return fecha_d;
	}

	public void setFecha_d(Date fecha_d) {
		this.fecha_d = fecha_d;
	}

	public String getObservacion_consejo_decanato() {
		return observacion_consejo_decanato;
	}

	public void setObservacion_consejo_decanato(
			String observacion_consejo_decanato) {
		this.observacion_consejo_decanato = observacion_consejo_decanato;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getUnidades_cursadas() {
		return unidades_cursadas;
	}

	public void setUnidades_cursadas(int unidades_cursadas) {
		this.unidades_cursadas = unidades_cursadas;
	}

	public int getUnidades_aprobadas() {
		return unidades_aprobadas;
	}

	public void setUnidades_aprobadas(int unidades_aprobadas) {
		this.unidades_aprobadas = unidades_aprobadas;
	}

	public int getUnidades_reprobadas() {
		return unidades_reprobadas;
	}

	public void setUnidades_reprobadas(int unidades_reprobadas) {
		this.unidades_reprobadas = unidades_reprobadas;
	}

	public float getIndice_grado() {
		return indice_grado;
	}

	public void setIndice_grado(float indice_grado) {
		this.indice_grado = indice_grado;
	}

	public List<RecaudoEntregado> getListaRecaudos1() {
		return listaRecaudos1;
	}

	public void setListaRecaudos1(List<RecaudoEntregado> listaRecaudos1) {
		this.listaRecaudos1 = listaRecaudos1;
	}

	public List<RecaudoEntregado> getListaRecaudos2() {
		return listaRecaudos2;
	}

	public void setListaRecaudos2(List<RecaudoEntregado> listaRecaudos2) {
		this.listaRecaudos2 = listaRecaudos2;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	
	@Init
	public void init(

			@ContextParam(ContextType.VIEW) Component view,
					@ExecutionArgParam("cedula") String v1,
					@ExecutionArgParam("codigoLapso") String v2,
					@ExecutionArgParam("nombres") String v3,
					@ExecutionArgParam("apellidos") String v4,
					@ExecutionArgParam("programa") String v5,
					@ExecutionArgParam("sancion") String v6,
					@ExecutionArgParam("semestre") int v7,
					@ExecutionArgParam("unidades_cursadas") int v8,
					@ExecutionArgParam("unidades_aprobadas") int v9,
					@ExecutionArgParam("unidades_reprobadas") int v10,
					@ExecutionArgParam("indice_grado") float v11,
					@ExecutionArgParam("fecha_comision") Date v12,
					@ExecutionArgParam("sugerencia") String v13,
					@ExecutionArgParam("observacion_comision") String v14,
					@ExecutionArgParam("codigo_sesion") String v15,
					@ExecutionArgParam("fecha_d") Date v16,
					@ExecutionArgParam("veredicto") String v17,
					@ExecutionArgParam("observacion_consejo_decanato") String v18,
					@ExecutionArgParam("listaRecaudos1") List<RecaudoEntregado> v19,
					@ExecutionArgParam("listaRecaudos2") List<RecaudoEntregado> v20,
					@ExecutionArgParam("fecha_ingreso") Date v21
					)
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.codigoLapso= v2;
		this.nombres = v3;
		this.apellidos = v4;
		this.programa = v5;
		this.sancion = v6;
		this.semestre = v7;
		this.unidades_cursadas = v8;
		this.unidades_aprobadas = v9;
		this.unidades_reprobadas = v10;
		this.indice_grado = v11;
		this.fecha_comision = v12;
		this.sugerencia = v13;
		this.observacion_comision = v14;
		this.codigo_sesion = v15;
		this.fecha_d = v16;
		this.veredicto = v17;
		this.observacion_consejo_decanato = v18;
		this.listaRecaudos1 = v19;
		this.listaRecaudos2 = v20;
		this.fecha_ingreso = v21;
		
	}
	
	
	@Command("GenerarReporte")
	@NotifyChange({ "reportConfig" })
	public void generarReporte() {

		reportConfig = new ReportConfig(ruta); // INSTANCIANDO UNA NUEVA LLAMADA AL
											// REPORTE
		reportConfig.getParameters().put("cedula_estudiante", cedula);
		reportConfig.getParameters().put("nombres", nombres);
		reportConfig.getParameters().put("apellidos", apellidos);
		reportConfig.getParameters().put("programa", programa);
		reportConfig.getParameters().put("sancion", sancion);
		reportConfig.getParameters().put("fecha_comision", fecha_comision);
		reportConfig.getParameters().put("observacion_comision", observacion_comision);
		reportConfig.getParameters().put("sugerencia", sugerencia);
		reportConfig.getParameters().put("nro_sesion", codigo_sesion);
		reportConfig.getParameters().put("fecha_d", fecha_d);
		reportConfig.getParameters().put("observacion_consejo_decanato", observacion_consejo_decanato);
		reportConfig.getParameters().put("veredicto", veredicto);
		reportConfig.getParameters().put("fecha_ingreso", fecha_ingreso);
		reportConfig.getParameters().put("unidades_cursadas", unidades_cursadas);
		reportConfig.getParameters().put("unidades_aprobadas", unidades_aprobadas);
		reportConfig.getParameters().put("unidades_reprobadas", unidades_reprobadas);
		reportConfig.getParameters().put("indice_academico", indice_grado);
		reportConfig.getParameters().put("listaRecaudosComision", new JRBeanCollectionDataSource(listaRecaudos1));
		reportConfig.getParameters().put("listaRecaudosCD", new JRBeanCollectionDataSource(listaRecaudos2));
		//reportConfig.getParameters().put("listaSanciones", new JRBeanCollectionDataSource());

		reportConfig.setType(reportType); // ASIGNANDO EL TIPO DE FORMATO DE

	}
	

	@Command
	public void closeThis() {
		window.detach();
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
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana,condicion);		
	}

}
