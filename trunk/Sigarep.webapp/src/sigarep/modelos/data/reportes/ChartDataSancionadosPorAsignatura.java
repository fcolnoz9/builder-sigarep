package sigarep.modelos.data.reportes;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataSancionadosPorAsignatura {

	public static PieModel getModel(){
		PieModel model = new SimplePieModel();
		model.setValue("Programacion I", new Double(21.2));
		model.setValue("Calculo II", new Double(10.2));
		model.setValue("Estructuras Discretas", new Double(40.4));
		model.setValue("Calculo I", new Double(28.2));
		return model;
	}
}
