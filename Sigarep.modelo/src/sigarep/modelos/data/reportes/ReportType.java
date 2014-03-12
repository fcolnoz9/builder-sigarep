package sigarep.modelos.data.reportes;

/** ReportType
 * @author BUILDER 
 * @version 1.0
 * @since 20/12/13
 */
public class ReportType {
	// Atributos de la clase
	private String value;
	private String label;

	/**
	 * Constructor ReportType
	 * @param label, value
	 * @return Constructor lleno
	 */
	public ReportType(String label, String value) {
		super();
		this.value = value;
		this.label = label;
	}

	//  Métodos Get
	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}// Fin Métodos Get

}//Fin Clase ReportType