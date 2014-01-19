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
public class VMVeredicto {
	@Wire("#modalDialog")
    private Window window;
	
	@WireVariable
	private SolicitudApelacion solicitudApelacion;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable 
	ServicioVeredicto servicioveredicto;
    
	private String cedula;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String sancion;
	private String programa;
	private String email;
	private String lapso;
	private Integer instancia;
	private String recaudo;
	private String asignatura;
	private Integer caso;
	private float indice;
	
	private String veredicto;
	private Date fechaSesion;
	private String observacion;
	private String radioSelected;
	private String apellidos;
	private String nombres;
	
	mensajes msjs = new mensajes();
		

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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}
	
	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
		    		@ExecutionArgParam("cedula") String v1,
		    		@ExecutionArgParam("primerNombre") String v2,
		    		@ExecutionArgParam("primerApellido") String v3,
		    		@ExecutionArgParam("email") String v4,
		    		@ExecutionArgParam("programa") String v5,
		    		@ExecutionArgParam("sancion") String v6,
		    		@ExecutionArgParam("lapso") String v7,
		    		@ExecutionArgParam("instancia") Integer v8,
		    		@ExecutionArgParam("segundoNombre") String v9,
		    		@ExecutionArgParam("segundoApellido") String v10,
		    		@ExecutionArgParam("caso") Integer v11,
		    		@ExecutionArgParam("indice") float v12){
	
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.primerNombre = v2;
		this.primerApellido = v3;
		this.email = v4;
		this.programa = v5;
		this.sancion = v6;
		this.lapso = v7;
		this.instancia = v8;
		this.segundoNombre = v9;
		this.segundoApellido = v10;
		this.caso = v11;
		
		solicitudApelacion = serviciosolicitudapelacion.buscarSolicitudPorClavePrimaria(new SolicitudApelacionPK(lapso, cedula, instancia));
            
   } 
    @Command
    public void closeThis() {
        window.detach();
    }

	public String getRadioSelected() {
		return radioSelected;
	}

	public void setRadioSelected(String radioSelected) {
		this.radioSelected = radioSelected;
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
    
//    @Command
//    @NotifyChange({"veredicto","observacion","fecha_veredicto","selected"})
//	public void registrarSolicitudApelacion() {
//			Date fecha= new Date();
//			Time hora= new Time(0);
//			
//			System.out.println("codigolapso  "+codigolapso);
//			if (!selected.equals("")){
//				if(getSelected().equals("procedente")){
//					solicitudApelacion.setVeredicto(selected);
//					System.out.println("veredictoIF"+selected);
//				}
//				else{
//					System.out.println("veredictoELSE: "+selected);
//					solicitudApelacion.setVeredicto(selected);
//			
//				}
//			}
//			System.out.println("fecha_veredicto "+fecha);
//			solicitudApelacion.setFechaSesion(fechaSesion);
//			System.out.println("observacion"+observacion);
//			solicitudApelacion.setObservacion(observacion);
//			System.out.println("cedula "+cedula);
//			//solicitudApelacionPK.setCedulaEstudiante(cedula);
//						
//			try {
//								
//			/*  this.solicitudApelacionPK.setCedulaEstudiante(cedula);
//		        System.out.println("cedula "+cedula);
//		        this.solicitudApelacionPK.setCodigoLapso(codigolapso);
//		        System.out.println("codigoLapso "+codigolapso);
//		        this.solicitudApelacionPK.setIdInstanciaApelada(instancia);
//		        System.out.println("instancia "+instancia);
//		      */  
//		        
//		    //    this.solicitudApelacion = serviciosolicitudapelacion.buscarUno(solicitudApelacionPK);
//		    //    System.out.println("objeto "+solicitudApelacion);
//		
//			//	serviciosolicitudapelacion.guardar(solicitudApelacion);
//			//	System.out.println("OBJETO "+solicitudApelacion);
//				
//				
//				
//		        
//		        
//				serviciosolicitudapelacion.guardar(solicitudapelacion);
//			} 
//			catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			msjs.informacionRegistroCorrecto();
//		
//		}
}
    

