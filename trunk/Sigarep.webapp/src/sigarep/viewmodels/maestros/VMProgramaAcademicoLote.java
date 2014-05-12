package sigarep.viewmodels.maestros;

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
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;

/** Clase VMProgramaAcademicoLote : Clase ViewModels 
relacionada con el Maestro ProgramaAcademico.

 * @author Equipo Builder
 * @version 2.5.2
 * @since 02-/12/13
 * @last 09/05/2014
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMProgramaAcademicoLote {
	//-----------------Servicios----------------------------
	@WireVariable 
	private ServicioProgramaAcademico servicioprogramaacademico;
	//-----------------Variables ProgramaAcademicoLote -----
	private Integer tamanoXML,id_programa;
	private String nombre_programa,textoXML;//datos del programa academico
	private Boolean estatus_programa;//estatus del programa
	private Media media;//Archivo de tipo media que soporta la extension Xml
	//-----------------Variables Lista----------------------
	private List<ProgramaAcademico> listaPrograma;//Lista de Programas
	//-----------------Variables Objeto---------------------
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	//Métodos Sets y Gets 
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

	public String getNombre_programa() {
		return nombre_programa;
	}

	public void setNombre_programa(String nombre_programa) {
		this.nombre_programa = nombre_programa;
	}

	public String getTextoXML() {
		return textoXML;
	}

	public void setTextoXML(String textoXML) {
		this.textoXML = textoXML;
	}

	public Boolean getEstatus_programa() {
		return estatus_programa;
	}

	public void setEstatus_programa(Boolean estatus_programa) {
		this.estatus_programa = estatus_programa;
	}

	public ServicioProgramaAcademico getServicioprogramaacademico() {
		return servicioprogramaacademico;
	}

	public void setServicioprogramaacademico(
			ServicioProgramaAcademico servicioprogramaacademico) {
		this.servicioprogramaacademico = servicioprogramaacademico;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
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
	
	// Fin de los Métodos Sets y Gets

	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param ninguno
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init()
	{
		listaPrograma();
	}

	/** listaProgramas
	 * 
	 * @param Ninguno
	 * @return Objeto ProgramaAcademico
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	@Command
	@NotifyChange({"listaPrograma"})
	public void listaPrograma()
	{
		listaPrograma=servicioprogramaacademico.listadoProgramas();
	}


	/**  cargarAsignatura.
	 * 
	 * @param @ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event
	 * @return Ninguno.
	 * @throws si el Archivo Xml no cumpla con el formato,este Corrupto o Ya la los Datos del Estudiante existen.
	 */
	@SuppressWarnings("unused")
	@Command
	@NotifyChange({"textoXML","tamanoXML","listaPrograma"})
	public void cargarAsignatura(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		int i;
		if (media != null) {
			if (media.getFormat().equals("xml")) {
				String dataXml = media.getStringData();//le pide al archivo media la data en string para guardarla en la variable DataXml
				SAXBuilder saxBuilder = new SAXBuilder();
				XMLOutputter output = new XMLOutputter();
				try {
					Document doc = saxBuilder.build(new StringReader(dataXml));// transformar y construir la DataXml con el formato del XML, usa la libreria Jdom-2.0.3 para poder usar sus propiedades
					Element rootNode = doc.getRootElement(); 
					@SuppressWarnings("rawtypes")
					List list = rootNode.getChildren("Programa");
					tamanoXML = list.size();
					for (i = 0; i < list.size(); i++) {
						Element node = (Element) list.get(i);
						id_programa =Integer.parseInt( node.getAttribute("id_programa").getValue());
						nombre_programa = node.getChildText("nombre_programa");
						if (node.getChildText("estatus_programa").equals("true")) {
							estatus_programa=true;
						} else {
							estatus_programa=false;
						}
						ProgramaAcademico programaacademico = new ProgramaAcademico(id_programa,nombre_programa,estatus_programa);
						servicioprogramaacademico.guardarPrograma(programaacademico);//Se guarda el programa


					}
					if (list.size() > 0){
						listaPrograma();
						mensajeAlUsuario.informacionOperacionExitosa();	
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
	 * Cerrar Ventana : Cierra el .zul asociado al VM. 
	 * @param Window ventana
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"textoXML","tamanoXML","listaPrograma"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana,condicion);		
	}
}
