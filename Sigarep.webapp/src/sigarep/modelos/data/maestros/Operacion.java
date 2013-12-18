package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.GrupoOperacion;

import java.util.List;


/**
 * The persistent class for the operacion database table.
 * 
 */
@Entity
@Table(name="operacion")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_operacion", unique=true, nullable=false)
	private Integer idOperacion;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_operacion", nullable=false, length=255)
	private String nombreOperacion;

	//bi-directional many-to-one association to MenuFuncion
	@ManyToOne
	@JoinColumn(name="id_funcion", nullable=true)
	private MenuFuncion menuFuncion;

	//bi-directional many-to-one association to GrupoOperacion
	@OneToMany(mappedBy="operacion")
	private List<GrupoOperacion> grupoOperacions;

	public Operacion() {
	}

	public Integer getIdOperacion() {
		return this.idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreOperacion() {
		return this.nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public MenuFuncion getMenuFuncion() {
		return this.menuFuncion;
	}

	public void setMenuFuncion(MenuFuncion menuFuncion) {
		this.menuFuncion = menuFuncion;
	}

	public List<GrupoOperacion> getGrupoOperacions() {
		return this.grupoOperacions;
	}

	public void setGrupoOperacions(List<GrupoOperacion> grupoOperacions) {
		this.grupoOperacions = grupoOperacions;
	}

	public GrupoOperacion addGrupoOperacion(GrupoOperacion grupoOperacion) {
		getGrupoOperacions().add(grupoOperacion);
		grupoOperacion.setOperacion(this);

		return grupoOperacion;
	}

	public GrupoOperacion removeGrupoOperacion(GrupoOperacion grupoOperacion) {
		getGrupoOperacions().remove(grupoOperacion);
		grupoOperacion.setOperacion(null);

		return grupoOperacion;
	}

}