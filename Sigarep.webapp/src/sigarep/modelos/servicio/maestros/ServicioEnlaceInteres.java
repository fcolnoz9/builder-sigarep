package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;


@Service("servicioenlacesinteres")
public class ServicioEnlaceInteres {
	private @Autowired IEnlaceInteresDAO enlaceinteres;

	//Guarda el objeto
	public void guardarEnlace(EnlaceInteres enlace) {
		enlaceinteres.save(enlace);
	}	
	//Permite la eliminación lógica del registro, por id, busca el id y cambia su estatus a false, 
	//la llamada enlaceinteres.save(enlaceBorrarLogico); actualiza el estatus del registro.
		public void eliminar(Integer idEnlace) {
			EnlaceInteres enlaceBorrarFisico = enlaceinteres.findOne(idEnlace);
			enlaceinteres.delete(enlaceBorrarFisico);
		}
	//Utiliza IEnlaceInteresDAO, el cual trae todos los registros en true,los que no han sido eliminado logicamente
		public List<EnlaceInteres> listadoEnlaceInteres() {
			return enlaceinteres.findAll();
		}

		//Permite la búsqueda por id en el filtro buscarEnlaceFiltroId() de VMenlaceInteres
		// utilizado en ActualizarEnlace.zul
					public List<EnlaceInteres> buscarEnlacesCodigo(Integer idEnlace){
						List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
						if (idEnlace==null){//si el id es null,el resultado va a ser la lista completa de todos los enlaces
							result = listadoEnlaceInteres();
						}else{//caso contrario, se recorre toda la lista y busca los enlaces con el id indicado en el intbox. Realiza la busqueda por el primer número y número completo.
							for (EnlaceInteres ei: listadoEnlaceInteres()){
								if (ei.getIdEnlace().toString().contains(idEnlace.toString())){
									result.add(ei);
								}
							}
						}
						return result;
				}
			
		//Permite la búsqueda por nombre en el filtro buscarEnlaceFiltroNombreEnlace() de VMenlaceInteres
		// utilizado en ActualizarEnlace.zul
			public List<EnlaceInteres> buscarEnlacesNombre(String nombreEnlace){
				List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
				if (nombreEnlace==null || "".equals(nombreEnlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los enlaces.
					result = listadoEnlaceInteres();
				}else{//caso contrario, se recorre toda la lista y busca los enlaces con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda por nombre y por inicial del nombre.
					for (EnlaceInteres e: listadoEnlaceInteres()){
						if (e.getNombreEnlace().toLowerCase().contains(nombreEnlace.toLowerCase())){
							result.add(e);
						}
					}
				}
				return result;
			}	

		//Permite la búsqueda por dirección en el filtro buscarEnlaceFiltroDireccionEnlace() de VMenlaceInteres
		// utilizado en ActualizarEnlace.zul		
				public List<EnlaceInteres> buscarEnlacesDireccion(String direccionEnlace){
					List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
					if (direccionEnlace==null || "".equals(direccionEnlace)){//si la dirección es null o vacio,el resultado va a ser la lista completa de todos los enlaces
						result = listadoEnlaceInteres();
					}else{//caso contrario, se recorre toda la lista y busca los enlaces con la dirección indicada en la caja de texto. Realiza la busqueda con la dirección y con la inicial de la dirección.
						for (EnlaceInteres e: listadoEnlaceInteres()){
							if (e.getDireccionEnlace().toLowerCase().contains(direccionEnlace.toLowerCase())){
								result.add(e);
							}
						}
					}
					return result;
				}
		}//fin ServicioEnlaceInteres

