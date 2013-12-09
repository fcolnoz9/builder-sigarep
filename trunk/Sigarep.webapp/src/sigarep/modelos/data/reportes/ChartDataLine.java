package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataLine {
	
	public static CategoryModel getModel() {
		CategoryModel model = new SimpleCategoryModel();
		String[] category = { "Apelaron", "Sancionados" };
		model.setValue(category[0], "2010-I", new Integer(25));
		model.setValue(category[0], "2010-II", new Integer(35));
		model.setValue(category[0], "2011-I", new Integer(45));
		model.setValue(category[0], "2011-II", new Integer(48));
		model.setValue(category[0], "2012-I", new Integer(53));
		model.setValue(category[0], "2012-II", new Integer(62));

		model.setValue(category[1], "2010-I", new Integer(28));
		model.setValue(category[1], "2010-II", new Integer(33));
		model.setValue(category[1], "2011-I", new Integer(40));
		model.setValue(category[1], "2011-II", new Integer(53));
		model.setValue(category[1], "2012-I", new Integer(58));
		model.setValue(category[1], "2012-II", new Integer(75));

		
		return model;
	}
}
