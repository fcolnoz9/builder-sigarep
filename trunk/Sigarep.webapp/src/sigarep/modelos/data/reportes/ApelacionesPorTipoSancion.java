package sigarep.modelos.data.reportes;

public class ApelacionesPorTipoSancion {
	private Integer cantidad;
	private String tiposancion;
	
	
	public ApelacionesPorTipoSancion(Integer cantidad, String tiposancion) {
		super();
		this.cantidad = cantidad;
		this.tiposancion = tiposancion;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getTipoSancion() {
		return tiposancion;
	}
	
	public void setTipoSancion(String tiposancion) {
		this.tiposancion = tiposancion;
	}
}