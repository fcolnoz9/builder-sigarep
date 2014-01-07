package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlaceInteres;

public interface IEnlaceInteresDAO extends JpaRepository<EnlaceInteres, Integer> {

	//permite la busqueda de los registros por estatus, para mostrar los registros en true, luego de la eliminación lógica.
	//se utiliza en ServicioEnlaceInteres public List<EnlaceInteres> listadoEnlaceInteres()
		@Query("select  e from EnlaceInteres e where e.estatus='true'")
		public List<EnlaceInteres> listaEnlaceLogico();
}
