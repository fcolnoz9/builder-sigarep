package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataEstudiantesSancionadosVsApelaron {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Sancionados", new Double(21.2));
		model.setValue("Apelaron", new Double(10.2));
		
		return model;
	}
}
