
package sigarep.modelos.repositorio.maestros;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Momento;

/*
  * @ (#) Momento.java 
  *
  * Copyright 2013 Builder. Todos los derechos reservados.
  * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
  */
/*
 ** Archivo del repositorio  del registro del maestro "Momento"
  * @ Author Lilibeth Achji 
  * @ Version 1.0, 16/12/13
 */

public interface IMomentoDAO extends JpaRepository<Momento, Integer> {

	@Query("Select tipo FROM Momento AS tipo WHERE estatus = TRUE")
	public List<Momento> buscarMomentoActivas();

}
