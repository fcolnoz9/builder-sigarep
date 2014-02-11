package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.TipoMotivo;

public interface ITipoMotivoDAO extends JpaRepository<TipoMotivo, Integer> {

	@Query("Select tipo FROM TipoMotivo AS tipo WHERE estatus = 'TRUE'")
	public List<TipoMotivo> buscarTipoMotivoActivas();
	
	@Query("SELECT tipo from TipoMotivo AS tipo WHERE tipo.protegido = 'FAlSE'")
	public List<TipoMotivo> buscarTodas();
	
}
