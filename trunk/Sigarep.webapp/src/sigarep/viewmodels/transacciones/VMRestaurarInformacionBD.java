package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;

/**
* Clase VMRestaurarInformacionBD : ViewModel que proporciona destinos de enlace de datos 
* para la vista RestaurarInformacion.zul 
* Se encarga de manejar los métodos necesarios para poder restaurar una base de datos
* @author Equipo Builder
* @version 1.0
* @since 17/01/2014
* @last 10/05/2014
*/

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRestaurarInformacionBD {	

	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	//---------Variables de control--------
	private String ruta = UtilidadesSigarep.obtenerDirectorio();
	private String txtPaquetesZip = "";
	private String nombreRespaldo;
	private String selected = "";
	//---------Variables Lista-------------
	private String[] listaDirectorio2 = null;
	private List<String> listaDirectorio = new ArrayList<String>();
	//---------Variables Objeto------------
	private Listitem respaldoSeleccionado = null;
	private FileInputStream lector;
	private File directorio = null;
	
	// Métodos Set y Get 
	public String[] getListaDirectorio2() {
		return listaDirectorio2;
	}
	
	public void setListaDirectorio2(String[] listaDirectorio2) {
		this.listaDirectorio2 = listaDirectorio2;
	}

	public String getTxtPaquetesZip() {
		return txtPaquetesZip;
	}

	public void setTxtPaquetesZip(String txtPaquetesZip) {
		this.txtPaquetesZip = txtPaquetesZip;
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
	
	public List<String> getListaDirectorio() {
		return listaDirectorio;
	}

	public void setListaDirectorio(List<String> listaDirectorio) {
		this.listaDirectorio = listaDirectorio;
	}
	
	public Listitem getRespaldoSeleccionado() {
		return respaldoSeleccionado;
	}

	public void setRespaldoSeleccionado(Listitem respaldoSeleccionado) {
		this.respaldoSeleccionado = respaldoSeleccionado;
	}
	//Fin Métodos Set y Get

	/**
	* Init. Código de inicialización.
	* @param Ninguno.
	* @return Objetos inicializados.
	* @throws No dispara ninguna excepción.
	*
	*/
	
	@Init
	public void init() {
		// initialization code
		cargarDirectorioSVN();
	}
	
	/** restaurarInformacion de la base de datos
	 * @param application, groupboxDispositivo, divArchivosLocal, backupseleccionado
	 * @return Restaura la base de datos con el archivo seleccionado, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws Dispara un excepción si la versión de postgresql no es la correcta (9.3).
	 */
	
	@NotifyChange({ "txtPaquetesZip", "ruta","selected","listaDirectorio","listaDirectorio2","directorio","respaldoSeleccionado"})
	@Command
	public void restaurarInformacion(@BindingParam("aplication") WebApp application, @BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal, @BindingParam("backupseleccionado") Listitem backupseleccionado){
		if (selected.equals("local") || selected.equals("dispositivo")){
			try {
				String[] formato = txtPaquetesZip.split(".back");
				if (!(txtPaquetesZip.equals("")) && !formato[formato.length-1].equals("up")) {
					mensajesAlUsuario.informacionRestauracionNoExitosa();
				} else {
					borrarEsquema("sigarep");
					crearEsquema("sigarep");
					Properties props = new Properties();
					lector = new java.io.FileInputStream(
							application.getRealPath("WEB-INF/sigarep/configuracionbd.properties"));
					props.load(lector);
					lector.close();
					Runtime r = Runtime.getRuntime();
					String archivoBD = null;
					if (!(txtPaquetesZip.equals(""))) {
						archivoBD = txtPaquetesZip;
					} else if (backupseleccionado != null) {
						// Trabajando con el proyecto directamente
						if (!backupseleccionado.getLabel().equals(""))
							archivoBD = ruta + "Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD" + "/" + backupseleccionado.getLabel();
					}
					Process p;
					ProcessBuilder pb;
					r = Runtime.getRuntime();
					List<String> lista = new ArrayList<String>();
					lista.add(props.getProperty("pgrestore"));
					lista.add("-i");
					lista.add("-h");
					lista.add(props.getProperty("host"));
					lista.add("-p");
					lista.add(props.getProperty("puerto"));
					lista.add("-U");
					lista.add(props.getProperty("usuario"));
					lista.add("-d");
					lista.add(props.getProperty("nombrebd"));
					lista.add("-v");
					lista.add(archivoBD);
					pb = new ProcessBuilder(lista);
					pb.environment().put("PGPASSWORD",props.getProperty("contrasenna"));
					pb.redirectErrorStream(true);
					p = pb.start();
					mensajesAlUsuario.informacionRestauracionEnProceso();
					limpiar();
					divArchivosLocal.setVisible(false);
					groupboxDispositivo.setVisible(false);
				}	
			} catch (Exception e) {
				mensajesAlUsuario.informacionRestauracionNoExitosa();
			}
		}else{
			mensajesAlUsuario.advertenciaSeleccionarUbicacionRestauracion();
		}	
	}
	
	/**
	* Permite la carga del directorio svn, cargando los archivos backups de la BD a la lista. Inicializa el código.
	*
	* @param Ninguno
	* @return Carga los backups en lista del directorio1, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	* @throws No dispara ninguna excepcion.
	*/
	
	@Command
	@NotifyChange({"listaDirectorio2","listaDirectorio","directorio"})
	public void cargarDirectorioSVN() {
		directorio = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD");
		listaDirectorio2 = directorio.list();
		for (int i=0; i<listaDirectorio2.length;i++)
		{
			if (!(listaDirectorio2[i].equals(".svn")))
			{
				listaDirectorio.add(listaDirectorio2[i]);
				Collections.sort(listaDirectorio);
			}
		}
	}
	
	/**
	* Permite crear un nuevo esquema de la BD en postgres para los nuevos datos. Inicializa el código.
	*
	* @param Ninguno
	* @return Creación del nuevo esquema.
	* @throws Dispara una excepción si no se logra acceder a la BD.
	*/	
	
	public void crearEsquema(String nombreEsquema) {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/SIGAREP-BD";
		String user = "postgres";
		String password = "postgres";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectString, user,password);
			Statement stmt = con.createStatement();
			// Borrando el esquema
			int count = stmt.executeUpdate("CREATE SCHEMA " + nombreEsquema + "");
			System.out.println("Esquema creado.");
			stmt.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	
	/**
	* Permite borrar el esquema de la BD en postgres para la eliminación de los datos. Inicializa el código.
	*
	* @param Ninguno
	* @return Eliminación del antiguo esquema que contenia los datos de la BD.
	* @throws Dispara una excepción si no se logra acceder a la BD.
	*/
	
	public void borrarEsquema(String nombreEsquema) {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/SIGAREP-BD";
		String user = "postgres";
		String password = "postgres";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectString, user,password);
			Statement stmt = con.createStatement();
			// Borrando el esquema				       
			int count = stmt.executeUpdate("DROP SCHEMA " + nombreEsquema + " CASCADE");
			System.out.println("Esquema eliminado.");
			stmt.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	
	/**
	* seleccionarRuta de respaldo en dispositivo externo
	*
	* @param Ninguno
	* @return Busca la ruta externa de respaldo, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	* @throws No dispara ninguna excepción.
	*
	*/
	
	@Command
	@NotifyChange({"txtPaquetesZip"})
	public void seleccionarRuta(){
		JFileChooser chooser = new JFileChooser();
		// Titulo que llevara la ventana
		chooser.setDialogTitle("Examinar...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		// Si seleccionamos algún archivo retornaremos su ubicacion
		if (chooser.getSelectedFile() != null) {
			txtPaquetesZip = chooser.getSelectedFile().getPath();
		}
	}
	
	/** HabilitarGroupBoxDispositivo para la ruta externa de respaldo
	 * @param groupboxDispositivo, divArchivosLocal
	 * @return ninguno, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	public void habilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(true);
		divArchivosLocal.setVisible(false);
	}
	
	/** DeshabilitarGroupBoxDispositivo para la ruta externa de respaldo
	 * @param groupboxDispositivo, divArchivosLocal
	 * @return ninguno, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	public void deshabilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(false);
		divArchivosLocal.setVisible(true);
	}
	
	/** Limpiar los campos de texto y los radiobutton
	 * @param ninguno
	 * @return ninguno, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepcion.
	 */
	
	@NotifyChange({ "txtPaquetesZip","selected","listaDirectorio2","directorio"})
	@Command
	public void limpiar(){
		txtPaquetesZip = "";
		selected = "";
		listaDirectorio2 = null;
		directorio = null;
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajesAlUsuario.confirmacionCerrarVentanaSimple(ventana, condicion);		
	}
}//fin VMRestaurarInformacionBD