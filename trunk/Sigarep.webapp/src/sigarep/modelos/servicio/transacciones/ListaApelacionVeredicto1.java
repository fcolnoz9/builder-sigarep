package sigarep.modelos.servicio.transacciones;

public class ListaApelacionVeredicto1 {

	private String sancion;
	private String programa;
	private float indice;
//	private String periodosancion;
	private String apellido;
	private String nombre;
	private String cedula;
	private String codigolapso;
	
	
	public ListaApelacionVeredicto1(String cedula, String nombre,
			 String apellido, String sancion,
			String programa, float indice, String codigolapso ) {
		super();
		this.sancion = sancion;
		this.programa = programa;
		this.indice = indice;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cedula = cedula;	
		this.codigolapso = codigolapso;
	}
	
	public String getCodigoLapso() {
		return codigolapso;
	}

	public void setCodigoLapso(String codigolapso) {
		this.codigolapso = codigolapso;
	}

	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
