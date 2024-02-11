package com.sena.hospitalWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.hospitalWeb.interfaceService.IpacienteService;
import com.sena.hospitalWeb.interfaces.Ipaciente;
import com.sena.hospitalWeb.model.paciente;

public class pacienteService implements IpacienteService{
	@Autowired
	private Ipaciente data;
	
	
	@Override
	public String save(paciente paciente) {
		data.save(paciente);
		return paciente.getId();
	}

	@Override
	public List<paciente> findAll() {
		List<paciente> listapaciente=
				(List<paciente>) data.findAll();
		//(List<paciente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <paciente>
		//- se convierte a list <paciente>
		return listapaciente;
	}
	@Override
	public Optional<paciente> findOne(String id_paciente) {
		Optional<paciente> paciente=data.findById(id_paciente);
		return paciente;
	}

	@Override
	public int delete(String id_paciente) {
		data.deleteById(id_paciente);
		return 1;
	}
	
}
