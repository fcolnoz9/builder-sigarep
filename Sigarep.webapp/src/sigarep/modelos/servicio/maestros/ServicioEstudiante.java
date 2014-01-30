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

	public void guardarEstudiante(Estudiante estudiante) {
		
		est.save(estudiante);
	}

	public void actualizar(Estudiante estudiante) {
	
	}

	public void eliminarEstudiante(String estudiante) {
		est.delete(estudiante);
	}

	public Estudiante buscarEstudiante(String cedula) {
		return est.findOne(cedula);
	}

	public List<Estudiante> listadoEstudiantes() {
		List<Estudiante> estudiantesLista = est.findAll();
		return estudiantesLista;
	}

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
