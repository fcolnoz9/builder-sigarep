package sigarep.viewmodels.seguridad;


import java.util.HashMap;


import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Box;
import org.zkoss.zul.Center;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMmenuAplicacion{
	
	private @Wire Window windowindex; 
	public Window getWindowindex() {
		return windowindex;
	}


	public void setWindowindex(Window windowindex) {
		this.windowindex = windowindex;
	}
	private VMAdvancedTreeModel contactTreeModel;
	private static VMmenuTreeNode  root;
	private @WireVariable ServicioNodo snodo;
	private @WireVariable ServicioGrupo sg;
    private static String ruta="timeout.zul";

	public String getRuta() {
		return ruta;
	}


	public static void setRuta(String ruta) {
		VMmenuAplicacion.ruta = ruta;
	}


	public VMAdvancedTreeModel getContactTreeModel() {
		return contactTreeModel;
	}


	public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
		this.contactTreeModel = contactTreeModel;
	}


	public void setRoot(VMmenuTreeNode root) {
		this.root = root;
	}


	public VMmenuTreeNode getRoot() {
		return root;
	}
	
	@AfterCompose
	public void Init(@ContextParam(ContextType.COMPONENT) Component windowindex,@ContextParam(ContextType.VIEW) Component view) {
		this.windowindex = (Window) windowindex;
		root = new VMmenuTreeNode(null, null);
		for (String rol : SecurityUtil.roles()) {
			Grupo g = sg.buscarGrupoNombre(rol);
			VMmenuTreeNode aux = null;
			for (Nodo a : g.getNodos()) {
				aux = new VMmenuTreeNode(a, null);
				VMmenuTreeNode ctreenodo = this.cargarPadre(aux);
				if (root.getChildCount() != 0) {
					this.agregarNodo(ctreenodo, root);
				} else {
					root.add(ctreenodo);
				}
			}
		}

		contactTreeModel = new VMAdvancedTreeModel(root);

	}


	public VMmenuTreeNode cargarPadre(VMmenuTreeNode nodo) {
		VMmenuTreeNode padre=null;
			if(nodo.getData().getPadre()!=0){
				Nodo npadre=snodo.buscarNodo(nodo.getData().getPadre());
				padre=new VMmenuTreeNode(npadre,null);
				padre.add(nodo);
				nodo=cargarPadre(padre);		
			}
			return nodo;
	}
	private void agregarNodo(VMmenuTreeNode nodo,VMmenuTreeNode root) { 
		boolean encontro=false;
		 for(int j=0;j< root.getChildCount();j++){
			  if(root.getChildAt(j).getData().getId()==nodo.getData().getId()){
			    	agregarNodo((VMmenuTreeNode) nodo.getChildAt(0),(VMmenuTreeNode) root.getChildAt(j));
			        encontro=true;
			    	break;
			    }
		 }
		 if(!encontro)
			 root.add(nodo);			 
	}
}
