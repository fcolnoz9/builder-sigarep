package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IEstudianteSancionadoDAO;

@Service("servicioestudiantesancionado")
public class ServicioEstudianteSancionado {
	
	private @Autowired IEstudianteSancionadoDAO iEstudianteSancionadoDAO;
	
	public EstudianteSancionado guardar(EstudianteSancionado estudianteSancionado) {
		return iEstudianteSancionadoDAO.save(estudianteSancionado);
	}
	
	public void eliminar(EstudianteSancionadoPK id){
		EstudianteSancionado miEstudianteSancionado = iEstudianteSancionadoDAO.findOne(id);
		miEstudianteSancionado.setEstatus(false);
		iEstudianteSancionadoDAO.save(miEstudianteSancionado);
	}
	
	public List<EstudianteSancionado> buscarTodos() {
		return iEstudianteSancionadoDAO.buscarSancionadosActivos();
	}
	
	public List<EstudianteSancionado> buscarSancionados() {
		return iEstudianteSancionadoDAO.buscarSancionados();
	}
	
	
	public EstudianteSancionado buscar(EstudianteSancionadoPK id) {
		return iEstudianteSancionadoDAO.findOne(id);
	}

	public int contarTodos() {
		return iEstudianteSancionadoDAO.findAll().size();
	}

	public EstudianteSancionado crear() {
		return new EstudianteSancionado();
	}
	
	public List<EstudianteSancionado> listadoEstudianteSancionado() {
		List<EstudianteSancionado> estudiantesancionadoLista = iEstudianteSancionadoDAO.buscarSancionadosActivos();
	    return estudiantesancionadoLista ;
	}
	
	
	public List<EstudianteSancionado> buscarEstudianteSancionadofiltros(String cedula, String nombre, String apellido, String sancion) {
		List<EstudianteSancionado> resultado = new LinkedList<EstudianteSancionado>();
		
		if (cedula == null || nombre == null || apellido == null|| sancion== null) {
			resultado = listadoEstudianteSancionado();
		} else {
			for (EstudianteSancionado inst : listadoEstudianteSancionado()) {
				if (inst.getId().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())
						&& inst.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())
						&& inst.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())
						&& inst.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}

//Maria Flores
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquico(){
		return iEstudianteSancionadoDAO.buscarSancionadosRecursoJerarquico();
	}
	
	public List<EstudianteSancionado> filtrarApelacionesRecursoJerarquico(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<EstudianteSancionado> result = new ArrayList<EstudianteSancionado>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosRecursoJerarquico();
        }
        else{
			for (EstudianteSancionado sa : buscarSancionadosRecursoJerarquico())
			{
				if (sa.getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	}

	public List<EstudianteSancionado> filtrarDatosIniciales(String programa, String cedula, String nombre, String apellido, String sancion ) {
		List<EstudianteSancionado> resultado = new LinkedList<EstudianteSancionado>();
		
		if (programa == null || cedula == null || nombre == null || apellido == null|| sancion== null) {
			resultado = buscarSancionados();
		} else {
			for (EstudianteSancionado inst : listadoEstudianteSancionado()) {
				if (inst.getEstudiante().getProgramaAcademico().getNombrePrograma().toLowerCase().contains(programa.toLowerCase())
						&& inst.getId().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())
						&& inst.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())
						&& inst.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())
						&& inst.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}
	
	public List<EstudianteSancionado> buscarApelacion(String cedula){
		return iEstudianteSancionadoDAO.buscarApelacion(cedula);
	}

	public List<EstudianteSancionado> buscarSancionadosReconsideracion(){
		return iEstudianteSancionadoDAO.buscarSancionadosReconsideracion();
	}

	public List<EstudianteSancionado> filtrarApelacionesReconsideracion(
			String programa, String cedula, String nombre, String apellido,
			String sancion) {
		
		List<EstudianteSancionado> result = new ArrayList<EstudianteSancionado>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosReconsideracion();
        }
        else{
			for (EstudianteSancionado sa : buscarSancionadosReconsideracion())
			{
				if (sa.getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	}
}
