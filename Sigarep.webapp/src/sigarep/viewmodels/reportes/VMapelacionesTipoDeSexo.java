package sigarep.viewmodels.reportes;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.PieModel;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.reportes.ApelacionesPorSexo;
import sigarep.modelos.data.reportes.ChartDataTipoDeSexo;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.servicio.reportes.ServicioApelacionesPorSexo;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMapelacionesTipoDeSexo {

	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioSancionMaestro serviciosancionmaestro;
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioApelacionesPorSexo servicioapelacionesporsexo;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private String nombreSancion;
	@WireVariable
	private String codigoLapso;

	PieModel model;

	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<SancionMaestro> listaTipoSancion;
	private List<LapsoAcademico> listaLapsoInicial;
	private List<LapsoAcademico> listaLapsoFinal;
	public String programa = "Informatica";

	private List<ApelacionesPorSexo> lista = new LinkedList<ApelacionesPorSexo>();
	
	@Init
	public void init() {
		buscarTipoMotivo();
		buscarPrograma();
		buscarTipoSancion();
		buscarLapsoInicial();
		buscarLapsoFinal();
		//buscarApelacionesR();
		// prepare chart data
		model = ChartDataTipoDeSexo.getModel();
		

	}
	
	@Command
	@NotifyChange({"lista"})
	public void buscarApelacionesR(){
	  			lista = servicioapelacionesporsexo.buscarPorPrograma(programa);
	  			//model = ChartDataTipoDeSexo.getModel(lista);
	  			
	}

	@Command
	@NotifyChange({ "listaPrograma" })
	public void buscarPrograma() {
		setListaPrograma(servicioprogramaacademico.buscarPrograma(nombrePrograma));
	}

	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTipoMotivo() {
		setListaTipoMotivo(serviciotipomotivo.buscarP(nombreTipoMotivo));
	}

	@Command
	@NotifyChange({ "listaTipoSancion" })
	public void buscarTipoSancion() {
		setListaTipoSancion(serviciosancionmaestro.listaTipoSanciones());
	}

	@Command
	@NotifyChange({ "listaLapsoInicial" })
	public void buscarLapsoInicial() {
		setListaLapsoInicial(serviciolapsoacademico.buscarLapsoAcademico(codigoLapso));
	}

	@Command
	@NotifyChange({ "listaLapsoFinal" })
	public void buscarLapsoFinal() {
		setListaLapsoFinal(serviciolapsoacademico.buscarLapsoAcademico(codigoLapso));
	}

	public PieModel getModel() {
		return model;
	}

	@GlobalCommand("dataChanged")
	@NotifyChange("model")
	public void onDataChanged(@BindingParam("category") String category,
			@BindingParam("num") Number num) {
		model.setValue(category, num);
	}

	public List<ProgramaAcademico> getListaPrograma() {
		return listaPrograma;
	}

	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public List<SancionMaestro> getListaTipoSancion() {
		return listaTipoSancion;
	}

	public void setListaTipoSancion(List<SancionMaestro> listaTipoSancion) {
		this.listaTipoSancion = listaTipoSancion;
	}

	public List<LapsoAcademico> getListaLapsoInicial() {
		return listaLapsoInicial;
	}

	public void setListaLapsoInicial(List<LapsoAcademico> listaLapsoInicial) {
		this.listaLapsoInicial = listaLapsoInicial;
	}

	public List<LapsoAcademico> getListaLapsoFinal() {
		return listaLapsoFinal;
	}

	public void setListaLapsoFinal(List<LapsoAcademico> listaLapsoFinal) {
		this.listaLapsoFinal = listaLapsoFinal;
	}

	public List<ApelacionesPorSexo> getLista() {
		return lista;
	}

	public void setLista(List<ApelacionesPorSexo> lista) {
		this.lista = lista;
	}
}
