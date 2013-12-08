package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataApelacionesPorLapso {
	
	public static CategoryModel getModel() {
		CategoryModel model = new SimpleCategoryModel();
		String[] category = { "Apelaciones"};
		model.setValue(category[0], "2010-I", new Integer(25));
		model.setValue(category[0], "2010-II", new Integer(35));
		model.setValue(category[0], "2011-I", new Integer(45));
		model.setValue(category[0], "2011-II", new Integer(48));
		model.setValue(category[0], "2012-I", new Integer(53));
		model.setValue(category[0], "2012-II", new Integer(62));

		
		return model;
	}
}
