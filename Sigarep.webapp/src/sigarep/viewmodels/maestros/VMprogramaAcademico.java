package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;

/**
 * ProgramaAcademico UCLA DCYT Sistemas de Informacion.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMprogramaAcademico {
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;

	private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private Integer idPrograma;
	private String nombrePrograma;
	private String nombreProgramaFiltro = "";
	private Boolean estatus;
	private List<ProgramaAcademico> listaPrograma;
	private ProgramaAcademico programaseleccionado;

	// Inicio Métodos Sets y Gets
	public Integer getIdProgramaAcademico() {
		return idPrograma;
	}

	public void setIdProgramaAcademico(Integer idProgramaAcademico) {
		this.idPrograma = idProgramaAcademico;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String programa) {
		this.nombrePrograma = programa;
	}

	public String getNombreProgramaFiltro() {
		return nombreProgramaFiltro;
	}

	public void setNombreProgramaFiltro(String programa) {
		this.nombreProgramaFiltro = programa;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public ProgramaAcademico getProgramaseleccionado() {
		return programaseleccionado;
	}

	public void setProgramaseleccionado(ProgramaAcademico programaseleccionado) {
		this.programaseleccionado = programaseleccionado;
	}

	// Fin Métodos Sets y Gets

	@Init
	public void init() {
		buscarProgramaA();
	}

	/**
	 * guardarPrograma
	 * 
	 * @param idPrograma
	 *            , nombrePrograma, estatus, listaPrograma
	 * @return No devuelve ningun valor
	 * @throws No
	 *             debe haber campos en blanco
	 */
	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus", "listaPrograma" })
	public void guardarPrograma() {
		if (nombrePrograma == null || nombrePrograma.equals("")) {
			mensajeAlUsuario.advertenciaLlenarCampos();
		} else {
			ProgramaAcademico proa = new ProgramaAcademico(idPrograma,
					nombrePrograma, true);
			servicioprogramaacademico.guardarPrograma(proa);
			mensajeAlUsuario.informacionRegistroCorrecto();
			limpiar();
		}
	}

	/**
	 * limpiar
	 * 
	 * @param idPrograma
	 *            , nombrePrograma, estatus, listaPrograma
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus", "listaPrograma",
			"nombreProgramaFiltro" })
	public void limpiar() {
		idPrograma = null;
		nombrePrograma = "";
		nombreProgramaFiltro = "";
		buscarProgramaA();
	}

	/**
	 * buscarProgramaA
	 * 
	 * @param listaPrograma
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.listadoProgramas();
	}

	/**
	 * eliminarPrograma
	 * 
	 * @param nombrePrograma
	 *            , listaPrograma
	 * @return No devuelve ningun valor
	 * @throws Debe
	 *             seleccionar un registro para poder eliminarlo
	 */
	@Command
	@NotifyChange({ "listaPrograma", "nombrePrograma" })
	public void eliminarPrograma() {
		if (nombrePrograma == null || nombrePrograma.equals("")) {
			mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
		} else {
			servicioprogramaacademico
					.eliminarPrograma(getProgramaseleccionado().getIdPrograma());
			mensajeAlUsuario.informacionEliminarCorrecto();
			limpiar();
		}
	}

	/**
	 * mostrarSeleccionado
	 * 
	 * @param nombrePrograma
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "nombrePrograma" })
	public void mostrarSeleccionado() {
		idPrograma = getProgramaseleccionado().getIdPrograma();
		nombrePrograma = getProgramaseleccionado().getNombrePrograma();
	}

	/**
	 * filtros
	 * 
	 * @param listaPrograma
	 *            , nombreProgramaFiltro
	 * @return No devuelve ningun valor
	 * @throws No
	 *             dispara ninguna excepción
	 */
	@Command
	@NotifyChange({ "listaPrograma", "nombreProgramaFiltro" })
	public void filtros() {
		listaPrograma = servicioprogramaacademico
				.buscarPrograma(nombreProgramaFiltro);
	}
}
