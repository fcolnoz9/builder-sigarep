package sigarep.viewmodels.transacciones;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
/**CargarEstudiante por XML
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarEstudiantesSancionadosXml {
	private Integer tamanoXML,id_programa;
	private String primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,email,telefono,cedula_estudiante,aux_fecha_nacimiento,aux_anio_ingreso,textoXML,sexo;//datos del Estudiante
	private Boolean estatus;//estatus del estudiante
    private Date fecha_nacimiento,anio_ingreso;
	@WireVariable 
	private  ServicioEstudiante servicioestudiante;
    private List<EstudianteSancionado> listaEstudiante;//Lista de Estudiante
	@WireVariable 
	private ServicioProgramaAcademico servicioprogramaacademico;
	//Estudiante sancionado
	private String codigo_lapso,lapsos_academicos_RP;
	private Integer unidades_cursadas,unidades_aprobadas,semestre,id_sancion;
	private float indice_grado;
	//Asignatura Estudiante Sancionado
	private Integer condicion_asignatura_1,condicion_asignatura_2,condicion_asignatura_3,periodo_sancion;
	private String codigo_asignatura_1,codigo_asignatura_2,codigo_asignatura_3;
	@WireVariable 
	private ServicioAsignatura servicioAsignatura;
	@WireVariable 
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado ;
	private LapsoAcademico lapsoAcademico;
	private SancionMaestro sancionMaestro;
	@WireVariable 
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable 
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable private ServicioEstudianteSancionado servicioestudiantesancionado;
	private Media media;//Archivo de tipo media que soporta la extension Xml
	
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	// Sets y gets 
	public String getTextoXML() {
		return textoXML;
	}
	public void setTextoXML(String textoXML) {
		this.textoXML = textoXML;
	}
	public Integer getTamanoXML() {
		return tamanoXML;
	}
	public void setTamanoXML(Integer tamanoXML) {
		this.tamanoXML = tamanoXML;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public List<EstudianteSancionado> getListaEstudiante() {
		return listaEstudiante;
	}
	public void setListaEstudiante(List<EstudianteSancionado> listaEstudiante) {
		this.listaEstudiante = listaEstudiante;
	}
	/** listaEstudiante
	  * @param listaEstudiante
	  * @return La listaEstudiante cargada con los sancionados
	  * 
	  */
	@Command
	@NotifyChange({"listaEstudiante"})
	public void listaEstudiante()
	{
		listaEstudiante=servicioestudiantesancionado.buscarTodos();
	}
	@Init
	public void init()
	{
		listaEstudiante();
	}
	/** Unico punto de entrada.
	  * @param UploadEvent event Zkoss UI
	  * @return No devuelve ningun valor.
	  * @throws las Excepciones son que el Archivo Xml no cumpla con el formato,este Corrupto o Ya la los Datos del Estudiante existen.
	  */
	@SuppressWarnings("unused")
	@Command
	@NotifyChange({"textoXML","tamanoXML","listaEstudiante"})
	public void cargarEstudiante(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
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
					List list = rootNode.getChildren("Estudiante");
					tamanoXML = list.size();
					for (i = 0; i < list.size(); i++) {
						Element node = (Element) list.get(i);
						cedula_estudiante = node.getAttribute("cedula_estudiante").getValue();
						primer_nombre = node.getChildText("primer_nombre");
						segundo_nombre = node.getChildText("segundo_nombre");
						primer_apellido = node.getChildText("primer_apellido");
						segundo_apellido = node.getChildText("segundo_apellido");
						sexo = node.getChildText("sexo");
						aux_fecha_nacimiento = node.getChildText("fecha_nacimiento");
						SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");// para darle formato a la fecha					
						fecha_nacimiento = null;
						try {
							fecha_nacimiento = formatoDelTexto.parse(aux_fecha_nacimiento);//parse a la fecha para colocarla en el formato "yyyy-MM-dd"
						} catch (ParseException ex) {
							ex.printStackTrace();
						}
						email = node.getChildText("email");
						telefono = node.getChildText("telefono");
						id_programa = Integer.parseInt(node.getChildText("id_programa"));
						aux_anio_ingreso = node.getChildText("anio_ingreso");
						anio_ingreso = null;
						try {
							anio_ingreso = formatoDelTexto.parse(aux_anio_ingreso);
						} catch (ParseException ex) {
							ex.printStackTrace();
						}
						if (node.getChildText("estatus").equals("true")) {
							estatus=true;
						} else {
							estatus=false;
						}
						codigo_lapso=node.getChildText("codigo_lapso");//datos del estudiante Sancionado
						indice_grado=Float.parseFloat(node.getChildText("indice_grado"));
						lapsos_academicos_RP=node.getChildText("lapsos_academicos_RP");
						id_sancion=Integer.parseInt(node.getChildText("id_sancion"));
						unidades_cursadas=Integer.parseInt(node.getChildText("unidades_cursadas"));
						unidades_aprobadas=Integer.parseInt(node.getChildText("unidades_aprobadas"));
						semestre=Integer.parseInt(node.getChildText("semestre"));
						periodo_sancion=Integer.parseInt(node.getChildText("periodo_sancion"));
						ProgramaAcademico programaacademico = new ProgramaAcademico();
						programaacademico = servicioprogramaacademico.buscarPrograma(id_programa);//busca el programa academico
						Estudiante estudiante = new Estudiante(cedula_estudiante, anio_ingreso, email,estatus, fecha_nacimiento, primer_apellido,primer_nombre, segundo_apellido,segundo_nombre, sexo, telefono,programaacademico);
						servicioestudiante.guardarEstudiante(estudiante);//Se guarda el estudiante
						EstudianteSancionadoPK id =new EstudianteSancionadoPK();
						id.setCedulaEstudiante(cedula_estudiante);
						id.setCodigoLapso(codigo_lapso);
						EstudianteSancionado estudiante_san=new EstudianteSancionado();
						estudiante_san=servicioestudiantesancionado.buscar(id);
						sancionMaestro = new SancionMaestro();
						sancionMaestro =serviciosancionmaestro.buscarUnaSancion(id_sancion);
						EstudianteSancionado estudiante_sancionado;
						lapsoAcademico=new LapsoAcademico();
						lapsoAcademico= serviciolapsoacademico.buscarUnLapsoAcademico(codigo_lapso);
						estudiante_sancionado=new EstudianteSancionado(id,indice_grado,lapsos_academicos_RP,semestre,unidades_aprobadas,unidades_cursadas,estudiante,lapsoAcademico,sancionMaestro,estatus,periodo_sancion);
						servicioestudiantesancionado.guardar(estudiante_sancionado);  
									   //las materias del Estudiante Sancionado. 
								if(!node.getChildText("codigo_asignatura_1").equals("0")){//Si es Igual a 0 es porque no tiene Materias, este valor viene del Xml
								   codigo_asignatura_1= node.getChildText("codigo_asignatura_1");
								   condicion_asignatura_1= Integer.parseInt(node.getChildText("condicion_asignatura_1"));
								   AsignaturaEstudianteSancionadoPK asignatura_est_san_1=new AsignaturaEstudianteSancionadoPK();
								   asignatura_est_san_1.setCedulaEstudiante(cedula_estudiante);
								   asignatura_est_san_1.setCodigoAsignatura(codigo_asignatura_1);
								   asignatura_est_san_1.setCodigoLapso(codigo_lapso);
								   Asignatura asignatura=servicioAsignatura.buscarAsignatura(codigo_asignatura_1);
								   AsignaturaEstudianteSancionado asignaturaSancionado =new AsignaturaEstudianteSancionado(asignatura_est_san_1,condicion_asignatura_1,asignatura,estudiante_sancionado);
								   servicioasignaturaestudiantesancionado.guardarAsignaturaEstudianteSancionado(asignaturaSancionado);
									   if(!node.getChildText("codigo_asignatura_2").equals("0")){
										   codigo_asignatura_2= node.getChildText("codigo_asignatura_2");
										   condicion_asignatura_2= Integer.parseInt(node.getChildText("condicion_asignatura_2"));
										   AsignaturaEstudianteSancionadoPK asignatura_est_san_2=new AsignaturaEstudianteSancionadoPK();
										   asignatura_est_san_2.setCedulaEstudiante(cedula_estudiante);
										   asignatura_est_san_2.setCodigoAsignatura(codigo_asignatura_2);
										   asignatura_est_san_2.setCodigoLapso(codigo_lapso);
										   Asignatura asignatura2=servicioAsignatura.buscarAsignatura(codigo_asignatura_2);
										   AsignaturaEstudianteSancionado asignaturaSancionado2 =new AsignaturaEstudianteSancionado(asignatura_est_san_2,condicion_asignatura_2,asignatura2,estudiante_sancionado);
										   servicioasignaturaestudiantesancionado.guardarAsignaturaEstudianteSancionado(asignaturaSancionado2);
										   if(!node.getChildText("codigo_asignatura_3").equals("0")){
											   codigo_asignatura_3= node.getChildText("codigo_asignatura_3");
											   condicion_asignatura_3= Integer.parseInt(node.getChildText("condicion_asignatura_3"));
											   AsignaturaEstudianteSancionadoPK asignatura_est_san_3=new AsignaturaEstudianteSancionadoPK();
											   asignatura_est_san_3.setCedulaEstudiante(cedula_estudiante);
											   asignatura_est_san_3.setCodigoAsignatura(codigo_asignatura_3);
											   asignatura_est_san_3.setCodigoLapso(codigo_lapso);
											   Asignatura asignatura3=servicioAsignatura.buscarAsignatura(codigo_asignatura_3);
											   AsignaturaEstudianteSancionado asignaturaSancionado3 =new AsignaturaEstudianteSancionado(asignatura_est_san_3,condicion_asignatura_3,asignatura3,estudiante_sancionado);
											   servicioasignaturaestudiantesancionado.guardarAsignaturaEstudianteSancionado(asignaturaSancionado3);
										   }
									   }
								   }
								   estudiante_sancionado=null;
//						} catch (Exception e) {
//							e.printStackTrace();
//							Messagebox.show("Ocurrio Un error!","ERROR", Messagebox.OK,Messagebox.ERROR);
//							// TODO: handle exception
//						}
					}
					listaEstudiante();
					mensajeAlUsuario.informacionOperacionExitosa();	
				} catch (JDOMException e) {
					// handle JDOMException
				} catch (IOException e) {
					// handle IOException
				}
			}
			else{
				mensajeAlUsuario.ErrorNoEsXML();
			}
		}
	}
}

