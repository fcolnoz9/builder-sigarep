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

public class VMmenuTreeRenderer2 implements TreeitemRenderer<VMmenuTreeNode> {
	@Override
	public void render(final Treeitem treeItem, VMmenuTreeNode treeNode, int index) throws Exception {
		VMmenuTreeNode ctn = treeNode;
		Nodo contact = (Nodo) ctn.getData();
		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		treeItem.setValue(ctn);
		treeItem.setOpen(ctn.isOpen());
			Hlayout hl = new Hlayout();
			//hl.appendChild(new Image("/img/" + contact.getProfilepic()));
			hl.appendChild(new Label(contact.getNombreFuncion()));
			hl.setSclass("h-inline-block");
			Treecell treeCell = new Treecell();
			treeCell.appendChild(hl);
			dataRow.appendChild(treeCell);
			dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					VMmenuTreeNode clickedNodeValue = (VMmenuTreeNode) ((Treeitem) event.getTarget().getParent())
							.getValue();
					
					VMmenuTreeNode padre=null;
					if(clickedNodeValue.getParent().getData()!=null){
							padre=obtenePadres((VMmenuTreeNode)clickedNodeValue.getParent(),clickedNodeValue);
							}else{
								padre=clickedNodeValue;
							}
					VMRegistrarGrupo dc=new VMRegistrarGrupo();
					if(dc.getRoot().getChildCount()==0)
						dc.getRoot().add(padre);
					else{
					this.agregarNodo(padre,dc.getRoot());
					}
				}

				

				private VMmenuTreeNode obtenePadres(VMmenuTreeNode padre,VMmenuTreeNode hijo) {
					VMmenuTreeNode aux=null;
					VMmenuTreeNode padre2=null;
					if(padre.getParent().getData()!=null){
						
						if(padre.getChildCount()==1){
						aux=padre;	
						}else{
							aux = new VMmenuTreeNode(padre.getData(),null);		
							aux.add(hijo);
						}
						padre2=obtenePadres((VMmenuTreeNode)padre.getParent(),aux);
									
					}else{
						    padre2 = new VMmenuTreeNode(padre.getData(),null);
						    padre2.add(hijo);
						    if(padre.getChildCount()==0)
						    padre.removeFromParent();
					}
					return padre2;
				}
				
				private void agregarNodo(VMmenuTreeNode nodo,VMmenuTreeNode root) { 
					boolean encontro=false;
					 for(int j=0;j< root.getChildCount();j++){
						    if(root.getChildAt(j).getData()==nodo.getData()){	
						    	agregarNodo((VMmenuTreeNode) nodo.getChildAt(0),(VMmenuTreeNode) root.getChildAt(j));
						        encontro=true;
						    	break;
						    }
					 }
					 if(!encontro)
						 root.add(nodo);
						 
				
				}
				
			});
			

			
	}

}
