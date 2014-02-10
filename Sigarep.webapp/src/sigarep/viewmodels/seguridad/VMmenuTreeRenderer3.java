package sigarep.viewmodels.seguridad;

import java.util.HashMap;



import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import sigarep.controlador.maestros.WindowController;
import sigarep.modelos.data.seguridad.Nodo;

public class VMmenuTreeRenderer3 implements TreeitemRenderer<VMmenuTreeNode> {
	
	Window w=null;
	WindowController winController = new WindowController();
	@Override
	public void render(final Treeitem treeItem, VMmenuTreeNode treeNode, int index) throws Exception {
		VMmenuTreeNode ctn = treeNode;
		Nodo contact = (Nodo) ctn.getData();

		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		treeItem.setValue(ctn);
			Hlayout hl = new Hlayout();		
			Treecell treeCell = new Treecell();
			treeCell.setLabel(contact.getNombreFuncion()); //1
			treeCell.appendChild(hl);
			dataRow.appendChild(treeCell);
			dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					VMmenuTreeNode clickedNodeValue = (VMmenuTreeNode) ((Treeitem) event.getTarget().getParent()).getValue();
					if (clickedNodeValue.getData().getVinculo() != null) {
						if (w != null) {
							w.detach();
						}
					winController.onClickMenu(clickedNodeValue.getData().getVinculo(),clickedNodeValue.getData().getRutaModal());
					}
				}
			});
	}
}