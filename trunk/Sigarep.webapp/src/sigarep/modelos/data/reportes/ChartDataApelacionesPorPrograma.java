package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataApelacionesPorPrograma {

	public static CategoryModel getModel(){
		SimpleCategoryModel model = new SimpleCategoryModel();
		model.setValue("Informática", "2006", new Integer(100));
		model.setValue("Informática", "2007", new Integer(143));
		model.setValue("Informática", "2008", new Integer(223));
		model.setValue("Informática", "2009", new Integer(378));
		
		model.setValue("Análisis", "2006", new Integer(174));
		model.setValue("Análisis", "2007", new Integer(244));
		model.setValue("Análisis", "2008", new Integer(124));
		model.setValue("Análisis", "2009", new Integer(174));


		model.setValue("Matemática", "2006", new Integer(137));
		model.setValue("Matemática", "2007", new Integer(297));
		model.setValue("Matemática", "2008", new Integer(307));
		model.setValue("Matemática", "2009", new Integer(317));
		
		model.setValue("Producción", "2006", new Integer(137));
		model.setValue("Producción", "2007", new Integer(297));
		model.setValue("Producción", "2008", new Integer(307));
		model.setValue("Producción", "2009", new Integer(317));
		
		
		return model;
	}
}
