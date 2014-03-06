package sigarep.viewmodels.maestros;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.maestros.ServicioReglamento;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;
import sigarep.modelos.data.maestros.Reglamento;



/**
 * VMrecaudoMotivoPortal por XML UCLA DCYT Sistemas de Información.
 * 
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 2.5.2
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMrecaudoMotivoPortal {
	private Integer idRecaudo;
	private String descripcion;
    private Boolean estatus;
    private String nombreRecaudo;
    private String observacion;
	private TipoMotivo tipoMotivo;//variable que relaciona recaudo con el id de la tabla TipoMotivo
	private Integer idTipoMotivo;
   	private String nombreTipoMotivo;
	private List<Recaudo> listaRecaudos;
	private List<TipoMotivo> listaTipoMotivo;
	@WireVariable 
	private ServicioRecaudo serviciorecaudo;
	@WireVariable 
	ServicioTipoMotivo serviciotipomotivo;
	private Recaudo recaudoSeleccionado;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	private List<Recaudo> listaRecaudosMotivos = new LinkedList<Recaudo>();
	private List<Reglamento> listaRecaudosMotivosPortal;
	@WireVariable
	private ServicioReglamento servicioreglamento;
	@Wire("#winMostrarRecaudoMotivoPortal")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	
	// Metodos GETS Y SETS
	public Integer getIdRecaudo() {
		return idRecaudo;
	}
	public void setIdRecaudo(Integer idRecaudo) {
		this.idRecaudo = idRecaudo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public String getNombreRecaudo() {
		return nombreRecaudo;
	}
	public void setNombreRecaudo(String nombreRecaudo) {
		this.nombreRecaudo = nombreRecaudo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public Integer getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Integer idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	public Recaudo getRecaudoSeleccionado() {
		return recaudoSeleccionado;
	}
	public void setRecaudoSeleccionado(Recaudo recaudoSeleccionado) {
		this.recaudoSeleccionado = recaudoSeleccionado;
	}


	public List<Recaudo> getListaRecaudos() {
		return listaRecaudos;
	}
	
	public List<Recaudo> getListaRecaudosMotivos() {
		return listaRecaudosMotivos;
	}
	public void setListaRecaudosMotivos(List<Recaudo> listaRecaudosMotivos) {
		this.listaRecaudosMotivos = listaRecaudosMotivos;
	}
	public void setListaRecaudos(List<Recaudo> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	
	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}
	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}
	
	public List<Reglamento> getListaRecaudosMotivosPortal() {
		return listaRecaudosMotivosPortal;
	}
	public void setListaRecaudosMotivosPortal(
			List<Reglamento> listaRecaudosMotivosPortal) {
		this.listaRecaudosMotivosPortal = listaRecaudosMotivosPortal;
	}
	
	//Fin de los métodos gets y sets
	
	
	
	/**
	 * inicialización
	 * 
	 * @param init
	 * @return código de inicialización
	 * @throws No
	 * dispara ninguna excepcion.
	 */
	@Init
	public void init() {
		// initialization code
		buscarRecaudos();
		buscarTiposMotivo();
		buscarRecaudosMotivosPortal();
    }
		
		
	/**
	 * Buscar Recaudos
	 * 
	 * @param buscar Recaudos, listaRecaudos
	 * @return listaRecaudos
	 * @throws No
	 * dispara ninguna excepcion.
	 */

		@Command
		@NotifyChange({"listaRecaudos"})
		public void buscarRecaudos(){
				listaRecaudos  = serviciorecaudo.listadoRecaudosActivos();
		}
		/**
		 * Buscar Tipo Motivo
		 * 
		 * @param buscarTiposMotivo, listaTipoMotivo
		 * @return listaRecaudos
		 * @throws No
		 * dispara ninguna excepcion.
		 */	
	
		@Command
		@NotifyChange({ "listaTipoMotivo" })
		public void buscarTiposMotivo() {
			listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
		}
		
				
		/**
		 * Objeto ComboMotivo
		 * 
		 * @param objetoComboMotivo
		 * @return Método que busca los tipos de motivos y los carga en el combobox de motivos
		 * @throws No
		 * dispara ninguna excepcion.
		 */	
		@Command
		 @NotifyChange({"listaTipoMotivo"})
		public TipoMotivo objetoComboMotivo() {
			//System.out.println(nombreTipoMotivo);
			return tipoMotivo;
		}
		
		//Combo
		@Command
		@NotifyChange({"descripcion", "nombreRecaudo", "observacion", "tipoMotivo","nombreTipoMotivo","listaRecaudos","listaTipoMotivo","listaRecaudosMotivos"})
		public void buscarRecaudoMotivoCombo() {
			listaRecaudosMotivos = serviciorecaudo.listadoRecaudosPorMotivo(tipoMotivo);
		}

		/**
		 * Buscar Recaudos de un Motivos 
		 * 
		 * @param buscarRecaudosMotivosPortal
		 * @return Metodo que buscar los recaudos que pertenecen a cada motivo de apelacion
		 * @throws No
		 * dispara ninguna excepcion.
		 */
	
		@Command
		 @NotifyChange({ "listaRecaudosMotivosPortal" })
		 public void buscarRecaudosMotivosPortal() {
			listaRecaudosMotivosPortal = servicioreglamento.buscarRecaudosPortal();
			
		 }
		/**
		 * descargar archivo
		 * 
		 * @param descargarArchivo
		 * @return Metodo que descarga un archivo con la lista de recaudos de cada motivo de apelacion
		 * @throws No
		 * dispara ninguna excepcion.
		 */
		
		@Command
		public void descargarArchivo(@ContextParam(ContextType.COMPONENT) Component componente){
			int idDocumento = Integer.parseInt(componente.getAttribute("idReglamento").toString());
			for (int i = 0; i < listaRecaudosMotivosPortal.size(); i++) {
				if (idDocumento == listaRecaudosMotivosPortal.get(i).getIdDocumento())
					Filedownload.save(listaRecaudosMotivosPortal.get(i).getDocumento().getContenidoDocumento(),
									  listaRecaudosMotivosPortal.get(i).getDocumento().getTipoDocumento(),
									  listaRecaudosMotivosPortal.get(i).getDocumento().getNombreDocumento());
			}
		}
		
		
		/**
		 * limpiar
		 * 
		 * @param limpiar
		 * @return Metodo que limpia todos los campos de la pantalla
		 * @throws No
		 * dispara ninguna excepcion.
		 */
		@Command
		@NotifyChange({"idRecaudo", "descripcion", "nombreRecaudo","observacion","nombreTipoMotivo","tipoMotivo","nombreRecaudofiltro","nombreTipoMotivofiltro","listaRecaudos"})
		public void limpiar(){
			idRecaudo = null;
			nombreRecaudo= null; 
			nombreTipoMotivo=null;
			descripcion=null; 
			observacion=null; 
			nombreRecaudo=null;
			tipoMotivo= null;
			buscarRecaudos();		
		}
				
}//Fin VMrecaudoMotivo

