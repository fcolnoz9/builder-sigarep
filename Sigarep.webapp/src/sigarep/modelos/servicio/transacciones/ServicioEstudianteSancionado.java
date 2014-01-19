package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IEstudianteSancionadoDAO;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;

@Service("servicioestudiantesancionado")
public class ServicioEstudianteSancionado {
	
	private @Autowired IEstudianteSancionadoDAO iEstudianteSancionadoDAO;
	private @Autowired ISolicitudApelacionDAO iSolicitudApelacion;
	
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
