//se almacena la url de la api
let url="http://localhost:8082/api/v1/medico/";
function listarMedico() {
    var busqueda = document.getElementById("buscar").value;
    var urlBusqueda = url;
    if (busqueda!=""){
        urlBusqueda+="busquedafiltro/"+busqueda;
    }   
    $.ajax({
        url:urlBusqueda,
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
                let celdaDireccion = document.createElement("td")
                let celdaEstado = document.createElement("td");
                // let celdaEditar = document.createElement("td");
                
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
                celdaDireccion.innerText = result[i]["direccion"];
                celdaEstado.innerText = result[i]["estado"];

            

                // let buttonHTML = "<button class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#exampleModal'>Editar</button>";
                // let button = document.createElement('button');
                // button.classList.add('btn', 'btn-primary');
                // button.setAttribute('data-bs-toggle', 'modal');
                // button.setAttribute('data-bs-target', '#exampleModal');
                // button.setAttribute('id', result[i]["id_medico"]);
                // button.innerText = 'Editar' ;
                // celdaEditar.appendChild(button);
                
                
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
                trRegistro.appendChild(celdaDireccion);
                trRegistro.appendChild(celdaEstado);
                
                //boton editar 
                let celdaOpcion= document.createElement("td");
                let botonEditarMedico= document.createElement("button");
                botonEditarMedico.value=result[i]["id_medico"];
                botonEditarMedico.innerHTML="Editar"; 

                botonEditarMedico.onclick=function(e){
                    $('#exampleModal').modal('show');
                    consultarMedicoID(this.value); 
                }
                botonEditarMedico.className= "btn btn-primary"

                celdaOpcion.appendChild(botonEditarMedico); 
                trRegistro.appendChild(celdaOpcion);

                curpoTablaMedico.appendChild(trRegistro);//se traen todos los registros

            }
        },
        error:function(error){
            alert("Error en la peticion ${error}");
        }
    })
 
}

