//se almacena la url de la api
let url = "http://localhost:8082/api/v1/ingreso/";
function listarIngreso() {
  var busqueda = document.getElementById("buscar").value;
  var urlBusqueda = url;
  if (busqueda!=""){
      urlBusqueda+="busquedafiltro/"+busqueda;
  }
 
  $.ajax({
    url: urlBusqueda,
    type: "GET",
    success: function (result) {
      //success: funcion que se ejecuta cusndo la peticion tiene exito
      console.log(result);
      let curpoTablaIngreso = document.getElementById("curpoTablaIngreso");
      curpoTablaIngreso.innerHTML = "";
      for (let i = 0; i < result.length; i++) {
        //se crea una etiqueta tr por cada registro
        let trRegistro = document.createElement("tr"); //fila por cada registro de la tabla
        let celdaId = document.createElement("td");
        let celdaHabitacion = document.createElement("td");
        let celdaCama = document.createElement("td");
        let celdaFechaIngreso = document.createElement("td");
        let celdaFechaSalida = document.createElement("td");
        let celdaIdMedico = document.createElement("td");
        let celdaIdPaciente = document.createElement("td");
        let celdaEstado = document.createElement("td"); 
        let celdaEditar = document.createElement("td");

        //almacenamos en valor

        celdaId.innerText = result[i]["id_ingreso"];
        celdaHabitacion.innerText = result[i]["habitacion"];
        celdaCama.innerText = result[i]["cama"];
        celdaFechaIngreso.innerText = result[i]["fecha_ingreso"];
        celdaFechaSalida.innerText = result[i]["fecha_salida"];
        celdaIdMedico.innerText = nombre_completo_medico =
          result[i]["medico"]["primer_nombre"] +
          " " +
          result[i]["medico"]["segundo_nombre"] +
          " " +
          result[i]["medico"]["primer_apellido"] +
          " " +
          result[i]["medico"]["segundo_apellido"];

        celdaIdPaciente.innerText = nombre_completo =
          result[i]["paciente"]["primer_nombre"] +
          " " +
          result[i]["paciente"]["segundo_nombre"] +
          " " +
          result[i]["paciente"]["primer_apellido"] +
          " " +
          result[i]["paciente"]["segundo_apellido"];
        celdaEstado.innerText = result[i]["estado"];
       

        //agregando a los td a su respectivo th y agregandolos a la fila

        trRegistro.appendChild(celdaId);
        trRegistro.appendChild(celdaHabitacion);
        trRegistro.appendChild(celdaCama);
        trRegistro.appendChild(celdaFechaIngreso);
        trRegistro.appendChild(celdaFechaSalida);
        trRegistro.appendChild(celdaIdMedico);
        trRegistro.appendChild(celdaIdPaciente);
        trRegistro.appendChild(celdaEstado);
    

         //boton editar 
         let celdaOpcion= document.createElement("td");
         let botonEditarIngreso= document.createElement("button");
         botonEditarIngreso.value=result[i]["id_ingreso"];
         botonEditarIngreso.innerHTML="Editar"; 

         botonEditarIngreso.onclick=function(e){
             $('#exampleModal').modal('show');
             CargarFormulario();
             consultarIngresoID(this.value);
            
         }
         botonEditarIngreso.className= "btn btn-primary"

         celdaOpcion.appendChild(botonEditarIngreso); 
         trRegistro.appendChild(celdaOpcion);

         curpoTablaIngreso.appendChild(trRegistro); //se traen todos los registros
          //boton desahiblitar- la funcion de deshabilitar se encuentra abajo 
          let botonDeshabilitaringreso= document.createElement("button");
          botonDeshabilitaringreso.innerHTML="<i class='fa-solid fa-trash'></i>"; 
          botonDeshabilitaringreso.className="btn btn-danger"; 

          let ingresoIdParaDeshabilitar= result[i]["id_ingreso"]; 
          botonDeshabilitaringreso.onclick=function(){
            deshabilitarIngreso(ingresoIdParaDeshabilitar);
          }
          celdaOpcion.appendChild(botonDeshabilitaringreso); 
          trRegistro.appendChild(celdaOpcion)
        }
    },
    error: function (error) {
      alert("Error en la peticion ${error}");
    },
  });
  
}
//que es Cors
function registrarIngreso() {
  let habitacion = document.getElementById("habitacion").value;
  let cama = document.getElementById("cama").value;
  let fecha_ingreso = document.getElementById("fecha_ingreso").value;
  let fecha_salida = document.getElementById("fecha_salida").value;
  let medico = document.getElementById("medico").value;
  let paciente = document.getElementById("paciente").value;
  let estado = document.getElementById("estado").value;

  let formData = {
    habitacion: habitacion,
    cama: cama,
    fecha_ingreso: fecha_ingreso,
    fecha_salida: fecha_salida,
    medico: medico,
    paciente: paciente,
    estado: estado,
  };

  if (validarCampos()) {
    $.ajax({
      url: url,
      type: "POST",
      data: formData,
      success: function (result) {
     //   alert("se guardó correctamente");
        
      Swal.fire({
        position: "center",
        icon: "success",
        title: "¡Se ha registrado correctamente!",
        showConfirmButton: false,
        timer: 1500
      });

      },
      error: function (error) {
       // alert("error al guardar".error);
        Swal.fire("Alerta", "¡Error al guardar! "+error.responseText, "warning");
      },
    });
  } else {
    Swal.fire("Error", "¡Faltan campos por llenar!", "error");
  }
}
function validarCampos() {
  let habitacion = document.getElementById("habitacion");
  return validarNumeroHabitacion(habitacion);
}

