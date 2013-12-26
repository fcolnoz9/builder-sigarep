package sigarep.modelos.servicio.transacciones;

public class ListaApelacionMomento {
	
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String nombreSancion;
	
	public ListaApelacionMomento() {
	}

	public ListaApelacionMomento(String cedulaEstudiante, String primerNombre, String primerApellido,
			 String nombreSancion) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.nombreSancion = nombreSancion;
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

	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String getNombreSancion() {
		return nombreSancion;
	}

	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}


}
