package sigarep.modelos.data.maestros;

//Clase de Filtros para ProgramaAcademico. Filtrar por nombre
public class ProgramaAcademicoFiltros {
	private String nombre = "";

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null ? "" : nombre.trim();
	}

}
