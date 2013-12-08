package sigarep.viewmodels.reportes;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class ModalControler2 extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Listen("onClick = #lblRegla")
	public void showModal(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/Modal/reglamentos.zul", null, null);
		window.doModal();
	}
	@Listen("onClick = #orderBtn2")
	public void showModal2(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"inde.zul", null, null);
		window.doModal();
	}
	@Listen("onClick = #buscar")
	public void showModal3(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/Modal/HistorialEst.zul", null, null);
		window.doModal();
	}
	@Listen("onClick = #rowcrog")
	public void showModal4(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"/Modal/DescripcionCrog.zul", null, null);
		window.doModal();
	}
	@Listen("onClick = #btnlogin")
	public void showModal43(Event e) {
		Executions.sendRedirect("index.zul");
	}
}

