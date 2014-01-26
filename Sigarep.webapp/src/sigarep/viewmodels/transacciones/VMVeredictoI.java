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
import org.zkoss.zul.Window;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioAsignatura;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioRecaudoEntregado;

import java.text.SimpleDateFormat;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMVeredictoI {
	@Wire("#modalDialog")
	private Window window;
	private String cedula;
	private String sancion;
	private String programa;
	private String email;
	private String primerApellido;
	private String primerNombre;
	private String lapso;
	private Integer instancia;
	private String recaudo;
	private String segundoNombre;
	private String segundoApellido;
	private String nombres;
	private String apellidos;
	private List<AsignaturaEstudianteSancionado> asignaturas;
	private Integer caso;
	private String fechaApelacion;
	private Integer peridoSancion;
	private String lapsosConsecutivos;
	private String asignaturaLapsosConsecutivos="";
	private String labelAsignaturaLapsosConsecutivos;
	private String observacionGeneral;
	private String veredicto;


	@WireVariable
	private ServicioRecaudoEntregado serviciorecaudoentregado;
	@WireVariable
	private ServicioAsignatura servicioAsignatura;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;

	private List<RecaudoEntregado> listaRecaudo; 

	public Integer getCaso() {
		return caso;
	}

	public void setCaso(Integer caso) {
		this.caso = caso;
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

	public String getRecaudo() {
		return recaudo;
	}

	public void setRecaudo(String recaudo) {
		this.recaudo = recaudo;
	}
	
	public Integer getInstancia() {
		return instancia;
	}

	public void setInstancia(Integer instancia) {
		this.instancia = instancia;
	}

	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void concatenacionNombres() {
		nombres = primerNombre + " " + segundoNombre;
	}

	public void concatenacionApellidos() {
		apellidos = primerApellido + " " + segundoApellido;
	}

	@Init
	public void init(
		@ContextParam(ContextType.VIEW) Component view,
		@ExecutionArgParam("sancionadoSeleccionado") SolicitudApelacion sa)
	{
		Selectors.wireComponents(view, this, false);
		this.cedula = sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante();
		this.primerNombre = sa.getEstudianteSancionado().getEstudiante().getPrimerNombre();
		this.primerApellido = sa.getEstudianteSancionado().getEstudiante().getPrimerApellido();
		this.email = sa.getEstudianteSancionado().getEstudiante().getEmail();
		this.programa = sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma();
		this.sancion = sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion();
		this.lapso = sa.getEstudianteSancionado().getLapsoAcademico().getCodigoLapso();
		this.instancia = sa.getInstanciaApelada().getIdInstanciaApelada();
		this.segundoNombre = sa.getEstudianteSancionado().getEstudiante().getSegundoNombre();
		this.segundoApellido = sa.getEstudianteSancionado().getEstudiante().getSegundoApellido();
		this.caso = sa.getNumeroCaso();
		this.lapsosConsecutivos = sa.getEstudianteSancionado().getLapsosAcademicosRp();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		this.fechaApelacion = sdf.format(sa.getFechaSolicitud());
		this.peridoSancion = sa.getEstudianteSancionado().getPeriodoSancion();
		
		concatenacionNombres();
		concatenacionApellidos();
		mostrarDatosDeSancion();

		buscarRecaudosEntregados(cedula);
	}
	
	private void mostrarDatosDeSancion() {
		if (sancion.equalsIgnoreCase("RR")){
			asignaturas = servicioasignaturaestudiantesancionado.buscarAsignaturaDeSancion(cedula, lapso);
			if (asignaturas != null)
				for (int i=0; i<asignaturas.size(); i++)
					asignaturaLapsosConsecutivos += asignaturas.get(i).getAsignatura().getNombreAsignatura() + ", ";
			labelAsignaturaLapsosConsecutivos = "Asignatura(s):";
		}
		else{
			labelAsignaturaLapsosConsecutivos = "Lapsos consecutivos:";
			asignaturaLapsosConsecutivos = lapsosConsecutivos;
		}
	}

	@Command
	@NotifyChange({ "listaRecaudo" })
	public void buscarRecaudosEntregados(String cedula) {
		listaRecaudo = serviciorecaudoentregado.buscarRecaudosEntregadosVeredictoI(cedula);
		listaRecaudo.get(0);
	}

	@Command
	public void closeThis() {
		window.detach();
	}
	
	public String getAsignaturaLapsosConsecutivos() {
		return asignaturaLapsosConsecutivos;
	}

	public void setAsignaturaLapsosConsecutivos(String asignaturaLapsosConsecutivos) {
		this.asignaturaLapsosConsecutivos = asignaturaLapsosConsecutivos;
	}

	public String getLabelAsignaturaLapsosConsecutivos() {
		return labelAsignaturaLapsosConsecutivos;
	}

	public void setLabelAsignaturaLapsosConsecutivos(
			String labelAsignaturaLapsosConsecutivos) {
		this.labelAsignaturaLapsosConsecutivos = labelAsignaturaLapsosConsecutivos;
	}

	public List<RecaudoEntregado> getListaRecaudo() {
		return listaRecaudo;
	}

	public void setListaRecaudo(List<RecaudoEntregado> listaRecaudo) {
		this.listaRecaudo = listaRecaudo;
	}

	public String getFechaApelacion() {
		return fechaApelacion;
	}

	public void setFechaApelacion(String fechaApelacion) {
		this.fechaApelacion = fechaApelacion;
	}

	public Integer getPeridoSancion() {
		return peridoSancion;
	}

	public void setPeridoSancion(Integer peridoSancion) {
		this.peridoSancion = peridoSancion;
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
    

