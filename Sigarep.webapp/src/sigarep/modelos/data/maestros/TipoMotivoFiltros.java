package sigarep.modelos.data.maestros;

public class TipoMotivoFiltros {
	private String nombre = "", descripcion = "";

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre == null ? "" : nombre.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}
	
}
