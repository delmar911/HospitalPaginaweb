package com.sena.hospital2.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.sena.hospital2.model.medico;

public interface Imedico extends CrudRepository<medico,String> {

}
