package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.data.maestros.Reglamento;

public interface IReglamentoDAO extends JpaRepository<Reglamento, Integer> {
	
	//permite la busqueda de los registros por estatus, para mostrar los registros en true, luego de la eliminación lógica.
		//se utiliza en ServicioReglamento public List<Reglamento> listaReglamento()
			@Query("select  r from Reglamento r where r.estatus='TRUE'")
			public List<Reglamento> listaReglamentoLogico();
			
			@Query("select  r from Reglamento r where r.estatus='TRUE' and r.categoria='reglamento'")
			public List<Reglamento> buscarReglamento();

}
