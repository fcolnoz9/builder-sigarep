package sigarep.modelos.lista;

/**
 * Clase ListaGenericaSancionados (Lista que almacena los datos de los estudiantes
 *  sancionados para las tres instancias)
 * @author Equipo Builder
 * @version 1.0
 * @since 15/12/2013
 * @last 10/05/2014
 */
public class ListaGenericaSancionados {
	
	// Atributos de la clase
	private String cedulaEstudiante;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String email;
	private String programaAcademico;
	private float indiceAcademico;
	private String nombreSancion;
	private String lapsosConsecutivos;
	private String lapsoAcademico;
	private Integer numeroCaso;
	private Integer idInstancia;

	/**
	 * Constructor de la clase ListaGenericaSancionados
	 * 
	 * @param cedulaEstudiante, primerNombre, segundoNombre, primerApellido,
	 * segundoApellido, email, programaAcademico, indiceAcademico, nombreSancion,
	 * lapsosConsecutivos, lapsoAcademico, numeroCaso, idInstancia
	 */
	public ListaGenericaSancionados(String cedulaEstudiante, String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, String email, String programaAcademico,
			float indiceAcademico, String nombreSancion,
			String lapsosConsecutivos, String lapsoAcademico,
			Integer numeroCaso, Integer idInstancia) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.email = email;
		this.programaAcademico = programaAcademico;
		this.indiceAcademico = indiceAcademico;
		this.nombreSancion = nombreSancion;
		this.lapsosConsecutivos = lapsosConsecutivos;
		this.lapsoAcademico = lapsoAcademico;
		this.numeroCaso = numeroCaso;
		this.idInstancia = idInstancia;
	}

	// Métodos Set y Get
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

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramaAcademico() {
		return programaAcademico;
	}

	public void setProgramaAcademico(String programaAcademico) {
		this.programaAcademico = programaAcademico;
	}

	public float getIndiceAcademico() {
		return indiceAcademico;
	}

	public void setIndiceAcademico(float indiceAcademico) {
		this.indiceAcademico = indiceAcademico;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public String getLapsosConsecutivos() {
		return lapsosConsecutivos;
	}

	public void setLapsosConsecutivos(String lapsosConsecutivos) {
		this.lapsosConsecutivos = lapsosConsecutivos;
	}

	public String getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(String lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public Integer getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(Integer numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public Integer getIdInstancia() {
		return idInstancia;
	}

	public void setIdInstancia(Integer idInstancia) {
		this.idInstancia = idInstancia;
	}
	// Fin Métodos Set y Get

} 
