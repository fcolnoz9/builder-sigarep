package sigarep.viewmodels.transacciones;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.mensajes;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.servicio.maestros.ServicioActividad;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.transacciones.ServicioCronograma;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCronograma {

	@WireVariable ServicioCronograma serviciocronograma;
	private CronogramaPK id;
	private Boolean estatus;
	private Date fechaInicio;
	private Date fechaFin;
	private Time horaInicio;
	private String lugar;
	private String observacion;
	private String codigoLapso;
	private LapsoAcademico lapsoActivo;
	@WireVariable
	private LapsoAcademico lapsoAcademico = new LapsoAcademico();
	@WireVariable
	private Actividad actividad = new Actividad();
	@WireVariable
	private ServicioLapsoAcademico serviciolapsoacademico;
	@WireVariable
	private ServicioActividad servicioactividad;
	
	@Wire
	private Textbox txtLapsoAcademico;
	@Wire
	private Combobox cmbActividad;
	@Wire
	private Datebox dtbFechaInicio;
	@Wire
	private Datebox dtbFechaFin;
	@Wire
	private Textbox txtLugar;
	@Wire
	private Timebox tmbHora;
	@Wire
	private Textbox txtObservacion;
	@Wire Window ventana;
	
	@WireVariable
	private List<Cronograma> listaCronograma;
	@WireVariable
	private List<Actividad> listaActividad;
	@WireVariable
	private Cronograma cronogramaSeleccionado;
	CronogramaPK cronogramaPK = new CronogramaPK();
	Cronograma cronograma = new Cronograma();
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	MensajesAlUsuario mensajesAlUsuario = new MensajesAlUsuario();

	// Metodos GETS Y SETS
    
	public List<Actividad> getListaActividad() {
		return listaActividad;
	}

	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;
	}

	public ServicioCronograma getServiciocronograma() {
		return serviciocronograma;
	}

	public CronogramaPK getId() {
		return id;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public String getLugar() {
		return lugar;
	}

	public String getObservacion() {
		return observacion;
	}

	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public ServicioLapsoAcademico getServiciolapsoacademico() {
		return serviciolapsoacademico;
	}

	public ServicioActividad getServicioactividad() {
		return servicioactividad;
	}

	public List<Cronograma> getListaCronograma() {
		return listaCronograma;
	}
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "listaCronograma"})
	public Cronograma getCronogramaSeleccionado() {
		return cronogramaSeleccionado;
	}
	public void setServiciocronograma(ServicioCronograma serviciocronograma) {
		this.serviciocronograma = serviciocronograma;
	}

	public void setId(CronogramaPK id) {
		this.id = id;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public void setServiciolapsoacademico(ServicioLapsoAcademico serviciolapsoacademico) {
		this.serviciolapsoacademico = serviciolapsoacademico;
	}

	public void setServicioactividad(ServicioActividad servicioactividad) {
		this.servicioactividad = servicioactividad;
	}

	public void setListaCronograma(List<Cronograma> listaCronograma) {
		this.listaCronograma = listaCronograma;
	}

	public void setCronogramaSeleccionado(Cronograma cronogramaSeleccionado) {
		this.cronogramaSeleccionado = cronogramaSeleccionado;
	}

	public String getCodigoLapso() {
		return codigoLapso;
	}

	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}

	public LapsoAcademico getLapsoActivo() {
		return lapsoActivo;
	}

	public void setLapsoActivo(LapsoAcademico lapsoActivo) {
		this.lapsoActivo = lapsoActivo;
	}
    //Fin de los metodod gets y sets
	
    // OTROS METODOS
	@Init
	public void init(){
        //initialization code
		buscarCronograma();
		buscarActividad();
		lapsoActivo = serviciolapsoacademico.encontrarLapsoActivo();
		if (serviciolapsoacademico.encontrarLapsoActivo() == null)
			mensajesAlUsuario.ErrorLapsoActivoNoExistente();
		else
			codigoLapso = lapsoActivo.getCodigoLapso();
    }

		@Command
		@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "listaCronograma", "actividad"})
		public void guardarCronograma() {
			
			if(fechaInicio==null || fechaFin ==null || horaInicio ==null || lugar.equals(""))
				msjs.advertenciaLlenarCampos();
			else {
				cronogramaPK.setIdActividad(actividad.getIdActividad());
				cronogramaPK.setCodigoLapso(codigoLapso);
				
				try {
					serviciocronograma.guardar(new Cronograma(cronogramaPK, true, fechaFin, fechaInicio, horaInicio, lugar, observacion));
	
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				msjs.informacionRegistroCorrecto();
				buscarCronograma();
				limpiar();
			}
		}
	
		@Command
		@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion","actividad",  "listaCronograma" })
	    public void buscarDetalleActividadCombo() {
		boolean check=false;
		for (int i = 0; i < listaCronograma.size(); i++) {
			if(actividad.getIdActividad() == listaCronograma.get(i).getActividad().getIdActividad()){
				fechaInicio=listaCronograma.get(i).getFechaInicio();
			    fechaFin=listaCronograma.get(i).getFechaFin();
			    lugar=listaCronograma.get(i).getLugar();
			    horaInicio=listaCronograma.get(i).getHoraInicio();
			    observacion=listaCronograma.get(i).getObservacion();
			   
			    check=true;
			}
		
		}
		if(!check){
			fechaInicio=null;
			fechaFin=null;
			horaInicio=null;
			lugar="";
			observacion="";
		}
	}
	
	//Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({"listaCronograma"})
	public void buscarCronograma(){
		listaCronograma =serviciocronograma.listadoCronograma();
	}
	
	
	@Command
	@NotifyChange({"listaActividad"})
	public void buscarActividad(){
		listaActividad =servicioactividad.listadoActividad();
	}
	//Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "listaCronograma", "actividad"})
	public void limpiar(){
		// se utiliza la fecha del sistema para colocarla al momento de limpiar
		fechaInicio=null;
		fechaFin=null;
		horaInicio=null;
		lugar="";
		observacion="";
		actividad= new Actividad();
		
	}
	
	//Metodo que elimina una noticia tomando en cuenta el idNoticia
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "listaCronograma"})
	public void eliminar(){
		serviciocronograma.eliminarCronograma(getCronogramaSeleccionado().getId());
		limpiar();
		msjs.informacionEliminarCorrecto();
		buscarCronograma();
		
	}
	
	//permite tomar los datos del objeto noticiaseleccionada
	@Command
	@NotifyChange({"fechaInicio", "fechaFin", "horaInicio", "lugar", "observacion", "listaCronograma", "actividad"})
	public void mostrarSeleccionado(){
		 Cronograma cro = getCronogramaSeleccionado();
		 fechaInicio = cro.getFechaInicio();
		 fechaFin = cro.getFechaFin();
		 horaInicio = cro.getHoraInicio();
		 lugar = cro.getLugar();
		 observacion = cro.getObservacion();
		 actividad=getCronogramaSeleccionado().getActividad();
	}
	
	@Command
	@NotifyChange({"fechaInicio", "fechaFin"})
	public void validarFecha(){
		if (fechaInicio != null && fechaFin != null){
			if (fechaInicio.compareTo(fechaFin) > 0){
				Messagebox.show("Rango de fechas Invalido", "Advertencia", Messagebox.OK, Messagebox.ERROR);
				fechaFin=null;
			}
		}
	}
	
	
//Fin de los otros metodos.
}
