package sigarep.viewmodels.reportes;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class ModalController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Listen("onClick = #btnBuscar")
	public void showModal(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/WEB-INF/sigarep/vistas/maestros/BuscarAsignatura.zul", null, null);
		window.doModal();
	}
	
	@Listen("onSelect = #box")
	public void seleccion(Event e){
		Window window = (Window)Executions.createComponents(
				"/modal.zul", null, null);
		window.doModal();
		
	}
}

