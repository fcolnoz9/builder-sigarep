package sigarep.herramientas;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

/*
 * Debe declarar una variable tipo MensajesAlUsuario como est�ndar mensajeAlUsuario(MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();) en donde usar� los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar alg�n mensaje coloca mensajeAlUsuario.NombreDelMetodo();
 * por ejemplo: mensajeAlUsuario.advertenciaLlenarCampos();
 * Si necesita alg�n otro mensaje agr�guelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del �ltimo de advertencia.
 * 
 * */

public class MensajesAlUsuario {

	public void advertenciaLlenarCampos() {

		Messagebox.show("�Debe llenar todos los campos!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

//	public void advertenciaContrase�aVacia() {
//
//		Messagebox.show("La nueva contrase�a no puede ser vac�a.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
//no se usa
	public void advertenciaIngresarCedula() {

		Messagebox.show("�Debe ingresar una c�dula!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void advertenciaMenudelGrupoVacio() {

		Messagebox.show("�Debe agregar al menos una funci�n al men� del grupo!",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarGrupoUsuario() {

		Messagebox.show("Debe seleccionar al menos un grupo.", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarOpcion() {
		Messagebox.show("Debe seleccionar alguna opci�n para continuar.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarImagen() {

		Messagebox.show("�Debe Cargar una Imagen!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaCargarDocumento() {

		Messagebox.show("�Debe Cargar un Documento!", "Advertencia",
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
		Messagebox.show("Debe agregar una observaci�n general del caso.",
				"Advertencia", Messagebox.OK,Messagebox.EXCLAMATION);
    }
	
	public void advertenciaSeleccionarLapso() {

		Messagebox.show("�Debe seleccionar un lapso acad�mico!", "Advertencia",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void advertenciaSeleccionarDestinoRespaldo() {

		Messagebox.show("Debe seleccionar el destino para el respaldo.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

//	public void advertenciaSeleccionarUbicacionRespaldo() {
//
//		Messagebox.show("Debe seleccionar la ubicaci�n del respaldo a restaurar.",
//				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
//	}
	
	public void advertenciaEscribirNombreDeRespaldo() {
		Messagebox.show("Debe escribir el nombre del archivo de respaldo.",
		"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }
	
	public void advertenciaContrasennasNoCoinciden() {
		Messagebox.show("Las contrase�as no coinciden.","Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
     }

	public void advertenciaNoExisteEstudianteSancionado() {

		Messagebox.show("Esta c�dula no est� en la lista de Estudiantes Sancionados.",
				"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	
	public void advertenciaGrupoYaExistente(String nombreGrupo) {

		Messagebox.show("�El grupo con nombre "+nombreGrupo+" ya se encuentra registrado!.",
				"Advertencia",Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void informacionHemosEnviadoCorreo() {

		Messagebox.show("Le hemos enviado un email con su nombre de usuario y contrase�a.",
						"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaAtualizada() {

		Messagebox.show("Se ha actualizado su contrase�a con �xito.",
				"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionContrasennaRecuperada() {

		Messagebox.show("Hemos enviado un email con su contrase�a.",
				"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public static void informacionRegistroCorrectoStatic() {

		Messagebox.show("Se ha registrado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRegistroCorrecto() {

		Messagebox.show("Se ha registrado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionEliminarCorrecto() {

		Messagebox.show("Se ha eliminado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionFinalizarLapsoExitoso() {

		Messagebox.show("�Lapso Acad�mico finalizado exitosamente!",
				"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionOperacionExitosa() {

		Messagebox.show("�Operaci�n realizada exitosamente!", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionCreacionHistorico(String nombreHistorico) {

		Messagebox.show("Se ha Creado un archivo hist�rico bajo el nombre de: "
				+ "" + nombreHistorico + ".sql", "Informacion", Messagebox.OK,
				Messagebox.INFORMATION);

	}

	public void informacionArchivoCargado() {

		Messagebox.show("Archivo cargado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);

	}

	public void informacionArchivoEliminado() {

		Messagebox.show("Archivo eliminado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionVeredictoRegistrado() {
		Messagebox.show("Veredicto registrado correctamente.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionFinalizarVeredictoApelacionesProcesadas() {
		Messagebox.show("No existen apelaciones para procesar.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	


//	public void informacionGuardarDatosSesion(int contador) {
//		Messagebox.show("Se almacenaron correctamente los datos de la sesi�n a "
//						+ contador + " apelaciones.", "Informaci�n",
//				Messagebox.OK, Messagebox.INFORMATION);
//	} no se usa por ahora

//	public void informacionDatosDeSesionNoEncontrados() {
//		Messagebox.show("Proporcione los datos de una nueva sesi�n.",
//				"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
//	}no se usa por ahora

	public void informacionDatosDeSesionEncontrados() {
		Messagebox.show("Se encontr� una Sesi�n activa. Puede continuar con la misma o indicar los datos de una nueva.",
						"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRestauracionNoExitosa() {
		Messagebox.show("El intento de restauraci�n no fue exitoso.",
				"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El intento de respaldo no fue exitoso.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionRespaldoExitoso() {
		Messagebox.show("Respaldo realizado de manera exitosa.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void informacionNoHayCoincidencias() {
		Messagebox.show("No Hay coincidencias para mostrar.", "Informaci�n",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	public void ErrorRestauracionEnProceso() {

		Messagebox.show("Restauraci�n en proceso, debe esperar mientras el proceso es completado.",
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
				+ "Existen apelaciones sin n�mero de sesi�n.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorFinalizarLapsoCronograma() {

		Messagebox.show("No puede finalizar el lapso actual. "
				+ "Existen actividades sin ejecutarse en el cronograma.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorLapsoActivoNoExistente() {

		Messagebox.show("No existe un lapso acad�mico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorNoHayVeredictosRegistrados() {

		Messagebox.show("No se ha podido finalizar. No hay veredictos registrados.",
				"Error", Messagebox.OK, Messagebox.ERROR);

	}

	public void ErrorLapsoActivoExistente() {

		Messagebox.show("Ya existe un lapso acad�mico activo.", "Error",
				Messagebox.OK, Messagebox.ERROR);

	}


	public void ErrorRangoFechas() {

		Messagebox.show("Error de rango de fechas.", "Error", Messagebox.OK,
				Messagebox.ERROR);

	}

	public void ErrorNoCoincidenLasContrasenias() {

		Messagebox.show("La nueva contrase�a y la contrase�a de confirmaci�n no coinciden.",
						"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorNoCoincideContraseniaUsuario() {

		Messagebox.show("La contrase�a ingresada no coincide con el usuario.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}

	public void ErrorNoHayResgistrosParaRespaldo() {

		Messagebox.show("No hay nada a lo que hacer respaldo en el lapso seleccionado.",
				"Error", Messagebox.OK, Messagebox.ERROR);
	}
	
	public void ErrorNoEsXML() {

		Messagebox.show("La Extensi�n del Archivo no es XML.", 
				"Error",Messagebox.OK, Messagebox.ERROR);
	}
	

	public void confirmacionSalir() {

		Messagebox.show("�Realmente desea salir?", "Confirmaci�n",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION);
	}

	public void confirmacionCerrarSesion() {

		Messagebox.show("�Est� seguro de querer cerrar sesi�n?",
				"Confirmaci�n", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION);

	}
	

}
