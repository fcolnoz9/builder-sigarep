package sigarep.modelos.data.reportes;
import java.math.BigDecimal;

/** Clase ListaApelacionesMotivoPorAsignatura
 * @author Equipo Builder 
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ListaApelacionesMotivoPorAsignatura {
	// Atributos de la clase
	private String asignatura;
	private String motivo;
	private BigDecimal apelaciones;
	private BigDecimal procedentes;
	private BigDecimal totalapelaciones;
	private BigDecimal totalprocedentes;

	// constructor por defecto
	public ListaApelacionesMotivoPorAsignatura(){

	}

	/**
	 * Constructor ListaApelacionesMotivoPorAsignatura
	 * @param asignatura, motivo, apelaciones, procedentes, totalapelaciones, totalprocedentes
	 */
	public ListaApelacionesMotivoPorAsignatura(String asignatura, String motivo, BigDecimal apelaciones, BigDecimal procedentes, BigDecimal totalapelaciones, BigDecimal totalprocedentes){
		super();
		this.asignatura= asignatura;
		this.motivo= motivo;
		this.apelaciones= apelaciones;
		this.procedentes= procedentes;
	}

	//  Métodos Set y Get
	public BigDecimal getTotalapelaciones() {
		return totalapelaciones;
	}
	
	public void setTotalapelaciones(BigDecimal totalapelaciones) {
		this.totalapelaciones = totalapelaciones;
	}
	
	public BigDecimal getTotalprocedentes() {
		return totalprocedentes;
	}
	
	public void setTotalprocedentes(BigDecimal totalprocedentes) {
		this.totalprocedentes = totalprocedentes;
	}
	
	public String getAsignatura() {
		return asignatura;
	}
	
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public BigDecimal getApelaciones() {
		return apelaciones;
	}
	
	public void setApelaciones(BigDecimal apelaciones) {
		this.apelaciones = apelaciones;
	}
	
	public BigDecimal getProcedentes() {
		return procedentes;
	}
	
	public void setProcedentes(BigDecimal procedentes) {
		this.procedentes = procedentes;
	}// Fin Métodos Set y Get
	
}
