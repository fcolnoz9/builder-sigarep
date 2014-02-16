package sigarep.herramientas;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

/*
 * Debe declarar una variable tipo MensajesAlUsuario como estándar mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) en donde usará los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar algún mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita algún otro mensaje agréguelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del último de advertencia.
 * 
 * */

public class MensajesAlUsuario {

	public void advertenciaLlenarCampos() {

		Messagebox.show("¡Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

//	public void advertenciaContraseñaVacia() {
//
//		Messagebox.show("La nueva contraseña no puede ser vacía.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
//no se usa
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

	public void advertenciaCargarDocumento() {

		Messagebox.show("¡Debe Cargar un Documento!", "Advertencia",
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
		Messagebox.show("Debe agregar una observación general del caso.",
				"Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
    }
	
	public void advertenciaSeleccionarLapso() {

		Messagebox.show("¡Debe seleccionar un lapso académico!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarDestinoRespaldo() {

		Messagebox.show("Debe seleccionar el destino para el respaldo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

//	public void advertenciaSeleccionarUbicacionRespaldo() {
//
//		Messagebox.show("Debe seleccionar la ubicación del respaldo a restaurar.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
	
	public void advertenciaEscribirNombreDeRespaldo() {
		Messagebox.show("Debe escribir el nombre del archivo de respaldo.",
		"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }
	
	public void advertenciaContrasennasNoCoinciden() {
		Messagebox.show("Las contraseñas no coinciden.","Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }

	public void advertenciaNoExisteEstudianteSancionado() {

		Messagebox.show("Esta cédula no está en la lista de Estudiantes Sancionados.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	
	public void advertenciaGrupoYaExistente(String nombreGrupo) {

		Messagebox.show("¡El grupo con nombre "+nombreGrupo+" ya se encuentra registrado!.",
				"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
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

		Messagebox.show("¡Operación realizada exitosamente!", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionCreacionHistorico(String nombreHistorico) {

		Messagebox.show("Se ha Creado un archivo histórico bajo el nombre de: "
				+ "" + nombreHistorico + ".sql", "Informacion", Messagebox.OK,
				Messagebox.INFORMATION);

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
		Messagebox.show("No existen apelaciones para procesar.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	


//	public void informacionGuardarDatosSesion(int contador) {
//		Messagebox.show("Se almacenaron correctamente los datos de la sesión a "
//						+ contador + " apelaciones.", "Información",
//				Messagebox.OK, Messagebox.INFORMATION);
//	} no se usa por ahora

//	public void informacionDatosDeSesionNoEncontrados() {
//		Messagebox.show("Proporcione los datos de una nueva sesión.",
//				"Información", Messagebox.OK, Messagebox.INFORMATION);
//	}no se usa por ahora

	public void informacionDatosDeSesionEncontrados() {
		Messagebox.show("Se encontró una Sesión activa. Puede continuar con la misma o indicar los datos de una nueva.",
						"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRestauracionNoExitosa() {
		Messagebox.show("El intento de restauración no fue exitoso.",
				"Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El intento de respaldo no fue exitoso.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRespaldoExitoso() {
		Messagebox.show("Respaldo realizado de manera exitosa.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionNoHayCoincidencias() {
		Messagebox.show("No Hay coincidencias para mostrar.", "Información",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void ErrorRestauracionEnProceso() {

		Messagebox.show("Restauración en proceso, debe esperar mientras el proceso es completado.",
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

	public void ErrorFinalizarLapsoSesion() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen apelaciones sin número de sesión.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorLapsoActivoNoExistente() {

		Messagebox.show("No existe un lapso académico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorNoHayVeredictosRegistrados() {

		Messagebox.show("No se ha podido finalizar. No hay veredictos registrados.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorLapsoActivoExistente() {

		Messagebox.show("Ya existe un lapso académico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}


	public void ErrorRangoFechas() {

		Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);

	}

	public void ErrorNoCoincidenLasContraseñas() {

		Messagebox.show("La nueva contraseña y la contraseña de confirmación no coinciden.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorNoCoincideContraseñaUsuario() {

		Messagebox.show("La contraseña ingresada no coincide con el usuario.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorNoHayResgistrosParaRespaldo() {

		Messagebox.show("No hay nada a lo que hacer respaldo en el lapso seleccionado.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}
	
	public void ErrorNoEsXML() {

		Messagebox.show("La Extensión del Archivo no es XML.", 
				"Error",Messagebox.OK, Messagebox.ERROR);
	}
	

	public void confirmacionSalir() {

		Messagebox.show("¿Realmente desea salir?", "Confirmación",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
	}

	public void confirmacionCerrarSesion() {

		Messagebox.show("¿Está seguro de querer cerrar sesión?",
				"Confirmación", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION);

	}
	

}
