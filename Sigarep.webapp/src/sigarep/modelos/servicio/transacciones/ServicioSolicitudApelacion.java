package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;
import sigarep.modelos.repositorio.transacciones.IMotivoDAO;

@Service("serviciosolicitudapelacion")
public class ServicioSolicitudApelacion {

	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	private @Autowired IMotivoDAO iMotivoDAO;
	
	public SolicitudApelacion guardar(SolicitudApelacion solicitudapelacion) {
		return iSolicitudApelacionDAO.save(solicitudapelacion);
	}
	
	public List<SolicitudApelacion> buscarSancionadosReconsideracion (){
		return iSolicitudApelacionDAO.buscarSancionadosReconsideracion();
	}
	
	public SolicitudApelacion buscarSolicitudPorID(SolicitudApelacionPK id) {
		return iSolicitudApelacionDAO.findOne(id);
	}
	
	public EstudianteSancionado buscarEstudianteSancionadoxSolicitud(String cedulaEstudiante){
		return iSolicitudApelacionDAO.buscarSancionado(cedulaEstudiante);
	}
	
	public void eliminar(SolicitudApelacionPK id){
		SolicitudApelacion solicitudApelacion = iSolicitudApelacionDAO.findOne(id);
		solicitudApelacion.setEstatus(false);
		iSolicitudApelacionDAO.save(solicitudApelacion);
	}
	
	public List<SolicitudApelacion> buscarTodos() {
		return iSolicitudApelacionDAO.findAll();
	}

	public int contarTodos() {
		return iSolicitudApelacionDAO.findAll().size();
	}
	
	public long contarApelacionesSinVeredicto(){
		return iSolicitudApelacionDAO.numeroApleacionesSinVeredicto();
	}
	
	public long contarApelacionesSinSesion(){
		return iSolicitudApelacionDAO.numeroApleacionesSinSesion();
	}

	public SolicitudApelacion crear() {
		return new SolicitudApelacion();
	}
	
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosI(){
		return iSolicitudApelacionDAO.buscarApelacionesVerificarRecaudosI();
	}
	
	public List<String> historicoSolicitudApelacion(LapsoAcademico lapso){
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		String codigolapsoAcademico = lapso.getCodigoLapso();
		List<SolicitudApelacion> solicitudApelaciones=iSolicitudApelacionDAO.buscarPorLapso(codigolapsoAcademico);
		
	    for(int i=0;i<solicitudApelaciones.size();i++){
	    	SolicitudApelacion solicitudApelacion = solicitudApelaciones.get(i);
	    	elementoAInsertar="INSERT INTO historico_solicitud_apelacion(cedula_estudiante, codigo_lapso, id_instancia_apelada, estatus, fecha_solicitud, numero_caso, fecha_sesion, codigo_sesion, observacion, tipo_sesion, veredicto, analizado, verificado)" +"VALUES ('"+solicitudApelacion.getId().getCedulaEstudiante()+"','"+solicitudApelacion.getId().getCodigoLapso()+"',"+solicitudApelacion.getInstanciaApelada().getIdInstanciaApelada()+",'"+solicitudApelacion.getEstatus()+"','"+solicitudApelacion.getFechaSolicitud()+"',"+solicitudApelacion.getNumeroCaso()+", '"+solicitudApelacion.getFechaSesion()+"','"+solicitudApelacion.getNumeroSesion()+"','"+solicitudApelacion.getObservacion()+"',"+solicitudApelacion.getTipoSesion()+",'"+solicitudApelacion.getVeredicto()+"', '"+solicitudApelacion.isAnalizado()+"', '"+solicitudApelacion.isVerificado()+"' );";
	    	listaElementosAInsertar.add(elementoAInsertar);
	    	Set<ApelacionEstadoApelacion> apelacionesEstadoApelacion = solicitudApelacion.getApelacionEstadosApelacion();
	    	for(Iterator<ApelacionEstadoApelacion> it = apelacionesEstadoApelacion.iterator();it.hasNext();){
	    		ApelacionEstadoApelacion apelacionEstadoApelacion=it.next();
	    		elementoAInsertar="INSERT INTO historico_apelacion_estado_apelacion(cedula_estudiante, codigo_lapso, id_instancia_apelada, id_estado_apelacion, fecha_estado)"+"VALUES ('"+apelacionEstadoApelacion.getId().getCedulaEstudiante()+"','"+apelacionEstadoApelacion.getId().getCodigoLapso()+"',"+apelacionEstadoApelacion.getId().getIdInstanciaApelada()+", "+apelacionEstadoApelacion.getEstadoApelacion().getIdEstadoApelacion()+",'"+apelacionEstadoApelacion.getFechaEstado()+"');";
	    		listaElementosAInsertar.add(elementoAInsertar);
	    	}
	    	List<Motivo> motivos = iMotivoDAO.findBySolicitudApelacion(solicitudApelacion);
	    	for(int j=0; j<motivos.size();j++){
	    		Motivo motivo = motivos.get(j);
	    		elementoAInsertar="INSERT INTO historico_motivo(cedula_estudiante, codigo_lapso, id_instancia_apelada, id_tipo_motivo, descripcion, estatus)"+"VALUES ('"+motivo.getId().getCedulaEstudiante()+"','"+motivo.getId().getCodigoLapso()+"',"+motivo.getId().getIdInstanciaApelada()+", "+motivo.getTipoMotivo().getIdTipoMotivo()+",'"+motivo.getDescripcion()+"','"+motivo.getEstatus()+"');";
	    		listaElementosAInsertar.add(elementoAInsertar);
	    	}
	    	iSolicitudApelacionDAO.delete(solicitudApelacion);
	    }
	    return listaElementosAInsertar;
	}
	
	public List<SolicitudApelacion> filtrarApelacionesReconsideracion(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosReconsideracion();
        }
        else{
			for (SolicitudApelacion sa : buscarSancionadosReconsideracion())
			{
				if (sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	} 
	
//Maria Flores
	public List<SolicitudApelacion> buscarSancionadosRecursoJerarquico(){
		return iSolicitudApelacionDAO.buscarSancionadosRecursoJerarquico();
	}
	
	public List<SolicitudApelacion> filtrarApelacionesRecurso(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosReconsideracion();
        }
        else{
			for (SolicitudApelacion sa : buscarSancionadosRecursoJerarquico())
			{
				if (sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	} 
}
