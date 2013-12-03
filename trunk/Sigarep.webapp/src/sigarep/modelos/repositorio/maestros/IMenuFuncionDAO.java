package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.MenuFuncion;

public interface IMenuFuncionDAO extends JpaRepository<MenuFuncion, Integer> {

}
