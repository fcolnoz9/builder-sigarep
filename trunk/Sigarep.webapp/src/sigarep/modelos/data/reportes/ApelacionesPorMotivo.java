package sigarep.modelos.data.reportes;

public class ApelacionesPorMotivo {
	private Integer apelaciones;
	private String motivo;
	private Integer procedentes;
	private Integer total;
	
	
	public ApelacionesPorMotivo(String motivo, Integer apelaciones, Integer procedentes, Integer total) {
		super();
		this.apelaciones = apelaciones;
		this.motivo = motivo;
		this.procedentes = procedentes;
		this.total = total;
	}
	

	public Integer getApelaciones() {
		return apelaciones;
	}
	
	public void setApelaciones(Integer apelaciones) {
		this.apelaciones = apelaciones;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Integer getProcedentes() {
		return procedentes;
	}


	public void setProcedentes(Integer procedentes) {
		this.procedentes = procedentes;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}
}