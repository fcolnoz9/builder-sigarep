package sigarep.modelos.data.reportes;

public class ApelacionesPorLapso {
	private Integer cantidad;
	private String lapso_academico;
	
	
	public ApelacionesPorLapso(Integer cantidad, String lapso_academico) {
		super();
		this.cantidad = cantidad;
		this.lapso_academico = lapso_academico;
	}
	

	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getLapso_Academico() {
		return lapso_academico;
	}
	
	public void setLapso_Academico(String lapso_academico) {
		this.lapso_academico = lapso_academico;
	}
}