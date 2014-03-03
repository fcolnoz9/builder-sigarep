package sigarep.modelos.servicio.transacciones;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.TipoMotivo;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;

import sigarep.modelos.repositorio.transacciones.IAsignaturaEstudianteSancionadoDAO;
import sigarep.modelos.repositorio.transacciones.IMotivoDAO;


@Service("servicioasignaturaestudiantesancionado")
public class ServicioAsignaturaEstudianteSancionado {
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
}
