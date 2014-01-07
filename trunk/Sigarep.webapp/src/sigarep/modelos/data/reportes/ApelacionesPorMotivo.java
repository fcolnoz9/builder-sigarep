package sigarep.modelos.data.reportes;

public class ApelacionesPorMotivo {
	private Integer cantidad;
	private String motivo;
	
	
	public ApelacionesPorMotivo(Integer cantidad, String motivo) {
		super();
		this.cantidad = cantidad;
		this.motivo = motivo;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}