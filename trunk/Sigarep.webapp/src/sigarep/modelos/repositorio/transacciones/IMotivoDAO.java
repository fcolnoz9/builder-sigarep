package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

public interface IMotivoDAO extends JpaRepository<Motivo, MotivoPK> {

	public List<Motivo> findBySolicitudApelacion(SolicitudApelacion solicitudApelacion);
}
