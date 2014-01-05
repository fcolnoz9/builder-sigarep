package sigarep.modelos.data.maestros;

//Clase de Filtros para SancionMaestros. Filtrar por nombre y descripción
public class SancionMaestroFiltros {
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
