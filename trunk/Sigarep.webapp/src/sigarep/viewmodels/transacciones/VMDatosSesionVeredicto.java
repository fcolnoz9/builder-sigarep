package sigarep.viewmodels.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.zkoss.zul.Messagebox;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

/** VMDatosSesionVeredicto
 * Contiene métodos necesarios  para el funcionamiento de DatosSesionVeredicto.zul
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0
 * @since 22/01/14
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDatosSesionVeredicto {
	
	//Datos de la sesion
	private String numeroSesion;
	private String tipoSesion;
	private Date fechaSesion = new Date();
	private String titulo;
	private LapsoAcademico lapsoActivo;
	private List<SolicitudApelacion> listaSancionados = new ArrayList<SolicitudApelacion>();
	
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	
	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private String rutaModal;
	
	private Integer numa;
	private Integer numb;
	
	public Integer getNuma() {
		return numa;
	}

	public void setNuma(Integer numa) {
		this.numa = numa;
	}

	public Integer getNumb() {
		return numb;
	}

	public void setNumb(Integer numb) {
		this.numb = numb;
	}

	public VMDatosSesionVeredicto() {
		
	}
	@Wire
	Textbox txtnombre_enlace;
	
	// Getters and Setters
	public String getNumeroSesion() {
		return numeroSesion;
	}

	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
	// fin Getters and Setters
	/**
	 * inicialización
	 * @param init
	 * @return código de inicialización
	 * @throws No dispara ninguna excepcion.
	 */
	@Wire("#winDatosSesionVeredicto")//para conectarse a la ventana con el ID
	Window ventana;
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
					 @ExecutionArgParam("rutaModal") String rutaModal,
					 @ContextParam(ContextType.BINDER) final Binder binder){
		Selectors.wireComponents(view, this, false);
		lapsoActivo = serviciolapsoacademico.buscarLapsoActivo();
		if(lapsoActivo==null)
			mensajeAlUsuario.advertenciaLapsoAcademicoNoActivo(ventana);
			this.rutaModal=rutaModal;
			buscarDatosSesion(binder);			

	}
	/**
	 * Buscar datos de sesión
	 * @param binder
	 * @return Permite Buscar los datos de la sesion activa .
	 * @throws No dispara ninguna excepcion.
	 */
	@NotifyChange ({"fechaSesion", "tipoSesion", "numeroSesion","titulo"})
	public void buscarDatosSesion(final Binder binder){
		List<SolicitudApelacion> solicitudApelacion;
		if (rutaModal.equalsIgnoreCase("transacciones/VeredictoI.zul")){
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudParaDatosSesion(1);
			ventana.setTitle("Gestión :: Evaluar Apelación :: Veredicto :: Datos de la Sesión");
			 numa = 2;  numb = 3;
		}
		else if (rutaModal.equalsIgnoreCase("transacciones/VeredictoII.zul")){
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudParaDatosSesion(2);
			ventana.setTitle("Gestión :: Evaluar Recurso de Reconsideración :: Veredicto :: Datos de la Sesión");
			numa = 1;  numb = 3;
		}
		else{
			ventana.setTitle("Gestión :: Evaluar Recurso Jerárquico :: Veredicto :: Datos de la Sesión");
			numa = 1;  numb = 2;
			}
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudParaDatosSesion(3);
		if (solicitudApelacion.size() > 0){
			fechaSesion = solicitudApelacion.get(0).getFechaSesion();
			tipoSesion = solicitudApelacion.get(0).getTipoSesion();
			numeroSesion = solicitudApelacion.get(0).getNumeroSesion();
			Messagebox.show("Se encontró una sesión activa ¿Desea continuar con la misma sesión?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case NO:
							binder.postCommand("limpiar", null);
					}
				}
			});	
		}else{
			titulo = "Proporcione los datos de una nueva sesión.";
		}
	}
	/**
	 * Guardar Datos de Sesión
	 * @param guardarDatosSesion
	 * @return Guarda el registro completo, el command indica a las variables el
	 *         cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	public void guardarDatosSesion(){
		if (fechaSesion == null || tipoSesion == null || numeroSesion == null)
			mensajeAlUsuario.advertenciaLlenarCampos();		
		else if (!validarSesion()){ 		
		}	
		else{
				mostrarListaSancionados();
			}
		
	}
	
	/**
	 * Guardar Datos de Sesión
	 * @param numeroSesion
	 * @return Valida el formato del número de sesión y verifica si es válido su uso,
	 * 		   retorna true si es correcto, false si es rechazado
	 * @throws No dispara ninguna excepcion.
	 */
	@Command
	public boolean validarSesion(){
		
		int num = 0;
		boolean pase = false;
		//String palabra ="1-2´3?4_5-";
		char arre[]=numeroSesion.toCharArray();
		for(int i =0;i < numeroSesion.length(); i++){
			num=arre[i];
			System.out.println("---"+i);
			System.out.println("valor es: "+num);
			if( (num > 47 && num < 58) || (num == 45)){System.out.println("bien");}
			else{System.out.println("mal"); pase = false; break;}
			if(i+1==numeroSesion.length())pase =true;
		}
		
		
		if(pase){
			if ( serviciosolicitudapelacion.buscarSesionValida(numeroSesion, numa, numb).size()>0){
				mensajeAlUsuario.advertenciaValidacionNumeroSesion();
				
				return false;
			}else{
				return true;
			}
		}else{ 
			mensajeAlUsuario.advertenciaFormatoNumeroSesion();
			return false;
		}
	}
	
	
	/**
	 * Limpiar.
	 * @param limpiar
	 * @return inicializa las cajas de texto
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	@NotifyChange ({"fechaSesion", "tipoSesion", "numeroSesion"})
	public void limpiar(){
		fechaSesion = new Date();
		tipoSesion = null;
		numeroSesion = null;
	}
	
	/**
	 * Mostrar Lista de Sancionados
	 * @param mostrarListaSancionados
	 * @return muestra la lista de estudiantes sancionados
	 * @throws No dispara ninguna excepcion.
	 */
	public void mostrarListaSancionados(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
	 	map.put("rutaModal", rutaModal);
	 	map.put("numeroSesion", numeroSesion);
	 	map.put("tipoSesion", tipoSesion);
	 	map.put("fechaSesion", fechaSesion);
	 	
	 	try {
			Borderlayout bl = (Borderlayout) Path.getComponent("/mainBorderLayout");
			Center center = bl.getCenter();
			center.getChildren().clear();
			Executions.createComponents("/WEB-INF/sigarep/vistas/transacciones/ListaGenericaSancionados.zul", center, map);
		} catch (Exception e) {
			Messagebox.show(e.toString());
		}
	}
	/**
	 * Cerrar Ventana
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */

	@Command
	@NotifyChange({"numeroSesion", "tipoSesion", "", "fechaSesion"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(numeroSesion != null || tipoSesion != null
				|| fechaSesion != null)
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
	
}
