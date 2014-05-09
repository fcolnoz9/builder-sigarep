package sigarep.modelos.data.transacciones;

import java.util.Date;

/**
 * historico estudiante , trae los objetos de esta clase compuesta por varias
 * claves principales y atributos propios
 * 
 * @author Equipo Builder
 * @version 1
 * @since 03/01/2014
 * @last 08/05/2014
 */
public class HistoricoEstudiante {

	private String cedula;
	private String programa;
	private String nombre;
	private String apellido;
	private String lapso_academico;
	private String tipo_sancion;
	private String periodo_inicial;
	private String periodo_final;
	private String materia;
	private Integer numeroCaso;
	private String descripcionMotivo;
	private Date fecha;

	// constructor por defecto
	public HistoricoEstudiante() {

	}

	/**
	 * Constructor HistoricoEstudiante
	 * 
	 * @param cedula, programa, nombre, apellido, lapso_academico,tipo_sancion,
	 *               periodo_inicial, periodo_final, materia, numeroCaso, fecha,
	 *               motivo
	 */
	public HistoricoEstudiante(String cedula, String programa, String nombre,
			String apellido, String lapso_academico, String tipo_sancion,
			String periodo_inicial, String periodo_final, String materia,
			Integer numeroCaso, Date fecha, String motivo) {
		super();
		// Atributos de la clase
		this.cedula = cedula;
		this.programa = programa;
		this.nombre = nombre;
		this.apellido = apellido;
		this.lapso_academico = lapso_academico;
		this.tipo_sancion = tipo_sancion;
		this.periodo_inicial = periodo_inicial;
		this.periodo_final = periodo_final;
		this.materia = materia;
		this.numeroCaso = numeroCaso;
		this.fecha = fecha;
		this.descripcionMotivo = motivo;
	}

	// Métodos Set y Get
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLapso_academico() {
		return lapso_academico;
	}

	public void setLapso_academico(String lapso_academico) {
		this.lapso_academico = lapso_academico;
	}

	public String getTipo_sancion() {
		return tipo_sancion;
	}

	public void setTipo_sancion(String tipo_sancion) {
		this.tipo_sancion = tipo_sancion;
	}

	public String getPeriodo_inicial() {
		return periodo_inicial;
	}

	public void setPeriodo_inicial(String periodo_inicial) {
		this.periodo_inicial = periodo_inicial;
	}

	public String getPeriodo_final() {
		return periodo_final;
	}

	public void setPeriodo_final(String periodo_final) {
		this.periodo_final = periodo_final;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(Integer numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public String getMotivo() {
		return descripcionMotivo;
	}

	public void setMotivo(String motivo) {
		this.descripcionMotivo = motivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}

	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}
	// Fin Métodos Set y Get
}