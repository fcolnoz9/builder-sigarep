package sigarep.controlador.maestros;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class ModalControler2 extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Listen("onClick = #btnlogin")
	public void showModal43(Event e) {
		Executions.sendRedirect("index.zul");
	}
	
	@Listen("onClick = #ordenRecuperarContrasenna")
	public void showModal5(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/WEB-INF/sigarep/vistas/portal/externo/modales/RecuperarContrasenna.zul", null, null);
		window.doModal();
	}
}

