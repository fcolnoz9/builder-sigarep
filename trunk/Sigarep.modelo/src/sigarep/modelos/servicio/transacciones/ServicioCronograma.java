package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.repositorio.transacciones.ICronogramaDAO;

/**
 * Clase ServicioCronograma : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Cronograma 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciocronograma")
public class ServicioCronograma {
	
	// Atributos de la clase
	@PersistenceContext
	private EntityManager emcro;
	
	private @Autowired
	ICronogramaDAO iCronograma;
	
	/**Elimina un cronograma . Busca el id del cronograma, actualiza estatus(false) y lo guarda en base de datos.
	 * @param id.
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminarCronograma(CronogramaPK id) {
		Cronograma c = iCronograma.findOne(id);
		c.setEstatus(false);
		iCronograma.save(c);
	}
	
	/**Busca una lista de todos los cronogramas activos.
	 * @return List<Cronograma> Lista de Cronograma.
	 * @throws No dispara ninguna excepción.
	 */
	public List<Cronograma> listadoCronograma() {
		List<Cronograma> cronogramaLista = iCronograma.findByEstatusTrue();
		return cronogramaLista;
	}
	
	/**Guarda un cronograma 
	 * @param cronograma.
	 * @throws No dispara ninguna excepción.
	 */
	public Cronograma guardar(Cronograma cronograma) {
		return iCronograma.save(cronograma);
	}
	
	/**Busca una lista de todos los cronogramas
	 * @return List<Cronograma> Lista de cronogramas
	 * @throws No dispara ninguna excepción.
	 */
	public  List<Cronograma> buscarTodos() {
		return iCronograma.findAll();
	}
	
	/**Busca una lista de todos los cronogramas donde coincida su código de lapso con el código de lapso que entra por parametro.
	 * @param codigoLapso.
	 * @return List<Cronograma> Lista de cronogramas
	 * @throws No dispara ninguna excepción.
	 */
    public List<Cronograma> buscarTodosCronogramas(String codigoLapso) {
		List<Cronograma> listaCronogramas = iCronograma.findById_CodigoLapso(codigoLapso);
		return listaCronogramas;
	}
    
    /**Busca dentro del cronograma actual la última fecha con una actividad
	 * @return Una fecha.
	 * @throws No dispara ninguna excepción.
	 */
    public Date buscarUltimaFechaDelCronogramaActual(){
    	return iCronograma.buscarUltimaFechaCronogramaActual();
    }
    
    /**Busca un cronograma haciendo filtrado por responsable, lugar y actividad
   	 * @param responsablef, lugarf, actividadf
   	 * @return List<Cronograma> Lista de cronograma
   	 * @throws La Excepción es que las variables que entran por parametro sean null
   	 */
    public List<Cronograma> filtrarCronograma(String responsablef, String lugarf, String actividadf){
		List<Cronograma> result = new ArrayList<Cronograma>();
        if(responsablef==null || lugarf==null|| actividadf==null){
        	result= listadoCronograma();
        }
        else{
			for (Cronograma cro : listadoCronograma())
			{
				if (cro.getResponsable().getInstanciaApelada().toLowerCase().contains(responsablef.toLowerCase())&&
						cro.getLugar().toLowerCase().contains(lugarf.toLowerCase())&&
						cro.getActividad().getNombre().toLowerCase().contains(actividadf.toLowerCase())){
					result.add(cro);
				}
			}
        }
		return result;
	} 
    
    /**
     * Busca una lista de actividades dentro de  un cronograma en un lapso académico
     * @param lapso
     * @return List<Cronograma> Lista de elementos insertar en el cronograma
     * @throws No dispara ninguna excepción.
     */
	public List<String> historicoCronogramaActividades(LapsoAcademico lapso) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		List<Cronograma> cronogramas = iCronograma.findById_CodigoLapso(lapso.getCodigoLapso());

		for (int i = 0; i < cronogramas.size(); i++) {
				Cronograma cronograma = cronogramas.get(i);
				elementoAInsertar = "INSERT INTO sigarep.cronograma(codigo_lapso, id_actividad, estatus, fecha_fin, fecha_inicio, hora_inicio, lugar, observacion, id_instancia_apelada)"
						+ "VALUES ('"
						+ cronograma.getId().getCodigoLapso()
						+ "',"
						+ cronograma.getId().getIdActividad()
						+ ",'"
						+ cronograma.getEstatus()
						+ "', '"
						+ cronograma.getFechaFin()
						+ "','"
						+ cronograma.getFechaInicio()
						+ "','"
						+ cronograma.getHoraInicio()
						+ "','"
						+ cronograma.getLugar()
						+ "','"
						+ cronograma.getObservacion()
						+ "','"
						+ cronograma.getActividad().getInstanciaApelada().getInstanciaApelada()
						+ "');";
				listaElementosAInsertar.add(elementoAInsertar);
		}
		return listaElementosAInsertar;
	}
    
}