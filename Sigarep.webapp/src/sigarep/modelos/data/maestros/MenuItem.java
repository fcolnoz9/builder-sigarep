package sigarep.modelos.data.maestros;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private String name;
	private String img;
	private String href;
	private List<MenuItem> children;
	private int level;

	public MenuItem(String name,String img,String href,int level) {
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
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
