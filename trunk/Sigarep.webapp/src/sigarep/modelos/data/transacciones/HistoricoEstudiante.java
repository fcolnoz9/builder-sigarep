package sigarep.modelos.data.transacciones;

import java.util.Date;
import java.util.List;

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
	private String numeroCaso;
	private List<String> listaMotivo;
	private Date fecha;

	public HistoricoEstudiante(String cedula, String programa, String nombre,
			String apellido, String lapso_academico, String tipo_sancion,
			String periodo_inicial, String periodo_final, String materia,
			String numeroCaso, Date fecha, List<String> listaMotivo) {
		super();
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
		this.listaMotivo = listaMotivo;
	}

	public HistoricoEstudiante() {

	}

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

	public String getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public List<String> getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List<String> listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}