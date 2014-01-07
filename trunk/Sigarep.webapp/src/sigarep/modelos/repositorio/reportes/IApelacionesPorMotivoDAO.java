package sigarep.modelos.repositorio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.reportes.ApelacionesPorMotivo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface IApelacionesPorMotivoDAO {
	
	
	/**
	 * Busca todas las apelaciones de estudiantes agrupadas por tipo de motivo.
	 * 
	 * @return numero de apelaciones agrupadas por lapso academico<code>apelacionespormotivo</code>;
	 * 		   o una lista vacia si no se registraron apelaciones
	 */
	List<ApelacionesPorMotivo> buscarTodos();
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorPrograma(String programa);
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorTipoSancion(String tiposancion);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorLapso(String lapsoinicial, String lapsofinal);
	
	
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param tiposancion, programa
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorTipoSancionYPrograma(String tiposancion, String programa);
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorLapsoYPrograma(String lapsoinicial, String lapsofinal, String programa);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorLapsoYTipoSancion(String lapsoinicial, String lapsofinal, String tiposancion);
	
	
	
	
	/**
	 * Busca por los filtros asignados para las apelaciones.
	 * 
	 * @param lapsoinicial, lapsofinal, tiposancion, programa 
	 * @return las apelaciones que cumplan con los filtros <code>apelacionespormotivo</code>;
	 * 		  o una lista vacia si no existe solicitudes que cumplan con dichos filtros.
	 */
	List<ApelacionesPorMotivo> buscarPorLapsoYTipoSancionYPrograma(String lapsoinicial, String lapsofinal, String tiposancion, String programa);
	
	


}