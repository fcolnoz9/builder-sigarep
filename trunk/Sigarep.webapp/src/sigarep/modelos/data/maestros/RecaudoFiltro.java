package sigarep.modelos.data.maestros;

public class RecaudoFiltro {
	private String nombreRecaudo = "", descripcion="", observacion="", nombreTipoMotivo = "";

	public String getNombreRecaudo() {
		return nombreRecaudo;
	}

	public void setNombreRecaudo(String nombreRecaudo) {
		this.nombreRecaudo = nombreRecaudo == null ? "" : nombreRecaudo.trim();

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion.trim();
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion == null ? "" : observacion.trim();;
	}

	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}

	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo == null ? "" : nombreTipoMotivo.trim();;
	}


}
	