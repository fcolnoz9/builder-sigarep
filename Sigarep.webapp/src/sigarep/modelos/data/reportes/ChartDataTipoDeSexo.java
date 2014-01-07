package sigarep.modelos.data.reportes;

import java.util.List;

import org.zkoss.zul.PieModel;

import org.zkoss.zul.SimplePieModel;

public class ChartDataTipoDeSexo {

	public static PieModel getModel(/*List<ApelacionesPorSexo> Prueba*/){
		PieModel model = new SimplePieModel();
		//model.setValue(Prueba.get(0).getSexo(), Prueba.get(0).getCantidad());
		//model.setValue(Prueba.get(1).getSexo(), Prueba.get(1).getCantidad());
		model.setValue("Masculino", 20);
		model.setValue("Femenino", 40);
		return model;
	}
}
