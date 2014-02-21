package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeModel;


import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;


public class VMModeloArbolAvanzado extends DefaultTreeModel<Nodo> {
	private static final long serialVersionUID = -5513180500300189445L;
	
	DefaultTreeNode<Nodo> _root;

	public VMModeloArbolAvanzado(VMNodoMenuArbol contactTreeNode) {
		super(contactTreeNode);
		_root = contactTreeNode;
	}

	/**
	 * remove the nodes which parent is <code>parent</code> with indexes
	 * <code>indexes</code>
	 * 
	 * @param parent
	 *            The parent of nodes are removed
	 * @param indexFrom
	 *            the lower index of the change range
	 * @param indexTo
	 *            the upper index of the change range
	 * @throws IndexOutOfBoundsException
	 *             - indexFrom < 0 or indexTo > number of parent's children
	 */
	public void remove(DefaultTreeNode<Nodo> parent, int indexFrom, int indexTo) throws IndexOutOfBoundsException {
		DefaultTreeNode<Nodo> stn = parent;
		for (int i = indexTo; i >= indexFrom; i--)
			try {
				stn.getChildren().remove(i);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
	}

	public void remove(DefaultTreeNode<Nodo> target) throws IndexOutOfBoundsException {
		int index = 0;
		DefaultTreeNode<Nodo> parent = null;
		// find the parent and index of target
		parent = dfSearchParent(_root, target);
		for (index = 0; index < parent.getChildCount(); index++) {
			if (parent.getChildAt(index).equals(target)) {
				break;
			}
		}
		remove(parent, index, index);
	}

	/**
	 * insert new nodes which parent is <code>parent</code> with indexes
	 * <code>indexes</code> by new nodes <code>newNodes</code>
	 * 
	 * @param parent
	 *            The parent of nodes are inserted
	 * @param indexFrom
	 *            the lower index of the change range
	 * @param indexTo
	 *            the upper index of the change range
	 * @param newNodes
	 *            New nodes which are inserted
	 * @throws IndexOutOfBoundsException
	 *             - indexFrom < 0 or indexTo > number of parent's children
	 */
	public void insert(DefaultTreeNode<Nodo> parent, int indexFrom, int indexTo, DefaultTreeNode<Nodo>[] newNodes)
			throws IndexOutOfBoundsException {
		DefaultTreeNode<Nodo> stn = parent;
		for (int i = indexFrom; i <= indexTo; i++) {
			try {
				stn.getChildren().add(i, newNodes[i - indexFrom]);
			} catch (Exception exp) {
				throw new IndexOutOfBoundsException("Out of bound: " + i + " while size=" + stn.getChildren().size());
			}
		}
	}

	/**
	 * append new nodes which parent is <code>parent</code> by new nodes
	 * <code>newNodes</code>
	 * 
	 * @param parent
	 *            The parent of nodes are appended
	 * @param newNodes
	 *            New nodes which are appended
	 */
	public void add(DefaultTreeNode<Nodo> parent, DefaultTreeNode<Nodo>[] newNodes) {
		DefaultTreeNode<Nodo> stn = (DefaultTreeNode<Nodo>) parent;

		for (int i = 0; i < newNodes.length; i++)
			stn.getChildren().add(newNodes[i]);

	}

	private DefaultTreeNode<Nodo> dfSearchParent(DefaultTreeNode<Nodo> node, DefaultTreeNode<Nodo> target) {
		if (node.getChildren() != null && node.getChildren().contains(target)) {
			return node;
		} else {
			int size = getChildCount(node);
			for (int i = 0; i < size; i++) {
				DefaultTreeNode<Nodo> parent = dfSearchParent((DefaultTreeNode<Nodo>) getChild(node, i), target);
				if (parent != null) {
					return parent;
				}
			}
		}
		return null;
	}

}
