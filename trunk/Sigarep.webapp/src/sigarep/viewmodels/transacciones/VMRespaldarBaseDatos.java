package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.Soporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRespaldarBaseDatos {	

	MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	String ruta = UtilidadesSigarep.obtenerDirectorio();
	String descripcion;
	String txtRuta = "";
	String nombreRespaldo;
	String fecha;
	String selected = "";
	//Accediendo a los archivos en el directorio
	FileInputStream lector;
	FileOutputStream escritor;
	File directorio = null;
	String[] listaDirectorio2 = null;
	List<String> listaDirectorio1 = new ArrayList<String>();
	
	private Documento doc = new Documento();
	private Media media;
	
	private MensajesAlUsuario mensajeAlUsuario;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String[] getListaDirectorio2() {
		return listaDirectorio2;
	}
	
	public void setListaDirectorio2(String[] listaDirectorio2) {
		this.listaDirectorio2 = listaDirectorio2;
	}

	public String getTxtRuta() {
		return txtRuta;
	}

	public void setTxtRuta(String txtRuta) {
		this.txtRuta = txtRuta;
	}

	public String getNombreRespaldo() {
		return nombreRespaldo;
	}

	public void setNombreRespaldo(String nombreRespaldo) {
		this.nombreRespaldo = nombreRespaldo;
	}
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public List<String> getListaDirectorio1() {
		return listaDirectorio1;
	}

	public void setListaDirectorio1(List<String> listaDirectorio1) {
		this.listaDirectorio1 = listaDirectorio1;
	}

	@Init
	public void init() {
		// initialization code
		cargarDirectorioSVN();
	}
	

	@Command
	@NotifyChange({"listaDirectorio2","listaDirectorio1","directorio"})
	public void cargarDirectorioSVN() {
		directorio = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD");
		listaDirectorio2 = directorio.list();
		for (int j=0; j<listaDirectorio1.size();j++)
		{
			System.out.println(listaDirectorio2[j].toString());
			listaDirectorio1.remove(j);
		}
		
		for (int i=0; i<listaDirectorio2.length;i++)
		{
			if (!(listaDirectorio2[i].equals(".svn")))
			{
				listaDirectorio1.add(listaDirectorio2[i]);
			}
		}
	}
	
	@Command
	@NotifyChange({"txtRuta"})
	public void seleccionarRuta(){
		JFileChooser chooser = new JFileChooser();
		// Titulo que llevara la ventana
		chooser.setDialogTitle("Guardar en...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setVisible(true);
		chooser.showOpenDialog(null);
		//Retornando el directorio destino directorio
		if(chooser.getSelectedFile()!=null){
	 		txtRuta = chooser.getSelectedFile().getPath();
	 	}	
	}
	
	@NotifyChange({ "txtRuta", "ruta", "descripcion","selected","listaDirectorio1","listaDirectorio2","directorio"})
	@Command
	public void guardarRespaldo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("aplication") WebApp application){
		if(descripcion.equals("")){
			mensajeAlUsuario.advertenciaEscribirNombreDeRespaldo();
		}
		else if (selected.equals("local")){
		//Aqui hay que pasar la ruta local para el método donde esta el código para respaldar
		//esta ruta es para la carpeta respaldos
		//Asi es para guardar directamente en el proyecto
		respaldarBD(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD",descripcion,application);
		limpiar();
		groupboxDispositivo.setVisible(false);
		//Asi guarda en .metadata
		//respaldarBD(application.getRealPath("/WEB-INF/sigarep/administracionBaseDatos/respaldosBD"),txtdescripcion.getValue());
		}else if(selected.equals("dispositivo")){
			respaldarBD(txtRuta,descripcion,application);
			limpiar();
			groupboxDispositivo.setVisible(false);
		}else{
			mensajeAlUsuario.advertenciaSeleccionarDestinoRespaldo();
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void respaldarBD(final String path2, final String namespace, WebApp application){
		
		try {
	        final Properties props = new Properties();
	        System.out.println("aplication: "+application.getAppName());
			lector = new java.io.FileInputStream(application.getRealPath("WEB-INF/sigarep/configuracionbd.properties"));
			props.load(lector);
			lector.close();
			
			Date ahora = new Date();
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("ddMMyyyy");
			fecha =  sdf.format(ahora);
			
			String nroRespaldo = props.getProperty("nrorespaldo");
			
			if (Integer.valueOf(nroRespaldo)!=5){
				Object[] arg = new Object[] { new Date(),Integer.valueOf(nroRespaldo) + 1 };
				nombreRespaldo = String.format(fecha, arg);		
				nombreRespaldo = "/" + namespace + "-" + nombreRespaldo + ".backup";
				props.setProperty("nrorespaldo", String.valueOf(Integer.valueOf(nroRespaldo) + 1));
				escritor = new java.io.FileOutputStream(application.getRealPath("WEB-INF/sigarep/configuracionbd.properties"));
				System.out.println(escritor.toString());
				props.store(escritor, "Incremento");
				ejecutarComandos(nombreRespaldo, props, path2);
			}else{
				//Cuando existen mas de 5 respaldos de la BD
				Messagebox.show("Se ha alcanzado el limite de respaldos,¿Desea reemplazar alguno de los ya existentes?",
				"Confirmar",Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, 
				new org.zkoss.zk.ui.event.EventListener() {
      			public void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						Object[] arg = new Object[] { new Date(), 1 };
						nombreRespaldo = String.format(fecha, arg);		
						nombreRespaldo = "/" + namespace + "-" + nombreRespaldo + ".backup";
						ejecutarComandos(nombreRespaldo, props, path2); 								
					}
				}
				});
			
			}
		   }catch (Exception e) {
			   mensajesAlUsuario.informacionRespaldoNoExitosa();
			}
	}
	
	@Command
	public void ejecutarComandos(String nombreRespaldo, Properties props, String path2){
		//Ruta del respaldo y nombre del respaldo
        String path3 = path2 + nombreRespaldo;
        System.out.println("Ruta: " + path3);
        Runtime r = Runtime.getRuntime();;
        Process p;
        ProcessBuilder pb;
        List<String> lista = new ArrayList<String>();
        lista.add(props.getProperty("pgdump"));
        lista.add("-i"); 
		lista.add("-h"); 
		lista.add(props.getProperty("host")); 
		lista.add("-p"); 
		lista.add(props.getProperty("puerto")); 
		lista.add("-U"); 
		lista.add(props.getProperty("usuario")); 
		lista.add("-F"); 
		lista.add("c"); 
		lista.add("-b"); 
		lista.add("-v"); 
		lista.add("-f"); 
		lista.add(path3);
		lista.add(props.getProperty("nombrebd"));
       
        pb = new ProcessBuilder(lista);
        pb.environment().put("PGPASSWORD", props.getProperty("contrasenna"));
        pb.redirectErrorStream(true);
        try {
			p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mensajesAlUsuario.informacionRespaldoExitoso();
	}
	
	@Command
	public void buscarBackups(@BindingParam("listaBackups") Listbox listaBackups){
		String archivoSelec = listaBackups.getSelectedItem().getValue();
	}
	
	
	@Command
	public void habilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo) {
		groupboxDispositivo.setVisible(true);
	}
	
	@Command
	public void deshabilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo) {
		groupboxDispositivo.setVisible(false);
	}
	
	@NotifyChange({ "txtRuta", "descripcion","selected","listaDirectorio1","listaDirectorio2","directorio"})
	@Command
	public void limpiar(){
		txtRuta = "";
		descripcion = "";
		selected = "";
		listaDirectorio2 = null;
		directorio = null;
		cargarDirectorioSVN();
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	
	@NotifyChange({"descripcion","selected"})
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(!descripcion.equals("") || !selected.equals(""))
			condicion = true;
        mensajeAlUsuario.confirmacionCerrarVentanaSimple(ventana, condicion);		
	}
}