function validarNumeroHabitacion(cuadroNumero) {
    
  let valor = cuadroNumero.value;
  let valido = true;
  if (valor.length < 1 || valor.length > 4) {
      valido = false;
  }

  if (valido) {
      cuadroNumero.className = "form-control is-valid"
  }
  else{
      cuadroNumero.className = "form-control is-invalid"
  }
  return valido;
}


function validarNumeroCama(cuadroNumero) {
    
  let valor = cuadroNumero.value;
  let valido = true;
  if (valor.length < 1 || valor.length > 4) {
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


function CargarFormulario() {
  cargarMedico();
  cargarPaciente();
}
//funcion para traer los medicos
function cargarMedico() {
  let urlMedico = "http://localhost:8082/api/v1/medico/";

  $.ajax({
    url: urlMedico,
    type: "GET",
    success: function (result) {
      let medico = document.getElementById("medico");
      medico.innerHTML = "";
      for (let i = 0; i < result.length; i++) {
        let nombreMedico = document.createElement("option");
        nombreMedico.value = result[i]["id_medico"];
        nombreMedico.innerText = nombre_completo_medico =
          result[i]["primer_nombre"] +
          " " +
          result[i]["segundo_nombre"] +
          " " +
          result[i]["primer_apellido"] +
          " " +
          result[i]["segundo_apellido"];
        medico.appendChild(nombreMedico);
      }
    },
  });
}
//funcion para traer los pacientes
function cargarPaciente() {
  let urlpaciente = "http://localhost:8082/api/v1/paciente/";

  $.ajax({
    url: urlpaciente,
    type: "GET",
    success: function (result) {
      let paciente = document.getElementById("paciente");
      paciente.innerHTML = "";
      for (let i = 0; i < result.length; i++) {
        let nombrepaciente = document.createElement("option");
        nombrepaciente.value = result[i]["id_paciente"];
        nombrepaciente.innerText = nombre_completo_paciente =
          result[i]["primer_nombre"] +
          " " +
          result[i]["segundo_nombre"] +
          " " +
          result[i]["primer_apellido"] +
          " " +
          result[i]["segundo_apellido"];
        paciente.appendChild(nombrepaciente);
      }
    },
  });
}

function updateIngreso() {
  var id_ingreso=document.getElementById("id_ingreso").value;
  consultarIngresoID(id_ingreso);
  let formData = {
   // "id_ingreso" : document.getElementById("id_ingreso").value,
    "habitacion": document.getElementById("habitacion").value,
    "cama" : document.getElementById("cama").value,
    "fecha_ingreso" : document.getElementById("fecha_ingreso").value,
    "fecha_salida" : document.getElementById( "fecha_salida" ).value,
    "medico" : document.getElementById("medico").value,
    "paciente" : document.getElementById("paciente").value,
    "estado" : document.getElementById("estado").value
    
  };
 
  

  //Cuando estamos actualizando los datos, y lo hacemos correctamente Aparecerá la Alerta EXCELENTE *****
  if(validarCampos()){
  $.ajax({
      url: url+id_ingreso,
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
          
          //CargarFormulario();
          listarIngreso(); //Lista los médicos después de actualizar
      },
      error: function(error) {
          Swal.fire("Error", "Error al guardar", "error");
      }  
  });
  }
  else{
      Swal.fire({
          title: "Error!",
          text: "complete los campos correctamente",
          icon: "error"
      });
      }
}


/* metodo para obtener los datos en el modal de actualizar*/ 
//1.Crear petición que traiga la información del medico por id
function consultarIngresoID(id_ingreso){
  //alert(id);
  $.ajax({
      url:url+id_ingreso,
      type:"GET",
      success: function(result){
          document.getElementById("id_ingreso").value=result["id_ingreso"];
          document.getElementById("habitacion").value=result["habitacion"];
          document.getElementById("cama").value=result["cama"];
          document.getElementById("fecha_ingreso").value = result["fecha_ingreso"];
          document.getElementById("fecha_salida").value=result["fecha_salida"];
          document.getElementById("medico").value=result[ "medico"]["id_medico"];
          document.getElementById("paciente").value=result[ "paciente"]["id_paciente"];
          document.getElementById("estado").value=result[ "estado"];

      }
  });
}
function filtro(){
  //alert(id);
  $.ajax({
      url:url+busquedafiltro,
      type:"GET",
      success: function(result){
          //document.getElementById("id_ingreso").value=result["id_ingreso"];
         

      }
  });
}
function limpiar(){
  document.getElementById("habitacion").className="form-control";
  document.getElementById("cama").className="form-control";
  document.getElementById("fecha_ingreso").className="form-control";
  document.getElementById("fecha_salida").className="form-control";
  document.getElementById("medico").className="form-control";
  document.getElementById("paciente").className="form-control";
  document.getElementById("estado").className="form-control";
 document.getElementById("habitacion").value="";
 document.getElementById("cama").value="";
 document.getElementById("fecha_ingreso").value="";
 document.getElementById("fecha_salida").value="";
 document.getElementById("medico").value="";
 document.getElementById("paciente").value="";
 document.getElementById("estado").value="";
}
// funcion  de deshabilitar ingreso
function deshabilitarIngreso(id){
  swal.fire({
    title: '¿Estás seguro?',
    text: "Esta opción no tiene marcha atrás",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor:'#3085d6',
    cancelButtonColor:'#d33',
    cancelButtonText:'Cancelar',
    confirmButtonText:'Sí, deshabilitar!',

  }).then((result)=>{
    if (result.isConfirmed){
      $.ajax({
        url: url +id,
        type: "DELETE",
        success: function(result){
          swal.fire(
            'Deshabilitado',
            'El registro ha sido deshabilitado ',
            'success'
          );
          listarIngreso();//recarga la lista de ingresos
        },
        error: function(error){
          Swal.fire(
            'Error',
            'No se puede deshabilitar el registro ',
            'Error',
          );
        }
      });
    }
  });
}

