package sigarep.viewmodels.transacciones;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import sigarep.modelos.data.transacciones.ListaCronograma;
import sigarep.modelos.servicio.transacciones.ServicioListaCronograma;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMListaCronograma {

	@WireVariable
	private String nombre;
	@WireVariable
	private String descripcion;
	@WireVariable
	private Date fecha_inicio;
	@WireVariable
	private Date fecha_fin;
	@WireVariable
	private Time hora_inicio;
	@WireVariable
	private String observacion;
	@WireVariable
	private String lugar;

	@WireVariable
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
	public void init() {
		buscarListaActividadCronograma();
	}

	@Command
	@NotifyChange({ "lista" })
	public void buscarListaActividadCronograma() {
		listaActividadCronograma = serviciolistacronograma
				.buscarActividadCronograma();
	}

	@Command
	@NotifyChange({ "nombre", "descripcion", "fecha_inicio", "fecha_fin",
			"hora_inicio", "observacion", "lugar" })
	public void showModal() {
		setNombre(getListaCronograma().getNombre());
		setDescripcion(getListaCronograma().getDescripcion());
		setFecha_inicio(getListaCronograma().getFecha_inicio());
		setFecha_fin(getListaCronograma().getFecha_fin());
		setHora_inicio(getListaCronograma().getHora_inicio());
		setObservacion(getListaCronograma().getObservacion());
		setLugar(getListaCronograma().getLugar());

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fecha_inicio", this.fecha_inicio);
		map.put("fecha_fin", this.fecha_fin);
		map.put("hora_inicio", this.hora_inicio);
		map.put("nombre", this.nombre);
		map.put("descripcion", this.descripcion);
		map.put("lugar", this.lugar);
		map.put("observacion", this.observacion);

		final Window window = (Window) Executions.createComponents(
				"/Modal/DescripcionCrog.zul", null, map);
		window.setMaximizable(true);
		window.doModal();
	}

}
