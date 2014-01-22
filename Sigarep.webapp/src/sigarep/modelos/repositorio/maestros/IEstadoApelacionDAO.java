
package sigarep.modelos.repositorio.maestros;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.Recaudo;

/*
  * @ (#) EstadoApelacion.java 
  *
  * Copyright 2013 Builder. Todos los derechos reservados.
  * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
  */
/*
 ** Archivo del repositorio  del registro del maestro "EstadoApelacion"
  * @ Author Lilibeth Achji 
  * @ Version 1.0, 16/12/13
 */

public interface IEstadoApelacionDAO extends JpaRepository<EstadoApelacion, Integer> {

	@Query("Select ea FROM EstadoApelacion AS ea WHERE ea.nombreEstado = :nombreEstado")
	public EstadoApelacion buscarEstadoPorNombre(@Param("nombreEstado") String nombreEstado);
	
	@Query("Select ea FROM EstadoApelacion AS ea WHERE estatus = TRUE")
	public List<EstadoApelacion> buscarEstadoApelacionActivas();
	
}
