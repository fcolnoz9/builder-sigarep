package sigarep.modelos.repositorio.maestros;

import java.util.List;

/*
 * @ (#) EstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
** Archivo del repositorio  del registro del maestro "Recaudo"
 * @ Author Beleanny Atacho 
 * @ Version 1.0, 16/12/13
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.Recaudo;

public interface IRecaudoDAO extends JpaRepository<Recaudo, Integer> {

	@Query("Select rec FROM Recaudo AS rec WHERE rec.tipoMotivo.idTipoMotivo = :tipoMotivo")
	public List<Recaudo> buscarRecaudosPorMotivo(@Param("tipoMotivo") Integer tipoMotivo);

	@Query("Select rec FROM Recaudo AS rec WHERE rec.nombreRecaudo = :nombreRecaudo")
	public Recaudo buscarRecaudoPorNombre(@Param("nombreRecaudo") String nombreRecaudo);
	
	@Query("Select rec FROM Recaudo AS rec WHERE rec.estatus = TRUE")
	public List<Recaudo> buscaRecaudosActivos();
	
	
}
