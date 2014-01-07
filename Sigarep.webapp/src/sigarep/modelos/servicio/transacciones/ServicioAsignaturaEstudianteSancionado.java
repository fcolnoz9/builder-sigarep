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
import sigarep.modelos.data.maestros.TipoMotivoFiltros;
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
	
	public Asignatura buscarAsignaturaPorEstudianteSancionado(String cedulaEstudiante, String codigoLapso){
		return iAsignaturaEstudianteSancionadoDAO.buscarAsignaturaEstudianteSancionado(cedulaEstudiante,codigoLapso);
	}
}
