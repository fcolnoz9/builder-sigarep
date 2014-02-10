package sigarep.viewmodels.seguridad;

import java.util.ArrayList;
import java.util.Collections;

import org.zkoss.bind.annotation.AfterCompose;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.data.seguridad.Usuario;

import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMmenuAplicacion{
	
	private VMmenuTreeRenderer3 rendererPortalAplicacion=new VMmenuTreeRenderer3();
	public VMmenuTreeRenderer3 getRendererPortalAplicacion() {
		return rendererPortalAplicacion;
	}


	public void setRendererPortalAplicacion(
			VMmenuTreeRenderer3 rendererPortalAplicacion) {
		this.rendererPortalAplicacion = rendererPortalAplicacion;
	}
	private VMAdvancedTreeModel contactTreeModel;
	private static VMmenuTreeNode  rootPortalInicial;
	private @WireVariable ServicioNodo servicionodo;
	private @WireVariable ServicioGrupo serviciogrupo;
	private @WireVariable ServicioUsuario serviciousuario;
	private AImage imagen;
	private String nombreUsuario;
    public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public AImage getImagen() {
		return imagen;
	}


	public void setImagen(AImage imagen) {
		this.imagen = imagen;
	}
	private static String ruta="timeout.zul";
    public VMAdvancedTreeModel getContactTreeModel() {
		return contactTreeModel;
	}


	public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
		this.contactTreeModel = contactTreeModel;
	}
	public static VMmenuTreeNode getRootPortalInicial() {
		return rootPortalInicial;
	}


	public static void setRootPortalInicial(VMmenuTreeNode rootPortalInicial) {
		VMmenuAplicacion.rootPortalInicial = rootPortalInicial;
	}
	
	@AfterCompose
	public void Init(@ContextParam(ContextType.COMPONENT) Component windowindex,@ContextParam(ContextType.VIEW) Component view) {
		rootPortalInicial = new VMmenuTreeNode(null,null);		
		for(String rol:SecurityUtil.roles()){
			Grupo g=serviciogrupo.buscarGrupoNombre(rol);
			VMmenuTreeNode aux=null;
			ArrayList<Nodo> nodosOrdenados = new ArrayList<Nodo>(g.getNodos());
			Collections.sort(nodosOrdenados, new Nodo());
			for(Nodo a:nodosOrdenados){
				aux=new VMmenuTreeNode(a);
				VMmenuTreeNode ctreenodo= this.cargarPadre(aux);
				Integer j = rootPortalInicial.getChildCount();
				if(!(j.compareTo(0)==0)){
					this.cargarNodos(ctreenodo,rootPortalInicial);
				}else{
					rootPortalInicial.add(ctreenodo);
				}	
			}
		}		
		contactTreeModel = new VMAdvancedTreeModel(rootPortalInicial);
	}


	public VMmenuTreeNode cargarPadre(VMmenuTreeNode nodo) {
		VMmenuTreeNode padre=null;
			if(nodo.getData().getPadre()!=0){
				Nodo npadre=servicionodo.buscarNodo(nodo.getData().getPadre());
				padre=new VMmenuTreeNode(npadre,null);
				padre.add(nodo);
				nodo=cargarPadre(padre);		
			}
			return nodo;
	}
	private void cargarNodos(VMmenuTreeNode nodo,VMmenuTreeNode root) { 
		boolean encontro=false;
		
		 for(int j=0;j< root.getChildCount();j++){
			  if(root.getChildAt(j).getData().getId().compareTo(nodo.getData().getId())==0){
				  for(int i=0;i< nodo.getChildCount();i++){
				    if(nodo.getChildCount()==1)
					cargarNodos((VMmenuTreeNode) nodo.getChildAt(0),(VMmenuTreeNode) root.getChildAt(j));  
				    else{
				    	 VMmenuTreeNode aux = new VMmenuTreeNode(nodo.getChildAt(i).getData(),null);
				         cargarNodos(aux,(VMmenuTreeNode) root.getChildAt(j));
				     }
				  }
				    encontro=true;
			    }
		 }
		 if(!encontro)
			 root.add(nodo);
	}	
}