//Paso para crear el registro de un médico ****
function registrarMedico() {
    
    let tipo_documento = document.getElementById("tipo_documento").value;
    let numero_documento = document.getElementById("numero_documento").value;
    let primer_nombre = document.getElementById("primer_nombre").value;
    let segundo_nombre = document.getElementById("segundo_nombre").value;
    let primer_apellido = document.getElementById("primer_apellido").value;
    let segundo_apellido = document.getElementById("segundo_apellido").value;
    let correo_electronico = document.getElementById("correo_electronico").value;
    let direccion = document.getElementById("direccion").value;
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
        "direccion": direccion,
        "estado": estado
    };

    if(validarCampos()){

        $.ajax({
          url: url,
          type: "POST",
          data: formData,
          success: function(result){
            Swal.fire({
              title: "Excelente",
              text: "Su registro se guardó correctamente",
              icon: "success"
            });
            // window.location.href= "http://127.0.0.1:5500/front_end/pacienteRegistro.html";
          },
          error: function(error){
            Swal.fire("Error", "Error al guardar "+error.responseText, "error");
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


//Paso para que el usuario se registre y llene todos los datos correctamente :D****
function validarCampos() {
    var numero_documento = document.getElementById("numero_documento");
    var primer_nombre = document.getElementById("primer_nombre"); 
    var primer_apellido = document.getElementById("primer_apellido"); 
    var direccion=document.getElementById("direccion");
    var celular = document.getElementById("celular"); 

    return validarNumeroDocumento(numero_documento) && validarNombreApellido(primer_nombre) 
         && validarNombreApellido(primer_apellido) && validarCelular(celular) && validarDireccion(direccion);
}

function validarNumeroDocumento(cuadroNumero) {
    if (!cuadroNumero || !cuadroNumero.value) {
        return false;
    }

    let valor = cuadroNumero.value;
    let valido = true;
    if (valor.length < 5 || valor.length > 11) {
        valido = false;
    }

    if (valido) {
        cuadroNumero.className = "form-control is-valid";
    } else {
        cuadroNumero.className = "form-control is-invalid";
    }
    return valido;
}


function validarNombreApellido(campo){
    var valido=true;
    if(campo.value.length < 3 || campo.value.length > 30){
        valido=false;
    }

    if (valido) {
        campo.className = "form-control is-valid"
    }
    else{
        campo.className = "form-control is-invalid"
    }
    return valido;
}


function validarCelular(Numero) {
    
    let valor = Numero.value;
    let valido = true;
    if (valor.length < 10 || valor.length >10) {
        valido = false
    }

    if (valido) {
        Numero.className = "form-control is-valid"
    }
    else{
        Numero.className = "form-control is-invalid"
    }
    return valido;
}


function validarDireccion(Direccion){
    let valor = Direccion.value;
    let valido = true;
    if (valor.length <=0 || valor.length >50) {
        valido = false
    }
    if (valido) {
      Direccion.className = "form-control is-valid"
    }
    else{
      Direccion.className = "form-control is-invalid"
    }
    return valido;
}


//Cuando le damos click al boton de guardar, este llamara a la function updateMedico por medio del onclick******
function updateMedico() {
    var id_medico = document.getElementById("id_medico").value;

    let formData = {
        "tipo_documento": document.getElementById("tipo_documento").value,
        "numero_documento": document.getElementById("numero_documento").value,
        "primer_nombre": document.getElementById("primer_nombre").value,
        "segundo_nombre": document.getElementById("segundo_nombre").value,
        "primer_apellido": document.getElementById("primer_apellido").value,
        "segundo_apellido": document.getElementById("segundo_apellido").value,
        "celular": document.getElementById("celular").value,
        "correo_electronico": document.getElementById("correo_electronico").value,
        "direccion": document.getElementById("direccion").value,
        "estado": document.getElementById("estado").value
    };


    //Cuando estamos actualizando los datos, y lo hacemos correctamente Aparecerá la Alerta EXCELENTE *****
    if(validarCampos()){
    $.ajax({
        url: url + id_medico,
        type: "PUT",
        data: formData,
        success: function(result) {
            Swal.fire({
                title: "Excelente",
                text: "Su registro se actualizó correctamente",
                icon: "success"
            });
            
            var modal = document.getElementById("exampleModal"); 
            modal.style.display = "hide";
            
            listarMedico(); //Lista los médicos después de actualizar
        },
        error: function(error) {
            Swal.fire("Error", "Error al guardar", "error");
        }  
    });
    }else{
        Swal.fire({
            title: "Error!",
            text: "complete los campos correctamente",
            icon: "error"
        });
        }
}


/* metodo para obtener los datos en el modal de actualizar*/ 
//1.Crear petición que traiga la información del medico por id
function consultarMedicoID(id){
    //alert(id);
    $.ajax({
        url:url+id,
        type:"GET",
        success: function(result){
            
            document.getElementById("id_medico").value=result["id_medico"];
            document.getElementById("tipo_documento").value=result["tipo_documento"];
            document.getElementById("numero_documento").value=result["numero_documento"];
            document.getElementById("primer_nombre").value=result["primer_nombre"];
            document.getElementById("segundo_nombre").value=result["segundo_nombre"];
            document.getElementById("primer_apellido").value=result["primer_apellido"];
            document.getElementById("segundo_apellido").value=result["segundo_apellido"];
            document.getElementById("celular").value=result["celular"];
            document.getElementById("correo_electronico").value=result["correo_electronico"];
            document.getElementById("direccion").value=result["direccion"];
            document.getElementById("estado").value=result["estado"];
        }
    });
}
function limpiar(){
    document.getElementById("numero_documento").className="form-control";
    document.getElementById("primer_nombre").className="form-control";
    document.getElementById("primer_apellido").className="form-control";
    document.getElementById("celular").className="form-control";
    document.getElementById("correo_electronico").className="form-control";
    document.getElementById("direccion").className="form-control";
    document.getElementById("estado").className="form-control";
    document.getElementById("tipo_documento").value = "";
    document.getElementById("numero_documento").value = "";
    document.getElementById("primer_nombre").value = "";
    document.getElementById("segundo_nombre").value = "";
    document.getElementById("primer_apellido").value = "";
    document.getElementById("segundo_apellido").value = "";
    document.getElementById("celular").value = "";
    document.getElementById("correo_electronico").value = "";
    document.getElementById("direccion").value = "";
    document.getElementById("estado").value="";
}





