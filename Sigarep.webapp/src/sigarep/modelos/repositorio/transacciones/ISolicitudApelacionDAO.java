package sigarep.modelos.repositorio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

	@Query("select sa from SolicitudApelacion sa where sa.fechaSolicitud<=?1")
	public List<SolicitudApelacion> buscarPorFechaHasta(Date fecha);
	
	 //@Query()
	 //public List<SolicitudApelacion> solicitudesApelacionPorSancionado(EstudianteSancionadoPK id);
}
