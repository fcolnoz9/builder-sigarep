package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Estudiante;
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
}
