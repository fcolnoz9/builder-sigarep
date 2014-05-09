package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.ContactoSigarep;

/**
 * Repositorio IContactoSigarepDAO: Repositorio relacionado con el Maestro ContactoSigarep. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 03/03/2014
 * @last 08/05/2014
 */
public interface IContactoSigarepDAO extends JpaRepository<ContactoSigarep, Integer> {

	/**
	 * Busca el último id insertado en la tabla ContactoSigarep
	 * 
	 * @return Último id insertado en la tabla ContactoSigarep
	 */
	@Query("SELECT COALESCE(MAX(cs.idContacto),0) FROM ContactoSigarep AS cs")
	public int buscarUltimoID();
}
