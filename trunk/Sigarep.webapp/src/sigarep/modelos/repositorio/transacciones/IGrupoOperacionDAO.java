package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.GrupoOperacion;
import sigarep.modelos.data.transacciones.GrupoOperacionPK;

public interface IGrupoOperacionDAO extends
		JpaRepository<GrupoOperacion, GrupoOperacionPK> {

}
