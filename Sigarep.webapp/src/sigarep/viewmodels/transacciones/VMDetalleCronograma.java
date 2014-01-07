package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import sigarep.modelos.data.transacciones.ListaCronograma;
import sigarep.modelos.servicio.transacciones.ServicioListaCronograma;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDetalleCronograma {

	@Wire("#modalDialog")
	private Window window;
	
	private String nombre;
	private String descripcion;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Time hora_inicio;
	private String observacion;
	private String lugar;
	private ListaCronograma listaCronograma;

	private List<ListaCronograma> listaActividadCronograma = new LinkedList<ListaCronograma>();

	@WireVariable
	private ServicioListaCronograma serviciolistacronograma;

	public ListaCronograma getListaCronograma() {
		return listaCronograma;
	}

	public void setListaCronograma(ListaCronograma listaCronograma) {
		this.listaCronograma = listaCronograma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<ListaCronograma> getLista() {
		return listaActividadCronograma;
	}

	public void setLista(List<ListaCronograma> lista) {
		this.listaActividadCronograma = lista;
	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("fecha_inicio") Date v1,
			@ExecutionArgParam("fecha_fin") Date v2,
			@ExecutionArgParam("hora_inicio") Time v3,
			@ExecutionArgParam("nombre") String v4,
			@ExecutionArgParam("descripcion") String v5,
			@ExecutionArgParam("lugar") String v6,
			@ExecutionArgParam("observacion") String v7)
			
	{
		Selectors.wireComponents(view, this, false);
		this.fecha_inicio = v1;
		this.fecha_fin = v2;
		this.hora_inicio = v3;
		this.nombre = v4;
		this.descripcion = v5;
		this.lugar = v6;
		this.observacion = v7;
	}

}
