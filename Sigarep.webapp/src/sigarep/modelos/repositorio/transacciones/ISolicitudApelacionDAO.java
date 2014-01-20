package sigarep.modelos.repositorio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;

public interface ISolicitudApelacionDAO extends JpaRepository<SolicitudApelacion, SolicitudApelacionPK> {

	@Query("select sa from SolicitudApelacion sa where sa.fechaSolicitud<=?1")
	public List<SolicitudApelacion> buscarPorFechaHasta(Date fecha);

	@Query("select sa.estudianteSancionado from SolicitudApelacion sa where sa.id.cedulaEstudiante = :cedulaEstudiante")
	public EstudianteSancionado buscarSancionado(@Param("cedulaEstudiante")String cedulaEstudiante);
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.veredicto is null")
	public long numeroApleacionesSinVeredicto();
	
	@Query("select count(sa.id.cedulaEstudiante) AS cuenta from SolicitudApelacion sa where sa.numeroSesion is null")
	public long numeroApleacionesSinSesion();
	
	 //@Query()
	 //public List<SolicitudApelacion> solicitudesApelacionPorSancionado(EstudianteSancionadoPK id);
}
