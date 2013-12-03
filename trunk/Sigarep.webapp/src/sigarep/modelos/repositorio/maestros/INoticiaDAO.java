package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Noticia;

public interface INoticiaDAO extends JpaRepository<Noticia, Integer> {

}
