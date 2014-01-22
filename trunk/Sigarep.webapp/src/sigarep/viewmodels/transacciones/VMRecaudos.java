package sigarep.viewmodels.transacciones;

import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zhtml.Messagebox;

import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;

import org.zkoss.zul.Intbox;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Datebox;

import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.servicio.maestros.ServicioEstudiante;
import sigarep.modelos.servicio.transacciones.ServicioApelacion;
import sigarep.modelos.servicio.transacciones.ServicioAsignaturaEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioEstudianteSancionado;
import sigarep.modelos.servicio.transacciones.ServicioMotivo;
import sigarep.modelos.servicio.transacciones.ServicioSolicitudApelacion;
import sigarep.modelos.servicio.maestros.ServicioLapsoAcademico;
import sigarep.modelos.servicio.maestros.ServicioProgramaAcademico;
import sigarep.modelos.servicio.maestros.ServicioRecaudo;
import sigarep.modelos.servicio.maestros.ServicioSancionMaestro;
import sigarep.modelos.servicio.maestros.ServicioTipoMotivo;

//import sigarep.modelos.servicio.maestros.ServicioEstudiante;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRecaudos {

	@WireVariable
	private LapsoAcademico lapsoAcademico;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private ProgramaAcademico programa;
	@WireVariable
	private String cedula;
	@WireVariable
	private String nombres;
	@WireVariable
	private String apellidos;
	@WireVariable
	private Asignatura asignatura;
	@WireVariable
	private SancionMaestro sancion;
	@WireVariable
	private String lapso;
	@WireVariable
	private Integer semestreSancion;

	@WireVariable
	private ServicioTipoMotivo serviciotipomotivo;
	@WireVariable
	private ServicioSolicitudApelacion serviciosolicitudapelacion;
	@WireVariable
	private ServicioAsignaturaEstudianteSancionado servicioasignaturaestudiantesancionado;
	@WireVariable
	private ServicioRecaudo serviciorecaudo;
	@WireVariable
	private ServicioMotivo serviciomotivo;
	@Wire
	private Combobox cmbSancion;
	@Wire
	private Datebox dtbFechaNacimiento;
	@Wire
	private Datebox	dtbAnnoIngreso;

	private List<LapsoAcademico> listaLapso;
	private List<SancionMaestro> listaSancion;
	
	RecaudoEntregado recaudoEntregado = new RecaudoEntregado();
	RecaudoEntregadoPK recaudoEntregadoPK = new RecaudoEntregadoPK();
	EstudianteSancionado estudianteSancionado = new EstudianteSancionado();
	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo

	@WireVariable
	private List<TipoMotivo> listaTipoMotivo;
	@WireVariable
	private TipoMotivo tipoMotivo;
	@WireVariable
	private List<Recaudo> listaRecaudosPorMotivo;
	@WireVariable
	private String telefono;
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<TipoMotivo> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivo> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	

	public ProgramaAcademico getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaAcademico programa) {
		this.programa = programa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}	
	
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	public SancionMaestro getSancion() {
		return sancion;
	}

	public void setSancion(SancionMaestro sancion) {
		this.sancion = sancion;
	}
	
	public Integer getSemestreSancion() {
		return semestreSancion;
	}

	public void setSemestreSancion(Integer semestreSancion) {
		this.semestreSancion = semestreSancion;
	}

	public List<Recaudo> getListaRecaudosPorMotivo() {
		return listaRecaudosPorMotivo;
	}

	public void setListaRecaudosPorMotivo(List<Recaudo> listaRecaudosPorMotivo) {
		this.listaRecaudosPorMotivo = listaRecaudosPorMotivo;
	}

	public List<LapsoAcademico> getListaLapso() {
		return listaLapso;
	}


	public void setListaLapso(List<LapsoAcademico> ListaLapso) {
		this.listaLapso = ListaLapso;
	}

	public List<SancionMaestro> getListaSancion() {
		return listaSancion;
	}

	public void setListaSancion(List<SancionMaestro> listaSancion) {
		this.listaSancion = listaSancion;
	}
	
	@Command
	@NotifyChange({"tipoMotivo", "nombreRecaudo","listaRecaudosPorMotivo"})
	public void buscarRecaudosPorTipoMotivo(Integer tipoMotivo){
			listaRecaudosPorMotivo  = serviciorecaudo.listadoRecaudosPorMotivo(tipoMotivo);
	}
	

	@Init
	public void init() {
		// initialization code
		buscarTiposMotivo();
		buscarRecaudosPorTipoMotivo(1);
	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTiposMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
	}

	@Command
	@NotifyChange({"cedula" ,"nombres" ,"apellidos","estudianteSancionado"})
	public void registrarRecaudosEntregados(@BindingParam("recaudosEntregados") List<Listitem> recaudos) {
		if(cedula==null || nombres==null || apellidos==null){
			msjs.advertenciaLlenarCampos();
		}
		else if(recaudos.size()==0) {
			Messagebox.show("Debe seleccionar al menos un recaudo entregado", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else
		{
			Recaudo recaudo = new Recaudo();
			for(int i=0;i<recaudos.size();i++){
				String nombreRecaudo = recaudos.get(i).getLabel();
				recaudo = serviciorecaudo.buscarRecaudoNombre(nombreRecaudo);
				recaudoEntregadoPK.setIdInstanciaApelada(1);
				recaudoEntregadoPK.setCedulaEstudiante(cedula);
				recaudoEntregadoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				recaudoEntregadoPK.setCodigoLapso(estudianteSancionado.getLapsoAcademico().getCodigoLapso());
				recaudoEntregadoPK.setIdRecaudo(recaudo.getIdRecaudo());
				RecaudoEntregado recaudoEntregadoAux = new RecaudoEntregado();
				recaudoEntregadoAux.setId(recaudoEntregadoPK);
				recaudoEntregadoAux.setEstatus(true);
				MotivoPK motivoPK = new MotivoPK();	
				motivoPK.setCedulaEstudiante(cedula);
				motivoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				motivoPK.setCodigoLapso(estudianteSancionado.getLapsoAcademico().getCodigoLapso());
				motivoPK.setIdInstanciaApelada(1);
				Motivo motivo = new Motivo();
				motivo.setId(motivoPK);
				motivo.setEstatus(true);
				motivo.addRecaudoEntregado(recaudoEntregadoAux);
				serviciomotivo.guardarMotivo(motivo);
			}
			try {
				
				msjs.informacionRegistroCorrecto();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			limpiar();
		}	
}
	 
	@Command
	@NotifyChange({"cedula" ,"nombres" ,"apellidos","telefono","programa","asignatura","lapsoAcademico","sancion","semestreSancion"})
	public void buscarEstudianteSancionado() {
		if (cedula==null)
			Messagebox.show("Debe ingresar una cedula", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		else {
			estudianteSancionado = new EstudianteSancionado();
			try {
				estudianteSancionado = serviciosolicitudapelacion.buscarEstudianteSancionadoxSolicitud(cedula);
				nombres = estudianteSancionado.getEstudiante().getPrimerNombre() + " " +estudianteSancionado.getEstudiante().getSegundoNombre();
				apellidos = estudianteSancionado.getEstudiante().getPrimerApellido() + " "+estudianteSancionado.getEstudiante().getSegundoApellido();
				telefono = estudianteSancionado.getEstudiante().getTelefono();
				programa = estudianteSancionado.getEstudiante().getProgramaAcademico();
				lapsoAcademico = estudianteSancionado.getLapsoAcademico();
				sancion = estudianteSancionado.getSancionMaestro();
				semestreSancion = estudianteSancionado.getSemestre();
//				asignatura = servicioasignaturaestudiantesancionado.buscarAsignaturaPorEstudianteSancionado(cedula, lapsoAcademico.getCodigoLapso());
			} catch (Exception e) {
				Messagebox.show("Estudiante de cedula: "+ cedula+ " no ha realizado alguna solicitud de apelación",
						"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	 @Command
	 @NotifyChange({"cedula", "nombres", "apellidos", "listaRecaudosPorMotivo","programa","lapsoAcademico","telefono","sancion","asignatura"})
	public void limpiar() {
		cedula = "";
		nombres = "";
		apellidos = "";
		telefono="";
		programa=null;
		lapsoAcademico=null;
		sancion=null;
		asignatura=null;
		buscarRecaudosPorTipoMotivo(1);
	}
	
	@Command
	@NotifyChange({"listaTipoMotivo","tipoMotivo","listaRecaudosPorMotivo"})
	public TipoMotivo objetoTipoMotivo() {
		System.out.println("tipo de motivo:"+tipoMotivo.getIdTipoMotivo());
		buscarRecaudosPorTipoMotivo(tipoMotivo.getIdTipoMotivo());
		return tipoMotivo;
	}
}