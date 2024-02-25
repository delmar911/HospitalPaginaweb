//se almacena la url de la api
let url="http://localhost:8082/api/v1/medico/";
function listarMedico() {
    $.ajax({
        url:url,
        type: "GET",
        success: function(result){//success: funcion que se ejecuta cusndo la peticion tiene exito
            console.log(result);
            let curpoTablaMedico = document.getElementById("curpoTablaMedico");
            curpoTablaMedico.innerHTML="";
            for (let i = 0; i < result.length; i++) {
               //se crea una etiqueta tr por cada registro
                let trRegistro = document.createElement("tr");//fila por cada registro de la tabla
                let celdaId = document.createElement("td");
                let celdaTipoDocumento = document.createElement("td");
                let celdaNumeroDocumento = document.createElement("td");
                let celdaPrimerNombre = document.createElement("td");
                let celdaSegundoNombre = document.createElement("td");
                let celdaPrimerApellido = document.createElement("td");
                let celdaSegundoApellido = document.createElement("td");
                let celdaCelular = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaEstado = document.createElement("td");
                let celdaEditar = document.createElement("td");
                
                //almacenamos en valor
                
                celdaId.innerText = result[i]["id_medico"];
                celdaTipoDocumento.innerText= result[i]["tipo_documento"];
                celdaNumeroDocumento.innerText = result[i]["numero_documento"];
                celdaPrimerNombre.innerText = result[i]["primer_nombre"];
                celdaSegundoNombre.innerText = result[i]["segundo_nombre"];
                celdaPrimerApellido.innerText = result[i]["primer_apellido"];
                celdaSegundoApellido.innerText = result[i]["segundo_apellido"];
                celdaCelular.innerText = result[i]["celular"];
                celdaCorreo.innerText = result[i]["correo_electronico"];
                celdaEstado.innerText = result[i]["estado"];
                celdaEditar.innerHTML = "<button onclick='editarMedico("+result
                [i]["id_medico"]+")' class='btn btn-primary'>Editar</button>";
                
                
                //agregando a los td a su respectivo th y agregandolos a la fila

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaTipoDocumento);
                trRegistro.appendChild(celdaNumeroDocumento);
                trRegistro.appendChild(celdaPrimerNombre);
                trRegistro.appendChild(celdaSegundoNombre);
                trRegistro.appendChild(celdaPrimerApellido);
                trRegistro.appendChild(celdaSegundoApellido);
                trRegistro.appendChild(celdaCelular);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaEstado);
                trRegistro.appendChild(celdaEditar);

                curpoTablaMedico.appendChild(trRegistro);//se traen todos los registros

            }
        },
        error:function(error){
            alert("Error en la peticion ${error}");
        }
    })
}
//que es Cors
function registrarMedico() {
    
    let tipo_documento = document.getElementById("tipo_documento").value;
    let numero_documento = document.getElementById("numero_documento").value;
    let primer_nombre = document.getElementById("primer_nombre").value;
    let segundo_nombre = document.getElementById("segundo_nombre").value;
    let primer_apellido = document.getElementById("primer_apellido").value;
    let segundo_apellido = document.getElementById("segundo_apellido").value;
    let correo_electronico = document.getElementById("correo_electronico").value;
    let celular = document.getElementById("celular").value;
    let estado = document.getElementById("estado").value;

    let formData = {
        
        "tipo_documento": tipo_documento,
        "numero_documento": numero_documento,
        "primer_nombre": primer_nombre,
        "segundo_nombre": segundo_nombre,
        "primer_apellido": primer_apellido,
        "segundo_apellido": segundo_apellido,
        "celular": celular,
        "correo_electronico": correo_electronico,
        "estado": estado
    };

    if (validarCampos()) {
        $.ajax({
            url:url,
            type: "POST",
            data: formData,
            success: function (result){
                alert("se guardó correctamente");
            },
            error: function (error) {
                alert("error al guardar".error)
            }
        });
    }
    else{
        Swal.fire(
            'Error',
            'Faltan campos por llenar!',
            'error'
          )
    }
    
   
}
function validarCampos() {
    let numero_documento = document.getElementById("numero_documento");
    return validarNumeroDocumento(numero_documento);
}
function validarNumeroDocumento(cuadroNumero) {
    
    let valor = cuadroNumero.value;
    let valido = true;
    if (valor.length < 5 || valor.length > 11) {
        valido = false
    }

    if (valido) {
        cuadroNumero.className = "form-control is-valid"
    }
    else{
        cuadroNumero.className = "form-control is-invalid"
    }
    return valido;
}

