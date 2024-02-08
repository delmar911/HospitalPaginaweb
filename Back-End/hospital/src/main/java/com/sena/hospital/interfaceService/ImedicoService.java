package com.sena.hospital.interfaceService;

import java.util.List;
import java.util.Optional;

import com.sena.hospital.model.medico;

public interface ImedicoService {
	public String save (medico medico);
	public List<medico>findAll();
	public Optional<medico> findOne(String id_medico);
	public int delete(String id_medico);
}
