package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Window;
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
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;

//import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMVerificarRecaudosEntregadosI {
	@WireVariable
	private ServicioEstudianteSancionado servicioestudiantesancionado;
	@WireVariable
	private LapsoAcademico lapsoAcademico;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String programa;
	@WireVariable
	private String cedula;
	@WireVariable
	private String nombres;
	@WireVariable
	private String apellidos;
	@WireVariable
	private String asignatura;
	@WireVariable
	private String sancion;
	@WireVariable
	private String lapso;
	@WireVariable
	private Integer semestreSancion;
	@WireVariable
	private String caso;
	@WireVariable
	private Integer periodoSancion;
	@WireVariable
	private String fechaApelacion;
	@WireVariable
	private String selected = "";
	@WireVariable
	private String lapsosConsecutivos;
	@WireVariable
	private String asignaturaLapsosConsecutivos="";
	@WireVariable
	private String labelAsignaturaLapsosConsecutivos;
	
	@WireVariable
	private List<Recaudo> listaRecaudos = new LinkedList<Recaudo>();
	@WireVariable
	private List<AsignaturaEstudianteSancionado> asignaturas;
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
	private Combobox cmbSancion;
	@Wire
	private Datebox dtbFechaNacimiento;
	@Wire
	private Datebox	dtbAnnoIngreso;

	private List<LapsoAcademico> listaLapso;
	private List<SancionMaestro> listaSancion;
	
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	List<Recaudo> listaRecaudosGenerales = new LinkedList<Recaudo>();
	MensajesAlUsuario mensajeAlUsuario  = new MensajesAlUsuario(); //para llamar a los diferentes mensajes de dialogo
	

	@WireVariable
	private TipoMotivo tipoMotivo;
	@WireVariable
	private List<Recaudo> listaRecaudosPorMotivo;
	@WireVariable
	private String telefono;
	
	
	
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
	
	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
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
	
	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}

	public String getFechaApelacion() {
		return fechaApelacion;
	}

	public void setFechaApelacion(String fechaApelacion) {
		this.fechaApelacion = fechaApelacion;
	}
	
	public List<AsignaturaEstudianteSancionado> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<AsignaturaEstudianteSancionado> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public void setAsignaturaLapsosConsecutivos(String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion sa)
	
	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.cedula = sa.getEstudianteSancionado().getId().getCedulaEstudiante();
		this.nombres = sa.getEstudianteSancionado().getEstudiante().getPrimerNombre() + " " +sa.getEstudianteSancionado().getEstudiante().getSegundoNombre();
		this.apellidos = sa.getEstudianteSancionado().getEstudiante().getPrimerApellido() + " "+sa.getEstudianteSancionado().getEstudiante().getSegundoApellido();
		this.programa = sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma();
		this.sancion = sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		this.lapso = sa.getId().getCodigoLapso();
		this.lapsosConsecutivos = sa.getEstudianteSancionado().getLapsosAcademicosRp();
		this.caso = sa.getNumeroCaso();
		
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		this.fechaApelacion = sdf.format(sa.getFechaSolicitud());
		this.periodoSancion = sa.getEstudianteSancionado().getPeriodoSancion();
		buscarRecaudos();
		
		if (sancion.equalsIgnoreCase("RR")){
			asignaturas = servicioasignaturaestudiantesancionado.buscarAsignaturaDeSancion(this.cedula, this.lapso);
			if (asignaturas != null)
				for (int i=0; i<asignaturas.size(); i++)
					this.asignaturaLapsosConsecutivos += asignaturas.get(i).getAsignatura().getNombreAsignatura() + ", ";
			this.labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		}
		else{
			this.labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			this.asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}
	}
	
	@Command
	@NotifyChange({"cedula","lapso","nombreRecaudo","nombreTipoMotivo","listaRecaudos" })
	public void buscarRecaudos() {
		listaRecaudos = serviciorecaudo.buscarRecaudosPorApelacion(cedula, lapso, 1);
	}
	
	@Command
	@NotifyChange({ "cedula", "nombres", "apellidos", "estudianteSancionado","lapso"})
	public void registrarRecaudosEntregados(@BindingParam("recaudosEntregados") Set<Listitem> recaudos, @BindingParam("window") Window winVerificarRecaudos, @BindingParam("listaRecaudos") Listbox listaRecaudos) {
		if (recaudos.size() == 0) {
			mensajeAlUsuario.advertenciaSeleccionarAlMenosUnRecaudoEntregado();
		}
		else 
		{
			ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
			if (getSelected().equals("PROCEDENTE"))
				apelacionEstadoApelacion.setSugerencia("PROCEDENTE");
			else if((getSelected().equals("NO PROCEDENTE")))
				apelacionEstadoApelacion.setSugerencia("NO PROCEDENTE");
			
			SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
			solicitudApelacionPK.setCedulaEstudiante(cedula);
			solicitudApelacionPK.setCodigoLapso(lapso);
			solicitudApelacionPK.setIdInstanciaApelada(1);
			SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudPorID(solicitudApelacionPK);
				
			Recaudo recaudo = new Recaudo();
			for(Listitem miRecaudo: recaudos){
				String nombreRecaudo = miRecaudo.getLabel();
				recaudo = serviciorecaudo.buscarRecaudoPorNombre(nombreRecaudo);
				recaudoEntregadoPK.setIdInstanciaApelada(1);
				recaudoEntregadoPK.setCedulaEstudiante(cedula);
				recaudoEntregadoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				recaudoEntregadoPK.setCodigoLapso(lapso);
				recaudoEntregadoPK.setIdRecaudo(recaudo.getIdRecaudo());
				RecaudoEntregado recaudoEntregadoAux = new RecaudoEntregado();
				recaudoEntregadoAux.setId(recaudoEntregadoPK);
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
				solicitudApelacionAux.setObservacion(solicitudApelacion.getObservacion());
				solicitudApelacionAux.setVerificado(true);
				solicitudApelacionAux.setAnalizado(false);
				ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
				apelacionEstadoApelacionPK.setCedulaEstudiante(cedula);
				apelacionEstadoApelacionPK.setCodigoLapso(lapso);
				apelacionEstadoApelacionPK.setIdEstadoApelacion(2);
				apelacionEstadoApelacionPK.setIdInstanciaApelada(1);
				
				apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
				apelacionEstadoApelacion.setFechaEstado(new Date());
				solicitudApelacionAux.addApelacionEstadosApelacion(apelacionEstadoApelacion);
				serviciosolicitudapelacion.guardar(solicitudApelacionAux);
				
				try {
					mensajeAlUsuario.informacionRegistroCorrecto();
					winVerificarRecaudos.detach(); //oculta el window
					actualizarListaSancionados();
					//falta actualizar la lista de apelaciones en este punto
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				limpiar(listaRecaudos);
		}
	}
	@GlobalCommand
    public void actualizarListaSancionados(){
    	BindUtils.postGlobalCommand(null, null, "buscarSancionados", null);
    }
	 @Command
	 @NotifyChange({"cedula", "nombres", "selected","apellidos", "listaRecaudosPorMotivo","programa","lapsoAcademico","telefono","sancion","asignatura"})
	public void limpiar(@BindingParam("listaRecaudos") Listbox listaRecaudos) {
		 selected = "";
		 buscarRecaudos();
		 listaRecaudos.clearSelection();
	}

	@Command
	public void notificarRecaudoVerificado(@BindingParam("lbxRecaudos") Listbox lbxRecaudos) {
		Listcell a = (Listcell)lbxRecaudos.getAttribute("identificadorListitem");
		if(lbxRecaudos.getSelectedIndex()!=-1)	
			Clients.showNotification("Recaudo Verificado",Clients.NOTIFICATION_TYPE_INFO,a,"middle_center",1000);
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
	@NotifyChange({"tipoMotivo", "nombreRecaudo","listaRecaudosPorMotivo"})
public void cerrarVentana(@BindingParam("ventana") final Window ventana, @BindingParam("recaudosEntregados") Set<Listitem> recaudos){
			boolean condicion = false;
			if(recaudos.size() != 0)
				condicion = true;
			mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
		}		
		

}