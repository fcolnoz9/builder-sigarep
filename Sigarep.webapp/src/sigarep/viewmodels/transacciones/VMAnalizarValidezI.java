package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAnalizarValidezI {
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String sancion;
	private String programa;
	private String email;
	private String primerApellido;
	private String primerNombre;
	private String lapso;
	private Integer instancia;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private Integer caso;
	private String fechaApelacion;
	private Integer peridoSancion;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	private MensajesAlUsuario mensajesAlUsuario;
	
	private String selected;
	private String observacion;


	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioRecaudo serviciorecaudo;
	@WireVariable
	private ServicioMotivo serviciomotivo;

	private List<RecaudoEntregado> listaRecaudo; 

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}
	
	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}

	@Init
	public void init(
		@ContextParam(ContextType.VIEW) Component view,
		@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion sa)
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante();
		this.primerNombre = sa.getEstudianteSancionado().getEstudiante().getPrimerNombre();
		this.primerApellido = sa.getEstudianteSancionado().getEstudiante().getPrimerApellido();
		this.email = sa.getEstudianteSancionado().getEstudiante().getEmail();
		this.programa = sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma();
		this.sancion = sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		this.lapso = sa.getEstudianteSancionado().getLapsoAcademico().getCodigoLapso();
		this.instancia = sa.getInstanciaApelada().getIdInstanciaApelada();
		this.segundoNombre = sa.getEstudianteSancionado().getEstudiante().getSegundoNombre();
		this.segundoApellido = sa.getEstudianteSancionado().getEstudiante().getSegundoApellido();
		this.caso = sa.getNumeroCaso();
		this.lapsosConsecutivos = sa.getEstudianteSancionado().getLapsosAcademicosRp();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		this.fechaApelacion = sdf.format(sa.getFechaSolicitud());
		this.peridoSancion = sa.getEstudianteSancionado().getPeriodoSancion();
		
		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();

		buscarRecaudosEntregados(cedula);
	}
	
	private void mostrarDatosDeSancion() {
		if (sancion.equalsIgnoreCase("RR")){
			asignaturas = servicioasignaturaestudiantesancionado.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i=0; i<asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i).getAsignatura().getNombreAsignatura() + ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		}
		else{
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}
	}

	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosAnalizarValidezI(cedula);
	}

	@Command
	public void closeThis() {
		window.detach();
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

	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public String getFechaApelacion() {
		return fechaApelacion;
	}

	public void setFechaApelacion(String fechaApelacion) {
		this.fechaApelacion = fechaApelacion;
	}

	public Integer getPeridoSancion() {
		return peridoSancion;
	}

	public void setPeridoSancion(Integer peridoSancion) {
		this.peridoSancion = peridoSancion;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public void actualizarRecaudosEntregados(@BindingParam("recaudosEntregados") List<Listitem> recaudos, @BindingParam("window") Window winAnalizarValidez1) {

		if (selected==null || selected.equals("") || observacion==null || observacion.equals("") ) {
			Messagebox.show("Debe Seleccionar una sugerencia de procedencia y emitir una observación general del caso","Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
		
		}else {
		
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
				apelacionEstadoApelacionPK.setIdEstadoApelacion(2);
				apelacionEstadoApelacionPK.setIdInstanciaApelada(1);
				ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
				apelacionEstadoApelacion.setId(apelacionEstadoApelacionPK);
				apelacionEstadoApelacion.setFechaEstado(new Date());
				apelacionEstadoApelacion.setObservacion(observacion);
				if (!selected.equals("")) {
					if (getSelected().equals("Procedente"))
						apelacionEstadoApelacion.setSugerencia("Procedente");
					else
						apelacionEstadoApelacion.setSugerencia("No procedente");
				}
				else 
					
				Messagebox.show("Debe Seleccionar una sugerencia de procedencia y emitir una observación general del caso","Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
				solicitudApelacionAux.addApelacionEstadosApelacion(apelacionEstadoApelacion);
				serviciosolicitudapelacion.guardar(solicitudApelacionAux);		
					
					
					
				try {
					MensajesAlUsuario.informacionRegistroCorrecto();				
					winAnalizarValidez1.detach();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
	}

}
    

