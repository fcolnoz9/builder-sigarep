package sigarep.viewmodels.transacciones;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
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
import sigarep.modelos.data.transacciones.ListaMomento;
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
	private Integer numeroCaso;
	@WireVariable
	private String descripcionMotivo;
	@WireVariable
	private String fecha;
	@WireVariable
	private List<HistoricoEstudiante> listaHistoricoEstudiante;

	private List<ListaMomento> listaMomento;

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

	public Integer getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(Integer numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<HistoricoEstudiante> getListaHistoricoEstudiante() {
		return listaHistoricoEstudiante;
	}

	public void setListaHistoricoEstudiante(
			List<HistoricoEstudiante> listaHistoricoEstudiante) {
		this.listaHistoricoEstudiante = listaHistoricoEstudiante;
	}

	public List<ListaMomento> getListaMomento() {
		return listaMomento;
	}

	public void setListaMomento(List<ListaMomento> listaMomento) {
		this.listaMomento = listaMomento;
	}

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String c) {
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
		listaHistoricoEstudiante = serviciohistoricoestudiante
				.buscarHistoricoEstudiante(cedula);
	}

	@Command
	@NotifyChange({ "listaMomento" })
	public void buscarListaMomentos(@BindingParam("id") Integer id) {
		listaMomento = serviciohistoricoestudiante.buscarListaMomentos(
				this.cedula, id);
	}

	@Command
	@NotifyChange({ "cedula", "programa", "nombre", "apellido",
			"lapsoAcademico", "tipoSancion", "periodoInicial", "periodoFinal",
			"materia", "numeroCaso", "fecha" })
	public void mostrarHistoricoEstudiante() {
		cedula = getListaHistoricoEstudiante().get(0).getCedula();
		programa = getListaHistoricoEstudiante().get(0).getPrograma();
		nombre = getListaHistoricoEstudiante().get(0).getNombre();
		apellido = getListaHistoricoEstudiante().get(0).getApellido();
		lapsoAcademico = getListaHistoricoEstudiante().get(0)
				.getLapso_academico();
		tipoSancion = getListaHistoricoEstudiante().get(0).getTipo_sancion();
		periodoInicial = getListaHistoricoEstudiante().get(0)
				.getPeriodo_inicial().substring(0, 6);
		periodoFinal = getListaHistoricoEstudiante().get(0).getPeriodo_final()
				.substring(7, 13);
		materia = getListaHistoricoEstudiante().get(0).getMateria();
		numeroCaso = getListaHistoricoEstudiante().get(0).getNumeroCaso();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		fecha = formateador.format(getListaHistoricoEstudiante().get(0)
				.getFecha());
	}

}
