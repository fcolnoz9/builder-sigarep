package sigarep.viewmodels.seguridad;

import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import sigarep.modelos.data.seguridad.Nodo;

public class VMRenderizarModeloMenuArbolPrincipal implements TreeitemRenderer<VMNodoMenuArbol> {
	@Override
	public void render(final Treeitem treeItem, VMNodoMenuArbol treeNode,int index) throws Exception {
		VMNodoMenuArbol ctn = treeNode;
		Nodo contact = (Nodo) ctn.getData();
		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		treeItem.setValue(ctn);
		treeItem.setOpen(ctn.isOpen());
		Hlayout hl = new Hlayout();

		hl.appendChild(new Label(contact.getNombreFuncion()));
		hl.setSclass("h-inline-block");
		Treecell treeCell = new Treecell();
		treeCell.appendChild(hl);
		dataRow.appendChild(treeCell);
		if(contact.esFuncion()==true)
		dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				VMNodoMenuArbol clickedNodeValue = (VMNodoMenuArbol) ((Treeitem) event.getTarget().getParent()).getValue();
				VMNodoMenuArbol padre = null;
				if (clickedNodeValue.getParent().getData() != null) {
					padre = obtenePadres((VMNodoMenuArbol) clickedNodeValue.getParent(),clickedNodeValue);
				} else {
					padre = clickedNodeValue;
				}
				
				VMRegistrarGrupo dc = new VMRegistrarGrupo();
				if (dc.getRoot2().getChildCount() == 0)
					dc.getRoot2().add(padre);
				else {
					this.agregarNodo(padre, dc.getRoot2());
				}
			}

			private VMNodoMenuArbol obtenePadres(VMNodoMenuArbol padre,
					VMNodoMenuArbol hijo) {
				VMNodoMenuArbol aux = null;
				VMNodoMenuArbol padre2 = null;
				if (padre.getParent().getData() != null) {

					if (padre.getChildCount() == 1) {
						aux = padre;
					} else {
						aux = new VMNodoMenuArbol(padre.getData(), null);
						aux.add(hijo);
					}
					aux.setOpen(true);
					padre2 = obtenePadres(
							(VMNodoMenuArbol) padre.getParent(), aux);
				} else {
					padre2 = new VMNodoMenuArbol(padre.getData(), null);
					padre2.setOpen(true);
					padre2.add(hijo);
					if (padre.getChildCount() == 0)
						padre.removeFromParent();
				}
				
				return padre2;
			}

			private void agregarNodo(VMNodoMenuArbol nodo,
					VMNodoMenuArbol root) {
				boolean encontro = false;
				for (int j = 0; j < root.getChildCount(); j++) {
					if (root.getChildAt(j).getData() == nodo.getData()) {
						agregarNodo((VMNodoMenuArbol) nodo.getChildAt(0),
								(VMNodoMenuArbol) root.getChildAt(j));
						encontro = true;
						break;
					}
				}
				if (!encontro)
					root.add(nodo);
			}
		});

	}

}
