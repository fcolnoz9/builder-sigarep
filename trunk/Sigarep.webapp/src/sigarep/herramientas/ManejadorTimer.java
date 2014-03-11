package sigarep.herramientas;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.servicio.maestros.ServicioNoticia;
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ManejadorTimer extends SelectorComposer<Component> {
	
	@WireVariable ServicioNoticia servicionoticia;
	
	private List<Noticia> listaNoticia = new LinkedList<Noticia>(); //Lista de las Noticias
	private Noticia noticiaSeleccionada;
	
	Window win=null;
	int idcount=0;

	private @Wire Listbox lbxNoticias;
	
	

	
	public List<Noticia> getListaNoticia() {
		return listaNoticia;
	}
	@Command
	@NotifyChange({"contenido", "enlaceNoticia", "fechaRegistro", "imagenNoticia", "titulo", "vencimiento", "listaNoticia"})
	public Noticia getNoticiaSeleccionada() {
		return noticiaSeleccionada;
	}
	
	public void setListaNoticia(List<Noticia> listaNoticia) {
		this.listaNoticia = listaNoticia;
	}
	public void setNoticiaSeleccionada(Noticia noticiaSeleccionada) {
		this.noticiaSeleccionada = noticiaSeleccionada;
	}
	

	

	// OTROS METODOS
	
	@Init
	// inicializador
	public void init(){
		//initialization code
		
		buscarNoticia();
	}

	/** Busca una Noticia
	 * @parameters listaNoticia cargada con  las noticias.
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"listaNoticia"})
	public void buscarNoticia(){
		listaNoticia =servicionoticia.listadoNoticia();
	}



	/** mostrarSeleccionado2. Permite tomar los datos del objeto noticiaseleccionada para pasarlo a la pantalla modal, que tambien se le hace llamado. José Galíndez
	 * @parameters contenido, enlaceNoticia, fechaRegistro, imagen, titulo, vencimiento, listaNoticia,fotoNoticia. 
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"noticiaSeleccionada"})
	public void mostrarSeleccionado2(){
		noticiaSeleccionada = getNoticiaSeleccionada();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("noticiaSeleccionada", this.noticiaSeleccionada);
		if(win!=null){
			win.detach();
			noticiaSeleccionada=null;
			win.setId(null);
		}
		win= (Window) Executions.createComponents("WEB-INF/sigarep/vistas/portal/externo/modales/DetalleNoticia.zul", null, map);
		win.setMaximizable(true);
		win.doModal();
		win.setId("doModal"+""+idcount+"");

	}

	/** reordenarLista.Metodo que reordena la lista
	 * @parameters listaNoticia cargada con las noticias. 
	 * @return No devuelve ningun valor.
	 */
	@Command
	@NotifyChange({"listaNoticia"})
	public void reordenarLista(List<Noticia> listaNoticia){		

		if(listaNoticia.size() > 2){
			Noticia nitic = listaNoticia.remove(0);
			listaNoticia.add(nitic);
			lbxNoticias.setModel(new ListModelList<Noticia>(listaNoticia));
		}//else{System.out.println("hay menos de 3 elementos en la lista");}

	}

	/** hacer. Maneja el timer de la  vista , se encarga de actualizar la lista cada 5 segundos
	 * @parameters onTimer. 
	 * @return No devuelve ningun valor.
	 */
	@Listen("onTimer = #tiempo")
	public void hacer(){
		reordenarLista(getListaNoticia());
	}

	/** doAfterCompose.
	 * @parameters comp. 
	 * @return No devuelve ningun valor.
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		buscarNoticia();		
	}
	
}
