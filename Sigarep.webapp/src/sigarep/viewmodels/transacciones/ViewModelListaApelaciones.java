package sigarep.viewmodels.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelArray;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.servicio.maestros.*;
import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelListaApelaciones {
	@WireVariable
	private String nombreTipoMotivo;
	@WireVariable
	private String nombrePrograma;
	
	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioProgramaAcademico servicioprogramaacademico;
	private List<ProgramaAcademico> listaPrograma;
	private List<TipoMotivo> listaTipoMotivo;
	private List<ApelacionMomento> listadoApelaciones;
	private ServicioApelacionMomento servicioapelacionmomento;
	public List<TipoMotivo> getListaTipoMotivo() {
			return listaTipoMotivo;
		}
	  
	public List<ProgramaAcademico> getListaPrograma() {
			return listaPrograma;
		}
	public List<ApelacionMomento> getListadoApelaciones() {
		return listadoApelaciones;
	}

	public void setListadoApelaciones(List<ApelacionMomento> listadoApelaciones) {
		this.listadoApelaciones = listadoApelaciones;
	}

	public void setListaTipoMotivo(List<TipoMotivo> ListaTipoMotivo) {
		this.listaTipoMotivo = ListaTipoMotivo;
	}
	
	public void setListaPrograma(List<ProgramaAcademico> listaPrograma) {
		this.listaPrograma = listaPrograma;
	}
   
	 @Init
	    public void init(){
	    	 //initialization code
	    	buscarTipoMotivo();
	    	buscarProgramaA ();
	    	
	    }
	    //Metodo que busca un motivo partiendo por su titulo
	  	@Command
	  	@NotifyChange({"listaTipoMotivo"})
	  	public void buscarTipoMotivo(){
	  		listaTipoMotivo = serviciotipomotivo.buscarP(nombreTipoMotivo);
	  	}
	  	
	  	@Command
		@NotifyChange({ "listaPrograma" })
		public void buscarProgramaA() {
			listaPrograma = servicioprogramaacademico.buscarPr(nombrePrograma);
		}
	  	@Command
		@NotifyChange({"listadoApelaciones"})
		public void buscarApelaciones(){
			listadoApelaciones =servicioapelacionmomento.listadoApelaciones();
		}
}