package sigarep.modelos.data.reportes;

import java.math.BigInteger;

public class ListaAsignaturasMayorCantidadSancionados {

	private String nombreAsignatura;
	private BigInteger cantidadSancionados;

	public ListaAsignaturasMayorCantidadSancionados() {
		
	}
	
	public ListaAsignaturasMayorCantidadSancionados(String nombreAsignatura,BigInteger cantidadSancionados) {
		super();
		this.nombreAsignatura= nombreAsignatura;
		this.cantidadSancionados= cantidadSancionados;
		
	}

	
	public BigInteger getCantidadSancionados() {
		return cantidadSancionados;
	}

	public void setCantidadSancionados(BigInteger cantidadSancionados) {
		this.cantidadSancionados = cantidadSancionados;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}


	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}
}
