package sigarep.modelos.data.reportes;

import java.math.BigDecimal;

public class ListaAsignaturasMayorCantidadSancionados {

	private String nombreAsignatura;
	private BigDecimal cantidadSancionados;
	private BigDecimal apelaciones;
	private BigDecimal enfermedad;
	private BigDecimal judicial;
	private BigDecimal economico;
	private BigDecimal medico;
	private BigDecimal procedentes;
	private BigDecimal noprocedentes;
	//private String nombrePrograma;
	
	

	public ListaAsignaturasMayorCantidadSancionados() {
		
	}
	
	public ListaAsignaturasMayorCantidadSancionados(String nombreAsignatura,BigDecimal cantidadSancionados,BigDecimal apelaciones, 
			BigDecimal enfermedad,BigDecimal judicial, BigDecimal economico,BigDecimal medico, BigDecimal procedentes, BigDecimal noprocedentes) {
		super();
		this.nombreAsignatura= nombreAsignatura;
		this.cantidadSancionados= cantidadSancionados;
		this.apelaciones= apelaciones;
		this.enfermedad= enfermedad;
		this.judicial= judicial;
		this.economico= economico;
		this.medico= medico;
		this.procedentes= procedentes;
		this.noprocedentes= noprocedentes;
		//this.nombrePrograma= nombrePrograma;
	}

	
	public BigDecimal getCantidadSancionados() {
		return cantidadSancionados;
	}

	public void setCantidadSancionados(BigDecimal cantidadSancionados) {
		this.cantidadSancionados = cantidadSancionados;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}


	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}


	public BigDecimal getApelaciones() {
		return apelaciones;
	}

	public void setApelaciones(BigDecimal apelaciones) {
		this.apelaciones = apelaciones;
	}

	public BigDecimal getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(BigDecimal enfermedad) {
		this.enfermedad = enfermedad;
	}

	public BigDecimal getJudicial() {
		return judicial;
	}

	public void setJudicial(BigDecimal judicial) {
		this.judicial = judicial;
	}

	public BigDecimal getEconomico() {
		return economico;
	}

	public void setEconomico(BigDecimal economico) {
		this.economico = economico;
	}

	public BigDecimal getMedico() {
		return medico;
	}

	public void setMedico(BigDecimal medico) {
		this.medico = medico;
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

	/*public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
*/
}
