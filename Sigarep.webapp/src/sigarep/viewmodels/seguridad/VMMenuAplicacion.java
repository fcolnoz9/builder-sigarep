package sigarep.viewmodels.seguridad;

import java.util.ArrayList;
import java.util.Collections;

import org.zkoss.bind.annotation.AfterCompose;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;

import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMMenuAplicacion{
	
	private VMRenderizarMenuArbolAplicacion renderizarPortalAplicacion=new VMRenderizarMenuArbolAplicacion();
	public VMRenderizarMenuArbolAplicacion getRendererPortalAplicacion() {
		return renderizarPortalAplicacion;
	}


	public void setRendererPortalAplicacion(
			VMRenderizarMenuArbolAplicacion rendererPortalAplicacion) {
		this.renderizarPortalAplicacion = rendererPortalAplicacion;
	}
	private VMModeloArbolAvanzado contactTreeModel;
	private static VMNodoMenuArbol  rootPortalInicial;
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
    public VMModeloArbolAvanzado getContactTreeModel() {
		return contactTreeModel;
	}


	public void setContactTreeModel(VMModeloArbolAvanzado contactTreeModel) {
		this.contactTreeModel = contactTreeModel;
	}
	public static VMNodoMenuArbol getRootPortalInicial() {
		return rootPortalInicial;
	}


	public static void setRootPortalInicial(VMNodoMenuArbol rootPortalInicial) {
		VMMenuAplicacion.rootPortalInicial = rootPortalInicial;
	}
	
	@AfterCompose
	@Command
	@GlobalCommand
	@NotifyChange({"contactTreeModel"})
	public void Init(@ContextParam(ContextType.COMPONENT) Component windowindex,@ContextParam(ContextType.VIEW) Component view) {
		rootPortalInicial = new VMNodoMenuArbol(null,null);		
		for(String rol:VMUtilidadesDeSeguridad.roles()){
			Grupo g=serviciogrupo.buscarGrupoNombre(rol);
			VMNodoMenuArbol aux=null;
			ArrayList<Nodo> nodosOrdenados = new ArrayList<Nodo>(g.getNodos());
			Collections.sort(nodosOrdenados, new Nodo());
			for(Nodo a:nodosOrdenados){
				aux=new VMNodoMenuArbol(a);
				VMNodoMenuArbol ctreenodo= this.cargarPadre(aux);
				Integer j = rootPortalInicial.getChildCount();
				if(!(j.compareTo(0)==0)){
					this.cargarNodos(ctreenodo,rootPortalInicial);
				}else{
					rootPortalInicial.add(ctreenodo);
				}	
			}
		}		
		contactTreeModel = new VMModeloArbolAvanzado(rootPortalInicial);
	}


	public VMNodoMenuArbol cargarPadre(VMNodoMenuArbol nodo) {
		VMNodoMenuArbol padre=null;
			if(nodo.getData().getPadre()!=0){
				Nodo npadre=servicionodo.buscarNodo(nodo.getData().getPadre());
				padre=new VMNodoMenuArbol(npadre,null);
				padre.add(nodo);
				nodo=cargarPadre(padre);		
			}
			return nodo;
	}
	private void cargarNodos(VMNodoMenuArbol nodo,VMNodoMenuArbol root) { 
		boolean encontro=false;
		
		 for(int j=0;j< root.getChildCount();j++){
			  if(root.getChildAt(j).getData().getId().compareTo(nodo.getData().getId())==0){
				  for(int i=0;i< nodo.getChildCount();i++){
				    if(nodo.getChildCount()==1)
					cargarNodos((VMNodoMenuArbol) nodo.getChildAt(0),(VMNodoMenuArbol) root.getChildAt(j));  
				    else{
				    	 VMNodoMenuArbol aux = new VMNodoMenuArbol(nodo.getChildAt(i).getData(),null);
				         cargarNodos(aux,(VMNodoMenuArbol) root.getChildAt(j));
				     }
				  }
				    encontro=true;
			    }
		 }
		 if(!encontro)
			 root.add(nodo);
	}	
}
