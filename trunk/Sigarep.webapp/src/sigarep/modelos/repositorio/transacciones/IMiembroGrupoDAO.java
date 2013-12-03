package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.MiembroGrupo;
import sigarep.modelos.data.transacciones.MiembroGrupoPK;

public interface IMiembroGrupoDAO extends
		JpaRepository<MiembroGrupo, MiembroGrupoPK> {

}
