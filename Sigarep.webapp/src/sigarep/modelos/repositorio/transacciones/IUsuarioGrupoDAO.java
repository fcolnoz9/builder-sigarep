package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

public interface IUsuarioGrupoDAO extends
		JpaRepository<UsuarioGrupo, UsuarioGrupoPK> {

	 //@Query()
	 //public List<SolicitudApelacion> solicitudesApelacionPorSancionado(EstudianteSancionadoPK id);
}
