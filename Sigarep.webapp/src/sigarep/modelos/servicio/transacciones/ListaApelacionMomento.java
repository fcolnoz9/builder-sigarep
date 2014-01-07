package sigarep.modelos.servicio.transacciones;

public class ListaApelacionMomento {
	
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombreSancion;
	private String email;
	private String telefono;
	private String programa;
	private String lapso;
	private Integer instancia;
	private String motivo;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String asignatura;
	private Integer caso;
	
	
	
	public ListaApelacionMomento(String cedulaEstudiante, String primerNombre,
			String primerApellido, String nombreSancion, String email,
			String telefono, String programa, String lapso, Integer instancia, String motivo,
			String recaudo, String segundoNombre, String segundoApellido, String asignatura, Integer caso) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.nombreSancion = nombreSancion;
		this.email = email;
		this.telefono = telefono;
		this.programa = programa;
		this.lapso = lapso;	
		this.instancia = instancia;
		this.motivo = motivo;
		this.recaudo = recaudo;
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


	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
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



	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}


	

}