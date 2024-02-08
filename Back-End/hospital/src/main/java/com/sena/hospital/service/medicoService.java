package com.sena.hospital.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.hospital.interfaceService.ImedicoService;
import com.sena.hospital.interfaces.Imedico;
import com.sena.hospital.model.medico;


@Service
public class medicoService implements ImedicoService {

	@Autowired
	private Imedico data;
	
	
	@Override
	public String save(medico medico) {
		data.save(medico);
		return medico.getId();
	}

	@Override
	public List<medico> findAll() {
		List<medico> listamedico=
				(List<medico>) data.findAll();
		//(List<medico>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <medico>
		//- se convierte a list <medico>
		return listamedico;
	}
	@Override
	public Optional<medico> findOne(String id_medico) {
		Optional<medico> medico=data.findById(id_medico);
		return medico;
	}

	@Override
	public int delete(String id_medico) {
		data.deleteById(id_medico);
		return 1;
	}
	
}
