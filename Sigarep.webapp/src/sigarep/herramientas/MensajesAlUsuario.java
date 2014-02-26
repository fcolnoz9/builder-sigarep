package sigarep.herramientas;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

/*
 * Debe declarar una variable tipo MensajesAlUsuario como estándar mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) en donde usaría los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar alguienn mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita alguien otro mensaje agréguelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del último de advertencia.
 * 
 * */

public class MensajesAlUsuario {

	/*-------------------------------------------Mensajes de Advertencia-----------------------------------*/
	public void advertenciaLlenarCampos() {

		Messagebox.show("¡Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaIngresarCedula() {

		Messagebox.show("¡Debe ingresar una cédula!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaMenudelGrupoVacio() {

		Messagebox.show("¡Debe agregar al menos una función al menú del grupo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarGrupoUsuario() {

		Messagebox.show("Debe seleccionar al menos un grupo.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarOpcion() {
		Messagebox.show("Debe seleccionar alguna opción para continuar.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarImagen() {

		Messagebox.show("¡Debe Cargar una Imagen!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaTamannoImagen(int pesoKB) {

		Messagebox.show("Debe seleccionar una imagen con tamaño menor a "
				+ pesoKB + " Kbytes.", "Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	public void advertenciaTamannoArchivo(int pesoKB) {

		Messagebox.show("Debe seleccionar un archivo con tamaño menor a "
				+ pesoKB + " Kbytes.", "Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarDocumento() {

		Messagebox.show("¡Debe Cargar un Documento!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarParaEliminar() {

		Messagebox.show("Debe seleccionar un registro para eliminarlo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarTodo() {

		Messagebox.show("Debe seleccionar todas las opciones.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
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
		Messagebox.show("Debe agregar un motivo al caso.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaAgregarObservacionGeneral() {
		Messagebox.show("Debe agregar una observación general del caso.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarLapso() {

		Messagebox.show("¡Debe seleccionar un lapso académico!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarDestinoRespaldo() {

		Messagebox.show("Debe seleccionar el destino para el respaldo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	 public void advertenciaSeleccionarUbicacionRestauracion() {
	
	 Messagebox.show("Debe seleccionar la ubicación de origen de su archivo backup.",
	 "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	 }

	public void advertenciaEscribirNombreDeRespaldo() {
		Messagebox.show("Debe escribir el nombre del archivo de respaldo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaContrasennasNoCoinciden() {
		Messagebox.show("Las contraseñas no coinciden.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCorreosNoCoinciden() {
		Messagebox.show("Los correos no coinciden.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaNoExisteEstudianteSancionado() {

		Messagebox
				.show("Esta cédula no está en la lista de Estudiantes Sancionados.",
						"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaGrupoYaExistente(String nombreGrupo) {

		Messagebox.show("¡El grupo con nombre " + nombreGrupo
				+ " ya se encuentra registrado!.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
/*-------------------------------------------Mensajes de Información-----------------------------------*/
	public void informacionHemosEnviadoCorreo() {

		Messagebox.show("Le hemos enviado un email con su nombre de usuario y contraseña.",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaAtualizada() {

		Messagebox.show("Se ha actualizado su contraseña con éxito.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaRecuperada() {

		Messagebox.show("Hemos enviado un email con su contraseña.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionCorreoEnviado() {

		Messagebox.show("Su correo ha sido enviado satisfactoriamente.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public static void informacionRegistroCorrectoStatic() {

		Messagebox.show("Se ha registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRegistroCorrecto() {

		Messagebox.show("Se ha registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionEliminarCorrecto() {

		Messagebox.show("Se ha eliminado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionFinalizarLapsoExitoso() {

		Messagebox.show("¡Lapso Académico finalizado exitosamente!",
				"Información", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionOperacionExitosa() {

		Messagebox.show("¡Operación realizada exitosamente!",
				"Informacion", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionCreacionHistorico(String nombreHistorico) {

		Messagebox.show(
				"Se ha Creado un archivo histórico bajo el nombre de: " + ""
						+ nombreHistorico + ".sql", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionArchivoCargado() {

		Messagebox.show("Archivo cargado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionArchivoEliminado() {

		Messagebox.show("Archivo eliminado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionVeredictoRegistrado() {
		Messagebox.show("Veredicto registrado correctamente.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionFinalizarVeredictoApelacionesProcesadas() {
		Messagebox.show("No existen apelaciones para procesar.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	// public void informacionGuardarDatosSesion(int contador) {
	// Messagebox.show("Se almacenaron correctamente los datos de la sesiï¿½n a "
	// + contador + " apelaciones.", "Informaciï¿½n",
	// Messagebox.OK, Messagebox.INFORMATION);
	// } no se usa por ahora

	// public void informacionDatosDeSesionNoEncontrados() {
	// Messagebox.show("Proporcione los datos de una nueva sesiï¿½n.",
	// "Informaciï¿½n", Messagebox.OK, Messagebox.INFORMATION);
	// }no se usa por ahora

	public void informacionDatosDeSesionEncontrados() {
		Messagebox
				.show("Se encontró una Sesión activa. Puede continuar con la misma o indicar los datos de una nueva.",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRestauracionNoExitosa() {
		Messagebox.show("El intento de restauración no fue exitoso.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El intento de respaldo no fue exitoso.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoExitoso() {
		Messagebox.show("Respaldo realizado de manera exitosa.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionNoHayCoincidencias() {
		Messagebox.show("No Hay coincidencias para mostrar.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionEstudianteSinSolicitudApelacion() {
		Messagebox.show("Usted no ha realizado solicitudes de apelación",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRestauracionEnProceso() {

		Messagebox.show("Restauración en proceso, debe esperar mientras el proceso es completado.",
						"Error", Messagebox.OK, Messagebox.INFORMATION);
	}
	/*-------------------------------------------Mensajes de Error-----------------------------------*/
	

	public void errorUsuarioEmailNoRegistrado() {
       Messagebox.show("Usuario o e-mail no registrado.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}

	public void errorFinalizarLapsoVeredicto() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin veredicto.", "Error", Messagebox.OK,
				Messagebox.ERROR);
	}

	public void errorFinalizarLapsoSesion() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin número de sesión.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoActivoNoExistente() {

		Messagebox.show("No existe un lapso académico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorNoHayVeredictosRegistrados() {

		Messagebox.show(
				"No se ha podido finalizar. No hay veredictos registrados.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoActivoExistente() {

		Messagebox.show("Ya existe un lapso académico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void errorLapsoFinalizadoNoModificable() {

		Messagebox
				.show("Lapso académico finalizado. No puede realizar cambios sobre él.",
						"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void errorRangoFechas() {

		Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);

	}

	public void errorNoCoincidenLasContrasenias() {

		Messagebox
				.show("La nueva contraseña y la contraseña de confirmación no coinciden.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void errorNoCoincideContraseniaUsuario() {

		Messagebox.show(
				"La contraseña ingresada no coincide con el usuario.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void errorNoHayResgistrosParaRespaldo() {

		Messagebox
				.show("No hay nada a lo que hacer respaldo en el lapso seleccionado.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void errorNoEsXML() {

		Messagebox.show("La Extensión del Archivo no es XML.", "Error",
				Messagebox.OK, Messagebox.ERROR);
	}

	/*-------------------------------------------Mensajes de Confirmación-----------------------------------*/
	public void confirmacionCerrarSesion() {

		Messagebox.show("¿Está seguro de querer cerrar sesión?",
				"Confirmación", new Messagebox.Button[] {
						Messagebox.Button.YES, Messagebox.Button.CANCEL },
				Messagebox.QUESTION, new EventListener<ClickEvent>() {
					@SuppressWarnings("incomplete-switch")
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
						case YES:
							Executions.sendRedirect("j_spring_security_logout");
						}
					}
				});

	}

	public void confirmacionCerrarVentanaMaestros(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show("¿Realmente desea cerrar la ventana sin guardar los cambios?",
							"Confirmar",
							new Messagebox.Button[] { Messagebox.Button.YES,
									Messagebox.Button.NO },
							Messagebox.QUESTION,
							new EventListener<ClickEvent>() {
								@SuppressWarnings("incomplete-switch")
								public void onEvent(ClickEvent e)
										throws Exception {
									switch (e.getButton()) {
									case YES:
										ventana.detach();
									}
								}
							});
		} else
			Messagebox.show("¿Realmente desea cerrar la ventana?", "Confirmar",
					new Messagebox.Button[] { Messagebox.Button.YES,
							Messagebox.Button.NO }, Messagebox.QUESTION,
					new EventListener<ClickEvent>() {
						@SuppressWarnings("incomplete-switch")
						public void onEvent(ClickEvent e) throws Exception {
							switch (e.getButton()) {
							case YES:
								ventana.detach();
							}
						}
					});
	}

	public void confirmacionCerrarVentanaTransacciones(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show(
					"¿Realmente desea cerrar la ventana sin realizar cambios?",
					"Confirmar", new Messagebox.Button[] {
							Messagebox.Button.YES, Messagebox.Button.NO },
					Messagebox.QUESTION, new EventListener<ClickEvent>() {
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

	public void confirmacionCerrarVentanaSimple(final Window ventana,
			boolean condicion) {
		if (condicion == true) {
			Messagebox.show("¿Realmente desea cerrar la ventana?", "Confirmar",
					new Messagebox.Button[] { Messagebox.Button.YES,
							Messagebox.Button.NO }, Messagebox.QUESTION,
					new EventListener<ClickEvent>() {
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
