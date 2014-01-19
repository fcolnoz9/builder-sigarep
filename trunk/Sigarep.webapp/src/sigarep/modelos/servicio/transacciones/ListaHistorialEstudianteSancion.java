package sigarep.modelos.servicio.transacciones;



public class ListaHistorialEstudianteSancion {
	
	private String codigoLapso;
	private String nombreSancion;
	
	public ListaHistorialEstudianteSancion(String codigoLapso,
			String nombreSancion) {
		super();
		this.codigoLapso = codigoLapso;
		this.nombreSancion = nombreSancion;
	}
	public String getCodigoLapso() {
		return codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	public String getNombreSancion() {
		return nombreSancion;
	}
	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}




	
	}