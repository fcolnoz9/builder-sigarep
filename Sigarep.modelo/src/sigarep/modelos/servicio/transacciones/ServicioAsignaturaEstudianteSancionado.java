package sigarep.modelos.servicio.transacciones;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.repositorio.transacciones.IAsignaturaEstudianteSancionadoDAO;



@Service("servicioasignaturaestudiantesancionado")
public class ServicioAsignaturaEstudianteSancionado {
	
	@PersistenceContext
	private EntityManager em;
	private @Autowired IAsignaturaEstudianteSancionadoDAO iAsignaturaEstudianteSancionadoDAO;
	
	//metodo que permite Guardar
	public void guardarAsignaturaEstudianteSancionado(AsignaturaEstudianteSancionado asignaturaEstudianteSancionado){
		iAsignaturaEstudianteSancionadoDAO.save(asignaturaEstudianteSancionado);
	}
	/**buscarAsignaturaDeSancion 
	 * @param cedula y lapso
	 * @return resultado es un listado de asignaturas por estudiante sancionado en un lapso academico
	 */
	public List<AsignaturaEstudianteSancionado> buscarAsignaturaDeSancion(String cedula, String lapso){
		return iAsignaturaEstudianteSancionadoDAO.findById_CedulaEstudianteAndId_CodigoLapso(cedula, lapso);
	}
	
	/**
	 * Eliminar fisicamente la asignatura del estudiante sancionado
	 * @param AsignaturaEstudianteSancionadoPK id (codigolapso, cedulaEstudiante, codigoAsignatura) 
	 * @return Elimina fisicamente el objeto
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
