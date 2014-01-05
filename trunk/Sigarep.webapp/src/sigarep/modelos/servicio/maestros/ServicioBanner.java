package sigarep.modelos.servicio.maestros;

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

	
	public List<Banner> buscarBannerTitulo(String titulo) {
		List<Banner> result = new LinkedList<Banner>();
		if (titulo == null || "".equals(titulo)) {
			result = listadoBanner();
		} else {
			for (Banner bann : listadoBanner()) {
				if (bann.getTitulo().toLowerCase()
						.contains(titulo.toLowerCase())){
					result.add(bann);
				}
			}
		}
		return result;
	}
	
	public List<Banner> buscarBannerEnlace(String enlace) {
		List<Banner> result = new LinkedList<Banner>();
		if (enlace == null || "".equals(enlace)) {
			result = listadoBanner();
		} else {
			for (Banner bann : listadoBanner()) {
				if (bann.getEnlace().toLowerCase().contains(enlace.toLowerCase())){
					result.add(bann);
				}
			}
		}
		return result;
	}
}
