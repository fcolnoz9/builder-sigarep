package sigarep.viewmodels.transacciones;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;

/**CargarAsignaturas por XML 
 * Maneja lo que respecta la carga de asignaturas mediante el archivo XML
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarAsignaturasXml {
	private Integer tamanoXML,id_programa,unidades_academicas,programa;
	private String codigo_asignatura,nombre_asignatura,textoXML;//datos de la Asignatura
	private Boolean estatus,procesado;//estatus de la Asignatura
	@WireVariable 
	private ServicioAsignatura servicioAsignatura;
	@WireVariable 
	private ServicioProgramaAcademico servicioprogramaacademico;
    private List<Asignatura> listaAsignatura;//Lista de Asignaturas
	private Media media;//Archivo de tipo media que soporta la extension Xml
	
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	// Sets y gets 
	public Integer getTamanoXML() {
		return tamanoXML;
	}
	public void setTamanoXML(Integer tamanoXML) {
		this.tamanoXML = tamanoXML;
	}
	public Integer getId_programa() {
		return id_programa;
	}
	public void setId_programa(Integer id_programa) {
		this.id_programa = id_programa;
	}
	public Integer getUnidades_academicas() {
		return unidades_academicas;
	}
	public void setUnidades_academicas(Integer unidades_academicas) {
		this.unidades_academicas = unidades_academicas;
	}
	public String getCodigo_asignatura() {
		return codigo_asignatura;
	}
	public void setCodigo_asignatura(String codigo_asignatura) {
		this.codigo_asignatura = codigo_asignatura;
	}
	public String getNombre_asignatura() {
		return nombre_asignatura;
	}
	public void setNombre_asignatura(String nombre_asignatura) {
		this.nombre_asignatura = nombre_asignatura;
	}
	public String getTextoXML() {
		return textoXML;
	}
	public void setTextoXML(String textoXML) {
		this.textoXML = textoXML;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public ServicioAsignatura getServicioAsignatura() {
		return servicioAsignatura;
	}
	public void setServicioAsignatura(ServicioAsignatura servicioAsignatura) {
		this.servicioAsignatura = servicioAsignatura;
	}
	public List<Asignatura> getListaAsignatura() {
		return listaAsignatura;
	}
	public void setListaAsignatura(List<Asignatura> listaAsignatura) {
		this.listaAsignatura = listaAsignatura;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public MensajesAlUsuario getMensajeAlUsuario() {
		return mensajeAlUsuario;
	}
	public void setMensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario) {
		this.mensajeAlUsuario = mensajeAlUsuario;
	}
	
	
	
	/** listaEstudiante
	  * @param listaEstudiante
	  * @return La listaEstudiante cargada con los sancionados
	  * 
	  */
	@Command
	@NotifyChange({"listaAsignatura"})
	public void listaAsignatura()
	{
		listaAsignatura=servicioAsignatura.listaAsignaturas();
	}
	
	@Init
	public void init()
	{
		listaAsignatura();
	}
	/** Unico punto de entrada.
	  * @param UploadEvent event Zkoss UI
	  * @return No devuelve ningun valor.
	  * @throws las Excepciones son que el Archivo Xml no cumpla con el formato,este Corrupto o Ya la los Datos del Estudiante existen.
	  */
	@SuppressWarnings("unused")
	@Command
	@NotifyChange({"textoXML","tamanoXML","listaAsignatura"})
	public void cargarAsignatura(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		int i;
		procesado=true;
		if (media != null) {
			if (media.getFormat().equals("xml")) {
				String dataXml = media.getStringData();//le pide al archivo media la data en string para guardarla en la variable DataXml
				SAXBuilder saxBuilder = new SAXBuilder();
				XMLOutputter output = new XMLOutputter();
				try {
					Document doc = saxBuilder.build(new StringReader(dataXml));// transformar y construir la DataXml con el formato del XML, usa la libreria Jdom-2.0.3 para poder usar sus propiedades
					Element rootNode = doc.getRootElement(); 
					@SuppressWarnings("rawtypes")
					List list = rootNode.getChildren("Asignatura");
					tamanoXML = list.size();
					for (i = 0; i < list.size(); i++) {
						Element node = (Element) list.get(i);
						codigo_asignatura = node.getAttribute("codigo_asignatura").getValue();
						nombre_asignatura = node.getChildText("nombre_asignatura");
						unidades_academicas =Integer.parseInt(node.getChildText("unidades_academicas"));
						id_programa = Integer.parseInt(node.getChildText("id_programa"));
						if (node.getChildText("estatus").equals("true")) {
							estatus=true;
						} else {
							estatus=false;
						}
						
						ProgramaAcademico programaacademico = new ProgramaAcademico();
						programaacademico = servicioprogramaacademico.buscarPrograma(id_programa);//busca el programa academico
						if (programaacademico != null) {
							Asignatura asignatura = new Asignatura(codigo_asignatura, estatus,nombre_asignatura,unidades_academicas,programaacademico);
							servicioAsignatura.guardarAsignatura(asignatura);//Se guarda la asignatura
						} else {
							procesado=false;
							programa=id_programa;
						}
				  }
					if (list.size() > 0){
						listaAsignatura();
						if (procesado==true){
							mensajeAlUsuario.informacionOperacionExitosa();	
						} else {
							mensajeAlUsuario.advertenciaProgramaNoRegistrado(programa);
						}
					} else {
						mensajeAlUsuario.errorContenidoXMLNoValido();
					}
				
						
				} catch (JDOMException e) {
					// handle JDOMException
				} catch (IOException e) {
					// handle IOException
				}
			}
			else{
				mensajeAlUsuario.errorNoEsXML();
			}
		}
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
	@NotifyChange({"textoXML","tamanoXML","listaAsignatura"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana,condicion);		
	}
	
}//fin VMCargarAsignaturasXml 

