package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataApelacionesPorPrograma {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Informatica", new Double(21.2));
		model.setValue("Analisis", new Double(10.2));
		model.setValue("Matematica", new Double(40.4));
		model.setValue("Produccion", new Double(28.2));
		return model;
	}
}
