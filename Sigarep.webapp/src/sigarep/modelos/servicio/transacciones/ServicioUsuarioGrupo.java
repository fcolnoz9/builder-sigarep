package sigarep.modelos.servicio.transacciones;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;

@Service("serviciousuariogrupo")
public class ServicioUsuarioGrupo {
	
	@PersistenceContext
	private EntityManager em;

	public @Autowired IUsuarioGrupoDAO iUsuarioGrupoDAO;
	
	public UsuarioGrupo guardar(UsuarioGrupo usuariogrupo) {
		return iUsuarioGrupoDAO.save(usuariogrupo);
	}
	
	public void eliminar(UsuarioGrupoPK id){
		UsuarioGrupo miUsuarioGrupo = iUsuarioGrupoDAO.findOne(id);
		miUsuarioGrupo.setEstatus(false);
		iUsuarioGrupoDAO.save(miUsuarioGrupo);
	}
		
	public void eliminarFisicamente(UsuarioGrupoPK id){
		UsuarioGrupo miUsuarioGrupo = iUsuarioGrupoDAO.findOne(id);
		iUsuarioGrupoDAO.delete(miUsuarioGrupo);
	}
	
	public int contarTodos() {
		return iUsuarioGrupoDAO.findAll().size();
	}

	public UsuarioGrupo crear() {
		return new UsuarioGrupo();
	}
	
	public void eliminarUsuarioGrupo(Integer idGrupo, String nombreUsuario) 
	{
		String queryStatement = "delete from usuario_grupo ug where " +
		"ug.idgrupo = '"+idGrupo +"' and ug.nombreusuario = '"+nombreUsuario +"'";
		Query query = em.createNativeQuery(queryStatement);
		try {
			query.getSingleResult();
		} catch (Exception exp) {
			System.out.println("");
		}
	}
}
