package sigarep.modelos.data.reportes;

/** Clase ConfigurableApelaciones
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ConfigurableApelaciones {
	// Atributos de la clase	
	private String nombrePrograma;
	private String nombreSancion;
	private String nombreTipoMotivo;
	private String codigoLapso;
	private String instanciaApelada;
	private String veredicto;
	private String edoApelacion;
	private String asignatura;

	/**
	 * Constructor ConfigurableApelaciones
	 * 
	 * @param nombrePrograma, nombreSancion, nombreTipoMotivo, instanciaApelada, codigoLapso, veredicto, edoApelacion, asignatura
	 */
	public ConfigurableApelaciones(String nombrePrograma, String nombreSancion,
			String nombreTipoMotivo, String instanciaApelada, String codigoLapso,String veredicto,String edoApelacion,String asignatura) {
		super();

		this.nombrePrograma = nombrePrograma;
		this.nombreSancion = nombreSancion;
		this.nombreTipoMotivo = nombreTipoMotivo;
		this.codigoLapso = codigoLapso;
		this.instanciaApelada = instanciaApelada;
		this.veredicto= veredicto;
		this.edoApelacion=edoApelacion;
		this.asignatura=asignatura;
	}

	//  Métodos Set y Get
	public String getEdoApelacion() {
		return edoApelacion;
	}
	
	public void setEdoApelacion(String edoApelacion) {
		this.edoApelacion = edoApelacion;
	}
	
	public String getInstanciaApelada() {
		return instanciaApelada;
	}
	
	public void setInstanciaApelada(String instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}
	
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	
	public String getNombreSancion() {
		return nombreSancion;
	}
	
	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}
	
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	
	public String getCodigoLapso() {
		return codigoLapso;
	}
	
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	
	public String getVeredicto() {
		return veredicto;
	}
	
	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	
	// Fin Métodos Set y Get
	
}


