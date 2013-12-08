package sigarep.viewmodels.maestros;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;


@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMlapsoAcademico {
	@WireVariable ServicioLapsoAcademico spp;
	private String codigoLapso;private Date fechaInicio ;private Date fechaCierre;private Boolean estatus;
	private List<LapsoAcademico> listaLapsoAcademico;
	private LapsoAcademico lapsoAcademicoseleccionado;
    @Wire Textbox txtcodigoLapso;
    @Wire Window ventana;
     public String getCodigoLapso() {
		return codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	
	
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	
	
	public List<LapsoAcademico> getLapsoAcademico() {
		return listaLapsoAcademico;
	}

	public void setListaLapsoAcademico(List<LapsoAcademico> listaLapsoAcademico) {
		this.listaLapsoAcademico = listaLapsoAcademico;
	}
	@Command
	@NotifyChange({"codigoLapso", "fechaCierre", "fechaInicio","estatus"})
	public LapsoAcademico getLapsoAcademicoseleccionado() {
		return lapsoAcademicoseleccionado;
	}
	public void setLapsoAcademicoseleccionado(LapsoAcademico lapsoAcademicoseleccionado) {
		this.lapsoAcademicoseleccionado = lapsoAcademicoseleccionado;
	}
	@Init
	public void init(){
        //initialization code
		buscarLapsoAcademico();
    }
	@Command
	@NotifyChange({"codigoLapso", "fechaInicio", "fechaCierre"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es se va a colocar en blanco al guardar!!
	public void guardar(){
		if (codigoLapso.equals("")||fechaInicio.equals("")|| fechaCierre.equals(""))
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		LapsoAcademico pro = new LapsoAcademico(codigoLapso,fechaInicio,fechaCierre,estatus);
		spp.guardar(pro);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
	@Command
	@NotifyChange({"codigoLapso", "fechaInicio", "fechaCierre","listaProfesor"})
	public void limpiar(){
		codigoLapso = "";//fechaInicio="";fechaCierre="";
		buscarLapsoAcademico();
	}
	@Command
	@NotifyChange({"listaLapsoAcademico"})
	public void buscarLapsoAcademico(){
		listaLapsoAcademico =spp.buscarP(codigoLapso);
	}
	@Command
	@NotifyChange({"codigoLapso", "fechaCierre", "fechaInicio"})
	public void mostrarSeleccionado(){
		LapsoAcademico pro =getLapsoAcademicoseleccionado();
		codigoLapso=pro.getCodigoLapso();
		fechaInicio=pro.getFechaInicio();
	    fechaCierre=pro.getFechaCierre();
	}
	
}