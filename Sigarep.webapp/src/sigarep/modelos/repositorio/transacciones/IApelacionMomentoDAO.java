package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.ApelacionMomentoPK;

public interface IApelacionMomentoDAO extends
		JpaRepository<ApelacionMomento, ApelacionMomentoPK> {

}
