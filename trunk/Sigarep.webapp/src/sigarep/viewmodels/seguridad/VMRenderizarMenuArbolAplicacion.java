package sigarep.viewmodels.seguridad;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import sigarep.herramientas.UtilidadArbol;
import sigarep.modelos.data.seguridad.Nodo;

public class VMRenderizarMenuArbolAplicacion implements TreeitemRenderer<VMNodoMenuArbol> {
	
	Window w=null;
	UtilidadArbol utilidadArbol = new UtilidadArbol();
	@Override
	public void render(final Treeitem treeItem, VMNodoMenuArbol treeNode, int index) throws Exception {
		VMNodoMenuArbol ctn = treeNode;
		Nodo contact = (Nodo) ctn.getData();

		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		treeItem.setValue(ctn);
			Hlayout hl = new Hlayout();		
			Treecell treeCell = new Treecell();
			treeCell.setLabel(contact.getNombreFuncion()); //1
			treeCell.appendChild(hl);
			if (contact.esFuncion()){
				treeCell.setImage("/imagenes/iconos/funcion-tree.png");
			}
			dataRow.appendChild(treeCell);
			dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					VMNodoMenuArbol clickedNodeValue = (VMNodoMenuArbol) ((Treeitem) event.getTarget().getParent()).getValue();
					if (clickedNodeValue.getData().getVinculo() != null) {
						if (w != null) {
							w.detach();
						}
					utilidadArbol.onClickMenu(clickedNodeValue.getData().getVinculo(),clickedNodeValue.getData().getRutaModal());
					}
				}
			});
	}
}