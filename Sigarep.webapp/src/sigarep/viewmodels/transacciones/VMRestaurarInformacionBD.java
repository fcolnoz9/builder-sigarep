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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRestaurarInformacionBD {	

	private MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();
	private String ruta = UtilidadesSigarep.obtenerDirectorio();
	private String txtPaquetesZip = "";
	private String nombreRespaldo;
	private String selected = "";
	private Listitem respaldoSeleccionado = null;
	//Accediendo a los archivos en el directorio
	private FileInputStream lector;
	private File directorio = null;
	private String[] listaDirectorio2 = null;
	private List<String> listaDirectorio = new ArrayList<String>();
	
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

	@Init
	public void init() {
		// initialization code
		cargarDirectorioSVN();
	}
	

	@Command
	@NotifyChange({"listaDirectorio2","listaDirectorio","directorio"})
	public void cargarDirectorioSVN() {
		directorio = new File(ruta+"Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD");
		listaDirectorio2 = directorio.list();
		for (int i=0; i<listaDirectorio2.length;i++)
		{
			if (!(listaDirectorio2[i].equals(".svn")))
			{
				Date fechaCreacion = new Date(new File(ruta + "Sigarep.webapp/WebContent/WEB-INF/sigarep/administracionBaseDatos/respaldosBD/" + listaDirectorio2[i]).lastModified());
				listaDirectorio.add(listaDirectorio2[i]);
				Collections.sort(listaDirectorio);
			}
		}
	}
	
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
	
	@Command
	public void habilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(true);
		divArchivosLocal.setVisible(false);
	}
	
	@Command
	public void deshabilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(false);
		divArchivosLocal.setVisible(true);
	}
	
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
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = true;
        mensajesAlUsuario.confirmacionCerrarVentanaSimple(ventana, condicion);		
	}
}