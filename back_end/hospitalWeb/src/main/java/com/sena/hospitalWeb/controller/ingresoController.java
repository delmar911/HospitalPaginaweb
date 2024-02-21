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

import com.sena.hospitalWeb.interfaceService.IingresoService;
import com.sena.hospitalWeb.model.ingreso;

@RequestMapping("/api/v1/ingreso")
@RestController
@CrossOrigin
public class ingresoController {
	@Autowired
	private IingresoService ingresoService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@ModelAttribute("ingreso") ingreso ingreso){
		ingresoService.save(ingreso);
		return new ResponseEntity<>(ingreso,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var Listaingresos=ingresoService.findAll();
		return new ResponseEntity<>(Listaingresos, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id_ingreso}")
		public ResponseEntity<Object> findOne(@PathVariable String id_ingreso){
			var ingreso=ingresoService.findOne(id_ingreso);
			return new ResponseEntity<>(ingreso,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/{id_ingreso}")
		public ResponseEntity<Object> delete(@PathVariable String id_ingreso){
			 ingresoService.delete(id_ingreso);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id_ingreso}")
		public ResponseEntity<Object> update(@PathVariable String id_ingreso, @ModelAttribute("ingreso")ingreso ingresoUpdate){
			var ingreso = ingresoService.findOne(id_ingreso).get();
			if (ingreso != null) {
				ingreso.setHabitacion(ingresoUpdate.getHabitacion());
				ingreso.setCama(ingresoUpdate.getCama());
				ingreso.setId_paciente(ingresoUpdate.getId_paciente());
				ingreso.setId_medico(ingresoUpdate.getId_medico());
				ingreso.setFecha_ingreso(ingresoUpdate.getFecha_ingreso());
				ingreso.setFecha_salida(ingresoUpdate.getFecha_salida());
				ingreso.setEstado(ingresoUpdate.getEstado());
			ingresoService.save(ingreso);
			return new ResponseEntity<>(ingreso, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error ingreso no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}
}
