package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dato_sistema database table.
 * 
 */
@Entity
@Table(name="dato_sistema")
public class DatoSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_sistema", unique=true, nullable=false)
	private Integer idSistema;

	@Column(name="area_estudio", length=255)
	private String areaEstudio;

	@Column(length=255)
	private String descripcion;

	private byte[] logo;

	@Column(name="nombre_completo", nullable=false, length=255)
	private String nombreCompleto;

	@Column(length=255)
	private String objetivo;

	//bi-directional many-to-one association to MenuFuncion
	@OneToMany(mappedBy="datoSistema")
	private List<MenuFuncion> menuFuncions;

	public DatoSistema() {
	}

	public Integer getIdSistema() {
		return this.idSistema;
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	public String getAreaEstudio() {
		return this.areaEstudio;
	}

	public void setAreaEstudio(String areaEstudio) {
		this.areaEstudio = areaEstudio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public List<MenuFuncion> getMenuFuncions() {
		return this.menuFuncions;
	}

	public void setMenuFuncions(List<MenuFuncion> menuFuncions) {
		this.menuFuncions = menuFuncions;
	}

	public MenuFuncion addMenuFuncion(MenuFuncion menuFuncion) {
		getMenuFuncions().add(menuFuncion);
		menuFuncion.setDatoSistema(this);

		return menuFuncion;
	}

	public MenuFuncion removeMenuFuncion(MenuFuncion menuFuncion) {
		getMenuFuncions().remove(menuFuncion);
		menuFuncion.setDatoSistema(null);

		return menuFuncion;
	}

}