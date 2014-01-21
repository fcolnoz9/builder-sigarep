package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
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
	
	public EstudianteSancionado buscar(EstudianteSancionadoPK id) {
		return iEstudianteSancionadoDAO.findOne(id);
	}

	public int contarTodos() {
		return iEstudianteSancionadoDAO.findAll().size();
	}

	public EstudianteSancionado crear() {
		return new EstudianteSancionado();
	}
}
