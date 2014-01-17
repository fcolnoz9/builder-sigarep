package sigarep.viewmodels.transacciones;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.servicio.transacciones.ServicioApelacionEstadoApelacion;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ServicioVeredicto;

import java.sql.Time;
import java.util.Date;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelWindowVeredicto1 {
	
	@WireVariable
	private SolicitudApelacion solicitudapelacion;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
//	private ServicioApelacionEstadoApelacion servicioapelacionestadoapelacion;
	
	
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	SolicitudApelacionPK solicitudApelacionPK = new SolicitudApelacionPK();
	SolicitudApelacion solicitudApelacion;
	ApelacionEstadoApelacionPK apelacionEstadoApelacionPK = new ApelacionEstadoApelacionPK();
	ApelacionEstadoApelacion apelacionEstadoApelacion = new ApelacionEstadoApelacion(); 


	@WireVariable ServicioVeredicto serviciolistaveredicto;
	@Wire("#modalDialog")
    private Window window;
    
	@WireVariable
	private String sancion;
	@WireVariable
	private String programa;
	@WireVariable
	private float indice;
	@WireVariable
	private String apellido;
	@WireVariable
	private String nombre;
	@WireVariable
	private String cedula;
	@WireVariable
	private String veredicto;
	@WireVariable
	private Date fechaSesion;
	@WireVariable
	private String observacion;
	@WireVariable
	private String codigolapso;
	
	@WireVariable
	private String selected ="";
	
	//PARA EL OBJETO SOLICITUDAPELACION
		
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getVeredicto() {
		return veredicto;
	}

	public void setVeredicto(String veredicto) {
		this.veredicto = veredicto;
	}

	public Date getFechaSesion() {
		return fechaSesion;
	}

	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
		
	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public float getIndice() {
		return indice;
	}

	public void setIndice(float indice) {
		this.indice = indice;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	public String getCodigoLapso() {
		return codigolapso;
	}

	public void setCodigoLapso(String codigolapso) {
		this.codigolapso = codigolapso;
	}
	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("sancion") String v1 ,
            @ExecutionArgParam("programa") String v2,
            @ExecutionArgParam("indice") float v3,
            @ExecutionArgParam("apellido") String v4,
            @ExecutionArgParam("nombre") String v5,
            @ExecutionArgParam("cedula") String v6
            ) 
	{
        Selectors.wireComponents(view, this, false);
                
        this.sancion = v1;
        this.programa = v2;
        this.indice = v3;
        this.apellido = v4;
        this.nombre = v5;
        this.cedula = v6;
                
   } 
    @Command
    public void closeThis() {
        window.detach();
    }
    
  //  @Command
	//@NotifyChange({"veredicto", "observacion", "responsable","fecha_veredicto"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	//public void guardar(){
    	//if (veredicto.equals("")||observacion.equals("")|| responsable.equals(""))
			//Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		//else{
		//objetoveredicto = new Profesor(veredicto,observacion,responsable,fecha_veredicto);
		//sa.guardar(objetoveredicto);
		//Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		//limpiar();
	//	}
	//}
    
    @Command
    @NotifyChange({"veredicto","observacion","fecha_veredicto","selected"})
	public void registrarSolicitudApelacion() {
			Date fecha= new Date();
			Time hora= new Time(0);
			
			System.out.println("codigolapso  "+codigolapso);
			if (!selected.equals("")){
				if(getSelected().equals("procedente")){
					solicitudApelacion.setVeredicto(selected);
					System.out.println("veredictoIF"+selected);
				}
				else{
					System.out.println("veredictoELSE: "+selected);
					solicitudApelacion.setVeredicto(selected);
			
				}
			}
			System.out.println("fecha_veredicto "+fecha);
			solicitudApelacion.setFechaSesion(fechaSesion);
			System.out.println("observacion"+observacion);
			solicitudApelacion.setObservacion(observacion);
			System.out.println("cedula "+cedula);
			//solicitudApelacionPK.setCedulaEstudiante(cedula);
						
			try {
								
			/*  this.solicitudApelacionPK.setCedulaEstudiante(cedula);
		        System.out.println("cedula "+cedula);
		        this.solicitudApelacionPK.setCodigoLapso(codigolapso);
		        System.out.println("codigoLapso "+codigolapso);
		        this.solicitudApelacionPK.setIdInstanciaApelada(instancia);
		        System.out.println("instancia "+instancia);
		      */  
		        
		    //    this.solicitudApelacion = serviciosolicitudapelacion.buscarUno(solicitudApelacionPK);
		    //    System.out.println("objeto "+solicitudApelacion);
		
			//	serviciosolicitudapelacion.guardar(solicitudApelacion);
			//	System.out.println("OBJETO "+solicitudApelacion);
				
				
				
		        
		        
				serviciosolicitudapelacion.guardar(solicitudapelacion);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			msjs.informacionRegistroCorrecto();
		
		}
    }
    

