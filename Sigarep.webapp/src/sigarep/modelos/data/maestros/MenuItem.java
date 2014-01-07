package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.image.AImage;

import sigarep.herramientas.Archivo;

public class MenuItem {
	private String name;
	private Archivo imagen = new Archivo();
	private AImage img;
	private String href;
	private List<MenuItem> children;
	private int level;

	public MenuItem(String name,AImage img,String href,int level) {
		this.name = name;
		this.img= img;
		this.level = level;
		this.href= href;
		children = new ArrayList<MenuItem>();
	}
	
	public void addChild(MenuItem node) {
		children.add(node);
	}

	public void appendChild(MenuItem child) {
		if (children == null)
			children = new ArrayList<MenuItem>();
		children.add(child);
	}
	
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

}
