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

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarGrupo {
	@Wire
	private Window demoWindowRegistrarGrupo;
	@Wire
	private Tree tree;
	@Wire
	private Tree tree2;
	private mensajes msjs = new mensajes();
	private @WireVariable ServicioNodo snodo;
	
	public ServicioNodo getsnodo() {
		return snodo;
	}


	public void setsnodo(ServicioNodo snodo) {
		this.snodo = snodo;
	}

	private VMAdvancedTreeModel contactTreeModel;
	private VMAdvancedTreeModel contactTreeModel2;
	
	private static VMmenuTreeNode  root;
	private static VMmenuTreeNode  root2;
	

	public Tree getTree() {
		return tree;
	}


	public void setTree(Tree tree) {
		this.tree = tree;
	}


	public Tree getTree2() {
		return tree2;
	}


	public void setTree2(Tree tree2) {
		this.tree2 = tree2;
	}


	public VMAdvancedTreeModel getContactTreeModel() {
		return contactTreeModel;
	}


	public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
		this.contactTreeModel = contactTreeModel;
	}


	public VMAdvancedTreeModel getContactTreeModel2() {
		return contactTreeModel2;
	}


	public void setContactTreeModel2(VMAdvancedTreeModel contactTreeModel2) {
		this.contactTreeModel2 = contactTreeModel2;
	}


	public VMmenuTreeNode getRoot2() {
		return root2;
	}


	public void setRoot2(VMmenuTreeNode root2) {
		this.root2 = root2;
	}


	public void setRoot(VMmenuTreeNode root) {
		this.root = root;
	}


	public VMmenuTreeNode getRoot() {
		return root;
	}
	
	@WireVariable
	private ServicioGrupo sg;

	
	private Integer idGrupo;
	private String nombre;
    private String descripcion;
    private String estado;
	private ListModelList<Nodo> modelonodos;


	public Integer getIdGrupo() {
		return idGrupo;
	}


	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public ListModelList<Nodo> getModeloGrupo() {
		return modelonodos;
	}


	public void setModeloGrupo(ListModelList<Nodo> modeloNodo) {
		this.modelonodos = modeloNodo;
	}
	
	@Command
	@NotifyChange({ "idGrupo", "descripcion", "nombre"})
	public void guardarGrupo(){
		if(nombre==null || descripcion==null)
		 msjs.advertenciaLlenarCampos();
		else if(root2.getChildren().size() > 0){
			Grupo grupo1=new Grupo();
			grupo1.setIdGrupo(idGrupo);
			grupo1.setDescripcion(descripcion);
			grupo1.setNombre(nombre);
			grupo1.setEstatus(true);
			if(root2.getChildCount()>0){
				for(TreeNode<Nodo> a2:root2.getChildren()){
					cargarHijosGrupo(a2,grupo1);
				}	
			}
			sg.guardarGrupo(grupo1);
			msjs.informacionRegistroCorrecto();
		}
		else msjs.advertenciaMenudelGrupoVacio();
	}

	@AfterCompose
	public void Init(Component comp) throws Exception {
		cargarArbol();	
		contactTreeModel = new VMAdvancedTreeModel(root);
		root2 = new VMmenuTreeNode(null,null);
		contactTreeModel2 = new VMAdvancedTreeModel(root2);
	}
	
	public void cargarArbol(){
		VMmenuTreeNode aux=null;
		root = new VMmenuTreeNode(null,null);
		for(Nodo a:snodo.buscarPadre(0)){
			aux=new VMmenuTreeNode(a,null);
		    this.cargarHijos(aux,a);
			root.add(aux);
		}
	}


	public void cargarHijos(VMmenuTreeNode m1, Nodo a1) {
		VMmenuTreeNode m2 = null;
			for(Nodo a2:snodo.buscarPadre(a1.getId())){
		    m2= new VMmenuTreeNode(a2,null);
		    m1.add(m2);
		    if(!snodo.buscarPadre(a2.getId()).isEmpty())
		    	cargarHijos(m2,a2);
			}
	}
	
	public void cargarHijosGrupo(TreeNode<Nodo> root, Grupo grupo) {
		if (root.getChildCount() > 0) {
			for (TreeNode<Nodo> a2 : root.getChildren()) {
				if (a2.getData().getTipo().equals("M")) {
					cargarHijosGrupo(a2, grupo);
				} else {
					grupo.addNodos(a2.getData());
				}
			}

		} else {
			grupo.equals(root.getData());
		}
	}
	
}
