package sigarep.modelos.data.reportes;

public class ApelacionesPorMotivo {
	private Integer cantidad;
	private String motivo;
	private String resultado;
	
	
	public ApelacionesPorMotivo(Integer cantidad, String motivo, String resultado) {
		super();
		this.cantidad = cantidad;
		this.motivo = motivo;
		this.resultado = resultado;
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


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}