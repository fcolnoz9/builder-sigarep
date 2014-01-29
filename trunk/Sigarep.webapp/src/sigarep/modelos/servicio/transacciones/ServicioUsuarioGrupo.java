package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;

@Service("serviciousuariogrupo")
public class ServicioUsuarioGrupo {
	

	public @Autowired IUsuarioGrupoDAO iUsuarioGrupoDAO;
	
	public UsuarioGrupo guardar(UsuarioGrupo usuariogrupo) {
		return iUsuarioGrupoDAO.save(usuariogrupo);
	}
	
	public void eliminar(UsuarioGrupoPK id){
		UsuarioGrupo miUsuarioGrupo = iUsuarioGrupoDAO.findOne(id);
		miUsuarioGrupo.setEstatus(false);
		iUsuarioGrupoDAO.save(miUsuarioGrupo);
	}
		
	/*public List<UsuarioGrupo> buscarPorUsuario(String nombreUsuario){
		return iUsuarioGrupoDAO.buscarPorUsuario(nombreUsuario) ;
	}
	
	public List<UsuarioGrupo> buscarPorUsuarioNO(String nombreUsuario){
		return iUsuarioGrupoDAO.buscarPorUsuarioNO(nombreUsuario) ;
	}*/
	
//	public List<UsuarioGrupo> buscarTodos() {
//		return iUsuarioGrupoDAO.buscarSancionadosActivos();
//	}
	
//	public EstudianteSancionado buscar(EstudianteSancionadoPK id) {
//		return iEstudianteSancionadoDAO.findOne(id);
//	}

	public int contarTodos() {
		return iUsuarioGrupoDAO.findAll().size();
	}

	public UsuarioGrupo crear() {
		return new UsuarioGrupo();
	}
}
