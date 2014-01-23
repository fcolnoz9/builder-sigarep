package sigarep.modelos.data.maestros;
/**Instancia Filtros
* UCLA DCYT Sistemas de Informacion.
* @author Equipo: Builder-SIGAREP 
* @version 1.0
* @since 20/12/13
*/

public class InstanciaApeladaFiltros {
	private String nombreInstancia = "", nombreRecurso = "", descripcion = "";

	public String getNombreInstancia() {
		return nombreInstancia;
	}

	public void setNombreInstancia(String nombreInstancia) {
		this.nombreInstancia = nombreInstancia == null ? "" : nombreInstancia.trim();
	}
	
	public String getNombreRecurso(){
		return nombreRecurso;
	}
	
	public void setNombreRecurso(String nombreRecurso){
		this.nombreRecurso = nombreRecurso == null ? "" : nombreRecurso.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}
}