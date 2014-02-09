package sigarep.viewmodels.seguridad;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;

/*
 * @ (#) Grupo.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Esta clase es el ViewModel del registro del maestro "Grupo"
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo: Builder-SIGAREP 
 * @Version 1.0,
 * @Since  04/02/13
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarGrupo {
	@Wire
	private Window demoWindowRegistrarGrupo;
	@Wire
	private Tree tree;
	@Wire
	private Tree tree2;
	
	private @WireVariable ServicioNodo snodo;

	private VMAdvancedTreeModel contactTreeModel;
	private VMAdvancedTreeModel contactTreeModel2;
	
	private static VMmenuTreeNode  root;
	private static VMmenuTreeNode  root2;
	
	@WireVariable
	private ServicioGrupo sg;

	
	private Integer idGrupo; // clave primaria de la tabla Grupo
	private String nombre; 	// nombre del Grupo
    private String descripcion; // descripción del Grupo
    private String estado; 
	private ListModelList<Nodo> modelonodos; // lista de los nodos u opciones del menú árbol.
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

	// Metodos GETS Y SETS
	
	public ServicioNodo getsnodo() {
		return snodo;
	}

	public void setsnodo(ServicioNodo snodo) {
		this.snodo = snodo;
	}
	
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
	
	// Fin de los metodos gets y sets
	// OTROS METODOS
	
	// Metodos que permite guardar el Grupo de usuario
	@Command
	@NotifyChange({ "idGrupo", "descripcion", "nombre"})
	public void guardarGrupo(){
		if(nombre==null || descripcion==null)
			mensajeAlUsuario.advertenciaLlenarCampos();
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
			mensajeAlUsuario.informacionRegistroCorrecto();
		}
		else mensajeAlUsuario.advertenciaMenudelGrupoVacio();
	}

	@AfterCompose
	public void Init(Component comp) throws Exception {
		cargarArbol();	
		contactTreeModel = new VMAdvancedTreeModel(root);
		root2 = new VMmenuTreeNode(null,null);
		contactTreeModel2 = new VMAdvancedTreeModel(root2);
	}
	
	// Metodo que carga los nodos del menú arbol
	public void cargarArbol(){
		VMmenuTreeNode aux=null;
		root = new VMmenuTreeNode(null,null);
		for(Nodo a:snodo.buscarPadre(0)){
			aux=new VMmenuTreeNode(a,null);
		    this.cargarHijos(aux,a);
			root.add(aux);
		}
	}

	// Metodo que carga los nodos hijos a los nodos padres
	public void cargarHijos(VMmenuTreeNode m1, Nodo a1) {
		VMmenuTreeNode m2 = null;
			for(Nodo a2:snodo.buscarPadre(a1.getId())){
		    m2= new VMmenuTreeNode(a2,null);
		    m1.add(m2);
		    if(!snodo.buscarPadre(a2.getId()).isEmpty())
		    	cargarHijos(m2,a2);
			}
	}
	
	// Metodo que carga los hijos del grupo de usuario
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
