package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

import sigarep.modelos.repositorio.maestros.IEstudianteDAO;




// El servicio interactua con la base de datos

@Service("servicioestudiante")
// Definiendo la variable servicio
public class ServicioEstudiante {
	private @Autowired IEstudianteDAO est;

	/**
	 *Guarda un estudiante
	 * @param Estudiante estudiante
	 * @return objeto guardado
	 * @throws No  dispara ninguna excepcion.
	 */
	public void guardarEstudiante(Estudiante estudiante) {
		
		est.save(estudiante);
	}

	public void actualizar(Estudiante estudiante) {
	
	}

	/**
	 *Elimina logicamente un estudiante
	 * @throws No  dispara ninguna excepcion.
	 */
	public void eliminarEstudiante(String estudiante) {
		est.delete(estudiante);
	}

	public Estudiante buscarEstudiante(String cedula) {
		return est.findOne(cedula);
	}

	/**
	 *Lista de estudiante
	 * @param 
	 * @return lista de estudiantes
	 * @throws No  dispara ninguna excepcion.
	 */
	public List<Estudiante> listadoEstudiantes() {
		List<Estudiante> estudiantesLista = est.findAll();
		return estudiantesLista;
	}

	/**
	 *buscar estudiante por su cedula
	 * @param String cedula
	 * @return busca un estudiante en una lista
	 * @throws No  dispara ninguna excepcion.
	 */
	public List<Estudiante> buscarEst(String cedula) {
		List<Estudiante> result = new LinkedList<Estudiante>();
		if (cedula == null || "".equals(cedula)) {
			result = listadoEstudiantes();
		} else {
			for (Estudiante est : listadoEstudiantes()) {
				if (est.getCedulaEstudiante().toLowerCase()
						.contains(cedula.toLowerCase())) {
					result.add(est);
				}
			}
		}
		return result;
	}
	/** Lista de Estudiante
	 * @return Lista de Estudiantes registrados
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<Estudiante> buscarEstudiante() {
		return est.buscarEstudiante();
	}
	
	
	/** Filtra Estudiante para ListaGenerica 
	 * @return Lista de EstudianteS
	 * @parameters String cedula, String programa, String nombre, String apellido
	 * @throws No dispara ninguna excepcion.
	   */
	public List<Estudiante> filtrarEstudiantesHistorial(
			String programa, String cedula, String nombre, String apellido) {
		
		List<Estudiante> result = new ArrayList<Estudiante>();
        if(programa==null || cedula==null || nombre==null || apellido==null ){
        	result= buscarEstudiante();
        }
        else{
			for (Estudiante sa : buscarEstudiante())
			{
				if (sa.getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	}
	
}
