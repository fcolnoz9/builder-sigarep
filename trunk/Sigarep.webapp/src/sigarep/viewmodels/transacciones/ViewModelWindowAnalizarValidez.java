package sigarep.viewmodels.transacciones;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Documento;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.servicio.maestros.ServicioEstadoApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ListaAnalizarValidez;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ListaRecaudosMotivoEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioMotivos;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAnalizarValidez;
import sigarep.modelos.servicio.transacciones.ServicioSoporte;

import org.zkoss.zul.Window;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelWindowAnalizarValidez {
	@Wire("#modalDialog")
	private Window window;
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String lapso;
	private int instancia;
	private String motivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private String asignatura;
	private Integer caso;
	private List<ListaAnalizarValidez> lista = new LinkedList<ListaAnalizarValidez>();
	private ListaAnalizarValidez listaanalizarvalidez;

	@WireVariable ServicioAnalizarValidez serviciolistaanalizarvalidez;

	@WireVariable
	private String nombreRecaudo;
	@WireVariable
	private String nombreTipoMotivo;
	private String nombreDocumento;
	private Byte[] contenidoDocumento;
	private String tipoDocumento;
	@WireVariable
	private Integer idTipoMotivo;
	private Integer idRecaudo;
	private List<TipoMotivo> listaTipoMotivo;
	private List<Recaudo> listaRecaudo;
	@WireVariable
	private ServicioApelacion serviciolista;
	private Documento doc = new Documento();
	private Media media;
	private AImage imagen;
	private String nombreDoc;
	@WireVariable
	private String cedula;
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	@WireVariable
	private ServicioMotivos serviciomotivos;
	@WireVariable
	private ServicioSoporte serviciosoporte;
	@WireVariable
	private ServicioEstadoApelacion servicioestadoapelacion;
	@WireVariable
	private Integer instanciaApelada;
	@WireVariable
	private Date fechaSolicitud;
	@Wire
	Label lblDocumento;
	mensajes msjs = new mensajes(); // para llamar a los diferentes mensajes de
									// dialogo
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion = new SolicitudApelacion();
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion();
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	Soporte soporte = new Soporte();
	Motivo motivos = new Motivo();
	MotivoPK motivoPK = new MotivoPK();
	EstadoApelacion estadoApelacion = new EstadoApelacion();
	Recaudo recaudos = new Recaudo();
	
	@WireVariable
	private List<ListaRecaudosMotivoEstudiante> listaRecaudos = new LinkedList<ListaRecaudosMotivoEstudiante>();
//	private List<ListaApelacionEstadoApelacion> lista = new LinkedList<ListaApelacionEstadoApelacion>();
//
//	public List<ListaApelacionEstadoApelacion> getLista() {
//		return lista;
//	}
//
//	public void setLista(List<ListaApelacionEstadoApelacion> lista) {
//		this.lista = lista;
//	}

	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}

	public ListaAnalizarValidez getListaanalizarvalidez() {
		return listaanalizarvalidez;
	}

	public void setListaanalizarvalidez(ListaAnalizarValidez listaanalizarvalidez) {
		this.listaanalizarvalidez = listaanalizarvalidez;
	}

	public List<ListaAnalizarValidez> getLista() {
		return lista;
	}

	public void setLista(List<ListaAnalizarValidez> lista) {
		this.lista = lista;
	}

	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}

	public Integer getIdRecaudo() {
		return idRecaudo;
	}

	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public Byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}

	public void setContenidoDocumento(Byte[] contenidoDocumento) {
		this.contenidoDocumento = contenidoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public List<Recaudo> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<Recaudo> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public String getNombreRecaudo() {
		return nombreRecaudo;
	}

	public void setNombreRecaudo(String nombreRecaudo) {
		this.nombreRecaudo = nombreRecaudo;
	}

	public String getNombreMotivo() {
		return nombreTipoMotivo;
	}

	public void setNombreMotivo(String nombreMotivo) {
		this.nombreTipoMotivo = nombreMotivo;
	}

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

	public AImage getImagen() {
		return imagen;
	}

	public void setImagen(AImage imagen) {
		this.imagen = imagen;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
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

	public List<ListaRecaudosMotivoEstudiante> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setListaRecaudos(
			List<ListaRecaudosMotivoEstudiante> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public SolicitudApelacion getSolicitudapelacion() {
		return solicitudapelacion;
	}

	public void setSolicitudapelacion(SolicitudApelacion solicitudapelacion) {
		this.solicitudapelacion = solicitudapelacion;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public ServicioLapsoAcademico getServiciolapsoacademico() {
		return serviciolapsoacademico;
	}

	public void setServiciolapsoacademico(
			ServicioLapsoAcademico serviciolapsoacademico) {
		this.serviciolapsoacademico = serviciolapsoacademico;
	}

	public ServicioSolicitudApelacion getServiciosolicitudapelacion() {
		return serviciosolicitudapelacion;
	}

	public void setServiciosolicitudapelacion(
			ServicioSolicitudApelacion serviciosolicitudapelacion) {
		this.serviciosolicitudapelacion = serviciosolicitudapelacion;
	}

	public Integer getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(Integer instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void concatenacionNombres() {

		String nombre1 = nombre;
		String nombre2 = segundoNombre;
		nombres = nombre1 + " " + nombre2;
		System.out.println(nombres);
	}

	public void concatenacionApellidos() {

		String apellido1 = apellido;
		String apellido2 = segundoApellido;
		apellidos = apellido1 + " " + apellido2;

	}


	// public void inhabilitar () {
	// String materia = asignatura;
	// if (materia == null){
	// txtAsignatura.setTextBlock(null);
	// }
	// }

	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("nombre") String v2,
			@ExecutionArgParam("apellido") String v3,
			@ExecutionArgParam("email") String v4,
			@ExecutionArgParam("telefono") String v5,
			@ExecutionArgParam("programa") String v6,
			@ExecutionArgParam("sancion") String v7,
			@ExecutionArgParam("lapso") String v8,
			@ExecutionArgParam("instancia") Integer v9,

			@ExecutionArgParam("segundoNombre") String v11,
			@ExecutionArgParam("segundoApellido") String v12,
			@ExecutionArgParam("asignatura") String v13,
			@ExecutionArgParam("caso") Integer v14)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.nombre = v2;
		this.apellido = v3;
		this.email = v4;
		this.telefono = v5;
		this.programa = v6;
		this.sancion = v7;
		this.lapso = v8;
		this.instancia = v9;

		this.segundoNombre = v11;
		this.segundoApellido = v12;
		this.asignatura = v13;
		this.caso = v14;

		concatenacionNombres();
		concatenacionApellidos();
	
	}

	@Command
	public void closeThis() {
		window.detach();
	}

	

}
