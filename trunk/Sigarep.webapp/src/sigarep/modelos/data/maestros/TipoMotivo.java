package sigarep.modelos.data.maestros;
import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.Motivo;


import java.util.LinkedList;
import java.util.List;



/**
 * The persistent class for the tipo_motivo database table.
 * 
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="tipo_motivo")
public class TipoMotivo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_motivo", unique=true, nullable=false)
	private Integer idTipoMotivo;
	@Column(name="nombre_tipo_motivo", nullable=false, length=60)
	private String nombreTipoMotivo;
	@Column(name="descripcion" ,length=255)
	private String descripcion;
	@Column(name ="estatus",nullable=false)
	private Boolean estatus;
	@Column(name ="protegido", nullable = true)
	private Boolean protegido;
	//bi-directional many-to-one association to Recaudo
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,mappedBy="tipoMotivo")
	private List<Recaudo> recaudos = new LinkedList<Recaudo>();
	//bi-directional many-to-one association to Motivo
	@OneToMany(mappedBy="tipoMotivo")
	private List<Motivo> motivos = new LinkedList<Motivo>();
	
	
	public TipoMotivo(){
		
	}

	//Constructor con parametros
	public TipoMotivo(Integer idTipoMotivo,String descripcion,Boolean estatus,
			String nombreTipoMotivo, Boolean protegido ) {
		super();
		this.idTipoMotivo= idTipoMotivo;
		this.descripcion= descripcion;
		this.estatus= estatus;
		this.nombreTipoMotivo = nombreTipoMotivo;
		this.protegido = protegido;
		
		// TODO Auto-generated constructor stub
	}

	public Integer getIdTipoMotivo() {
		return this.idTipoMotivo;
	}
	public String getNombreTipoMotivo() {
		return this.nombreTipoMotivo;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public Boolean getEstatus() {
		return this.estatus;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<Recaudo> getRecaudos() {
		return this.recaudos;
	}
	public void setRecaudos(List<Recaudo> recaudos) {
		this.recaudos = recaudos;
	}
	public Recaudo addRecaudo(Recaudo recaudo) {
		getRecaudos().add(recaudo);
		recaudo.setTipoMotivo(this);
		
		return recaudo;
	}
	public Recaudo removeRecaudo(Recaudo recaudo) {
		getRecaudos().remove(recaudo);
		recaudo.setTipoMotivo(null);

		return recaudo;
	}
	public List<Motivo> getMotivos() {
		return this.motivos;
	}
	public void setMotivos(List<Motivo> motivos) {
		this.motivos = motivos;
	}
	public Motivo addMotivo(Motivo motivo) {
		getMotivos().add(motivo);
		motivo.setTipoMotivo(this);

		return motivo;
	}
	public Motivo removeMotivo(Motivo motivo) {
		getMotivos().remove(motivo);
		motivo.setTipoMotivo(null);

		return motivo;
	}

	public Boolean getProtegido() {
		return protegido;
	}

	public void setProtegido(Boolean protegido) {
		this.protegido = protegido;
	}

}