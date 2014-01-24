
package sigarep.modelos.data.reportes;
public class ReportTypeApelaciones {
	private String value;
	private String label;

	public ReportTypeApelaciones(String label, String value) {
		super();
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}