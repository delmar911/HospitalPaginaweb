//se almacena la url de la api
let url = "http://localhost:8082/api/v1/ingreso/";
function listarIngreso() {
  $.ajax({
    url: url,
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
        celdaEditar.innerHTML =
          "<button onclick='editarIngreso(" +
          result[i]["id_ingreso"] +
          ")' class='btn btn-primary'>Editar</button>";

        //agregando a los td a su respectivo th y agregandolos a la fila

        trRegistro.appendChild(celdaId);
        trRegistro.appendChild(celdaHabitacion);
        trRegistro.appendChild(celdaCama);
        trRegistro.appendChild(celdaFechaIngreso);
        trRegistro.appendChild(celdaFechaSalida);
        trRegistro.appendChild(celdaIdMedico);
        trRegistro.appendChild(celdaIdPaciente);
        trRegistro.appendChild(celdaEstado);
        trRegistro.appendChild(celdaEditar);

        curpoTablaIngreso.appendChild(trRegistro); //se traen todos los registros
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
        title: "Se ha registrado correctamente!",
        showConfirmButton: false,
        timer: 1500
      });

      },
      error: function (error) {
       // alert("error al guardar".error);
        Swal.fire("Error", "Error al guardar", "error");
      },
    });
  } else {
    Swal.fire("Error", "Faltan campos por llenar!", "error");
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

