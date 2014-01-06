package sigarep.viewmodels.transacciones;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarEstudiantesSancionadosXml {
	private Document document;
	private String textoXML;
	private Integer tamanoXML;
	private String  extensionArchivo="xml";
	private String primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,email,telefono,cedula_estudiante,aux_fecha_nacimiento,aux_anio_ingreso;
	private String sexo;
	private Boolean estatus;
    private Date fecha_nacimiento,anio_ingreso;
    private Integer id_programa;
	public 	JFileChooser selector2;
	@WireVariable 
	private ServicioEstudiante servicioestudiante;
	@WireVariable
	private Estudiante estudiante;
	@WireVariable 
	private ProgramaAcademico programacademico;
	
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
	@Command
	@NotifyChange({"textoXML","tamanoXML"})
	public void carga(){
		JFileChooser selector=new JFileChooser();
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("Archivos XML","xml");
	    selector.setFileFilter(filtro);
	    int r=selector.showOpenDialog(null);
		if(r==JFileChooser.APPROVE_OPTION){
			if(selector.getFileFilter()==filtro){
				leerXml(selector.getSelectedFile());
			System.out.println("extension :"+filtro.getExtensions());
			}
			else{
				Messagebox.show("La Extension del Archivo no es XML", "ERROR",
						Messagebox.OK, Messagebox.ERROR);
				System.out.println("extension :"+filtro.getExtensions());
			}
		}
	}
	@Command
	@NotifyChange({"textoXML","tamanoXML"})
	public String soloLecturaXml(File f)
	{
		String datos="";
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			document = saxBuilder.build(f);
			XMLOutputter output = new XMLOutputter();
			textoXML=(output.outputString(document));
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
		
	@Command
	@NotifyChange({"texto"})
	public String leerXml(File f){
		String datos="";
		int tamaño=0;
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			document = saxBuilder.build(f);
			XMLOutputter output = new XMLOutputter();
			System.out.println("Data Archivo"+output.outputString(document));
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("Estudiante");
			System.out.println("Tamaño L");
			for (int i = 0; i < list.size(); i++) {
				   Element node = (Element) list.get(i);
//				   System.out.println("Cedula Estudiante : " + node.getAttribute("cedula_estudiante").getValue());
//				   System.out.println("Primer Nombre : " + node.getChildText("primer_nombre"));
//				   System.out.println("Segundo_nombre : " + node.getChildText("segundo_nombre"));
//				   System.out.println("primer_apellido : " + node.getChildText("primer_apellido"));
//				   System.out.println("segundo_apellido : " + node.getChildText("segundo_apellido"));
//				   System.out.println("sexo : " + node.getChildText("sexo"));
//				   System.out.println("fecha_nacimiento : " + node.getChildText("fecha_nacimiento"));
//				   System.out.println("email : " + node.getChildText("email"));
//				   System.out.println("telefono : " + node.getChildText("telefono"));
//				   System.out.println("id_programa : " + node.getChildText("id_programa"));
//				   System.out.println("anio_ingreso : " + node.getChildText("anio_ingreso"));
//				   System.out.println("estatus : " + node.getChildText("estatus"));
				   cedula_estudiante=node.getAttribute("cedula_estudiante").getValue();
				   primer_nombre=node.getChildText("primer_nombre");
				   segundo_nombre=node.getChildText("segundo_nombre");
				   primer_apellido=node.getChildText("primer_apellido");
				   segundo_apellido=node.getChildText("segundo_apellido");
				   sexo=node.getChildText("sexo");
				   aux_fecha_nacimiento=node.getChildText("fecha_nacimiento");
				   SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");//para darle formato a la fecha para poder
				   fecha_nacimiento=null;
				   try {
					   fecha_nacimiento = formatoDelTexto.parse(aux_fecha_nacimiento);
					   } catch (ParseException ex) {
					   ex.printStackTrace();
					   }
				   email=node.getChildText("email");
				   telefono=node.getChildText("telefono");
				   id_programa=Integer.parseInt(node.getChildText("id_programa"));
				   aux_anio_ingreso=node.getChildText("anio_ingreso");
				   anio_ingreso=null;
				   try {
					   anio_ingreso = formatoDelTexto.parse(aux_anio_ingreso);
					   } catch (ParseException ex) {
					   ex.printStackTrace();
					   }
//				   if(node.getChildText("estatus")==true"){
					   estatus=true;
//				   }
//				   else{
//				   estatus=false; 
//				   }
				   ProgramaAcademico programaacademico=new ProgramaAcademico();
				   programaacademico.setIdPrograma(id_programa);
				   programaacademico.setEstatusPrograma(true);
				   programaacademico.setNombrePrograma("Informatica");
				   Estudiante estudiante = new Estudiante(cedula_estudiante,anio_ingreso,email,estatus,fecha_nacimiento,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,sexo,telefono,programaacademico);
				   servicioestudiante.guardarPrograma(estudiante);
				}
			System.out.println("Tamaño del Xml :"+tamaño);
			textoXML=(output.outputString(document));
			//System.out.println("texttoooo"+texto);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
}
