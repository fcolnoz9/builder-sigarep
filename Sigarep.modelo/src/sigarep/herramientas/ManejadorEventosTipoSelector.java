package sigarep.herramientas;


import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import sigarep.modelos.data.maestros.Noticia;
@SuppressWarnings("serial")
public class ManejadorEventosTipoSelector extends SelectorComposer<Component> {

	private @Wire Listbox lbxNoticias;
	private @Wire Listbox backupsListbox;

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

	/** hacer. Maneja el timer de la  vista , se encarga de actualizar la lista de noticias del portal cada 5 segundos
	 * @parameters onTimer. 
	 * @return No devuelve ningun valor.
	 */
	@Listen("onTimer = #tiempo")
	public void hacer(){
		List<Noticia> listaNoticia = new LinkedList<Noticia>();
		for(int i = 0; i < lbxNoticias.getModel().getSize(); i++){
			listaNoticia.add((Noticia) lbxNoticias.getModel().getElementAt(i));
		}
		reordenarLista(listaNoticia);
	}

	/** OnTimer$timer. Maneja el timer de la  vista , se encarga de actualizar el contenido del listbox de respaldos 
	 * de la BD de la funcionalidad respaldar datos cada 50 segundos (una vez que la función ejecutarComandos haya creado 
	 * el backup por completo, actualizando su tamaño en el listbox)
	 * @parameters backupsListbox. 
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepcion.
	 */
	@Listen("onTimer = #timerRespaldarDatos")
	public void onTimer(){
		    backupsListbox.setModel(backupsListbox.getModel());
	}
	
	/** doAfterCompose.
	 * @parameters comp. 
	 * @return No devuelve ningun valor.
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);		
	}
	
	/**
	 * modalRecuperarContrasenna.
	 * 
	 * @param Ninguno
	 * @return Muestra la ventana de Recuperar la contraseña de Usuario.
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	
	@Listen("onClick = #ordenRecuperarContrasenna")
	public void modalRecuperarContrasenna(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/WEB-INF/sigarep/vistas/portal/externo/modales/RecuperarContrasenna.zul", null, null);
		window.doModal();
	}
}
