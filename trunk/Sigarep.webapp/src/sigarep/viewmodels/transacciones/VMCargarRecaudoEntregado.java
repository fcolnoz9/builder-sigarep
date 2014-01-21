package sigarep.viewmodels.transacciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import sigarep.herramientas.Documento;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.transacciones.ListaBuscarRecaudosEntregados;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarRecaudoEntregado {
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
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	
	private Documento doc = new Documento();
	private Media media;
	mensajes msjs = new mensajes();

	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioSoporte serviciosoporte;
	@WireVariable
	private List<ListaBuscarRecaudosEntregados> listaRecaudos = new LinkedList<ListaBuscarRecaudosEntregados>();

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

	public List<ListaBuscarRecaudosEntregados> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setListaRecaudos(
			List<ListaBuscarRecaudosEntregados> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
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
		@ExecutionArgParam("cedula") String v1,
		@ExecutionArgParam("primerNombre") String v2,
		@ExecutionArgParam("primerApellido") String v3,
		@ExecutionArgParam("email") String v4,
		@ExecutionArgParam("programa") String v5,
		@ExecutionArgParam("sancion") String v6,
		@ExecutionArgParam("lapsoAcademico") String v7,
		@ExecutionArgParam("idInstancia") Integer v8,
		@ExecutionArgParam("segundoNombre") String v9,
		@ExecutionArgParam("segundoApellido") String v10,
		@ExecutionArgParam("numeroCaso") Integer v11,
		@ExecutionArgParam("lapsosConsecutivos") String v12)
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.primerNombre = v2;
		this.primerApellido = v3;
		this.email = v4;
		this.programa = v5;
		this.sancion = v6;
		this.lapso = v7;
		this.instancia = v8;
		this.segundoNombre = v9;
		this.segundoApellido = v10;
		this.caso = v11;
		this.lapsosConsecutivos = v12;
		
		concatenacionNombres();
		concatenacionApellidos();
		
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

		media = null;
		doc = new Documento();
		buscarRecaudosEntregados(cedula);
	}
	
	
	private List<RecaudoEntregado> listaRecaudo; 
	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		//listaRecaudos = serviciorecaudoentregado.buscarRecaudosEntregados(cedula);
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregados(cedula);
	}

	@Command
	public void closeThis() {
		window.detach();
	}

	@Command
	public void descargarRecaudoEntregado(@ContextParam(ContextType.COMPONENT) Component componente) {
		int idRecaudo = Integer.parseInt(componente.getAttribute("idRecaudo").toString());
		for (int j = 0; j < listaRecaudos.size(); j++) {
			if (listaRecaudos.get(j).getIdRecaudo() == idRecaudo)
				Filedownload.save(listaRecaudos.get(j).getContenidoDocumento(),
				listaRecaudos.get(j).getTipoDocumento(), listaRecaudos.get(j).getNombreDocumento());
		}

	}

	@Command
	@NotifyChange({"listaRecaudos" })
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
					
					/*SoportePK soportePK = new SoportePK(Integer.parseInt(componente.getAttribute("idRecaudo").toString()), 
														Integer.parseInt(componente.getAttribute("idTipoMotivo").toString()),
														lapso, cedula, instancia);*/
					
					//Soporte soporte = new Soporte(soportePK,true,new Date(),doc);
					//serviciosoporte.guardar(soporte);
					buscarRecaudosEntregados(cedula);
					msjs.informacionRegistroCorrecto();
			} else {
				Messagebox.show(media.getName()+ " No es un tipo de archivo valido!", "Error",Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	@Command
	@NotifyChange({"listaRecaudos" })
	public void eliminarRecaudoEntregado(@ContextParam(ContextType.COMPONENT) Component componente) {			
		/*SoportePK soportePK = new SoportePK(Integer.parseInt(componente.getAttribute("idRecaudo").toString()), 
											Integer.parseInt(componente.getAttribute("idTipoMotivo").toString()),
											lapso, cedula, instancia);
		if (serviciosoporte.buscarSoportePorID(soportePK) != null){
			serviciosoporte.eliminar(soportePK);
			buscarRecaudosEntregados(cedula);
			msjs.informacionEliminarCorrecto();
		}*/
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

}
