package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;

public interface IAsignaturaEstudianteSancionadoDAO
		extends
		JpaRepository<AsignaturaEstudianteSancionado, AsignaturaEstudianteSancionadoPK> {

		@Query("Select asig FROM AsignaturaEstudianteSancionado AS asigEstudiante, Asignatura AS asig WHERE asig.codigoAsignatura = asigEstudiante.id.codigoAsignatura AND asigEstudiante.id.cedulaEstudiante = :cedulaEstudiante AND asigEstudiante.id.codigoLapso =:codigoLapso")
		public Asignatura buscarAsignaturaEstudianteSancionado(@Param("cedulaEstudiante") String cedulaEstudiante, @Param("codigoLapso") String codigoLapso);
}
