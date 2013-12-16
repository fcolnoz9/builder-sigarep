        zk.afterMount(function() {
            jq("$txtInstancia").Watermark("Nombre de la Instancia","gray");
            jq("$txtRecurso").Watermark("Nombre del Recurso","gray");
            jq("$txtDescripcion").Watermark("Ingrese alguna descripcion","gray");
            jq("$txtPregunta").Watermark("Introduzca la pregunta","gray");
            jq("$txtRespuesta").Watermark("Introduzca la respuesta","gray");
            jq("$txtTitulo").Watermark("Introduzca el titulo","gray");
            jq("$txtContenido").Watermark("Introduzca el contenido","gray");
            jq("$txtNombreFoto").Watermark("Introduzca el nombre","gray");
            jq("$txtEnlace").Watermark("Introduzca el enlace","gray");
            
            
            $.mask.definitions['A']='[A-Z]';
            $.mask.definitions['m']='[01]';
            $.mask.definitions['d']='[0123]';
            $.mask.definitions['y']='[12]';
             
            jq("$phone").mask("(999) 999-9999");
            jq("$date").mask("m9/d9/y999");
            jq("$country").mask("AA");
            jq("$cc").mask("9999-9999-9999-9999");
        });
