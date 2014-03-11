package sigarep.modelos.data.reportes;
/** Apelaciones
 * Reporte Estadisticos Comparativos 
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/
public class ApelacionesComparativos {
	private Integer apelaciones;
	private String categoria;
	private Integer procedentes;
	private Integer total;
	private Integer sancionados;

	public ApelacionesComparativos(String categoria, Integer apelaciones,
			Integer procedentes, Integer total, Integer sancionados) {
		super();
		this.apelaciones = apelaciones;
		this.categoria = categoria;
		this.procedentes = procedentes;
		this.total = total;
		this.sancionados = sancionados;
	}

	// Métodos GET y SET
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
	}
}