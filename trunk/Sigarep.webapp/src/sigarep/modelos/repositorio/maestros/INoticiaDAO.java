package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Noticia;

/**Noticia
 * Registra y modifica una noticia. Utilizada en el portal web.
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14 
 */

public interface INoticiaDAO extends JpaRepository<Noticia, Integer> {
	
	@Query("select n from Noticia n where n.estatus = true")
	public List<Noticia> busar();

}
