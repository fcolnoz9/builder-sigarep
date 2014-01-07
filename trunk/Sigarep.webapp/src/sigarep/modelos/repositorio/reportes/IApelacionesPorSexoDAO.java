package sigarep.modelos.repositorio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.reportes.ApelacionesPorSexo;
import sigarep.modelos.data.reportes.ChartDataApelacionesPorLapso;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.ApelacionMomentoPK;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorSexoDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por sexo.
	 * 
	 * @return numero de apelaciones agrupadas por sexo<code>apelacionesporsexo</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorSexo> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorPrograma(String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorTipoMotivo(String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorTipoSancion(String tiposancion);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorTipoSancionYPrograma(String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorTipoMotivoYPrograma(String programa, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYPrograma(String lapsoinicial, String lapsofinal, String programa);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, tipomotivo
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorTipoSancionYTipoMotivo(String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoMotivo(String lapsoinicial, String lapsofinal, String tipomotivo);
	
	

	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorProgramaYTipoSancionYTipoMotivo(String programa, String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoSancionYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tipomotivo, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoSancionYTipoMotivo(String lapsoinicial, String lapsofinal, String tiposancion, String tipomotivo);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesporsexo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorSexo> buscarPorLapsoYTipoSancionYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa, String tipomotivo);
	

}