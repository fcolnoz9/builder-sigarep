package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.filter.OncePerRequestFilter;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zhtml.Messagebox;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.mensajes;
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

//import sigarep.modelos.servicio.maestros.ServicioEstudiante;

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
	@WireVariable
	private Integer semestreSancion;
	private Integer caso;
	private String fechaApelacion;
	private String observacion;
	private String selected = "";	
	private String observacionexperto;
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
	private Combobox cmbSancion;
	private Datebox dtbFechaNacimiento;
	@Wire
	private Datebox	dtbAnnoIngreso;

	private List<LapsoAcademico> listaLapso;
	private List<SancionMaestro> listaSancion;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	List<Recaudo> listaRecaudosGenerales = new LinkedList<Recaudo>();
	private SolicitudApelacion sancionadoSeleccionado;
	mensajes msjs = new mensajes(); 
	private TipoMotivo tipoMotivo;
	private List<Recaudo> listaRecaudosPorMotivo;
	private String telefono;
	private List<RecaudoEntregado> listaRecaudo;
	private Integer periodoSancion; 
	
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

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
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

	@Command
	@NotifyChange({"tipoMotivo", "nombreRecaudo","listaRecaudosPorMotivo"})
	public void buscarRecaudosPorTipoMotivo(Integer tipoMotivo){
			listaRecaudosPorMotivo  = serviciorecaudo.listadoRecaudosPorMotivo(tipoMotivo);
	}
		
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
						
	@Command
	@NotifyChange({ "cedula", "nombres", "apellidos", "estudianteSancionado","lapso","observacionExperto","observacion"})
	public void actualizarRecaudosEntregados(@BindingParam("recaudosEntregados") List<Listitem> recaudos, @BindingParam("window") Window winAnalizarValidezI) {

		if (selected==null || selected.equals("") || observacion==null || observacion.equals("")) {
			Messagebox.show("Debe Seleccionar una sugerencia de procedencia y emitir una observacón general del caso","Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);		
		}
		else {
			ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
			if (getSelected().equals("Procedente"))
				apelacionEstadoApelacion.setSugerencia("Procedente");
			else
				apelacionEstadoApelacion.setSugerencia("No procedente");
			
			SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
			solicitudApelacionPK.setCedulaEstudiante(cedula);
			solicitudApelacionPK.setCodigoLapso(lapso);
			solicitudApelacionPK.setIdInstanciaApelada(1);
			SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
			solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudPorID(solicitudApelacionPK);
			
			Recaudo recaudo = new Recaudo();
			for(Listitem miRecaudo: recaudos){
				//System.out.println(((Listcell) miRecaudo.getChildren().get(1)).getLabel());
				String nombreRecaudo = ((Listcell) miRecaudo.getChildren().get(1)).getLabel();
				String observacionExperto = ((Textbox)(miRecaudo.getChildren().get(2)).getFirstChild()).getValue();
				recaudo = serviciorecaudo.buscarRecaudoNombre(nombreRecaudo);
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
					MensajesAlUsuario.informacionRegistroCorrecto();				
					winAnalizarValidezI.detach();
					
					} catch (Exception e) {
					System.out.println(e.getMessage());

					}
		}
	}	
		
	@Command
	@NotifyChange({ "listaRecaudo", "observacion", "selected", "observacionexperto"})
	public void limpiar(){
		observacion = "";
		selected = "";
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregados(cedula);
	}

}