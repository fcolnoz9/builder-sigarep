package sigarep.modelos.data.reportes;

import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

public class ChartDataApelacionesPorMotivo {

	public static CategoryModel getModel(){
		SimpleCategoryModel model = new SimpleCategoryModel();
		model.setValue("Enfermedad", "2006", new Integer(100));
		model.setValue("Enfermedad", "2007", new Integer(143));
		model.setValue("Enfermedad", "2008", new Integer(223));
		model.setValue("Enfermedad", "2009", new Integer(378));
		
		model.setValue("Estatus Laboral/Económico", "2006", new Integer(174));
		model.setValue("Estatus Laboral/Económico", "2007", new Integer(244));
		model.setValue("Estatus Laboral/Económico", "2008", new Integer(124));
		model.setValue("Estatus Laboral/Económico", "2009", new Integer(174));


		model.setValue("Muerte Familiar", "2006", new Integer(137));
		model.setValue("Muerte Familiar", "2007", new Integer(297));
		model.setValue("Muerte Familiar", "2008", new Integer(307));
		model.setValue("Muerte Familiar", "2009", new Integer(317));
		
		model.setValue("Detención Judicial o Policial", "2006", new Integer(137));
		model.setValue("Detención Judicial o Policial", "2007", new Integer(297));
		model.setValue("Detención Judicial o Policial", "2008", new Integer(307));
		model.setValue("Detención Judicial o Policial", "2009", new Integer(317));
		
		model.setValue("Falta de Consejero", "2006", new Integer(137));
		model.setValue("Falta de Consejero", "2007", new Integer(297));
		model.setValue("Falta de Consejero", "2008", new Integer(307));
		model.setValue("Falta de Consejero", "2009", new Integer(317));
		
	
		
		model.setValue("Carga Académica", "2006", new Integer(127));
		model.setValue("Carga Académica", "2007", new Integer(207));
		model.setValue("Carga Académica", "2008", new Integer(300));
		model.setValue("Carga Académica", "2009", new Integer(409));
		return model;
	}
}
