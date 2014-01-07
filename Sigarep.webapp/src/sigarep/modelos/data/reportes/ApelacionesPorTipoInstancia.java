package sigarep.modelos.data.reportes;

public class ApelacionesPorTipoInstancia {
	private Integer cantidad;
	private String instancia;
	
	
	public ApelacionesPorTipoInstancia(Integer cantidad, String instancia) {
		super();
		this.cantidad = cantidad;
		this.instancia = instancia;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getInstancia() {
		return instancia;
	}
	
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
}