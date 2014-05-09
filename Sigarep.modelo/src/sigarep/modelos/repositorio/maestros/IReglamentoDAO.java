package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Reglamento;

/**
 * Repositorio IReglamentoDAO: Repositorio relacionado con el Maestro Reglamento.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */ 
public interface IReglamentoDAO extends JpaRepository<Reglamento, Integer> {
	
	/**
	 * Busca el último id insertado en la tabla Reglamento
	 * 
	 * @return Último id insertado en la tabla Reglamento
	 */
	@Query("SELECT COALESCE(MAX(r.idDocumento),0) FROM Reglamento AS r")
	public int buscarUltimoID();
	
	/**
	 * Busca los Reglamentos activos, es decir, que poseen estatus == true
	 * 
	 * @return List<Reglamento> Lista de Reglamentos con estatus == true
	 */
	public List<Reglamento> findByEstatusTrue();

	/**
	 * Busca los docuemtos activos que pertenecen a una categoría dada
	 * 
	 * @return List<Reglamento> Lista de Documentos pertenecientes a una categoría
	 */
	public List<Reglamento> findByCategoriaAndEstatusTrue(String categoria);
}
