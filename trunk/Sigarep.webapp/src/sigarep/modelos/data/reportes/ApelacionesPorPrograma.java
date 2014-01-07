package sigarep.modelos.data.reportes;

public class ApelacionesPorPrograma {
	private Integer cantidad;
	private String programa;
	
	
	public ApelacionesPorPrograma(Integer cantidad, String programa) {
		super();
		this.cantidad = cantidad;
		this.programa = programa;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getPrograma() {
		return programa;
	}
	
	public void setPrograma(String programa) {
		this.programa = programa;
	}
}