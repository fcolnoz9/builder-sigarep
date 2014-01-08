package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Recaudo;

public interface IRecaudoDAO extends JpaRepository<Recaudo, Integer> {

	@Query("Select rec FROM Recaudo AS rec WHERE rec.tipoMotivo.idTipoMotivo = :tipoMotivo")
	public List<Recaudo> buscarRecaudosPorMotivo(@Param("tipoMotivo") Integer tipoMotivo);

	@Query("Select rec FROM Recaudo AS rec WHERE rec.nombreRecaudo = :nombreRecaudo")
	public Recaudo buscarRecaudoPorNombre(@Param("nombreRecaudo") String nombreRecaudo);
	
	@Query("Select rec FROM Recaudo AS rec WHERE rec.estatus = TRUE")
	public List<Recaudo> buscaRecaudosActivos();
}
