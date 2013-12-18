package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the menu_funcion database table.
 * 
 */
@Entity
@Table(name="menu_funcion")
public class MenuFuncion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_funcion", unique=true, nullable=false)
	private Integer idFuncion;

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_funcion", nullable=false, length=50)
	private String nombreFuncion;

	//bi-directional many-to-one association to DatoSistema
	@ManyToOne
	@JoinColumn(name="dato_sistemaid_sistema", nullable=false)
	private DatoSistema datoSistema;

	//bi-directional many-to-one association to NivelMenu
	@ManyToOne
	@JoinColumn(name="id_nivel_menu", nullable=false)
	private NivelMenu nivelMenu;

	//bi-directional many-to-one association to Operacion
	@OneToMany(mappedBy="menuFuncion")
	private List<Operacion> operacions;

	public MenuFuncion() {
	}

	public Integer getIdFuncion() {
		return this.idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreFuncion() {
		return this.nombreFuncion;
	}

	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public DatoSistema getDatoSistema() {
		return this.datoSistema;
	}

	public void setDatoSistema(DatoSistema datoSistema) {
		this.datoSistema = datoSistema;
	}

	public NivelMenu getNivelMenu() {
		return this.nivelMenu;
	}

	public void setNivelMenu(NivelMenu nivelMenu) {
		this.nivelMenu = nivelMenu;
	}

	public List<Operacion> getOperacions() {
		return this.operacions;
	}

	public void setOperacions(List<Operacion> operacions) {
		this.operacions = operacions;
	}

	public Operacion addOperacion(Operacion operacion) {
		getOperacions().add(operacion);
		operacion.setMenuFuncion(this);

		return operacion;
	}

	public Operacion removeOperacion(Operacion operacion) {
		getOperacions().remove(operacion);
		operacion.setMenuFuncion(null);

		return operacion;
	}

}