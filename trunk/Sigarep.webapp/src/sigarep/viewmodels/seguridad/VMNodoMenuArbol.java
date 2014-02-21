package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;


public class VMNodoMenuArbol extends DefaultTreeNode<Nodo> {
	private static final long serialVersionUID = -7012663776755277499L;
	
	private boolean open = false;

	public VMNodoMenuArbol(Nodo data, DefaultTreeNode<Nodo>[] children) {
		super(data, children);
	}

	public VMNodoMenuArbol(Nodo data, DefaultTreeNode<Nodo>[] children, boolean open) {
		super(data, children);
		setOpen(open);
	}

	public VMNodoMenuArbol(Nodo data) {
		super(data);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
