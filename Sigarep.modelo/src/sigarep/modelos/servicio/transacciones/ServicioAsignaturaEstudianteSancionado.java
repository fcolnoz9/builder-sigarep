package sigarep.modelos.servicio.transacciones;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.repositorio.transacciones.IAsignaturaEstudianteSancionadoDAO;


/**
 * Clase ServicioAsignaturaEstudianteSancionado : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla AsignaturaEstudianteSancionado 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("servicioasignaturaestudiantesancionado")
public class ServicioAsignaturaEstudianteSancionado {
	
	// Atributos de la clase
	@PersistenceContext
	private EntityManager em;
	private @Autowired IAsignaturaEstudianteSancionadoDAO iAsignaturaEstudianteSancionadoDAO;
	
	/**
	 * Guarda la asignatura por la cual un estudiante es sancionado
	 * @param asignaturaEstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado){
		iAsignaturaEstudianteSancionadoDAO.save(asignaturaEstudianteSancionado);
	}
	
	/**Busca las asignaturas por las que los estudiantes son sancionados
	 * @param cedula, lapso
	 * @return List<AsignaturaEstudianteSancionado> Lista de asignaturas por estudiante sancionado en un lapso académico
	 * @throws No dispara ninguna excepción.
	 */
	public List<AsignaturaEstudianteSancionado> buscarAsignaturaDeSancion(String cedula, String lapso){
		return iAsignaturaEstudianteSancionadoDAO.findById_CedulaEstudianteAndId_CodigoLapso(cedula, lapso);
	}
	
	/**
	 * Elimina físicamente la asignatura del estudiante sancionado
	 * @param cedulaEstudiante, codigoAsignatura
	 * @return Elimina físicamente el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminarAsignaturaEstudianteSancionadoFisicamente(String cedulaEstudiante, String codigoAsignatura){
		String queryStatement = "delete from sigarep.asignatura_estudiante_sancionado aes where " +
		"aes.cedula_estudiante = '"+ cedulaEstudiante +"' and aes.codigo_asignatura = '"+ codigoAsignatura +"'";
		Query query = em.createNativeQuery(queryStatement);
		try {
			query.getSingleResult();
		} catch (Exception exp) {
			System.out.println("");
		}
	}
}
