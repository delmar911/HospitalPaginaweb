package com.sena.hospital2.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.hospital2.model.paciente;

@Repository

public interface Ipaciente extends CrudRepository<paciente,String> {

}
