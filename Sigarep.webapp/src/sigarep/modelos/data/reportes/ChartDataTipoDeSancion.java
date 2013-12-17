package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataTipoDeSancion {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Regimen de Repitencia", new Double(21.2));
		model.setValue("Regimen de Permanencia", new Double(10.2));
		
		return model;
	}
}
