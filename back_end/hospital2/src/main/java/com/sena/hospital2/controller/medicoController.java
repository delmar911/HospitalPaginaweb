package com.sena.hospital2.controller;

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

import com.sena.hospital2.interfaceService.ImedicoService;
import com.sena.hospital2.model.medico;
@RequestMapping("/api/v1/medico")
@RestController
@CrossOrigin
public class medicoController {
	
	@Autowired
	private ImedicoService medicoService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(
			@ModelAttribute("medico") medico medico
			){
		medicoService.save(medico);
		return new ResponseEntity<>(medico,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaMedico=medicoService.findAll();
		return new ResponseEntity<>(ListaMedico, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id}")
		public ResponseEntity<Object> findOne(@PathVariable String id){
			var medico=medicoService.findOne(id);
			return new ResponseEntity<>(medico,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> delete(@PathVariable String id){
			 medicoService.delete(id);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id}")
		public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("medico")medico medicoUpdate){
			var medico = medicoService.findOne(id).get();
			if (medico != null) {
				medico.setTipo_documento(medicoUpdate.getTipo_documento());
				medico.setNumero_documento(medicoUpdate.getNumero_documento());
				medico.setPrimer_nombre(medicoUpdate.getPrimer_nombre());
				medico.setSegundo_nombre(medicoUpdate.getSegundo_nombre());
				medico.setPrimer_apellido(medicoUpdate.getPrimer_apellido());
				medico.setSegundo_apellido(medicoUpdate.getSegundo_apellido());
				medico.setCelular(medicoUpdate.getCelular());
				medico.setCorreo_electronico(medicoUpdate.getCorreo_electronico());
				medico.setDireccion(medicoUpdate.getDireccion());
				medico.setEstado(medicoUpdate.getEstado());
			medicoService.save(medico);
			return new ResponseEntity<>(medico, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error medico no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}
}