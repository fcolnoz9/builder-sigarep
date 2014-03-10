package sigarep.viewmodels.reportes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.reportes.ReportConfig;
import sigarep.modelos.data.reportes.ReportType;
import sigarep.modelos.data.reportes.Sancionados;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.reportes.ServicioReportes;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInformeConsejoDecanato {

	/**VM  Informe Estructurado al Consejo de Decanato
	 * UCLA DCYT Sistemas de Información.
	 * @author Equipo : Builder-Sigarep Lapso 2013-2
	 * @version 1.0
	 */
	//***********************************DECLARACION DE LAS VARIABLES SERVICIOS*************************
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioReportes servicioreportes;
	//***********************************PARÁMETROS PARA SERVICIOS*************************
	@WireVariable
	private String codigoLapso;
	@Wire private String para ="";
	@Wire private String de="";
	@Wire private String contenido="";
	@Wire private String nro="";
	
	private int procedentesInf;
	private int procedentesPro;
	private int procedentesAna;
	private int procedentesMat;
	private int denegadosInf;
	private int denegadosPro;
	private int denegadosAna;
	private int denegadosMat;
	
	
	//***********************************DECLARACIÓN DE LISTAS*************************
	private List<LapsoAcademico> listaLapso;
	private List<Sancionados> listaInformatica = new LinkedList<Sancionados>();
	private List<Sancionados> listaAnalisis = new LinkedList<Sancionados>();
	private List<Sancionados> listaProduccion = new LinkedList<Sancionados>();
	private List<Sancionados> listaMatematicas= new LinkedList<Sancionados>();
	
	//***********************************DECLARACION DE LAS VARIABLES TIPO OBJETO *************************
	private LapsoAcademico objLapso;
	
	//*********************************Mensajes***************************************
		@Wire("#winApelacion")//para conectarse a la ventana con el ID
		Window ventana;
		 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
	    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
	        Selectors.wireComponents(view, this, false);
	    }
		 MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();//Llama a los diferentes mensajes de dialogo
	// *************************INSTANCIANDO LAS CLASES NECESARIAS PARA EL REPORTE***************************
	ReportType reportType = null;
	private ReportConfig reportConfig = null;

	String ruta="/WEB-INF/sigarepReportes/informes/estructurados/RpInformeConsejoDecanato.jasper";
	
	
	@Init
	public void init() {
		buscarLapso();
		
	}
	/** buscar Lapso Académico
	 * @param  
	 * @return lista de Lapso Académico
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaLapso" })
	public void buscarLapso() {
		listaLapso = serviciolapsoacademico.buscarTodosLosLapsos();
	}
	/** Objeto Combo Lapso Académico.
 	* @param Ninguno
 	* @return Objeto Lapso Académico.
 	* @throws No dispara ninguna excepción.
 	*/
	@Command
	@NotifyChange({ "listaLapso" })
	public LapsoAcademico objCmbLapso() {
		return objLapso;

	}
	//Reporte SET/GETS
	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getNro() {
		return nro;
	}
	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> listaLapso) {
		this.listaLapso = listaLapso;
	}

	public LapsoAcademico getObjLapso() {
		return objLapso;
	}

	public void setObjLapso(LapsoAcademico objLapso) {
		this.objLapso = objLapso;
	}

	//===============================FIN DE LOS METODOS SET Y GET==============================
	
		//REPORTE
				/** Muestra los tipos de formatos que puede mostrarse el reporte
				 * @param  
				 * @return modelos de la lista
				 * @throws No dispara ninguna excepción.
				 */
				private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
						Arrays.asList(new ReportType("PDF", "pdf"), new ReportType("HTML",
								"html"), new ReportType("Word (RTF)", "rtf"),
								new ReportType("Excel", "xls"), new ReportType(
										"Excel (JXL)", "jxl"),
								new ReportType("CSV", "csv"), new ReportType(
										"OpenOffice (ODT)", "odt")));


	/** Generar Informe Estructurado al Consejo de Decanato
	* @param Ninguno
	* @return  Informe Estructurado al Consejo de Decanato generado en PDF u otro tipo de archivo
	* @throws Si la lista está vacía no genera el reporte.
	*/	//
	@Command("GenerarReporteApelacionesMotivo")
	@NotifyChange({ "reportConfig","para","de","contenido","tituloinstancia","titulosancion","tituloprograma" })
	public void GenerarReporte() {

		listaInformatica.clear();
		listaAnalisis.clear();
		listaProduccion.clear();
		listaMatematicas.clear();
		
		if(objLapso==null || para.equals("") || nro.equals("") ||de.equals("") || contenido.equals(""))
			mensajeAlUsuario.advertenciaLlenarCampos();
		else{
		
		listaInformatica = servicioreportes.buscarEstudiantesComision(1);
		if(listaInformatica.size()>0){
		procedentesInf = listaInformatica.get(0).getProcedentes();
		denegadosInf = listaInformatica.get(0).getNoProcedentes();
		}
		
		listaProduccion = servicioreportes.buscarEstudiantesComision(2);
		if(listaProduccion.size()>0){
		procedentesPro = listaProduccion.get(0).getProcedentes();
		denegadosPro = listaProduccion.get(0).getNoProcedentes();
		}
		
		listaAnalisis = servicioreportes.buscarEstudiantesComision(3);
		if(listaAnalisis.size()>0){
		procedentesAna = listaAnalisis.get(0).getProcedentes();
		denegadosAna = listaAnalisis.get(0).getNoProcedentes();
		}
		
		listaMatematicas = servicioreportes.buscarEstudiantesComision(4);
		if(listaMatematicas.size()>0){
		procedentesMat = listaMatematicas.get(0).getProcedentes();
		denegadosMat = listaMatematicas.get(0).getNoProcedentes();
		}
		 
		
		reportConfig = new ReportConfig(ruta); 
		reportConfig.getParameters().put("De", de);
		reportConfig.getParameters().put("Para", para);
		reportConfig.getParameters().put("Contenido", contenido);
		reportConfig.getParameters().put("nro", nro);
		reportConfig.getParameters().put("codigoLapso", objLapso.getCodigoLapso());
		reportConfig.getParameters().put("procedentesInf", procedentesInf);
		reportConfig.getParameters().put("denegadosInf", denegadosInf);
		reportConfig.getParameters().put("procedentesAna", procedentesAna);
		reportConfig.getParameters().put("denegadosAna", denegadosAna);
		reportConfig.getParameters().put("procedentesPro", procedentesPro);
		reportConfig.getParameters().put("denegadosPro", denegadosPro);
		reportConfig.getParameters().put("procedentesMat", procedentesMat);
		reportConfig.getParameters().put("denegadosMat", denegadosMat);
		reportConfig.getParameters().put("listaInformatica", (new JRBeanCollectionDataSource(listaInformatica)));
		reportConfig.getParameters().put("listaAnalisis", (new JRBeanCollectionDataSource(listaAnalisis)));
		reportConfig.getParameters().put("listaProduccion", (new JRBeanCollectionDataSource(listaProduccion)));
		reportConfig.getParameters().put("listaMatematicas", (new JRBeanCollectionDataSource(listaMatematicas)));
		reportConfig.setType(reportType); 
		}

	}
	
	
	@Command
	@NotifyChange({"para", "de", "contenido", "nro", "contenido","objLapso"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		
		
		para="";
		de="";
		nro= "";
		contenido="";
		objLapso = null;

	}


	// #####################FIN DEL METODO##########################

	//#####################MENSAJE PARA CERRAR VENTANA##########################
		@Command
		@NotifyChange({ })
		public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
			Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
						Messagebox.QUESTION,new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
							case YES:
									ventana.detach();
						
						
						}
					}
				});		
			}


}
