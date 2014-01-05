package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Noticia;

public interface INoticiaDAO extends JpaRepository<Noticia, Integer> {
	
	@Query("select n from Noticia n where n.estatus = true")
	public List<Noticia> busar();

}
