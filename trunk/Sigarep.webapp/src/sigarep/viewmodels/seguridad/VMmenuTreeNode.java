package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;


public class VMmenuTreeNode extends DefaultTreeNode<Nodo> {
	private static final long serialVersionUID = -7012663776755277499L;
	
	private boolean open = false;

	public VMmenuTreeNode(Nodo data, DefaultTreeNode<Nodo>[] children) {
		super(data, children);
	}

	public VMmenuTreeNode(Nodo data, DefaultTreeNode<Nodo>[] children, boolean open) {
		super(data, children);
		setOpen(open);
	}

	public VMmenuTreeNode(Nodo data) {
		super(data);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
