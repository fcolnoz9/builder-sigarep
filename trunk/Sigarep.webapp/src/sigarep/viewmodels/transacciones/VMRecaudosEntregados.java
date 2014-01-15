package sigarep.viewmodels.transacciones;

import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.zkoss.bind.annotation.Command;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zhtml.Messagebox;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
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
import org.zkoss.zul.Window;

import sigarep.herramientas.Documento;
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
import sigarep.modelos.servicio.transacciones.ListaRecaudosMotivoEstudiante;
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
public class VMRecaudosEntregados {
	@Wire("#modalDialog")
	private Window window;
	@WireVariable
	private ServicioApelacion serviciolista;
	@WireVariable
	private LapsoAcademico lapsoAcademico;
	@WireVariable
	private String nombrePrograma;
	@WireVariable
	private String programa;
	@WireVariable
	private String cedula;
	@WireVariable
	private String nombres;
	@WireVariable
	private String apellidos;
	@WireVariable
	private String asignatura;
	@WireVariable
	private String sancion;
	@WireVariable
	private String lapso;
	@WireVariable
	private Integer semestreSancion;
	@WireVariable
	private String selected = "";	
	@WireVariable
	private List<ListaRecaudosMotivoEstudiante> listaRecaudos = new LinkedList<ListaRecaudosMotivoEstudiante>();
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
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}	
	
	public LapsoAcademico getLapsoAcademico() {
		return lapsoAcademico;
	}

	public void setLapsoAcademico(LapsoAcademico lapsoAcademico) {
		this.lapsoAcademico = lapsoAcademico;
	}
	
	public String getSancion() {
		return sancion;
	}

	public void setSancion(String sancion) {
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
	
	public List<ListaRecaudosMotivoEstudiante> getListaRecaudos() {
		return listaRecaudos;
	}

	public void setListaRecaudos(List<ListaRecaudosMotivoEstudiante> listaRecaudos) {
		this.listaRecaudos = listaRecaudos;
	}
	
	public String getLapso() {
		return lapso;
	}

	public void setLapso(String lapso) {
		this.lapso = lapso;
	}

	@Command
	@NotifyChange({"tipoMotivo", "nombreRecaudo","listaRecaudosPorMotivo"})
	public void buscarRecaudosPorTipoMotivo(Integer tipoMotivo){
			listaRecaudosPorMotivo  = serviciorecaudo.listadoRecaudosPorMotivo(tipoMotivo);
	}
	
	@Init
	public void init(

	@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cedula") String v1,
			@ExecutionArgParam("nombre") String v2,
			@ExecutionArgParam("apellido") String v3,
			@ExecutionArgParam("email") String v4,
			@ExecutionArgParam("telefono") String v5,
			@ExecutionArgParam("programa") String v6,
			@ExecutionArgParam("sancion") String v7,
			@ExecutionArgParam("lapso") String v8,
			@ExecutionArgParam("instancia") Integer v9,

			@ExecutionArgParam("segundoNombre") String v11,
			@ExecutionArgParam("segundoApellido") String v12,
			@ExecutionArgParam("asignatura") String v13,
			@ExecutionArgParam("caso") Integer v14)

	// initialization code

	{
		Selectors.wireComponents(view, this, false);
		this.cedula = v1;
		this.nombres = v2 + " " +v11;
		this.apellidos = v3 + " "+v12;
		this.programa = v6;
		this.sancion = v7;
		this.lapso = v8;
		this.asignatura = v13;		
		buscarRecaudos();
	}

	@Command
	public void closeThis() {
		window.detach();
	}
	
	@Command
	@NotifyChange({"nombreRecaudo","nombreTipoMotivo","listaRecaudos" })
	public void buscarRecaudos() {
		listaRecaudos = serviciolista.buscarRecaudosMotivos();
	}
	
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaTipoMotivo" })
	public void buscarTiposMotivo() {
		listaTipoMotivo = serviciotipomotivo.listadoTipoMotivo();
	}

	@Command
	@NotifyChange({ "cedula", "nombres", "apellidos", "estudianteSancionado","lapso"})
	public void registrarRecaudosEntregados(@BindingParam("recaudosEntregados") Set<Listitem> recaudos) {
		if (cedula == null || nombres == null || apellidos == null) {
			msjs.advertenciaLlenarCampos();
		} else if (recaudos.size() == 0) {
			Messagebox.show("Debe seleccionar al menos un recaudo entregado",
					"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			Recaudo recaudo = new Recaudo();
			for(Listitem miRecaudo: recaudos){
				String nombreRecaudo = miRecaudo.getLabel();
				recaudo = serviciorecaudo.buscarRecaudoNombre(nombreRecaudo);
				recaudoEntregadoPK.setIdInstanciaApelada(1);
				recaudoEntregadoPK.setCedulaEstudiante(cedula);
				recaudoEntregadoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				recaudoEntregadoPK.setCodigoLapso(lapso);
				recaudoEntregadoPK.setIdRecaudo(recaudo.getIdRecaudo());
				RecaudoEntregado recaudoEntregadoAux = new RecaudoEntregado();
				recaudoEntregadoAux.setId(recaudoEntregadoPK);
				recaudoEntregadoAux.setEstatus(true);
				MotivoPK motivoPK = new MotivoPK();
				motivoPK.setCedulaEstudiante(cedula);
				motivoPK.setIdTipoMotivo(recaudo.getTipoMotivo().getIdTipoMotivo());
				motivoPK.setCodigoLapso(lapso);
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
	 @NotifyChange({"cedula", "nombres", "apellidos", "listaRecaudosPorMotivo","programa","lapsoAcademico","telefono","sancion","asignatura"})
	public void limpiar() {
		 buscarRecaudos();
	}
	
	@Command
	@NotifyChange({"listaTipoMotivo","tipoMotivo","listaRecaudosPorMotivo"})
	public TipoMotivo objetoTipoMotivo() {
		System.out.println("tipo de motivo:"+tipoMotivo.getIdTipoMotivo());
		buscarRecaudosPorTipoMotivo(tipoMotivo.getIdTipoMotivo());
		return tipoMotivo;
	}
}