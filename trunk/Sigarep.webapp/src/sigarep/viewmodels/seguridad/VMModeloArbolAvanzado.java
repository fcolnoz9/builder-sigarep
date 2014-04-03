package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeModel;


import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;


public class VMModeloArbolAvanzado extends DefaultTreeModel<Nodo> {
	private static final long serialVersionUID = -5513180500300189445L;
	
	DefaultTreeNode<Nodo> _root;

	public VMModeloArbolAvanzado(VMNodoMenuArbol nodoMenuArbol) {
		super(nodoMenuArbol);
		_root = nodoMenuArbol;
	}

	/**
	 * eliminar los nodos cuyo padre es <code>padre</code> con indices
	 * <code>indices</code>
	 * 
	 * @param padre
	 *            El padre de los nodos que serán removidos
	 * @param indiceDesde
	 *            El índice más bajo del rango de cambio
	 * @param indiceA
	 *            El índice superior del rango de cambio
	 * @throws ocurre una excepción IndexOutOfBoundsException cuando 
	 * - indiceDesde < 0 o el indiceA > Número de hijos de los padres
	 */
	public void remover(DefaultTreeNode<Nodo> padre, int indiceDesde, int indiceA) throws IndexOutOfBoundsException {
		DefaultTreeNode<Nodo> nodoPadre = padre;
		for (int i = indiceA; i >= indiceDesde; i--)
			try {
				nodoPadre.getChildren().remove(i);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
	}

	public void remover(DefaultTreeNode<Nodo> objetivo) throws IndexOutOfBoundsException {
		int indice = 0;
		DefaultTreeNode<Nodo> padre = null;
		// encontrar el padre y el índice del objetivo
		padre = dfbuscarPadre(_root, objetivo);
		for (indice = 0; indice < padre.getChildCount(); indice++) {
			if (padre.getChildAt(indice).equals(objetivo)) {
				break;
			}
		}
		remover(padre, indice, indice);
	}

	/**
	 * insertar nuevos nodos cuyo padre es <code>padre</code> con indices
	 * <code>indices</code> con nuevos nodos <code>nuevosNodos</code>
	 * 
	 * @param padre
	 *            El padre de los nodos que seran insertados.
	 * @param indiceDesde
	 *            El índice más bajo del rango de cambio
	 * @param indiceA
	 *            El índice superior del rango de cambio
	 * @param nuevosNodos
	 *            Los nodos nuevos que se insertan
	 * @throws ocurre una excepción IndexOutOfBoundsException cuando 
	 * - indiceDesde < 0 o el indiceA > Número de hijos de los padres
	 */
	public void insertar(DefaultTreeNode<Nodo> padre, int indiceDesde, int indiceA, DefaultTreeNode<Nodo>[] nuevosNodos)
			throws IndexOutOfBoundsException {
		DefaultTreeNode<Nodo> nodoPadre = padre;
		for (int i = indiceDesde; i <= indiceA; i++) {
			try {
				nodoPadre.getChildren().add(i, nuevosNodos[i - indiceDesde]);
			} catch (Exception exp) {
				throw new IndexOutOfBoundsException("Fuera de límite: " + i + " mientras tamaño=" + nodoPadre.getChildren().size());
			}
		}
	}

	/**
	 * agregar nuevos nodos cuyo padre es <code>padre</code> con nuevos nodos
	 * <code>nuevosNodos</code>
	 * 
	 * @param padre
	 *            El padre de los nodos que se adjuntan
	 * @param nuevosNodos
	 *            Nuevos nodos que se adjuntan
	 */
	public void add(DefaultTreeNode<Nodo> padre, DefaultTreeNode<Nodo>[] nuevosNodos) {
		DefaultTreeNode<Nodo> nodoPadre = (DefaultTreeNode<Nodo>) padre;

		for (int i = 0; i < nuevosNodos.length; i++)
			nodoPadre.getChildren().add(nuevosNodos[i]);
	}

	private DefaultTreeNode<Nodo> dfbuscarPadre(DefaultTreeNode<Nodo> nodo, DefaultTreeNode<Nodo> objetivo) {
		if (nodo.getChildren() != null && nodo.getChildren().contains(objetivo)) {
			return nodo;
		} else {
			int tamanho = getChildCount(nodo);
			for (int i = 0; i < tamanho; i++) {
				DefaultTreeNode<Nodo> padre = dfbuscarPadre((DefaultTreeNode<Nodo>) getChild(nodo, i), objetivo);
				if (padre != null) {
					return padre;
				}
			}
		}
		return null;
	}

}
