package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sigarep.modelos.data.maestros.Persona;

public interface IPersonaDAO extends JpaRepository<Persona, String> {
	
	
	
	
	@Query("select per from Persona per where per.estatus = true")
	public List<Persona> buscarpersona();
}
