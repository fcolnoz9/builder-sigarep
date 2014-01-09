package sigarep.viewmodels.transacciones;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
//import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;
//import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
//import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
//import sigarep.modelos.servicio.transacciones.ServicioApelacionMomento;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewModelWindowRegistrarReconsideracion  {
	
	@Wire("#modalDialog")
    private Window window;
    
	
	private String sancion;
	private String programa;
	private String telefono;
	private String email;
	private String apellido;
	private String nombre;
	private String cedula;
	
	
	

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("cedula") String v1 ,
            @ExecutionArgParam("nombre") String v2,
            @ExecutionArgParam("apellido") String v3,
            @ExecutionArgParam("email") String v4,
            @ExecutionArgParam("telefono") String v5,
            @ExecutionArgParam("programa") String v6,
            @ExecutionArgParam("sancion") String v7
            ) 
	{
        Selectors.wireComponents(view, this, false);
        this.cedula = v1;
        this.nombre = v2;
        this.apellido = v3;
        this.email = v4;
        this.telefono = v5;
        this.programa = v6;
        this.sancion = v7;
        
   } 
    @Command
    public void closeThis() {
        window.detach();
    }
	  }