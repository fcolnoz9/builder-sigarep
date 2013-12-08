package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartData {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Enfermedad", new Double(21.2));
		model.setValue("Estatus Laboral/Economico", new Double(10.2));
		model.setValue("Muerte Familiar", new Double(40.4));
		model.setValue("Detencion Judicial o Policial", new Double(28.2));
		return model;
	}
}
