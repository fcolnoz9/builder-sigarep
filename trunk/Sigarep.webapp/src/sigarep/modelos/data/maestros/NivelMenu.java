package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nivel_menu database table.
 * 
 */
@Entity
@Table(name="nivel_menu")
public class NivelMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_nivel_menu", unique=true, nullable=false)
	private Integer idNivelMenu;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name="nombre_nivel_menu", nullable=false, length=50)
	private String nombreNivelMenu;

	//bi-directional many-to-one association to MenuFuncion
	@OneToMany(mappedBy="nivelMenu")
	private List<MenuFuncion> menuFuncions;

	//bi-directional many-to-one association to NivelMenu
	@ManyToOne
	@JoinColumn(name="id_nivel", nullable=false)
	private NivelMenu nivelMenu;

	//bi-directional many-to-one association to NivelMenu
	@OneToMany(mappedBy="nivelMenu")
	private List<NivelMenu> nivelMenus;

	public NivelMenu() {
	}

	public Integer getIdNivelMenu() {
		return this.idNivelMenu;
	}

	public void setIdNivelMenu(Integer idNivelMenu) {
		this.idNivelMenu = idNivelMenu;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombreNivelMenu() {
		return this.nombreNivelMenu;
	}

	public void setNombreNivelMenu(String nombreNivelMenu) {
		this.nombreNivelMenu = nombreNivelMenu;
	}

	public List<MenuFuncion> getMenuFuncions() {
		return this.menuFuncions;
	}

	public void setMenuFuncions(List<MenuFuncion> menuFuncions) {
		this.menuFuncions = menuFuncions;
	}

	public MenuFuncion addMenuFuncion(MenuFuncion menuFuncion) {
		getMenuFuncions().add(menuFuncion);
		menuFuncion.setNivelMenu(this);

		return menuFuncion;
	}

	public MenuFuncion removeMenuFuncion(MenuFuncion menuFuncion) {
		getMenuFuncions().remove(menuFuncion);
		menuFuncion.setNivelMenu(null);

		return menuFuncion;
	}

	public NivelMenu getNivelMenu() {
		return this.nivelMenu;
	}

	public void setNivelMenu(NivelMenu nivelMenu) {
		this.nivelMenu = nivelMenu;
	}

	public List<NivelMenu> getNivelMenus() {
		return this.nivelMenus;
	}

	public void setNivelMenus(List<NivelMenu> nivelMenus) {
		this.nivelMenus = nivelMenus;
	}

	public NivelMenu addNivelMenus(NivelMenu nivelMenus) {
		getNivelMenus().add(nivelMenus);
		nivelMenus.setNivelMenu(this);

		return nivelMenus;
	}

	public NivelMenu removeNivelMenus(NivelMenu nivelMenus) {
		getNivelMenus().remove(nivelMenus);
		nivelMenus.setNivelMenu(null);

		return nivelMenus;
	}

}