package com.sena.hospital2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.hospital2.interfaceService.IpacienteService;
import com.sena.hospital2.interfaces.Ipaciente;
import com.sena.hospital2.model.paciente;

@Service
public class pacienteService 
implements IpacienteService
{
	
	@Autowired
	private Ipaciente data;
	
	
	@Override
	public String save(paciente paciente) {
		data.save(paciente);
		return paciente.getId_paciente();
	}

	@Override
	public List<paciente> findAll() {
		List<paciente> listaPaciente=
				(List<paciente>) data.findAll();
		//(List<paciente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <paciente>
		//- se convierte a list <paciente>
		return listaPaciente;
	}
	
	@Override
	public List<paciente> filtroPaciente(String filtro) {
		List<paciente>ListaPaciente=data.filtroPaciente(filtro);
		return ListaPaciente;
	}
	
	
	@Override
	public Optional<paciente> findOne(String id) {
		Optional<paciente> paciente=data.findById(id);
		return paciente;
	}

	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}

	@Override
	public List<paciente>filtroCedulaPaciente(String numero_documento ) {
		List<paciente>ListaPaciente=data.filtroCedulaPaciente(numero_documento);
		return ListaPaciente;
	}
	
}

