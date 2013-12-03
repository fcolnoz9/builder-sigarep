package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;

public interface IAsignaturaEstudianteSancionadoDAO
		extends
		JpaRepository<AsignaturaEstudianteSancionado, AsignaturaEstudianteSancionadoPK> {

}
