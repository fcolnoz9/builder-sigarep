package sigarep.modelos.servicio.transacciones;

public class ListaVeredicto {

	private String programa;
	private float indice;
	private String cedulaEstudiante;
	private String codigoLapso;	
	private String primerNombre;
	private String primerApellido;
	private String segundoNombre;
	private String segundoApellido;
	private String nombreSancion;
	private String email;
	private Integer instancia;
	private Integer numeroCaso;
	
	public ListaVeredicto(String cedulaEstudiante,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String programa, String email,
			float indice, String codigoLapso, String nombreSancion,
			Integer instancia, Integer numeroCaso) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.programa = programa;
		this.email = email;
		this.indice = indice;
		this.codigoLapso = codigoLapso;
		this.nombreSancion = nombreSancion;
		this.instancia = instancia;
		this.numeroCaso = numeroCaso;
	}
	
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public float getIndice() {
		return indice;
	}
	public void setIndice(float indice) {
		this.indice = indice;
	}
	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}
	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}
	public String getCodigoLapso() {
		return codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
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
	public Integer getInstancia() {
		return instancia;
	}
	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}
	public Integer getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(Integer numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	


}
