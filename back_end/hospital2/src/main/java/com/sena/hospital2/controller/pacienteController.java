package com.sena.hospital2.controller;

import java.util.List;

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

import com.sena.hospital2.interfaceService.IpacienteService;
import com.sena.hospital2.model.paciente;


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
				// condicion para cuando ya exista el  registro 
				List<paciente>listaPacienteValidacion=pacienteService.filtroCedulaPaciente(paciente.getNumero_documento());
			if(listaPacienteValidacion.size()!=0){
				//ya tiene un registro activo
				return new ResponseEntity<>("El paciente ya se encuentra registrado",HttpStatus.BAD_REQUEST);		
			}
			if (paciente.getTipo_documento().equals("")) {
				return new ResponseEntity<>("El tipo documento es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getNumero_documento().equals("")) {
				return new ResponseEntity<>("El número de documento es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getPrimer_nombre().equals("")) {
				return new ResponseEntity<>("El primer nombre es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getPrimer_apellido().equals("")) {
				return new ResponseEntity<>("El primer apellido es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getCorreo_electronico().equals("")) {
				return new ResponseEntity<>("El correo electronico es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getCelular().equals("")) {
				return new ResponseEntity<>("El número celular es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getDireccion().equals("")) {
				return new ResponseEntity<>("La dirección es obligatoria", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getEstado().equals("")) {
				return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (paciente.getCelularPersonaContacto().equals("")) {
				return new ResponseEntity<>("El número de persona de contacto es obligatorio", HttpStatus.BAD_REQUEST);
			}	
			if (paciente.getNombrePersonaContacto().equals("")) {
				return new ResponseEntity<>("El nombre de persona de contacto es obligatorio", HttpStatus.BAD_REQUEST);
			}	
		pacienteService.save(paciente);
		return new ResponseEntity<>(paciente,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var listaPaciente=pacienteService.findAll();
		return new ResponseEntity<>(listaPaciente, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
		var ListaPaciente=pacienteService.filtroPaciente(filtro);
		return new ResponseEntity<>(ListaPaciente, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id}")
		public ResponseEntity<Object> findOne(@PathVariable String id){
			var paciente=pacienteService.findOne(id);
			return new ResponseEntity<>(paciente,HttpStatus.OK);
			
		}
		
	// @DeleteMapping("/eliminarPermanente/{id}")
		// public ResponseEntity<Object> delete(@PathVariable String id){
		// 	 pacienteService.delete(id);
		// 			return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		// }
        
		//eliminado logico
		@DeleteMapping("/{id_paciente}")
		public ResponseEntity<Object> delete(@PathVariable String id_paciente){
			 pacienteService.delete(id_paciente);
					return new ResponseEntity<>("Registro Deshabilitado",HttpStatus.OK);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("paciente")paciente pacienteUpdate){
			var paciente = pacienteService.findOne(id).get();
			if (paciente != null) {
				paciente.setTipo_documento(pacienteUpdate.getTipo_documento());
				paciente.setNumero_documento(pacienteUpdate.getNumero_documento());
				paciente.setPrimer_nombre(pacienteUpdate.getPrimer_nombre());
				paciente.setSegundo_nombre(pacienteUpdate.getSegundo_nombre());
				paciente.setPrimer_apellido(pacienteUpdate.getPrimer_apellido());
				paciente.setSegundo_apellido(pacienteUpdate.getSegundo_apellido());
				paciente.setCelular(pacienteUpdate.getCelular());
				paciente.setDireccion(pacienteUpdate.getDireccion());
				paciente.setCorreo_electronico(pacienteUpdate.getCorreo_electronico());
				paciente.setNombrePersonaContacto(pacienteUpdate.getNombrePersonaContacto());
				paciente.setCelularPersonaContacto(pacienteUpdate.getCelularPersonaContacto());
				paciente.setEstado(pacienteUpdate.getEstado());
				pacienteService.save(paciente);
			return new ResponseEntity<>(paciente, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error paciente no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}

}
