package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataAsignaturasMayorCantidadSancionados {

	public static CategoryModel getModel(){
		SimpleCategoryModel model = new SimpleCategoryModel();
		model.setValue("Programación I", "2006", new Integer(100));
		model.setValue("Programación I", "2007", new Integer(143));
		model.setValue("Programación I", "2008", new Integer(223));
		model.setValue("Programación I", "2009", new Integer(378));
		
		model.setValue("Matemática II", "2006", new Integer(174));
		model.setValue("Matemática II", "2007", new Integer(244));
		model.setValue("Matemática II", "2008", new Integer(124));
		model.setValue("Matemática II", "2009", new Integer(174));


		model.setValue("Estructuras Discretas", "2006", new Integer(137));
		model.setValue("Estructuras Discretas", "2007", new Integer(297));
		model.setValue("Estructuras Discretas", "2008", new Integer(307));
		model.setValue("Estructuras Discretas", "2009", new Integer(317));
		
		model.setValue("Matemática I", "2006", new Integer(137));
		model.setValue("Matemática I", "2007", new Integer(297));
		model.setValue("Matemática I", "2008", new Integer(307));
		model.setValue("Matemática I", "2009", new Integer(317));
		
		
		return model;
	}
}
