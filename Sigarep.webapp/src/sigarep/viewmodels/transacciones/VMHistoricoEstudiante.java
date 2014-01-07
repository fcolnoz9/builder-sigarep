package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.modelos.data.transacciones.HistoricoEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioHistoricoEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMHistoricoEstudiante {
	
	@Wire("#modalDialog")
	
	@WireVariable
	private String cedula;
	@WireVariable
	private String programa;
	@WireVariable
	private String nombre;
	@WireVariable
	private String apellido;
	@WireVariable
	private String lapsoAcademico;
	@WireVariable
	private String tipoSancion;
	@WireVariable
	private String periodoInicial;
	@WireVariable
	private String periodoFinal;
	@WireVariable
	private String materia;
	@WireVariable
	private String numeroCaso;
	@WireVariable
	private List<String> listaMotivo;
	@WireVariable
	private String fecha;

	@WireVariable
	private HistoricoEstudiante historicoEstudiante;

	@WireVariable
	private ServicioHistoricoEstudiante serviciohistoricoestudiante;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(String lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public String getTipoSancion() {
		return tipoSancion;
	}

	public void setTipoSancion(String tipoSancion) {
		this.tipoSancion = tipoSancion;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<String> getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List<String> listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public HistoricoEstudiante getHistoricoEstudiante() {
		return historicoEstudiante;
	}

	public void setHistoricoEstudiante(HistoricoEstudiante historicoEstudiante) {
		this.historicoEstudiante = historicoEstudiante;
	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("cedula") String c) {
		Selectors.wireComponents(view, this, false);
		this.cedula = c;
		buscarHistorialEstudiante(c);
		mostrarHistoricoEstudiante();
	}

	@Command
	@NotifyChange({ "cedula", "programa", "nombre", "apellido",
			"lapsoAcademico", "tipoSancion", "periodoInicial", "periodoFinal",
			"materia", "numeroCaso", "fecha" })
	public void buscarHistorialEstudiante(String cedula) {
		historicoEstudiante = serviciohistoricoestudiante
				.buscarHistoricoEstudiante(cedula);
	}

	@Command
	@NotifyChange({ "cedula", "programa", "nombre", "apellido",
			"lapsoAcademico", "tipoSancion", "periodoInicial", "periodoFinal",
			"materia", "numeroCaso", "fecha" })
	public void mostrarHistoricoEstudiante() {
		cedula = getHistoricoEstudiante().getCedula();
		programa = getHistoricoEstudiante().getPrograma();
		nombre = getHistoricoEstudiante().getNombre();
		apellido = getHistoricoEstudiante().getApellido();
		lapsoAcademico = getHistoricoEstudiante().getLapso_academico();
		tipoSancion = getHistoricoEstudiante().getTipo_sancion();
		periodoInicial = getHistoricoEstudiante().getPeriodo_inicial()
				.substring(0, 6);
		periodoFinal = getHistoricoEstudiante().getPeriodo_final().substring(7,
				13);
		materia = getHistoricoEstudiante().getMateria();
		numeroCaso = getHistoricoEstudiante().getNumeroCaso();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		fecha = formateador.format(getHistoricoEstudiante().getFecha());
	}
}
