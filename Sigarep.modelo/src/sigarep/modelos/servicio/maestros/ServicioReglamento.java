package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.repositorio.maestros.IReglamentoDAO;

/**
 * Clase  ServicioReglamento 
 * 
 * @author BUILDER
 * @version 1.0
 * @since 18/12/2013
 */
@Service("servicioreglamento")
public class ServicioReglamento {
	private @Autowired IReglamentoDAO rg;
	
	/**guardarReglamento
	 * @param Reglamento r
	 * @return No devuelve ningún valor
	 */
	public void guardarReglamento(Reglamento r){
		if (r.getIdDocumento() != null)
			rg.save(r);
		else{
			r.setIdDocumento(rg.buscarUltimoID()+1);
			rg.save(r);
		}
	}
	
	/**eliminar 
	 * @param idDocumento
	 * @return No devuelve ningún valor
	 */
	public void eliminar(Integer idDocumento) {
		Reglamento reglamentoBorrarLogico = rg.findOne(idDocumento);
		reglamentoBorrarLogico.setEstatus(false);
		rg.save(reglamentoBorrarLogico);
	}
	
	/**buscarReglamento		
	 * @param Integer idDocumento
	 * @return No devuelve ningún valor
	 */
	public Reglamento buscarReglamento(Integer idDocumento){
		return rg.findOne(idDocumento);
	}
	
	/**listaReglamento
	 * @param IDAO, el cual trae todos los registros en true,
	 * los que no han sido eliminado logicamente
	 * @return listaReglamentoLogico
	 */
	public List<Reglamento> listaReglamento() {
		return rg.findByEstatusTrue();
	}
	
	/**
	 *filtrarReglamento
	 * 
	 * @param String tituloF,String  categoriaF
	 * @return busca un documento por título o categoría en el filtro
	 * @throws No  dispara ninguna excepción.
	 */
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
		
	//Buscar Documentos específicos
	public List<Reglamento> buscarReglamentoPortal(){
		return rg.findByCategoriaAndEstatusTrue("reglamento");
	}
	
	public List<Reglamento> buscarRecaudosPortal(){
		return rg.findByCategoriaAndEstatusTrue("recaudo");
	}
	
	public List<Reglamento> buscarFormatoPortal(){
		return rg.findByCategoriaAndEstatusTrue("formato");
	}
	
	public List<Reglamento> buscarGuia(){
		return rg.findByCategoriaAndEstatusTrue("guia");
	}
	
	public List<Reglamento> buscarCalendario(){
		return rg.findByCategoriaAndEstatusTrue("calendario");
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
	//Fin Buscar Documentos específicos
}