package sigarep.viewmodels.transacciones;

import java.util.Date;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
/**VM Regisrar Datos Iniciales
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarDatosIniciales {
	@Wire("#modalDialog")
    private Window window;
	
	@WireVariable
	private SolicitudApelacion solicitudApelacion;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	
	private String cedula;
	private String primerNombre;
	private String segundoNombre;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String apellidos;
	private String sancion;
	private String programa;
	private String lapso;
	private String asignatura;
	private float indice;
	private String lapsosConsecutivos;
	private Date fecha;
	private List<TipoMotivo> listamotivo;
	private String descripcion;
	private Motivo motivo;
	private List<Motivo> listamotivos;
	private List<mot> listamo;
	private Motivo motivoseleccionado;
	private String listamotivoseleccionado;
	private mensajes msjs = new mensajes();
	
	public List<mot> getListamo() {
		return listamo;
	}

	public void setListamo(List<mot> listamo) {
		this.listamo = listamo;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getListamotivoseleccionado() {
		return listamotivoseleccionado;
	}

	public void setListamotivoseleccionado(String listamotivoseleccionado) {
		this.listamotivoseleccionado = listamotivoseleccionado;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public SolicitudApelacion getSolicitudApelacion() {
		return solicitudApelacion;
	}

	public void setSolicitudApelacion(SolicitudApelacion solicitudApelacion) {
		this.solicitudApelacion = solicitudApelacion;
	}

	public ServicioSolicitudApelacion getServiciosolicitudapelacion() {
		return serviciosolicitudapelacion;
	}

	public void setServiciosolicitudapelacion(
			ServicioSolicitudApelacion serviciosolicitudapelacion) {
		this.serviciosolicitudapelacion = serviciosolicitudapelacion;
	}

	public ServicioTipoMotivo getServiciotipomotivo() {
		return serviciotipomotivo;
	}

	public void setServiciotipomotivo(ServicioTipoMotivo serviciotipomotivo) {
		this.serviciotipomotivo = serviciotipomotivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public float getIndice() {
		return indice;
	}

	public void setIndice(float indice) {
		this.indice = indice;
	}

	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<TipoMotivo> getListamotivo() {
		return listamotivo;
	}

	public void setListamotivo(List<TipoMotivo> listamotivo) {
		this.listamotivo = listamotivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public List<Motivo> getListamotivos() {
		return listamotivos;
	}

	public void setListamotivos(List<Motivo> listamotivos) {
		this.listamotivos = listamotivos;
	}

	public Motivo getMotivoseleccionado() {
		return motivoseleccionado;
	}

	public void setMotivoseleccionado(Motivo motivoseleccionado) {
		this.motivoseleccionado = motivoseleccionado;
	}
	
	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}

	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
		    		@ExecutionArgParam("cedula") String v1,
		    		@ExecutionArgParam("primerNombre") String v2,
		    		@ExecutionArgParam("primerApellido") String v3,
		    		@ExecutionArgParam("programa") String v5,
		    		@ExecutionArgParam("sancion") String v6,
		    		@ExecutionArgParam("lapso") String v7,
		    		@ExecutionArgParam("segundoNombre") String v9,
		    		@ExecutionArgParam("segundoApellido") String v10,
		    		@ExecutionArgParam("indice") float v12){
	
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.primerNombre = v2;
		this.primerApellido = v3;
		this.programa = v5;
		this.sancion = v6;
		this.lapso = v7;
		this.segundoNombre = v9;
		this.segundoApellido = v10;
		concatenacionNombres();
		concatenacionApellidos();
		
		listamotivo = serviciotipomotivo.buscarTodas();
	}
	
	@Command
	public void closeThis() {
		window.detach();
	}
	
	@Command
	public void agregarMotivo(){
		if(listamotivoseleccionado == null || descripcion == null)
				msjs.advertenciaLlenarCampos();
		else{
				for(int i=0; i<listamotivo.size();i++){
					if(listamotivoseleccionado.equals(listamotivo.get(i).getNombreTipoMotivo())){
						mot mo = new mot();
						mo.setDescripcion(descripcion);
						mo.setNombreMotivo(listamotivoseleccionado);
						listamo.add(mo);
					}
					limpiar();
					msjs.informacionRegistroCorrecto();
					}
		}
	}
	
	@Command
	public void limpiar(){
		listamotivoseleccionado = "";
		descripcion = "";
		fecha = null;
	}
	
	@Command
	public void eliminarMotivoSeleccionado(){
		
	}
	
	@Command
	public void guardar(){
		
	}
	
	@Command
	public void cancelar(){
		
	}
	
	class mot{
		String nombremotivo;
		String descripcion;
		
		mot(){}
		public void setNombreMotivo(String nm){
			this.nombremotivo = nm;
		}
		
		public String getNombreMotivo(){
			return this.nombremotivo;
		}
		
		public void setDescripcion(String d){
			this.descripcion = d;
		}
		
		public String getDescripcion(){
			return this.descripcion;
		}
	}
}
