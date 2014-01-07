package sigarep.modelos.repositorio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.reportes.ApelacionesPorTipoInstancia;
import sigarep.modelos.data.reportes.ChartDataApelacionesPorLapso;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorTipoInstanciaDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por tipo de instancia.
	 * 
	 * @return numero de apelaciones agrupadas por sexo<code>apelacionesportipoinstancia</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorTipoInstancia> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorPrograma(String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorTipoMotivo(String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorTipoSancion(String tiposancion);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorTipoSancionYPrograma(String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tipomotivo, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorTipoMotivoYPrograma(String programa, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYPrograma(String lapsoinicial, String lapsofinal, String programa);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, tipomotivo
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorTipoSancionYTipoMotivo(String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoMotivo(String lapsoinicial, String lapsofinal, String tipomotivo);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorProgramaYTipoSancionYTipoMotivo(String programa, String tiposancion, String tipomotivo);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoSancionYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoSancionYTipoMotivo(String lapsoinicial, String lapsofinal, String tiposancion, String tipomotivo);
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa, tipomotivo 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoSancionYTipoMotivoYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa, String tipomotivo);

	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tipomotivo, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionesportipoinstancia</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorTipoInstancia> buscarPorLapsoYTipoMotivoYPrograma(
			String lapsoinicial, String lapsofinal, String tipomotivo,
			String programa);
	

}