package sigarep.modelos.servicio.maestros;



import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.maestros.ReglamentoFiltros;
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


/**Constructor Vacio
 * @param constructor sin parametros	
 */
	public ServicioReglamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
/**guardarReglamento
 * @param r
 * @return No devuelve ningun valor
 */

	public void guardarReglamento(Reglamento r){
		rg.save(r);
	}
	
	/**elininar 
	 * @param idDocumento
	 * @return No devuelve ningun valor
	 */
	//Permite la eliminación lógica del registro, por id, busca el id y cambia su estatus a false, 
		//la llamada reglamento.save(enlaceBorrarLogico); actualiza el estatus del registro.
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
				return rg.listaReglamentoLogico();
			}
			
			public List<Reglamento> buscarReglamento(String tituloF,String  categoriaF) {
				List<Reglamento> resultado = new LinkedList<Reglamento>();	
				if (tituloF == null ||categoriaF==null ) {
					resultado = listaReglamento();
				} else {
					for (Reglamento r : listaReglamento()) {
						if (r.getTitulo().toLowerCase().contains(tituloF)
								&& r.getCategoria().toLowerCase()
								.contains(categoriaF))
						{
							resultado.add(r);
						}
					}
				}
				return resultado;
			}
			
	
	public List<Reglamento> buscarReglamentoPortal(){
		return rg.buscarReglamento();
	}
	
	public List<Reglamento> buscarRecaudosPortal(){
		return rg.buscarRecaudos();
	}
	
	public List<Reglamento> buscarFormatoPortal(){
		return rg.buscarFormato();
	}

}