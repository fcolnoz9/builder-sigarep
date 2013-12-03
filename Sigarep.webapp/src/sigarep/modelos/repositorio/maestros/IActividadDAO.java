package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Actividad;

public interface IActividadDAO extends JpaRepository<Actividad, Integer> {

}
