package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;

public interface IEstudianteSancionadoDAO extends JpaRepository<EstudianteSancionado, EstudianteSancionadoPK> {
			
	@Query("Select esa FROM EstudianteSancionado AS esa where estatus = TRUE")		
	public List<EstudianteSancionado> buscarSancionadosActivos();
}
