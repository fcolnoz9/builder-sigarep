package sigarep.modelos.data.transacciones;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the soporte database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="soporte")
public class Soporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SoportePK id;

	@Column(name="archivo_soporte", nullable=false)
	private byte[] archivoSoporte;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="extension_archivo", nullable=false, length=4)
	private String extensionArchivo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_subida", nullable=false)
	private Date fechaSubida;

	@Column(name="nombre_archivo", nullable=false, length=60)
	private String nombreArchivo;

	@Column(name="tamanio_archivo_mb", nullable=false, precision=19)
	private BigDecimal tamanioArchivoMb;

	//bi-directional many-to-one association to RecaudoEntregado
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cedula_estudiante", referencedColumnName="cedula_estudiante", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="codigo_lapso", referencedColumnName="codigo_lapso", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_instancia_apelada", referencedColumnName="id_instancia_apelada", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_recaudo", referencedColumnName="id_recaudo", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="id_tipo_motivo", referencedColumnName="id_tipo_motivo", nullable=false, insertable=false, updatable=false)
		})
	private RecaudoEntregado recaudoEntregado;

	public Soporte() {
	}

	public SoportePK getId() {
		return this.id;
	}

	public void setId(SoportePK id) {
		this.id = id;
	}

	public byte[] getArchivoSoporte() {
		return this.archivoSoporte;
	}

	public void setArchivoSoporte(byte[] archivoSoporte) {
		this.archivoSoporte = archivoSoporte;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getExtensionArchivo() {
		return this.extensionArchivo;
	}

	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	public Date getFechaSubida() {
		return this.fechaSubida;
	}

	public void setFechaSubida(Date fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public BigDecimal getTamanioArchivoMb() {
		return this.tamanioArchivoMb;
	}

	public void setTamanioArchivoMb(BigDecimal tamanioArchivoMb) {
		this.tamanioArchivoMb = tamanioArchivoMb;
	}

	public RecaudoEntregado getRecaudoEntregado() {
		return this.recaudoEntregado;
	}

	public void setRecaudoEntregado(RecaudoEntregado recaudoEntregado) {
		this.recaudoEntregado = recaudoEntregado;
	}

}