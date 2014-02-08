package sigarep.modelos.repositorio.reportes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.reportes.ApelacionesPorPrograma;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorProgramaDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por programa.
	 * 
	 * @return numero de apelaciones agrupadas por lapso academico<code>ApelacionesPorPrograma</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorPrograma> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorTipoMotivo(String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorTipoSancion(String tiposancion);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, tipomotivo
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorTipoSancionYTipoMotivo(String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorLapsoYTipoSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorLapsoYTipoMotivo(String lapsoinicial, String lapsofinal, String tipomotivo);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>ApelacionesPorPrograma</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorPrograma> buscarPorLapsoYTipoSancionYTipoMotivo(String lapsoinicial, String lapsofinal, String tiposancion, String tipomotivo);
	
	
	

}