package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, String> {

}
