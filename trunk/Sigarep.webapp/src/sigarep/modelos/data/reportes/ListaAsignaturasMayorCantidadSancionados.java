package sigarep.modelos.data.reportes;

import java.math.BigDecimal;

public class ListaAsignaturasMayorCantidadSancionados {

	private String asignatura;
	private BigDecimal sanciones;
	private BigDecimal apelaciones;
	private BigDecimal procedentes;
	private BigDecimal noprocedentes;
	private BigDecimal totalsancion;
	private BigDecimal totalapela;
	//private String nombrePrograma;
	
	

	public ListaAsignaturasMayorCantidadSancionados() {
		
	}
	
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
		//this.nombrePrograma= nombrePrograma;
	}

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
	}

}
