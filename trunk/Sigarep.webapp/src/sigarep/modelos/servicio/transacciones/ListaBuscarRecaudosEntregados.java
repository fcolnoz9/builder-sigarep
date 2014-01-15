package sigarep.modelos.servicio.transacciones;

public class ListaBuscarRecaudosEntregados {
	
	private String nombreRecaudo;
	private String nombreTipoMotivo;
	private String nombreDocumento;
	private byte[] contenidoDocumento;
	private String tipoDocumento;
	private Integer idTipoMotivo;
	private Integer idRecaudo;
	
	public ListaBuscarRecaudosEntregados(String nombreRecaudo,
			String nombreTipoMotivo, String nombreDocumento,
			byte[] contenidoDocumento, String tipoDocumento, Integer idTipoMotivo,Integer idRecaudo) {
		super();
		this.nombreRecaudo = nombreRecaudo;
		this.nombreTipoMotivo = nombreTipoMotivo;
		this.nombreDocumento = nombreDocumento;
		this.contenidoDocumento = contenidoDocumento;
		this.tipoDocumento = tipoDocumento;
		this.idTipoMotivo = idTipoMotivo;
		this.idRecaudo = idRecaudo;
	}
	
	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}

	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}

	public Integer getIdRecaudo() {
		return idRecaudo;
	}

	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
	}

	public String getNombreRecaudo() {
		return nombreRecaudo;
	}
	public void setNombreRecaudo(String nombreRecaudo) {
		this.nombreRecaudo = nombreRecaudo;
	}
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}
	public void setContenidoDocumento(byte[] contenidoDocumento) {
		this.contenidoDocumento = contenidoDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	
	
}