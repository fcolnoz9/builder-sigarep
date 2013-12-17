package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataApelacionesPorInstancia {

	public static CategoryModel getModel(){
		SimpleCategoryModel model = new SimpleCategoryModel();
		model.setValue("Inicial", "2006", new Integer(100));
		model.setValue("Inicial", "2007", new Integer(143));
		model.setValue("Inicial", "2008", new Integer(223));
		model.setValue("Inicial", "2009", new Integer(378));
		
		model.setValue("Consejo de Decanato", "2006", new Integer(174));
		model.setValue("Consejo de Decanato", "2007", new Integer(244));
		model.setValue("Consejo de Decanato", "2008", new Integer(124));
		model.setValue("Consejo de Decanato", "2009", new Integer(174));


		model.setValue("Consejo Universitario", "2006", new Integer(137));
		model.setValue("Consejo Universitario", "2007", new Integer(297));
		model.setValue("Consejo Universitario", "2008", new Integer(307));
		model.setValue("Consejo Universitario", "2009", new Integer(317));
		return model;
	}
}
