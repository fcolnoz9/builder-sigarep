package sigarep.modelos.data.reportes;
import java.util.Date;

/** Clase ListaEspecialEstudiantesSancionadosApelaciones
 * @author Equipo Builder   
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ListaEspecialEstudiantesSancionadosApelaciones {
	// Atributos de la clase
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombrePrograma;
	private String nombreSancion;
	private String codigoLapso;
	private Integer periodoSancion;
	private String nombreAsignatura;
	private String instanciaApelada;
	private String veredicto;
	private String observacion;	
	private String tipoMotivo;
	private String descripcion;
	private String nroSesion;
	private Date fechaSesion;

	/**
	 * Constructor ListaEspecialEstudiantesSancionadosApelaciones
	 * @param cedulaEstudiante, primerNombre, primerApellido, nombrePrograma,
	 * nombreSancion, codigoLapso, periodoSancion, nombreAsignatura, instanciaApelada,
	 * veredicto, observacion, nombreTipoMotivo, descripcion, nroSesion, fechaSesion
	 */
	public ListaEspecialEstudiantesSancionadosApelaciones(String cedulaEstudiante,
			String primerNombre, String primerApellido, String nombrePrograma,
			String nombreSancion, String codigoLapso, Integer periodoSancion,
			String nombreAsignatura, String instanciaApelada, String veredicto,
			String observacion, String nombreTipoMotivo, String descripcion, String nroSesion, Date fechaSesion) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.nombrePrograma = nombrePrograma;
		this.nombreSancion = nombreSancion;
		this.codigoLapso = codigoLapso;
		this.periodoSancion = periodoSancion;
		this.nombreAsignatura = nombreAsignatura;
		this.instanciaApelada = instanciaApelada;
		this.veredicto = veredicto;
		this.observacion = observacion;
		this.tipoMotivo = nombreTipoMotivo;
		this.descripcion = descripcion;
		this.nroSesion= nroSesion;
		this.fechaSesion= fechaSesion;
	}

	//  Métodos Set y Get
	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public Integer getPeriodoSancion() {
		return periodoSancion;
	}

	public void setPeriodoSancion(Integer periodoSancion) {
		this.periodoSancion = periodoSancion;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(String instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoMotivo() {
		return tipoMotivo;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.tipoMotivo = nombreTipoMotivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNroSesion() {
		return nroSesion;
	}

	public void setNroSesion(String nroSesion) {
		this.nroSesion = nroSesion;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}// Fin Métodos Set y Get

}
