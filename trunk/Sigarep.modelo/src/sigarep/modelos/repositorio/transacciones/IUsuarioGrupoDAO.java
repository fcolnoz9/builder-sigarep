package sigarep.modelos.repositorio.transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

/**
 * Repositorio IUsuarioGrupoDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface IUsuarioGrupoDAO extends
		JpaRepository<UsuarioGrupo, UsuarioGrupoPK> {
}
