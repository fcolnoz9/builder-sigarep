package sigarep.modelos.repositorio.reportes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.reportes.ApelacionesPorLapso;
import sigarep.modelos.data.reportes.ApelacionesPorSexo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorLapsoDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por lapso.
	 * 
	 * @return numero de apelaciones agrupadas por lapso academico<code>apelacionesporlapso</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorLapso> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorPrograma(String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorTipoMotivo(String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorTipoSancion(String tiposancion);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorTipoSancionYPrograma(String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorTipoMotivoYPrograma(String programa, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYPrograma(String lapsoinicial, String lapsofinal, String programa);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, tipomotivo
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorTipoSancionYTipoMotivo(String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoMotivo(String lapsoinicial, String lapsofinal, String tipomotivo);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorProgramaYTipoSancionYTipoMotivo(String programa, String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYTipoMotivo(String lapsoinicial, String lapsofinal, String tiposancion, String tipomotivo);
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tipomotivo, String programa);
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporlapso</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa, String tipomotivo);
	

}