package sigarep.modelos.servicio.transacciones;

public class ListaCargarRecaudoEntregado {
	
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombreSancion;
	private String email;
	private String programa;
	private String lapso;
	private Integer instancia;
	private String segundoNombre;
	private String segundoApellido;
	private String asignatura;
	private Integer caso;

	public ListaCargarRecaudoEntregado(String cedulaEstudiante, String primerNombre,
			String primerApellido, String nombreSancion, String email,
			String programa, String lapso, Integer instancia, String segundoNombre,
			String segundoApellido, String asignatura, Integer caso) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.nombreSancion = nombreSancion;
		this.email = email;
		this.programa = programa;
		this.lapso = lapso;	
		this.instancia = instancia;
	
	
		this.segundoNombre = segundoNombre;
		this.segundoApellido = segundoApellido;
		this.asignatura = asignatura;
		this.caso = caso;
	
	}

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Integer getInstancia() {
		return instancia;
	}
	
	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

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

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}


	public ListaCargarRecaudoEntregado() {
		// TODO Auto-generated constructor stub
	}

}
