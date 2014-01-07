package sigarep.viewmodels.transacciones;

import java.util.HashMap;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPortalPrincipal {

	@WireVariable
	private String cedula;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Command
	public void showModal() {
		if (cedula == "" || cedula == null) {
			Messagebox.show("Debe introducir una cedula", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cedula", this.cedula);
			Executions.createComponents("/Modal/HistorialEst.zul", null, map);
		}
	}

}
