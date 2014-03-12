package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;

import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;


import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRespaldarBaseDatos{	

	/**
	 * 
	 */
	private String ruta = UtilidadesSigarep.obtenerDirectorio();
	private String descripcion = "";
	private String txtRuta = "";
	private String nombreRespaldo;
	private String fecha;
	private String selected = "";
	private FileInputStream lector;
	private FileOutputStream escritor;
	private File directorio = null;
	private String[] listaDirectorio2 = null;
	private List<String> listaDirectorio1 = new ArrayList<String>();
	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();

	/** doAfterCompose.
	 * @parameters view. 
	 * @return No devuelve ningun valor.
	 */
	
	@Wire("#backupsListbox")//para conectarse al listbox con el ID
	Listbox backupsListbox;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	//Metodos SETs Y GETs  
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
	
	//Fin Metodos SETs Y GETs 

	/**
	 * Inicialización
	 * 
	 * @param init
	 * @return Carga de Variables y metodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Init
	public void init() {
		// initialization code
		lector = null;
		escritor = null;
		directorio = null;
		directorio = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD");
		listaDirectorio2 = directorio.list();
		cargarBackups();
	}
	
	/** Cargar backups al modelo del listbox de backups
	 * @parameters listaDirectorio1, listaDirectorio2 y directorio
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	@NotifyChange({"listaDirectorio2","listaDirectorio1","directorio"})
	public void cargarBackups() {
		directorio = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD");
		//Accediendo a los archivos en el directorio
		listaDirectorio2 = directorio.list();
		for (int i=0; i<listaDirectorio2.length;i++)
		{
			if (!(listaDirectorio2[i].equals(".svn")))
			{
				listaDirectorio1.add(listaDirectorio2[i]);
			}
		}
	}
	
	/** Seleccionar la Ruta de respaldo en dispositivo externo
	 * @parameters txtRuta
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	
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
	
	/** Guardar respaldo de la base de datos
	 * @parameters txtRuta, ruta (directorio del proyecto), selected, listaDirectorio1, listaDirectorio2, directorio
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@NotifyChange({ "txtRuta", "ruta", "descripcion","selected","listaDirectorio1","listaDirectorio2","directorio"})
	@Command
	public void guardarRespaldo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("aplication") WebApp application){
		if(descripcion.equals("")){
			mensajesAlUsuario.advertenciaEscribirNombreDeRespaldo();
		}
		else if (selected.equals("local")){
		//Aqui hay que pasar la ruta local para el método donde esta el código para respaldar
		//esta ruta es para la carpeta respaldos
		//Asi es para guardar directamente en el proyecto
		crearRespaldoBD(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD",descripcion,application);
		limpiar(groupboxDispositivo);
		}else if(selected.equals("dispositivo")){
			crearRespaldoBD(txtRuta,descripcion,application);
			limpiar(groupboxDispositivo);
		}else{
			mensajesAlUsuario.advertenciaSeleccionarDestinoRespaldo();
		}
	}
	
	/** Crear respaldo de la BD
	 * @parameters path2, namespace, application, listaDirectorio1, fecha, lector, txtRuta, escritor, backupsListbox
	 * @return No devuelve ningun valor.
	 * @throws Se dispara excepción cuando no existe BD para ser respaldada o cuando la ruta de respaldo seleccionada es invalida .
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@NotifyChange({"listaDirectorio1", "fecha", "lector", "txtRuta", "escritor"})
	@Command
	public void crearRespaldoBD(final String path2, final String namespace, WebApp application){
		try {
	        final Properties props = new Properties();
			lector = new java.io.FileInputStream(application.getRealPath("WEB-INF/sigarep/configuracionbd.properties"));
			props.load(lector);
			lector.close();
			Date ahora = new Date();
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("ddMMyyyy-hh-mm-ss");
			fecha =  sdf.format(ahora);
			props.setProperty("nrorespaldo", String.valueOf(listaDirectorio1.size()));
			String nroRespaldo = props.getProperty("nrorespaldo");
			if (Integer.valueOf(nroRespaldo) < 5){
				Object[] arg = new Object[] { new Date(),Integer.valueOf(nroRespaldo) + 1 };
				nombreRespaldo = String.format(fecha, arg);
				if(!path2.equals(txtRuta))
					listaDirectorio1.add(namespace + "-" + nombreRespaldo + ".backup");
				nombreRespaldo = "/" + namespace + "-" + nombreRespaldo + ".backup";
				props.setProperty("nrorespaldo", String.valueOf(Integer.valueOf(nroRespaldo) + 1));
				escritor = new java.io.FileOutputStream(application.getRealPath("WEB-INF/sigarep/configuracionbd.properties"));
				props.store(escritor, "Incremento");				
				ejecutarComandos(nombreRespaldo, props, path2);
			}else{
				//Cuando existen mas de 5 respaldos de la BD
				Messagebox.show("Se ha alcanzado el limite de respaldos de la BD, el nuevo respaldo reemplazará la copia mas antigua, ¿Desea continuar?",
				"Confirmar",Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, 
				new org.zkoss.zk.ui.event.EventListener() {
				public void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
						Object[] arg = new Object[] { new Date(), 1 };
						nombreRespaldo = String.format(fecha, arg);
						File archivoEliminado = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD/"+listaDirectorio1.remove(0));
						archivoEliminado.delete();
						if(!path2.equals(txtRuta)){
							listaDirectorio1.add(namespace + "-" + nombreRespaldo + ".backup");
							backupsListbox.setModel(new SimpleListModel<String>(listaDirectorio1));
						}
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
	
	/** Ejecutar comandos para la creación de la BD en postgreSQL
	 * @parameters nombreRespaldo, props, path2
	 * @return No devuelve ningun valor.
	 * @throws Se dispara excepción cuando el contructor de procesos no inicia es decir no logra acceder al usuario de postgreSQL
	 */
	
	@Command
	public void ejecutarComandos(String nombreRespaldo, Properties props, String path2){
		//Ruta del respaldo y nombre del respaldo
        String path3 = path2 + nombreRespaldo;
        System.out.println("Ruta: " + path3);
        Runtime r = Runtime.getRuntime();
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
	
	/** HabilitarGroupBoxDispositivo para la ruta externa de respaldo
	 * @parameters groupboxDispositivo
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
		
	@Command
	public void habilitarGroupBoxDispositivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo) {
		groupboxDispositivo.setVisible(true);
	}
	
	/** DeshabilitarGroupBoxDispositivo para la ruta externa de respaldo
	 * @parameters groupboxDispositivo
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	public void deshabilitarGroupBoxDispositivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo) {
		groupboxDispositivo.setVisible(false);
	}
	
	/** Limpiar los campos de texto, los radiobutton y deshabilitar el groupbox de dispositivo
	 * @parameters txtRuta, descripcion, selected, listaDirectorio2, groupboxDispositivo
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@NotifyChange({ "txtRuta", "descripcion","selected","listaDirectorio2"})
	@Command
	public void limpiar(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo){
		txtRuta = "";
		descripcion = "";
		selected = "";
		listaDirectorio2 = null;
		groupboxDispositivo.setVisible(false);
	}
	
	/** Cerrar Ventana
	 * @param ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */
	
	@NotifyChange({"descripcion","selected"})
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(!descripcion.equals("") || !selected.equals(""))
			condicion = true;
        mensajesAlUsuario.confirmacionCerrarVentanaMaestros(ventana, condicion);		
	}
}