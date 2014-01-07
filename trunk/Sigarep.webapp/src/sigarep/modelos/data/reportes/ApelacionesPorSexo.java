package sigarep.modelos.data.reportes;

public class ApelacionesPorSexo {
	private Integer cantidad;
	private String sexo;
	
	
	public ApelacionesPorSexo(Integer cantidad, String sexo) {
		super();
		this.cantidad = cantidad;
		this.sexo = sexo;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}