package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.TipoMotivo;

public interface ITipoMotivoDAO extends JpaRepository<TipoMotivo, Integer> {

}
