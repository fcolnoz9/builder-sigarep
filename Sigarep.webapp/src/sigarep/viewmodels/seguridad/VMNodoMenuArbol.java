package sigarep.viewmodels.seguridad;

import org.zkoss.zul.DefaultTreeNode;
import sigarep.modelos.data.seguridad.Nodo;

/**
* Clase VMNodoMenuArbol : Clase ViewModels relacionada con las propiedades por defecto del nodo del menú arbol.
*
* @author Equipo Builder
* @version 1.0
* @since 19/12/2014
* @last 10/05/2014
*/

public class VMNodoMenuArbol extends DefaultTreeNode<Nodo> {
	private static final long serialVersionUID = -7012663776755277499L;
	//---------Variables de control------------------------
	private boolean open = false;

	/**
	* Constructor de la clase VMNodoMenuArbol para un nodo del arbol con hijos
	*
	* @param datos, ninos
	*/
	public VMNodoMenuArbol(Nodo datos, DefaultTreeNode<Nodo>[] ninos) {
		super(datos, ninos);
	}

	/**
	* Constructor de la clase VMNodoMenuArbol para un nodo del arbol con hijos y abierto.
	*
	* @param datos, ninos, abierto
	*/
	
	public VMNodoMenuArbol(Nodo datos, DefaultTreeNode<Nodo>[] ninos, boolean abierto) {
		super(datos, ninos);
		setOpen(abierto);
	}
	
	/**
	* Constructor de la clase VMNodoMenuArbol para un nodo del arbol sin hijos
	*
	* @param datos
	*/

	public VMNodoMenuArbol(Nodo datos) {
		super(datos);
	}

	/**
	* Permite conocer si el item del arbol está abierto o cerrado.
	*
	* @param ninguno.
	* @return true si el item del arbol está abierto y false en caso contrario.
	* @throws No dispara ninguna excepción.
	*/
	
	public boolean isOpen() {
		return open;
	}

	/**
	* Permite abrir o cerrar el item del arbol.
	*
	* @param open.
	* @return si open es tree abre el item del arbol y si es false lo cierra.
	* @throws No dispara ninguna excepción.
	*/
	
	public void setOpen(boolean open) {
		this.open = open;
	}
} //fin VMNodoMenuArbol