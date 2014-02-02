package sigarep.modelos.data.reportes;

public class EstudianteSancionado {
	private String primerNombre;
	private String primerApellido;
	private String sexo;
	private String nombrePrograma;
	private String nombreSancion;
	private String nombreTipoMotivo;
	private String codigoLapso;
	private String instanciaApelada;
	
	
	public EstudianteSancionado(String primerNombre, String primerApellido,
			String sexo, String nombrePrograma, String nombreSancion,
			String nombreTipoMotivo, String instanciaApelada, String codigoLapso) {
		super();
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.sexo = sexo;
		this.nombrePrograma = nombrePrograma;
		this.nombreSancion = nombreSancion;
		this.nombreTipoMotivo = nombreTipoMotivo;
		this.codigoLapso = codigoLapso;
		this.instanciaApelada = instanciaApelada;
	}
	
	public String getInstanciaApelada() {
		return instanciaApelada;
	}

	public void setInstanciaApelada(String instanciaApelada) {
		this.instanciaApelada = instanciaApelada;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
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

}
