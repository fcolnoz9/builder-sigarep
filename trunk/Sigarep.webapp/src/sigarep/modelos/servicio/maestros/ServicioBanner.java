package sigarep.modelos.servicio.maestros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Banner;
import sigarep.modelos.repositorio.maestros.IBannerDAO;


@Service("servicioBanner") //Definiendo la variable servicio
public class ServicioBanner {

	private @Autowired IBannerDAO banner ;
	
	public void guardarImagen(Banner ba) {
		
	    banner.save(ba);
	}
	public void actualizar(){
		
	}
	
	public void eliminarBanner(Integer idImagen){
		Banner b= banner.findOne(idImagen);
		b.setEstatus(false);
		banner.save(b);
	}
	public List<Banner> listadoBanner() {
		return banner.listaBanner();
	}
	
	public List<Banner> buscarFiltroBanner(String titulo, String enlace){
		List<Banner> result = new ArrayList<Banner>();
        if(titulo==null || enlace==null){
        	result= listadoBanner();
        }
        else{
			for (Banner b: listadoBanner())
			{
				if (b.getTitulo().toLowerCase().contains(titulo.toLowerCase())&&
					b.getEnlace().toLowerCase().contains(enlace.toLowerCase())){
					result.add(b);
				}
			}
        }
		return result;
        } 
	
	public List<Banner> buscarTodosBanner() {
			return banner.listaBanner();
	}
	
}
