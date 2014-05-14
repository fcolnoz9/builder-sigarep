package sigarep.modelos.data.reportes;

/** Clase ProcedentesComision 
 * @author Equipo Builder 
 * @version 1.0
 * @since 13/05/2014
 * @last 13/05/2014
 */
public class ProcedentesComision {
	// Atributos de la clase
	private String programa;
	private Integer procedentes;
	private Integer noprocedentes;

	/**
	 * Constructor ProcedentesComision
	 * 
	 * @param programa, procedentes, no procedentes
	 */
	public ProcedentesComision(String programa, Integer procedentes,
			Integer noprocedentes) {
		super();
		this.programa = programa;
		this.procedentes = procedentes;
		this.noprocedentes = noprocedentes;
	}

	//  Métodos Set y Get
	public String getPrograma() {
		return programa;
	}
	
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	public Integer getProcedentes() {
		return procedentes;
	}
	
	public void setProcedentes(Integer procedentes) {
		this.procedentes = procedentes;
	}
	
	public Integer getNoprocedentes() {
		return noprocedentes;
	}
	
	public void setNoprocedentes(Integer noprocedentes) {
		this.noprocedentes = noprocedentes;
	}
	// Fin Métodos Set y Get
}
	