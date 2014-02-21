package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Reglamento;

public interface IReglamentoDAO extends JpaRepository<Reglamento, Integer> {
	
	/**
	 * Busca el ultimo id insertado en la tabla Reglamento
	 * @return Ultimo id insertado en la tabla Reglamento
	 */
	@Query("SELECT COALESCE(MAX(r.idDocumento),0) FROM Reglamento AS r")
	public int buscarUltimoID();
	
	/**
	 * Busca los Reglamentos activos, es decir, que poseen estatus true
	 * @return List<Reglamento> Lista de Reglamentos con estatus true
	 */
	public List<Reglamento> findByEstatusTrue();

	/**
	 * Busca los docuemtos activos que pertenecen a una categoria dada
	 * @return List<Reglamento> Lista de Docuemtos pertenecientes a una categoria
	 */
	public List<Reglamento> findByCategoriaAndEstatusTrue(String categoria);
}
