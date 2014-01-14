package sigarep.viewmodels.seguridad;

import java.util.HashMap;




import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConfigurarOpcionesUsuario {
	@Wire
	private Window WindowConfigurarOpcionesUsuario;
	@Wire
	private Tree tree;

	private VMAdvancedTreeModel contactTreeModel;
	
	private static VMmenuTreeNode  root;
	
	private @WireVariable ServicioNodo snodo;
	private @WireVariable ServicioGrupo sg;
	private @WireVariable ServicioUsuario su;
	private String nombreUsuario;
	private String nombre;
	private String apellido;
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	private String telefono;
	
	public Tree getTree() {
		return tree;
	}


	public void setTree(Tree tree) {
		this.tree = tree;
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
	
	@NotifyChange({ "contactTreeModel" })
	@Command
	public void buscarUsuario(){
		root = new VMmenuTreeNode(null,null);
		Usuario usuario=su.encontrarUsuario(nombreUsuario);
		for(Grupo g:usuario.getGrupos()){
			VMmenuTreeNode aux=null;
			for(Nodo a:g.getNodos()){
				aux=new VMmenuTreeNode(a,null);
				VMmenuTreeNode ctreenodo= this.cargarPadre(aux);
				if(root.getChildCount()!=0){
					this.agregarNodo(ctreenodo,root);
				}else{
					root.add(ctreenodo);
				}
			    }}
		
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
