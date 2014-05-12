package sigarep.modelos.repositorio.transacciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;

/**
 * Repositorio IInstanciaMiembroDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface IInstanciaMiembroDAO extends
		JpaRepository<InstanciaMiembro, InstanciaMiembroPK> {
	
	/**
	 * Busca la persona de esa cedula
	 * @param CedulaPersona
	 * @return List<InstanciaMiembro> lista con los datos de la persona
	 */
	public List<InstanciaMiembro> findById_CedulaPersona(String CedulaPersona);
	
	/**
	 * Busca la instancia a la que pertenece
	 * @param idInstanciaApelada
	 * @return List<InstanciaMiembro> lista con la instancia
	 */
	public List<InstanciaMiembro> findById_IdInstanciaApelada(Integer idInstanciaApelada);
}
