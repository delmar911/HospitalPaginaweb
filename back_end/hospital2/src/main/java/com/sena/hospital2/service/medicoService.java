package com.sena.hospital2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.hospital2.interfaceService.ImedicoService;
import com.sena.hospital2.interfaces.Imedico;
import com.sena.hospital2.model.medico;

@Service
public class medicoService 
implements ImedicoService 
{

	@Autowired
	private Imedico data;
	
	
	@Override
	public String save(medico medico) {
		data.save(medico);
		return medico.getId_medico();
	}

	@Override
	public List<medico> findAll() {
		List<medico> listaMedico=
				(List<medico>) data.findAll();
		//(List<medico>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <medico>
		//- se convierte a list <medico>
		return listaMedico;
	}
	@Override
	public Optional<medico> findOne(String id) {
		Optional<medico> medico=data.findById(id);
		return medico;
	}

	@Override
	public int delete(String id) {
		data.deleteById(id);
		return 1;
	}
	
}
