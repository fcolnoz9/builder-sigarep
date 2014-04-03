package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;


public class VMNodoMenuArbol extends DefaultTreeNode<Nodo> {
	private static final long serialVersionUID = -7012663776755277499L;
	
	private boolean open = false;

	public VMNodoMenuArbol(Nodo datos, DefaultTreeNode<Nodo>[] ninos) {
		super(datos, ninos);
	}

	public VMNodoMenuArbol(Nodo datos, DefaultTreeNode<Nodo>[] ninos, boolean abierto) {
		super(datos, ninos);
		setOpen(abierto);
	}

	public VMNodoMenuArbol(Nodo datos) {
		super(datos);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
