package com.sena.hospital2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.hospital2.interfaceService.IingresoService;
import com.sena.hospital2.model.ingreso;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping("/api/v1/ingreso")
@RestController
@CrossOrigin

public class ingresoController {
	
	@Autowired
	private IingresoService ingresoService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(
			@ModelAttribute("ingreso") ingreso ingreso
			){
				// condicion para cuando ya exista el  registro 
				List<ingreso> listaPacienteActivo=ingresoService.filtroEstado(ingreso.getPaciente().getId_paciente());
			if(listaPacienteActivo.size()!=0){
				//ya tiene un registro activo
				return new ResponseEntity<>("el paciente ya tiene un ingreso activo",HttpStatus.BAD_REQUEST);		
			}

			List <ingreso> listaCamaIngresos=ingresoService.filtroCama(ingreso.getCama(), ingreso.getHabitacion());
			if(listaCamaIngresos.size()!=0){
				//ya tiene un registro activo
				return new ResponseEntity<>("la cama y la habitación ya se encuentran ocupada",HttpStatus.BAD_REQUEST);		
			}
		ingresoService.save(ingreso);
		return new ResponseEntity<>(ingreso,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaIngreso=ingresoService.findAll();
		return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> filtroIngreso(@PathVariable String filtro){
		var ListaIngreso=ingresoService.filtroIngreso(filtro);
		return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
	}

	@GetMapping("/busquedaFechaIngreso/{fecha_ingreso}")
	public ResponseEntity<Object> filtroFechaIngreso(@PathVariable LocalDateTime fecha_ingreso){
		var ListaIngreso=ingresoService.filtroFechaIngreso(fecha_ingreso);
		return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id}")
		public ResponseEntity<Object> findOne(@PathVariable String id){
			var ingreso=ingresoService.findOne(id);
			return new ResponseEntity<>(ingreso,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/eliminarPermanente/{id}")
		public ResponseEntity<Object> delete(@PathVariable String id){
			 ingresoService.delete(id);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id}")
		public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("ingreso")ingreso ingresoUpdate){
			var ingreso = ingresoService.findOne(id).get();
			if (ingreso != null) {
				ingreso.setHabitacion(ingresoUpdate.getHabitacion());
				ingreso.setCama(ingresoUpdate.getCama());
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

