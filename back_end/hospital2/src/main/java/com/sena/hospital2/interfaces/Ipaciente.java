package com.sena.hospital2.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.sena.hospital2.model.paciente;

public interface Ipaciente extends CrudRepository<paciente,String> {

}
