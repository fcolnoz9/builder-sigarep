package sigarep.modelos.data.reportes;

/** Clase ListaEstudiantesProcedentes
 * @author Equipo Builder   
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public class ListaEstudiantesProcedentes {
	// Atributos de la clase
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombrePrograma;
	private String veredicto;
	private String instanciaApelada;
	private Integer semestre;
	
	// constructor por defecto
	public ListaEstudiantesProcedentes(){
		
	}
	
	/**
	 * Constructor ListaEspecialEstudiantesSancionadosApelaciones
	 * @param cedulaEstudiante, primerNombre, primerApellido, nombrePrograma, veredicto, instanciaApelada, semestre
	 */
	public ListaEstudiantesProcedentes(String cedulaEstudiante, String primerNombre, String primerApellido,
										String nombrePrograma, String veredicto, String instanciaApelada, Integer semestre){
		
		this.cedulaEstudiante= cedulaEstudiante;
		this.primerNombre= primerNombre;
		this.primerApellido= primerApellido;
		this.nombrePrograma= nombrePrograma;
		this.veredicto= veredicto;
		this.instanciaApelada= instanciaApelada;
		this.semestre= semestre;
		
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

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public String getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(String instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}// Fin Métodos Set y Get

	
	
}
