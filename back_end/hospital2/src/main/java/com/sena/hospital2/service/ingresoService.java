package com.sena.hospital2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.hospital2.interfaceService.IingresoService;
import com.sena.hospital2.interfaces.Iingreso;
import com.sena.hospital2.model.ingreso;

@Service
public class ingresoService 
implements IingresoService 
{
	
	@Autowired
	private Iingreso data;
	
	
	@Override
	public String save(ingreso ingreso) {
		data.save(ingreso);
		return ingreso.getId_ingreso();
	}

	@Override
	public List<ingreso> findAll() {
		List<ingreso> listaIngreso=
				(List<ingreso>) data.findAll();
		//(List<ingreso>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <ingreso>
		//- se convierte a list <ingreso>
		return listaIngreso;
	}
	
	@Override
	public List<ingreso> filtroIngreso(String filtro) {
		List<ingreso>ListaIngreso=data.findFilterIngreso(filtro);
		return ListaIngreso;
	}

    // la variable que almacena los registros
	@Override
	public List<ingreso> filtroEstado(String id_paciente) {
		List<ingreso>ListaIngreso=data.filtroEstado(id_paciente);
		return ListaIngreso;
	}

	@Override
	public List<ingreso> filtroFechaIngreso(LocalDateTime fecha_ingreso) {
		List<ingreso>ListaIngreso=data.filtroFechaIngreso(fecha_ingreso);
		return ListaIngreso;
	}

	@Override
	public Optional<ingreso> findOne(String id) {
		Optional<ingreso> ingreso=data.findById(id);
		return ingreso;
	}

	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}
}
