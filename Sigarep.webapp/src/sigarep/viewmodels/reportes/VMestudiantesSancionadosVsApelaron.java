package sigarep.viewmodels.reportes;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.PieModel;


import sigarep.modelos.data.reportes.ChartDataEstudiantesSancionadosVsApelaron;

public class VMestudiantesSancionadosVsApelaron {

	PieModel model;
	
	@Init
	public void init() {
		// prepare chart data
		model = ChartDataEstudiantesSancionadosVsApelaron.getModel();
	}

	public PieModel getModel() {
		return model;
	}
	
	@GlobalCommand("dataChanged") 
	@NotifyChange("model")
	public void onDataChanged(
			@BindingParam("category")String category,
			@BindingParam("num") Number num){
		model.setValue(category, num);
	}
}
