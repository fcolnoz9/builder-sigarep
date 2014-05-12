package sigarep.viewmodels.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioNodo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;

/**
* Clase VMRegistrarGrupo : Clase ViewModels relacionada con el Maestro Grupo.
*
* @author Equipo Builder
* @version 1.0
* @since 04/12/13
* @last 10/05/2014
*/

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarGrupo {
	//-----------------Servicios----------------------------
	@WireVariable
	private ServicioUsuario serviciousuario;
	@WireVariable 
	private ServicioNodo servicionodo;
	@WireVariable
	private ServicioGrupo serviciogrupo;
	//-----------------Variables de control ------------------	
	String ruta = UtilidadesSigarep.obtenerDirectorio();
	//-----------------Variables Grupo ------------------
	private Integer idGrupo; // clave primaria de la tabla Grupo
	private String nombre = ""; 	// nombre del Grupo
    private String descripcion = ""; // descripción del Grupo
    private String estado;
    //-----------------Variables Filtro---------------------
	private String nombreGrupofiltro = "";
	private String descripcionfiltro = "";
	//-----------------Variables Lista----------------------
    private List<Grupo> listaGrupos = new LinkedList<Grupo>();
    private ListModelList<Nodo> modelonodos; // lista de los nodos u opciones del menú árbol.
    //-----------------Variables Objeto---------------------
	@Wire
	private Window demoWindowRegistrarGrupo;
	@Wire
	private Tree arbol;
	@Wire
	private Tree arbol2;	
	private VMModeloArbolAvanzado modeloMenuArbol;
	private VMModeloArbolAvanzado modeloMenuArbol2;
	private static VMNodoMenuArbol  raiz;
	private static VMNodoMenuArbol  raiz2;
    private Grupo grupoAux = null;
    private Grupo grupoAux2 = null;
    private Grupo grupoSeleccionado = new Grupo();
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	VMUtilidadesDeSeguridad seguridad = new VMUtilidadesDeSeguridad();

	// Métodos Set y Get
	public ServicioNodo getsnodo() {
		return servicionodo;
	}

	public void setsnodo(ServicioNodo snodo) {
		this.servicionodo = snodo;
	}
	
	public Tree getArbol() {
		return arbol;
	}

	public void setArbol(Tree arbol) {
		this.arbol = arbol;
	}

	public Tree getArbol2() {
		return arbol2;
	}

	public void setArbol2(Tree arbol2) {
		this.arbol2 = arbol2;
	}

	public VMModeloArbolAvanzado getModeloMenuArbol() {
		return modeloMenuArbol;
	}

	public void setModeloMenuArbol(VMModeloArbolAvanzado modeloMenuArbol) {
		this.modeloMenuArbol = modeloMenuArbol;
	}

	public VMModeloArbolAvanzado getModeloMenuArbol2() {
		return modeloMenuArbol2;
	}

	public void setModeloMenuArbol2(VMModeloArbolAvanzado modeloMenuArbol2) {
		this.modeloMenuArbol2 = modeloMenuArbol2;
	}

	public VMNodoMenuArbol getRaiz2() {
		return raiz2;
	}

	public void setRaiz2(VMNodoMenuArbol raiz2) {
		this.raiz2 = raiz2;
	}

	public void setRaiz(VMNodoMenuArbol raiz) {
		this.raiz = raiz;
	}

	public VMNodoMenuArbol getRaiz() {
		return raiz;
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
	
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	
	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
	
	public String getNombreGrupofiltro() {
		return nombreGrupofiltro;
	}

	public void setNombreGrupofiltro(String nombreGrupofiltro) {
		this.nombreGrupofiltro = nombreGrupofiltro;
	}

	public String getDescripcionfiltro() {
		return descripcionfiltro;
	}

	public void setDescripcionfiltro(String descripcionfiltro) {
		this.descripcionfiltro = descripcionfiltro;
	}
	//Fin Métodos Set y Get	


	/**
	 * Init. Código de inicialización.
	 * 
	 * @param @ContextParam(ContextType.VIEW) Component view, Component comp
	 * @return Carga de Variables, carga de los dos menu tipo arbol y metodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Wire("#winRegistrarGrupo")//para conectarse a la ventana con el ID
	Window ventana;
	@AfterCompose
	public void Init(@ContextParam(ContextType.VIEW) Component view, Component comp) throws Exception {
		Selectors.wireComponents(view, this, false);
		cargarArbol();	
		modeloMenuArbol = new VMModeloArbolAvanzado(raiz); //modelo del menú arbol de las funciones que no tiene el grupo (es variable) 
		raiz2 = new VMNodoMenuArbol(null,null); 
		modeloMenuArbol2 = new VMModeloArbolAvanzado(raiz2); //modelo del menú arbol de las funciones del grupo (es variable).
		buscarListadoGrupos();
	}
	
	/**
	 * Buscar la información del grupo de usuarios, el nombre, la descripción, 
	 * el menú asociado con sus funcionalidades actuales (modeloMenuArbol2)
	 * así como el menú de las funcionalidades que no están asociadas al grupo
	 * (modeloMenuArbol) y que aun se pueden asociar 
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción. 
	 */
	
	@Command
	@NotifyChange({ "nombre", "descripcion","grupoAux","grupoAux2","modeloMenuArbol","modeloMenuArbol2","grupoSeleccionado"})
	public void buscarGrupo(@BindingParam("listboxGrupos") Listbox listboxGrupos){
			grupoAux = new Grupo();
			grupoAux2 = new Grupo();
			Grupo g = serviciogrupo.buscarGrupoNombre(grupoSeleccionado.getNombre());
			ArrayList<Nodo> nodosOrdenados = new ArrayList<Nodo>(g.getNodos());		
			Collections.sort(nodosOrdenados, new Nodo());
			raiz2 = new VMNodoMenuArbol(null,null);
			cargarMenu(nodosOrdenados,raiz2);
			modeloMenuArbol2 = new VMModeloArbolAvanzado(raiz2);
			raiz = new VMNodoMenuArbol(null,null);
			cargarMenu(servicionodo.funcionesGrupoNoPerteneceGrupo(g.getIdGrupo()), raiz);
			modeloMenuArbol = new VMModeloArbolAvanzado(raiz);
			grupoAux = g;
			grupoAux2 = g;
			nombre = grupoAux.getNombre();
			descripcion = grupoAux.getDescripcion();
			listboxGrupos.clearSelection();
	}
	
	/**
	* Buscar la lista de grupos registrados. Inicializa el código.
	*
	* @param Ninguno
	* @return Busca todos los grupos registrados.
	* @throws No dispara ninguna excepcion.
	*/
	
	@Command
	@NotifyChange({ "listaGrupos"})
	public void buscarListadoGrupos(){
		listaGrupos = serviciogrupo.listadoGrupo();
	}
	
	/**
	 * Guarda el grupo, así como las funciones asociadas a éste o en su 
	 * defecto los modifica si se trata de un grupo existente
	 * @param ninguno 
	 * @return Guarda la información del grupo asi como sus funciones, el command 
	 * indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción. 
	 */
	
	@Command
	@NotifyChange({ "idGrupo", "descripcion", "nombre","grupoAux","listaGrupos","modeloMenuArbol","modeloMenuArbol2"})
	public void guardarGrupo(){
		Grupo grupo1;
		Set<Nodo> nodos = new HashSet<Nodo>();
		if (nombre.equals("") || descripcion.equals(""))
			mensajeAlUsuario.advertenciaLlenarCampos();
		 else if(grupoAux == null && serviciogrupo.buscarGrupoNombre(nombre)!=null)
			 mensajeAlUsuario.advertenciaGrupoYaExistente(nombre);
		else if (raiz2.getChildren().size() > 0) {
			if (grupoAux == null) {
				grupo1 = new Grupo();
				grupo1.setIdGrupo(idGrupo);
			} 
			else 
			{
				grupo1 = grupoAux;
			}
			grupo1.setDescripcion(descripcion);
			grupo1.setNombre(nombre);
			grupo1.setEstatus(true);

			if (raiz2.getChildCount() > 0) {
				for (TreeNode<Nodo> a2 : raiz2.getChildren()) {
					cargarHijosGrupo(a2, nodos);
				}
			}
			grupo1.setNodos(nodos);
			serviciogrupo.guardarGrupo(grupo1);
			limpiar();
			mensajeAlUsuario.informacionRegistroCorrecto();
		} else
			mensajeAlUsuario.advertenciaMenudelGrupoVacio();
	}
	
	/**
	 * Carga del menú arbol por defecto.
	 * carga el primer menú del arbol con todas las funciones existentes (nodos)
	 * @param ninguno 
	 * @return Menu del arbol por defecto cargado con todos los nodos de la BD.
	 * @throws No dispara ninguna excepción. 
	 */
	
	@NotifyChange({"raiz"})
	public void cargarArbol(){
		raiz = new VMNodoMenuArbol(null,null);
		VMNodoMenuArbol aux=null;
		ArrayList<Nodo> nodosPorPadreOrdenados = new ArrayList<Nodo>(servicionodo.buscarNodosPorPadre(0));
		Collections.sort(nodosPorPadreOrdenados, new Nodo());
		for(Nodo a:nodosPorPadreOrdenados){
			aux=new VMNodoMenuArbol(a,null);
		    this.cargarHijos(aux,a);
		    raiz.add(aux);
		}
	}

	/**
	 * Cargar recursivo los hijos de un nodo en particular
	 * Metodo que permite la carga los nodos hijos a los nodos padres
	 * @param m1(modelo VMmenuTreeNode del padre), a1(nodo objetivo padre) 
	 * @return ninguno.
	 * @throws No dispara ninguna excepción. 
	 */

	public void cargarHijos(VMNodoMenuArbol m1, Nodo a1) {
		VMNodoMenuArbol m2 = null;
		ArrayList<Nodo> buscarPadreOrdenadamente = new ArrayList<Nodo>(servicionodo.buscarNodosPorPadre(a1.getId()));
		Collections.sort(buscarPadreOrdenadamente, new Nodo());
		for (Nodo a2 : buscarPadreOrdenadamente) {
			m1.setOpen(true);
			if (a2.esFuncion() == false)
				m2 = new VMNodoMenuArbol(a2, null);
			else
				m2 = new VMNodoMenuArbol(a2);
			m1.add(m2);
			if (!servicionodo.buscarNodosPorPadre(a2.getId()).isEmpty())
				cargarHijos(m2, a2);
		}
	}
	
	/**
	 * Cargar los nodos hijos al grupo dado un nodo raiz
	 * metodo que carga los nodos hijos (funciones) del grupo de usuario
	 * @param raiz(nodo del arbol), nodos(Colección de nodos hijos encontrados) 
	 * @return ninguno pero actualiza la coleccion de nodos hijos.
	 * @throws No se dispara ninguna excepción. 
	 */
	
	public void cargarHijosGrupo(TreeNode<Nodo> raiz, Set<Nodo> nodos) {
		if (raiz.getChildCount() > 0) {
			for (TreeNode<Nodo> a2 : raiz.getChildren()) {
				if (a2.getData().esFuncion()==false) {
					cargarHijosGrupo(a2, nodos);
				} else {
					nodos.add(a2.getData());
				}
			}
		} else {
			nodos.equals(raiz.getData());
		}
	}
		
	/**
	 * Cargar Menú, metodo reusable que permite cargar los nodos (funciones) 
	 * del menú del grupo y del menú genera.
	 * @param nodos(Colección de nodos hijos), raiz(nodo raíz del arbol). 
	 * @return ninguno.
	 * @throws No dispara ninguna excepción. 
	 */
	
	public void cargarMenu(List<Nodo> nodos, VMNodoMenuArbol raiz){
		VMNodoMenuArbol aux=null;
		for(Nodo a:nodos){
			aux=new VMNodoMenuArbol(a);
			VMNodoMenuArbol ctreenodo = this.cargarPadre(aux);
			ctreenodo.setOpen(true);
			Integer j = raiz.getChildCount();
			if(!(j.compareTo(0)==0)){
				this.cargarNodos(ctreenodo,raiz);
			}else{
				raiz.add(ctreenodo);
			}	
		}	
	}
	
	/**
	 * Cargar nodo padre permite cargar el nodo padre (nodo del menú arbol) dado 
	 * un nodo en especifico
	 * @param VMNodoMenuArbol nodo. 
	 * @return VMmenuTreeNode nodo del ultimo padre cargado recursivamente.
	 * @throws No dispara ninguna excepción. 
	 */
	
	public VMNodoMenuArbol cargarPadre(VMNodoMenuArbol nodo) {
		VMNodoMenuArbol padre=null;
			if(nodo.getData().getPadre()!=0){
				Nodo npadre=servicionodo.buscarNodo(nodo.getData().getPadre());
				padre=new VMNodoMenuArbol(npadre,null);
				nodo.setOpen(true);
				padre.add(nodo);
				nodo=cargarPadre(padre);		
			}
			return nodo;
	}
	
	/**
	 * Metodo que permite cargar el nodo padre (nodo del menú arbol) dado un nodo en especifico
	 * @param VMNodoMenuArbol nodo. 
	 * @return VMmenuTreeNode nodo del ultimo padre cargado recursivamente.
	 * @throws No dispara ninguna excepción. 
	 */
	
	private void cargarNodos(VMNodoMenuArbol nodo,VMNodoMenuArbol raiz) { 
		boolean encontro=false;
		 for(int j=0;j< raiz.getChildCount();j++){
			  if(raiz.getChildAt(j).getData().getId().compareTo(nodo.getData().getId())==0){
				  for(int i=0;i< nodo.getChildCount();i++){
				    if(nodo.getChildCount()==1)
				    	 cargarNodos((VMNodoMenuArbol) nodo.getChildAt(0),(VMNodoMenuArbol) raiz.getChildAt(j));  
				    else{
				    	 VMNodoMenuArbol aux = new VMNodoMenuArbol(nodo.getChildAt(i).getData(),null);
				    	 cargarNodos(aux,(VMNodoMenuArbol) raiz.getChildAt(j));
				     }
				  }
				    encontro=true;
			    }
		 }
		 if(!encontro){
			 raiz.add(nodo);
		 }
	}	
	
	@GlobalCommand
	@NotifyChange({"grupoAux2"})
    public void actualizarMenuArbolSIGAREP(){
		if(grupoAux2!=null){
			ArrayList<Grupo> rolesUsuario = new ArrayList<Grupo>(serviciousuario.rolesDelUsuario((seguridad.getUsuario().getUsername())));
			for(Grupo rol : rolesUsuario){
				if(grupoAux2.getNombre().equals(rol.getNombre())){
					BindUtils.postGlobalCommand(null, null, "Init", null);
				break;					
				}
			}
			grupoAux2 = null;
		}
    }
	
	// Método que busca y filtra los usuarios
	@Command
	@NotifyChange({"listaGrupos"})
	public void filtros(){
		listaGrupos = serviciogrupo.buscarGrupoFiltro(nombreGrupofiltro, descripcionfiltro);
	}

	/**
	 * Limpia los campos nombre, descripción del grupo, el menu del grupo
	 * y restaura todos las funciones registradas en el menú general, actualiza la 
	 * lista de grupos registrados. 
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción. 
	 */
	
	@Command
	@NotifyChange({ "nombre", "descripcion", "modeloMenuArbol2","modeloMenuArbol","raiz","raiz2","listaGrupos","grupoSeleccionado","grupoAux"})
	public void limpiar(){
		nombre = "";
		descripcion = "";
		cargarArbol();
		modeloMenuArbol = new VMModeloArbolAvanzado(raiz);
		raiz2 = new VMNodoMenuArbol(null,null);
		modeloMenuArbol2 = new VMModeloArbolAvanzado(raiz2);
		grupoSeleccionado = null;
		grupoAux = null;
		buscarListadoGrupos();
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	
	@Command
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(raiz2.getChildCount() > 0)
				condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}

} //fin VMRegistrarGrupo
