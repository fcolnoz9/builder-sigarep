package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;

/**
* Clase VMModeloArbolAvanzado : Clase ViewModels relacionada con las propiedades por defecto del menú arbol.
*
* @author Equipo Builder
* @version 1.0
* @since 19/12/2014
* @last 10/05/2014
*/

public class VMModeloArbolAvanzado extends DefaultTreeModel<Nodo> {
	private static final long serialVersionUID = -5513180500300189445L;
	//-----------------Variables Objeto--------------------
	DefaultTreeNode<Nodo> _root;

	/**
	* Constructor de la clase VMModeloArbolAvanzado
	*
	* @param nodoMenuArbol
	*/
	
	public VMModeloArbolAvanzado(VMNodoMenuArbol nodoMenuArbol) {
		super(nodoMenuArbol);
		_root = nodoMenuArbol;
	}

	/**
	 * Eliminar los nodos cuyo padre es <code>padre</code> con indices
	 * <code>indices</code>
	 * 
	 * @param padre
	 *            El padre de los nodos que serán removidos
	 * @param indiceDesde
	 *            El índice más bajo del rango de cambio
	 * @param indiceA
	 *            El índice superior del rango de cambio
	 * @return ninguno. 
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

	/**
	 * Eliminar los nodos hijos cuyo padre tiene como hijo a objetivo
	 * <code>indices</code>
	 * 
	 * @param objetivo
	 * @return ninguno. 
	 * @throws No dispara ninguna excepcion. 
	 */
	
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
	 * Insertar nuevos nodos cuyo padre es <code>padre</code> con indices
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
	 * @return ninguno.
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
	* @return ninguno.
	* @throws No dispara ninguna excepcion.
	 */
	public void agregar(DefaultTreeNode<Nodo> padre, DefaultTreeNode<Nodo>[] nuevosNodos) {
		DefaultTreeNode<Nodo> nodoPadre = (DefaultTreeNode<Nodo>) padre;

		for (int i = 0; i < nuevosNodos.length; i++)
			nodoPadre.getChildren().add(nuevosNodos[i]);
	}

	/**
	 * Buscar el DefaultTreeNode<Nodo> padre dado un nodo objetivo y 
	 * el número de hijos de un nodo determinado.
	 * 
	 * @param nodo, objetivo
	 * @return Objeto DefaultTreeNode<Nodo>. 
	 * @throws No dispara ninguna excepcion. 
	 */	
	
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
} //fin VMModeloArbolAvanzado.
