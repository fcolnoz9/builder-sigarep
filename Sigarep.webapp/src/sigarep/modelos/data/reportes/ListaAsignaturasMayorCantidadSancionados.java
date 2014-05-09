package sigarep.modelos.data.reportes;
import java.math.BigDecimal;

/** Clase ListaAsignaturasMayorCantidadSancionados
 * @author Equipo Builder 
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ListaAsignaturasMayorCantidadSancionados {
	// Atributos de la clase
	private String asignatura;
	private BigDecimal sanciones;
	private BigDecimal apelaciones;
	private BigDecimal procedentes;
	private BigDecimal noprocedentes;
	private BigDecimal totalsancion;
	private BigDecimal totalapela;

	// constructor por defecto
	public ListaAsignaturasMayorCantidadSancionados() {

	}

	/**
	 * Constructor ListaAsignaturasMayorCantidadSancionados
	 * @param asignatura, sanciones, apelaciones, procedentes, noprocedentes, toltalsancion, totalapela
	 */
	public ListaAsignaturasMayorCantidadSancionados(String asignatura,BigDecimal sanciones,BigDecimal apelaciones,
			BigDecimal procedentes, BigDecimal noprocedentes,BigDecimal toltalsancion, BigDecimal totalapela) {
		super();
		this.asignatura= asignatura;
		this.sanciones= sanciones;
		this.apelaciones= apelaciones;
		this.setProcedentes(procedentes);
		this.noprocedentes= noprocedentes;
		this.totalsancion= toltalsancion;
		this.totalapela= totalapela;
	}

	//  Métodos Set y Get
	public BigDecimal getApelaciones() {
		return apelaciones;
	}
	
	public void setApelaciones(BigDecimal apelaciones) {
		this.apelaciones = apelaciones;
	}
	
	public String getAsignatura() {
		return asignatura;
	}
	
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	
	public BigDecimal getSanciones() {
		return sanciones;
	}
	
	public void setSanciones(BigDecimal sanciones) {
		this.sanciones = sanciones;
	}
	
	public BigDecimal getProcedentes() {
		return procedentes;
	}
	
	public void setProcedentes(BigDecimal procedentes) {
		this.procedentes = procedentes;
	}
	
	public BigDecimal getNoprocedentes() {
		return noprocedentes;
	}
	
	public void setNoprocedentes(BigDecimal noprocedentes) {
		this.noprocedentes = noprocedentes;
	}
	
	public BigDecimal getTotalsancion() {
		return totalsancion;
	}
	
	public void setTotalsancion(BigDecimal totalsancion) {
		this.totalsancion = totalsancion;
	}
	
	public BigDecimal getTotalapela() {
		return totalapela;
	}
	
	public void setTotalapela(BigDecimal totalapela) {
		this.totalapela = totalapela;
	}// Fin Métodos Set y Get
	
}
