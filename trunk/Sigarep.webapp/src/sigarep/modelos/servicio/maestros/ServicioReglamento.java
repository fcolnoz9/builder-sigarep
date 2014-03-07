package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.repositorio.maestros.IReglamentoDAO;

/**Reglamento
 * UCLA DCYT Sistemas de Información
 * @author Equipo : Builder-Sigarep Lapso 2013-1
 * @version 1.0 
 *@since 22/01/14
 */


@Service("servicioreglamento")
public class ServicioReglamento {
	private @Autowired IReglamentoDAO rg;
	
	/**guardarReglamento
	 * @param r
	 * @return No devuelve ningun valor
	 */
	public void guardarReglamento(Reglamento r){
		if (r.getIdDocumento() != null)
			rg.save(r);
		else{
			r.setIdDocumento(rg.buscarUltimoID()+1);
			rg.save(r);
		}
	}
	
	/**elininar 
	 * @param idDocumento
	 * @return No devuelve ningun valor
	 */
	public void eliminar(Integer idDocumento) {
		Reglamento reglamentoBorrarLogico = rg.findOne(idDocumento);
		reglamentoBorrarLogico.setEstatus(false);
		rg.save(reglamentoBorrarLogico);
	}
	
	/**buscarReglamento		
	 * @param idDocumento
	 * @return No devuelve ningun valor
	 */
	public Reglamento buscarReglamento(Integer idDocumento){
		return rg.findOne(idDocumento);
	}
	
	/**listaReglamento
	 * @param IDAO, el cual trae todos los registros en true,los que no han sido eliminado logicamente
	 * @return listaReglamentoLogico
	 */
	public List<Reglamento> listaReglamento() {
		return rg.findByEstatusTrue();
	}
	
	public List<Reglamento> filtrarReglamento(String tituloF,String  categoriaF) {
		List<Reglamento> resultado = new ArrayList<Reglamento>();	
		if (tituloF == null ||categoriaF == null ) {
			resultado = listaReglamento();
		} else {
			for (Reglamento r : listaReglamento()) {
				if (r.getTitulo().toLowerCase().contains(tituloF.toLowerCase())&&
					r.getCategoria().toLowerCase().contains(categoriaF.toLowerCase()))
				{
					resultado.add(r);
				}
			}
		}
		return resultado;
	}
			
	public List<Reglamento> buscarReglamentoPortal(){
		return rg.findByCategoriaAndEstatusTrue("Reglamento");
	}
	
	public List<Reglamento> buscarRecaudosPortal(){
		return rg.findByCategoriaAndEstatusTrue("Recaudo");
	}
	
	public List<Reglamento> buscarFormatoPortal(){
		return rg.findByCategoriaAndEstatusTrue("Formato");
	}
	
	public List<Reglamento> buscarGuia(){
		return rg.findByCategoriaAndEstatusTrue("Guia");
	}
	
	public List<Reglamento> buscarCalendario(){
		return rg.findByCategoriaAndEstatusTrue("Calendario");
	}
	
	public List<Reglamento> buscarActa(){
		return rg.findByCategoriaAndEstatusTrue("Acta");
	}
	
	public List<Reglamento> buscarManualUsuario(){
		return rg.findByCategoriaAndEstatusTrue("ManualUsuario");
	}
	
	public List<Reglamento> buscarManualSistema(){
		return rg.findByCategoriaAndEstatusTrue("ManualSistema");
	}
}