package com.sena.hospitalWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.hospitalWeb.interfaceService.IpacienteService;
import com.sena.hospitalWeb.model.paciente;

@RequestMapping("/api/v1/paciente")
@RestController
@CrossOrigin
public class pacienteController {

	@Autowired
	private IpacienteService pacienteService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(
			@ModelAttribute("paciente") paciente paciente
			){
		pacienteService.save(paciente);
		return new ResponseEntity<>(paciente,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var Listapacientes=pacienteService.findAll();
		return new ResponseEntity<>(Listapacientes, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id_paciente}")
		public ResponseEntity<Object> findOne(@PathVariable String id_paciente){
			var paciente=pacienteService.findOne(id_paciente);
			return new ResponseEntity<>(paciente,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/{id_paciente}")
		public ResponseEntity<Object> delete(@PathVariable String id_paciente){
			 pacienteService.delete(id_paciente);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id_paciente}")
		public ResponseEntity<Object> update(@PathVariable String id_paciente, @ModelAttribute("paciente")paciente pacienteUpdate){
			var paciente = pacienteService.findOne(id_paciente).get();
			if (paciente != null) {
				paciente.setTipo_documento(pacienteUpdate.getTipo_documento());
				paciente.setNumero_documento(pacienteUpdate.getNumero_documento());
				paciente.setPrimer_nombre(pacienteUpdate.getPrimer_nombre());
				paciente.setSegundo_nombre(pacienteUpdate.getSegundo_nombre());
				paciente.setPrimer_apellido(pacienteUpdate.getPrimer_apellido());
				paciente.setSegundo_apellido(pacienteUpdate.getSegundo_apellido());
				paciente.setCelular(pacienteUpdate.getCelular());
				paciente.setCorreo_electronico(pacienteUpdate.getCorreo_electronico());
				paciente.setNombrePersonaContacto(pacienteUpdate.getNombrePersonaContacto());
				paciente.setCelularPersonaContacto(pacienteUpdate.getCelularPersonaContacto());
				pacienteService.save(paciente);
			return new ResponseEntity<>(paciente, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error paciente no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}

}
