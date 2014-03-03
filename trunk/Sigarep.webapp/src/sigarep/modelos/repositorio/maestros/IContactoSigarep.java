package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.ContactoSigarep;

/**
 * Repositorio ContactoSigarep
 * 
 * @author BUILDER
 * @version 1
 * @since 03/03/2014
 */

public interface IContactoSigarep extends JpaRepository<ContactoSigarep, Integer> {

	/**
	 * Busca el ultimo id insertado en la tabla ContactoSigarep
	 * 
	 * @return Ultimo id insertado en la tabla ContactoSigarep
	 */
	@Query("SELECT COALESCE(MAX(cs.idContacto),0) FROM ContactoSigarep AS cs")
	public int buscarUltimoID();
}
