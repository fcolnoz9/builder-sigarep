package sigarep.herramientas;

import java.awt.MenuItem;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

/*
 * Debe declarar una variable tipo MensajesAlUsuario como estï¿½ndar mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) en donde usarï¿½ los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar algï¿½n mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita algï¿½n otro mensaje agrï¿½guelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del ï¿½ltimo de advertencia.
 * 
 * */

public class MensajesAlUsuario {

	public void advertenciaLlenarCampos() {

		Messagebox.show("ï¿½Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

//	public void advertenciaContraseï¿½aVacia() {
//
//		Messagebox.show("La nueva contraseï¿½a no puede ser vacï¿½a.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
//no se usa
	public void advertenciaIngresarCedula() {

		Messagebox.show("ï¿½Debe ingresar una cï¿½dula!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaMenudelGrupoVacio() {

		Messagebox.show("ï¿½Debe agregar al menos una funciï¿½n al menï¿½ del grupo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarGrupoUsuario() {

		Messagebox.show("Debe seleccionar al menos un grupo.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarOpcion() {
		Messagebox.show("Debe seleccionar alguna opciï¿½n para continuar.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarImagen() {

		Messagebox.show("ï¿½Debe Cargar una Imagen!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarDocumento() {

		Messagebox.show("ï¿½Debe Cargar un Documento!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarParaEliminar() {

		Messagebox.show("Debe seleccionar un registro para eliminarlo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarTodo() {

		Messagebox.show("Debe Seleccionar Todos los Campos.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaFormatoNoSoportado() {

		Messagebox.show("El formato no es soportado.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaGuardarVeredicto() {

		Messagebox.show("Debe especificar un veredicto para este caso.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarAlMenosUnRecaudoEntregado() {

		Messagebox.show("Debe seleccionar al menos un recaudo entregado.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarSugerencia() {

		Messagebox.show("Debe seleccionar una sugerencia del caso.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaAgregarMotivo() {
		Messagebox.show("Debe agregar un motivo al caso.",
				"Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
     }
	
	public void advertenciaAgregarObservacionGeneral() {
		Messagebox.show("Debe agregar una observaciï¿½n general del caso.",
				"Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
    }
	
	public void advertenciaSeleccionarLapso() {

		Messagebox.show("ï¿½Debe seleccionar un lapso acadï¿½mico!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarDestinoRespaldo() {

		Messagebox.show("Debe seleccionar el destino para el respaldo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

//	public void advertenciaSeleccionarUbicacionRespaldo() {
//
//		Messagebox.show("Debe seleccionar la ubicaciï¿½n del respaldo a restaurar.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
	
	public void advertenciaEscribirNombreDeRespaldo() {
		Messagebox.show("Debe escribir el nombre del archivo de respaldo.",
		"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }
	
	public void advertenciaContrasennasNoCoinciden() {
		Messagebox.show("Las contraseï¿½as no coinciden.","Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }
	
	public void advertenciaCorreosNoCoinciden() {
		Messagebox.show("Los correos no coinciden.","Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }
	public void advertenciaNoExisteEstudianteSancionado() {

		Messagebox.show("Esta cï¿½dula no estï¿½ en la lista de Estudiantes Sancionados.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	
	public void advertenciaGrupoYaExistente(String nombreGrupo) {

		Messagebox.show("ï¿½El grupo con nombre "+nombreGrupo+" ya se encuentra registrado!.",
				"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void informacionHemosEnviadoCorreo() {

		Messagebox.show("Le hemos enviado un email con su nombre de usuario y contraseï¿½a.",
						"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaAtualizada() {

		Messagebox.show("Se ha actualizado su contraseï¿½a con ï¿½xito.",
				"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaRecuperada() {

		Messagebox.show("Hemos enviado un email con su contraseï¿½a.",
				"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public static void informacionRegistroCorrectoStatic() {

		Messagebox.show("Se ha registrado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRegistroCorrecto() {

		Messagebox.show("Se ha registrado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionEliminarCorrecto() {

		Messagebox.show("Se ha eliminado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionFinalizarLapsoExitoso() {

		Messagebox.show("ï¿½Lapso Acadï¿½mico finalizado exitosamente!",
				"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionOperacionExitosa() {

		Messagebox.show("ï¿½Operaciï¿½n realizada exitosamente!", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionCreacionHistorico(String nombreHistorico) {

		Messagebox.show("Se ha Creado un archivo histï¿½rico bajo el nombre de: "
				+ "" + nombreHistorico + ".sql", "Informacion", Messagebox.OK,
				Messagebox.INFORMATION);

	}

	public void informacionArchivoCargado() {

		Messagebox.show("Archivo cargado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionArchivoEliminado() {

		Messagebox.show("Archivo eliminado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionVeredictoRegistrado() {
		Messagebox.show("Veredicto registrado correctamente.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionFinalizarVeredictoApelacionesProcesadas() {
		Messagebox.show("No existen apelaciones para procesar.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	


//	public void informacionGuardarDatosSesion(int contador) {
//		Messagebox.show("Se almacenaron correctamente los datos de la sesiï¿½n a "
//						+ contador + " apelaciones.", "Informaciï¿½n",
//				Messagebox.OK, Messagebox.INFORMATION);
//	} no se usa por ahora

//	public void informacionDatosDeSesionNoEncontrados() {
//		Messagebox.show("Proporcione los datos de una nueva sesiï¿½n.",
//				"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
//	}no se usa por ahora

	public void informacionDatosDeSesionEncontrados() {
		Messagebox.show("Se encontrï¿½ una Sesiï¿½n activa. Puede continuar con la misma o indicar los datos de una nueva.",
						"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRestauracionNoExitosa() {
		Messagebox.show("El intento de restauraciï¿½n no fue exitoso.",
				"Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El intento de respaldo no fue exitoso.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRespaldoExitoso() {
		Messagebox.show("Respaldo realizado de manera exitosa.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionNoHayCoincidencias() {
		Messagebox.show("No Hay coincidencias para mostrar.", "Informaciï¿½n",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionEstudianteSinSolicitudApelacion(){
		Messagebox.show("Usted no ha realizado solicitudes de apelación", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void ErrorRestauracionEnProceso() {

		Messagebox.show("Restauraciï¿½n en proceso, debe esperar mientras el proceso es completado.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorUsuarioEmailNoRegistrado() {

		Messagebox.show("Usuario o e-mail no registrado.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorFinalizarLapsoVeredicto() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin veredicto.", "Error", Messagebox.OK,
				Messagebox.ERROR);
	}

	public void errorFinalizarLapsoSesion() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin nï¿½mero de sesiï¿½n.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoActivoNoExistente() {

		Messagebox.show("No existe un lapso acadï¿½mico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorNoHayVeredictosRegistrados() {

		Messagebox.show("No se ha podido finalizar. No hay veredictos registrados.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoActivoExistente() {

		Messagebox.show("Ya existe un lapso acadï¿½mico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}
    
	public void errorLapsoFinalizadoNoModificable() {

		Messagebox.show("Lapso académico finalizado. No puede realizar cambios sobre él.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorRangoFechas() {

		Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);

	}

	public void errorNoCoincidenLasContrasenias() {

		Messagebox.show("La nueva contraseï¿½a y la contraseï¿½a de confirmaciï¿½n no coinciden.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void errorNoCoincideContraseniaUsuario() {

		Messagebox.show("La contraseï¿½a ingresada no coincide con el usuario.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void errorNoHayResgistrosParaRespaldo() {

		Messagebox.show("No hay nada a lo que hacer respaldo en el lapso seleccionado.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}
	
	public void errorNoEsXML() {

		Messagebox.show("La Extensiï¿½n del Archivo no es XML.", 
				"Error",Messagebox.OK, Messagebox.ERROR);
	}
	

//	public void confirmacionEliminarRegistro(final Window ventana, boolean condicion) {
//		if(condicion==true){
//			Messagebox.show("¿Realmente desea eliminar el registro?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
//					Messagebox.QUESTION,new EventListener<ClickEvent>() {
//				@SuppressWarnings("incomplete-switch")
//				public void onEvent(ClickEvent e) throws Exception {
//					switch (e.getButton()) {
//						case YES:
//								ventana.detach();
//					}
//				}
//			});		
//		}
//			
//	}
	
	
	public void confirmacionCerrarSesion() {
		
		Messagebox.show("¿Está seguro de querer cerrar sesión?","Confirmación",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.CANCEL },
				Messagebox.QUESTION,new EventListener<ClickEvent>() {
			@SuppressWarnings("incomplete-switch")
			public void onEvent(ClickEvent e) throws Exception {
				switch (e.getButton()) {
					case YES:
						Executions.sendRedirect("j_spring_security_logout");
				}
			}
		});

	}
	
	public void confirmacionCerrarVentanaMaestros(final Window ventana, boolean condicion) {
		if(condicion==true){
			Messagebox.show("¿Realmente desea cerrar la ventana sin guardar los cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					}
				}
			});		
		}
		else 
			Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					}
				}
			});	
	}
	
	
	public void confirmacionCerrarVentanaTransacciones(final Window ventana, boolean condicion) {
		if(condicion==true){
			Messagebox.show("¿Realmente desea cerrar la ventana sin realizar cambios?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					}
				}
			});		
		}	
	}
	
	
	public void confirmacionCerrarVentanaSimple(final Window ventana, boolean condicion) {
		if(condicion==true){
			Messagebox.show("¿Realmente desea cerrar la ventana?","Confirmar",new Messagebox.Button[] { Messagebox.Button.YES,Messagebox.Button.NO },
					Messagebox.QUESTION,new EventListener<ClickEvent>() {
				@SuppressWarnings("incomplete-switch")
				public void onEvent(ClickEvent e) throws Exception {
					switch (e.getButton()) {
						case YES:
								ventana.detach();
					}
				}
			});		
		}
	}
	

}
