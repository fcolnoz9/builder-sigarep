package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

public interface IMiembroGrupoDAO extends
		JpaRepository<UsuarioGrupo, UsuarioGrupoPK> {

}
