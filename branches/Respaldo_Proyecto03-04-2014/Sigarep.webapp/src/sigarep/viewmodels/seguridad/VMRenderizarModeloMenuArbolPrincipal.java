package sigarep.viewmodels.seguridad;

import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import sigarep.modelos.data.seguridad.Nodo;

public class VMRenderizarModeloMenuArbolPrincipal implements TreeitemRenderer<VMNodoMenuArbol> {
	@Override
	public void render(final Treeitem articuloArbol, VMNodoMenuArbol nodoArbol,int indice) throws Exception {
		VMNodoMenuArbol ctn = nodoArbol;
		Nodo nodo = (Nodo) ctn.getData();
		Treerow filaArbol = new Treerow();
		filaArbol.setParent(articuloArbol);
		articuloArbol.setValue(ctn);
		articuloArbol.setOpen(ctn.isOpen());
		Hlayout hl = new Hlayout();

		hl.appendChild(new Label(nodo.getNombreFuncion()));
		hl.setSclass("h-inline-block");
		Treecell celdaArbol = new Treecell();
		celdaArbol.appendChild(hl);
		filaArbol.appendChild(celdaArbol);
		if (nodo.esFuncion()){
			celdaArbol.setImage("/imagenes/iconos/funcion-tree.png");
		}
		if(nodo.esFuncion())
			filaArbol.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				VMNodoMenuArbol valorNodoClickeado = (VMNodoMenuArbol) ((Treeitem) event.getTarget().getParent()).getValue();
				VMNodoMenuArbol padre = null;
				if (valorNodoClickeado.getParent().getData() != null) {
					padre = obtenePadres((VMNodoMenuArbol) valorNodoClickeado.getParent(),valorNodoClickeado);
				} else {
					padre = valorNodoClickeado;
				}
				
				VMRegistrarGrupo dc = new VMRegistrarGrupo();
				if (dc.getRaiz2().getChildCount() == 0)
					dc.getRaiz2().add(padre);
				else {
					this.agregarNodo(padre, dc.getRaiz2());
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
					VMNodoMenuArbol raiz) {
				boolean encontro = false;
				for (int j = 0; j < raiz.getChildCount(); j++) {
					if (raiz.getChildAt(j).getData() == nodo.getData()) {
						agregarNodo((VMNodoMenuArbol) nodo.getChildAt(0),
								(VMNodoMenuArbol) raiz.getChildAt(j));
						encontro = true;
						break;
					}
				}
				if (!encontro)
					raiz.add(nodo);
			}
		});

	}

}
