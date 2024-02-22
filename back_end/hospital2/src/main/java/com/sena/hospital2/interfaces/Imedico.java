package com.sena.hospital2.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.hospital2.model.medico;

@Repository
public interface Imedico extends CrudRepository<medico,String> {

}
