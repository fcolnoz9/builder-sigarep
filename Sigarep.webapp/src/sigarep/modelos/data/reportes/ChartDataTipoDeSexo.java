package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataTipoDeSexo {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Femenino", new Double(21.2));
		model.setValue("Masculino", new Double(10.2));
		
		return model;
	}
}
