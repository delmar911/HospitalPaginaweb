//se almacena la url de la api
let url="http://localhost:8080/api/v1/paciente/";
function listarPaciente() {
    $.ajax({
        url:url,
        type: "GET",
        success: function(result){//success: funcion que se ejecuta cusndo la peticion tiene exito
            console.log(result);
            let cuerpoTabla = document.getElementById("cuerpoTabla");
            cuerpoTabla.innerHTML="";
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
                let celdaTelefono = document.createElement("td");
                let celdaCorreo = document.createElement("td");
                let celdaDireccion = document.createElement("td"); 
                let celdaEstado = document.createElement("td");
                let celdaEditar = document.createElement("td");
                
                //almacenamos en valor
                
                celdaId.innerText = result[i]["celdaId"];
                celdaTipoDocumento.innerText= result[i]["celdaTipoDocumento"];
                celdaNumeroDocumento.innerText = result[i]["celdaNumeroDocumento"];
                celdaPrimerNombre.innerText = result[i]["celdaPrimerNombre"];
                celdaSegundoNombre.innerText = result[i]["celdaSegundoNombre"];
                celdaPrimerApellido.innerText = result[i]["celdaPrimerApellido"];
                celdaSegundoApellido.innerText = result[i]["celdaSegundoApellido"];
                celdaTelefono.innerText = result[i]["celdaTelefono"];
                celdaCorreo.innerText = result[i]["celdaCorreo"];
                celdaDireccion.innerText = result[i]["celdaDireccion"];
                celdaEstado.innerText = result[i]["celdaEstado"];
                celdaEditar.innerHTML = "<button onclick='editarmMedico("+result
                [i]["id"]+")' class='btn btn-primary'>Editar</button>";
                
                
                //agregando a los td a su respectivo th y agregandolos a la fila

                trRegistro.appendChild(celdaId);
                trRegistro.appendChild(celdaTipoDocumento);
                trRegistro.appendChild(celdaNumeroDocumento);
                trRegistro.appendChild(celdaPrimerNombre);
                trRegistro.appendChild(celdaSegundoNombre);
                trRegistro.appendChild(celdaPrimerApellido);
                trRegistro.appendChild(celdaSegundoApellido);
                trRegistro.appendChild(celdaTelefono);
                trRegistro.appendChild(celdaCorreo);
                trRegistro.appendChild(celdaDireccion);
                trRegistro.appendChild(celdaEstado);
                trRegistro.appendChild(celdaEditar);

                cuerpoTabla.appendChild(trRegistro);//se traen todos los registros

            }
        },
        error:function(error){
            alert("Error en la peticion ${error}");
        }
    })
}
//que es Cors
function RegistrarPaciente() {

    let tipo_documento = document.getElementById("Tipo_documento").value;
    let numero_documento = document.getElementById("txtNumero_identificacion").value;
    let primer_nombre = document.getElementById("txtPrimer_Nombre").value;
    let segundo_nombre = document.getElementById("txtSegundo_Nombre").value;
    let primer_apellido = document.getElementById("txtPrimer_Apellido").value;
    let segundo_apellido = document.getElementById("txtSegundo_Apellido").value;
    let correo = document.getElementById("txtCorreo_electronico").value;
    let telefono = document.getElementById("txtNumero_Telefonico").value;
    let direccion = document.getElementById("txtDireccion").value;
    let estado = document.getElementById("Estado").value;


    let formData = {
        "tipo_documento": tipo_documento,
        "numero_documento": numero_documento,
        "primer_nombre": primer_nombre,
        "segundo_nombre": segundo_nombre,
        "primer_apellido": primer_apellido,
        "segundo_apellido": segundo_apellido,
        "telefono": telefono,
        "correo_electronico": correo,
        "direccion": direccion,
        "estado": estado
    };

    if(validarCampos()){
        $.ajax({
          url: url,
          type: "POST",
          data: formData,
          success: function(reslt){
            Swal.fire({
              title: "Exelengte",
              text: "su registro se guardo correctamente",
              icon: "success"
            });
            window.location.href= "http://localhost:5500/view/listarClientes.html";
          },
          error: function(error){
            alert("error al guardar".error);
          }
        });
      }else{
       // alert("llena los campos correctamente")
        Swal.fire({
          title: "Error!",
          text: "complete los campos correctamente",
          icon: "error"
        });
      }
    }

    function validarNumeroIdentificacion(cuadroNumero){
        var valor=cuadroNumero.value; 
        var valido=true; 
        if(valor.length<5 || valor.length>11){
          valido=false
        }
        if (valido) {
          //cuadro de texto cumple
          //se modifica la clase del cuadro de textp
          cuadroNumero.className="form-control is-valid";
        }else{
        //cuadro de texto no cumple 
        cuadroNumero.className="form-control is-invalid";
        }
      return valido; 
      }
      function validarCampos(){
        var numero_identificacion =document.getElementById("txtNumero_Identificacion"); 
        return validarNumeroIdentificacion(numero_identificacion); 
      }





