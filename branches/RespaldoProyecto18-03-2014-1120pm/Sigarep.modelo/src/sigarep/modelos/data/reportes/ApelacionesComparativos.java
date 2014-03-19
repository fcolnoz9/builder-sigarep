package sigarep.modelos.data.reportes;

/** Apelaciones
 * Reporte Estadisticos Comparativos 
 * @author BUILDER 
 * @version 1.0
 * @since 20/12/13
 */
public class ApelacionesComparativos {
	// Atributos de la clase
	private Integer apelaciones;
	private String categoria;
	private Integer procedentes;
	private Integer total;
	private Integer sancionados;

	/**
	 * Constructor ApelacionesComparativos
	 * 
	 * @param categoria, apelaciones, procedentes, total, sancionados
	 * @return Constructor lleno
	 */
	public ApelacionesComparativos(String categoria, Integer apelaciones,
			Integer procedentes, Integer total, Integer sancionados) {
		super();
		this.apelaciones = apelaciones;
		this.categoria = categoria;
		this.procedentes = procedentes;
		this.total = total;
		this.sancionados = sancionados;
	}

	//  Métodos Set y Get
	public Integer getApelaciones() {
		return apelaciones;
	}
	
	public void setApelaciones(Integer apelaciones) {
		this.apelaciones = apelaciones;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	
	public Integer getSancionados() {
		return sancionados;
	}
	
	public void setSancionados(Integer sancionados) {
		this.sancionados = sancionados;
	}// Fin Métodos Set y Get
	
}//Fin Clase ApelacionesComparativos