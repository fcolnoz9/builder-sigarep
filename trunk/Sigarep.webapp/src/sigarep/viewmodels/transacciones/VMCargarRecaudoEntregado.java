package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

/**CargarRecaudoEntregado  
 * Maneja la carga de documentos que utilizaran los estudiantes 
 * durante los distintos procesos de apelación.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.6.2
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarRecaudoEntregado {

	private String cedula;
	private String sancion;
	private String programa;
	private String email;
	private String primerApellido;
	private String primerNombre;
	private String lapso;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private String caso;
	private String fechaApelacion;
	private Integer peridoSancion;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	
	private Documento doc = new Documento();
	private Media media;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioSoporte serviciosoporte;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;

	private List<RecaudoEntregado> listaRecaudo; 
	
	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
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
	
	
	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}

	@Init
	public void init(
		@ExecutionArgParam("estudianteSeleccionado") EstudianteSancionado es)
	{
		this.cedula = es.getEstudiante().getCedulaEstudiante();
		this.primerNombre = es.getEstudiante().getPrimerNombre();
		this.primerApellido = es.getEstudiante().getPrimerApellido();
		this.email = es.getEstudiante().getEmail();
		this.programa = es.getEstudiante().getProgramaAcademico().getNombrePrograma();
		this.sancion = es.getSancionMaestro().getNombreSancion();
		this.lapso = es.getLapsoAcademico().getCodigoLapso();
		this.segundoNombre = es.getEstudiante().getSegundoNombre();
		this.segundoApellido = es.getEstudiante().getSegundoApellido();
		this.lapsosConsecutivos = es.getLapsosAcademicosRp();
		this.caso = serviciosolicitudapelacion.buscarNumeroDeCasoCargarRecaudo(cedula);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		this.fechaApelacion = sdf.format(serviciosolicitudapelacion.buscarFechaApelacionCargarRecaudo(cedula));
		this.peridoSancion = es.getPeriodoSancion();
		
		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();

		media = null;
		doc = new Documento();
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
		List<RecaudoEntregado> listaAux;
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosConSoporte(cedula);
		listaAux = serviciorecaudoentregado.buscarRecaudosEntregadosSinSoporte(cedula);
		listaRecaudo.addAll(listaAux);
	}

	@Command
	public void descargarRecaudoEntregado(@ContextParam(ContextType.COMPONENT) Component componente) {
		int idSoporte = Integer.parseInt(componente.getAttribute("idSoporte").toString());
		for (int j = 0; j < listaRecaudo.size(); j++) {
			if (listaRecaudo.get(j).getSoporte()!=null){
				if (listaRecaudo.get(j).getSoporte().getIdSoporte() == idSoporte)
					Filedownload.save(listaRecaudo.get(j).getSoporte().getDocumento().getContenidoDocumento(),
					listaRecaudo.get(j).getSoporte().getDocumento().getTipoDocumento(),
					listaRecaudo.get(j).getSoporte().getDocumento().getNombreDocumento());
			}
		}
	}

	@Command
	@NotifyChange({"listaRecaudo" })
	public void cargarRecaudoEntregado(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event,
			@ContextParam(ContextType.COMPONENT) Component componente) {
		media = event.getMedia();
		if (media != null) {
			if (media.getContentType().equals("image/jpeg")
				|| media.getContentType().equals("application/pdf")
				|| media.getContentType().equals("application/msword")
				|| media.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
				|| media.getContentType().equals("application/vnd.oasis.opendocument.text")
				|| media.getContentType().equals("application/x-vnd.oasis.opendocument.text")) {
					doc.setNombreDocumento(media.getName());
					doc.setTipoDocumento(media.getContentType());
					doc.setContenidoDocumento(media.getByteData());
					
					if(doc.getTamanoDocumento()>20971520){
						mensajeAlUsuario.advertenciaTamannoArchivo(20480);
						
						doc=new Documento();
					}else{ 
							
							Integer idRecaudo = Integer.parseInt(componente.getAttribute("idRecaudo").toString());
							Integer idTipoMotivo = Integer.parseInt(componente.getAttribute("idTipoMotivo").toString());
							Integer instancia = Integer.parseInt(componente.getAttribute("idInstanciaApelada").toString());
							
							RecaudoEntregadoPK recaudoEntregadoPK= new RecaudoEntregadoPK(idRecaudo,idTipoMotivo,lapso,cedula,instancia);
							RecaudoEntregado recaudoEntregado = serviciorecaudoentregado.buscarPorId(recaudoEntregadoPK);
							
							Soporte soporte = serviciosoporte.buscarPorIdDeRecaudoEntregado(recaudoEntregadoPK);
							if (soporte == null)
								soporte= new Soporte(true,new Date(),doc,recaudoEntregado);
							else
							{
								soporte.setDocumento(doc);
								soporte.setFechaSubida(new Date());
							}
							serviciosoporte.guardar(soporte);
							buscarRecaudosEntregados(cedula);
							mensajeAlUsuario.informacionRegistroCorrecto();
					}
			} else {
				mensajeAlUsuario.advertenciaFormatoImagenNoSoportado(media);
			}
		}
	}
	
	@Command
	@NotifyChange({"listaRecaudo" })
	public void eliminarRecaudoEntregado(@ContextParam(ContextType.COMPONENT) Component componente) {			
		if(componente.getAttribute("idSoporte") != null){
			Integer idSoporte = Integer.parseInt(componente.getAttribute("idSoporte").toString());
			if (serviciosoporte.buscarSoportePorID(idSoporte) != null){
				serviciosoporte.eliminar(idSoporte);
				buscarRecaudosEntregados(cedula);
				mensajeAlUsuario.informacionEliminarCorrecto();
			}
		}	
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
	@NotifyChange({ "listaRecaudo" })
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		ventana.detach();
	}
}//fin VMCargarRecaudoEntregado
