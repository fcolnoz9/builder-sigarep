package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMprogramaAcademico {
	@WireVariable
	ServicioProgramaAcademico servicioprogramaacademico;
	private Integer idPrograma;
	private String nombrePrograma;
	private Boolean estatus;
	private List<ProgramaAcademico> listaPrograma;
	private ProgramaAcademico programaseleccionado;

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

	@Init
	public void init() {
		buscarProgramaA();
	}

	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus", "listaPrograma" })
	public void guardarPrograma() {
		if (nombrePrograma.equals(""))
			Messagebox.show("Debe llenar todos los campos", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		else {
			ProgramaAcademico proa = new ProgramaAcademico(idPrograma,
					nombrePrograma, true);
			servicioprogramaacademico.guardarPrograma(proa);
			Messagebox.show("Se ha registrado correctamente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiar();
		}
	}

	@Command
	@NotifyChange({ "idPrograma", "nombrePrograma", "estatus" })
	public void limpiar() {
		nombrePrograma = "";
		buscarProgramaA();
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarProgramaA() {
		listaPrograma = servicioprogramaacademico.buscarPr(nombrePrograma);
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void eliminarPrograma() {
		servicioprogramaacademico.eliminarPrograma(getProgramaseleccionado()
				.getIdPrograma());
		limpiar();
		Messagebox.show("Se ha eliminado correctamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	@Command
	@NotifyChange({ "nombrePrograma" })
	public void mostrarSeleccionado() {
		nombrePrograma = getProgramaseleccionado().getNombrePrograma();
	}

}
