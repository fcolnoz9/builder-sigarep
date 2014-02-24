package sigarep.viewmodels.transacciones;
/** Transaccion para Analizar Validez II- recurso Jerárquico
 * @author BUILDER
 * @version 1.3
 * @since 12/01/2014 
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAnalizarValidezI {
	@WireVariable
	private LapsoAcademico lapsoAcademico;
	private String programa;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String asignatura;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;	
	private String sancion;
	private String lapso;
	private String caso;
	private String fechaApelacion;
	private String observacion;
	private String selected = "";	
	private String observacionexperto= "";
	@WireVariable
	private Integer semestreSancion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioRecaudo serviciorecaudo;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@Wire
	private Datebox	dtbAnnoIngreso;
	private SolicitudApelacion sancionadoSeleccionado;
	private Integer periodoSancion; 
	private TipoMotivo tipoMotivo;
	private String telefono;
	private List<LapsoAcademico> listaLapso;
	private List<SancionMaestro> listaSancion;
	private List<Recaudo> listaRecaudosPorMotivo;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private List<RecaudoEntregado> listaRecaudo;
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	List<Recaudo> listaRecaudosGenerales = new LinkedList<Recaudo>();
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	@Wire("#winAnalizarValidezI")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	//Metodos SETs Y GETs 
	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}
	
	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}	
	
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}
	
	public Integer getSemestreSancion() {
		return semestreSancion;
	}

	public void setSemestreSancion(Integer semestreSancion) {
		this.semestreSancion = semestreSancion;
	}

	public List<Recaudo> getListaRecaudosPorMotivo() {
		return listaRecaudosPorMotivo;
	}

	public void setListaRecaudosPorMotivo(List<Recaudo> listaRecaudosPorMotivo) {
		this.listaRecaudosPorMotivo = listaRecaudosPorMotivo;
	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}

	public void setListaLapso(List<LapsoAcademico> ListaLapso) {
		this.listaLapso = ListaLapso;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
		
	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}
	
	public String getFechaApelacion() {
		return fechaApelacion;
	}

	public void setFechaApelacion(String fechaApelacion) {
		this.fechaApelacion = fechaApelacion;
	}
	
	public SolicitudApelacion getSancionadoSeleccionado() {
		return sancionadoSeleccionado;
	}
	public void setSancionadoSeleccionado(
			SolicitudApelacion sancionadoSeleccionado) {
		this.sancionadoSeleccionado = sancionadoSeleccionado;
	}
	
	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public void setAsignaturaLapsosConsecutivos(String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}
	
	public String getObservacionexperto() {
		return observacionexperto;
	}

	public void setObservacionexperto(String observacionexperto) {
		this.observacionexperto = observacionexperto;
	}	
	
	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}
	// FIN DEL METODO GET Y SET

	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosAnalizarValidezI(cedula);
		System.out.println(listaRecaudosPorMotivo);
	}

	@Init
	public void init(			
			@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion v1)
	{
		Selectors.wireComponents(view, this, false);
		this.sancionadoSeleccionado = v1;
		cedula = sancionadoSeleccionado.getId().getCedulaEstudiante();
		lapso = sancionadoSeleccionado.getEstudianteSancionado().getLapsoAcademico().getCodigoLapso();
		sancion = sancionadoSeleccionado.getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		periodoSancion = sancionadoSeleccionado.getEstudianteSancionado().getPeriodoSancion();
		lapsosConsecutivos = sancionadoSeleccionado.getEstudianteSancionado().getLapsosAcademicosRp();
		caso = sancionadoSeleccionado.getNumeroCaso();

		buscarRecaudosEntregados(cedula);
		
		if (sancion.equalsIgnoreCase("RR")) {
			asignaturas = servicioasignaturaestudiantesancionado
					.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i = 0; i < asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i)
							.getAsignatura().getNombreAsignatura()
							+ ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		} else {
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}

		SolicitudApelacionPK solicitudApelacionPK2 = new SolicitudApelacionPK();
		solicitudApelacionPK2.setCedulaEstudiante(cedula);
		solicitudApelacionPK2.setCodigoLapso(lapso);
		solicitudApelacionPK2.setIdInstanciaApelada(1);
		Date fechaSA = serviciosolicitudapelacion.buscarSolicitudPorID(solicitudApelacionPK2).getFechaSolicitud();
		SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
		String fecha =  sdf.format(fechaSA);
		this.fechaApelacion = fecha;
		
	}
	
	/** guarda los recaudos entregados junto con su observacion
	    * @param cedula, nombres, apellidos, estudianteSancionado,lapso,observacionExperto,observacion
	   */
	@Command
	@NotifyChange({ "cedula", "nombres", "apellidos", "estudianteSancionado","lapso","observacionExperto","observacion"})
	public void actualizarRecaudosEntregados(@BindingParam("recaudosEntregados") List<Listitem> recaudos, @BindingParam("window") Window winAnalizarValidezI) {

		if (selected==null || selected.equals("")) {
			mensajeAlUsuario.advertenciaSeleccionarSugerencia();		
		}
		else if (observacion==null || observacion.equals("")) {
			mensajeAlUsuario.advertenciaAgregarObservacionGeneral();
		}
		else {
			ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
			if (getSelected().equals("PROCEDENTE"))
				apelacionEstadoApelacion.setSugerencia("PROCEDENTE");
			else
				apelacionEstadoApelacion.setSugerencia("NO PROCEDENTE");
			
			SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
			solicitudApelacionPK.setCedulaEstudiante(cedula);
			solicitudApelacionPK.setCodigoLapso(lapso);
			solicitudApelacionPK.setIdInstanciaApelada(1);
			SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudPorID(solicitudApelacionPK);
			
			Recaudo recaudo = new Recaudo();
			for(Listitem miRecaudo: recaudos){
				String nombreRecaudo = ((Listcell) miRecaudo.getChildren().get(1)).getLabel();
				String observacionExperto = ((Textbox)(miRecaudo.getChildren().get(2)).getFirstChild()).getValue();
				if(observacionExperto.equals(""))
					observacionExperto=null;
				recaudo = serviciorecaudo.buscarRecaudoPorNombre(nombreRecaudo);
				RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
				recaudoEntregadoPK.setIdInstanciaApelada(1);
				recaudoEntregadoPK.setCedulaEstudiante(cedula);
				recaudoEntregadoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				recaudoEntregadoPK.setCodigoLapso(lapso);
				recaudoEntregadoPK.setIdRecaudo(recaudo.getIdRecaudo());
				RecaudoEntregado recaudoEntregadoAux = new RecaudoEntregado();
				recaudoEntregadoAux.setId(recaudoEntregadoPK);
				recaudoEntregadoAux.setObservacionExperto(observacionExperto);
				recaudoEntregadoAux.setEstatus(true);
				MotivoPK motivoPK = new MotivoPK();
				motivoPK.setCedulaEstudiante(cedula);
				motivoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				motivoPK.setCodigoLapso(lapso);
				motivoPK.setIdInstanciaApelada(1);
				Motivo motivo = new Motivo();
				motivo.setId(motivoPK);
				motivo.setEstatus(true);
				motivo.addRecaudoEntregado(recaudoEntregadoAux);
				serviciomotivo.guardarMotivo(motivo);
			}
				SolicitudApelacion solicitudApelacionAux = new SolicitudApelacion();
				solicitudApelacionAux.setId(solicitudApelacionPK);
				solicitudApelacionAux.setEstatus(true);
				solicitudApelacionAux.setFechaSesion(solicitudApelacion.getFechaSesion());
				solicitudApelacionAux.setFechaSolicitud(solicitudApelacion.getFechaSolicitud());
				solicitudApelacionAux.setNumeroCaso(solicitudApelacion.getNumeroCaso());
				solicitudApelacionAux.setNumeroSesion(solicitudApelacion.getNumeroSesion());
				solicitudApelacionAux.setVeredicto(solicitudApelacion.getVeredicto());
				solicitudApelacionAux.setObservacion(observacion);
				solicitudApelacionAux.setVerificado(true);
				solicitudApelacionAux.setAnalizado(true);
				ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
				apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
				apelacionEstadoApelacionPK.setCodigoLapso(lapso);
				apelacionEstadoApelacionPK.setIdEstadoApelacion(3);
				apelacionEstadoApelacionPK.setIdInstanciaApelada(1);
				
				apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
				apelacionEstadoApelacion.setFechaEstado(new Date());
				apelacionEstadoApelacion.setObservacion(observacion);
				solicitudApelacionAux.addApelacionEstadosApelacion(apelacionEstadoApelacion);
				serviciosolicitudapelacion.guardar(solicitudApelacionAux);									
					
				try {
					MensajesAlUsuario.informacionRegistroCorrectoStatic();				
					winAnalizarValidezI.detach();
					actualizarListaSancionados();
					} catch (Exception e) {
					System.out.println(e.getMessage());

					}
		}
	}	
	
	/** Limpia los campos 
	    */
	@Command
	@NotifyChange({ "listaRecaudo", "observacion", "selected", "observacionexperto"})
	public void limpiar(){
		observacion = "";
		selected = "";
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosAnalizarValidezI(cedula);
	}
	@GlobalCommand
    public void actualizarListaSancionados(){
    	BindUtils.postGlobalCommand(null, null, "buscarSancionados", null);
    }

	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Command
	@NotifyChange({ "cedula", "nombres", "apellidos", "estudianteSancionado","lapso","observacionExperto","observacion"})
	public void cerrarVentana(@ContextParam(ContextType.BINDER) final Binder binder){
			
		if ( observacion != null ||	selected != null || observacionexperto != null){
			Messagebox.show("¿Realemente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
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
		else{
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
}