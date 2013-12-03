package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;

public interface IEstudianteSancionadoDAO extends
		JpaRepository<EstudianteSancionado, EstudianteSancionadoPK> {

}
