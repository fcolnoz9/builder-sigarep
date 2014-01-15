package sigarep.modelos.data.maestros;

public class ReglamentoFiltros {

	private String titulo = "";
	private String descripcion="";
	private String categoria="";
	//private Date fechaSubida= new Date();
	//private Documento documento=null;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo == null ? "" : titulo.trim();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria == null ? "" : categoria.trim();
	}
	
	
	
	
}
