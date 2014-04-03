package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.image.AImage;

/**
 * Clase MenuItem (Se utiliza para mostrar las imagenes en los maestros)
 * 
 * @author BUILDER
 * @version 1
 * @since 15/01/2014
 */

public class MenuItem {
	
	private String name;
	private AImage img;
	private String href;
	private List<MenuItem> children;
	private int level;

	/**
	 * Constructor MenuItem
	 * 
	 * @param name, img, href, level
	 * @return Constructor lleno
	 */
	public MenuItem(String name, AImage img, String href, int level) {
		this.name = name;
		this.img = img;
		this.level = level;
		this.href = href;
		children = new ArrayList<MenuItem>();
	}

	/**
	 * addChild
	 * 
	 * @param node
	 * @return agrega un nodo al menu de enlaces de interes
	 */
	public void addChild(MenuItem node) {
		children.add(node);
	}

	/**
	 * appendChild
	 * 
	 * @param child
	 * @return agrega un nodo hijo al arrelo del menu de enlaces de interes
	 */
	public void appendChild(MenuItem child) {
		if (children == null)
			children = new ArrayList<MenuItem>();
		children.add(child);
	}

	// Métodos GET y SET
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AImage getImg() {
		return img;
	}

	public void setImg(AImage img) {
		this.img = img;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	//Fin GET y SET
}
