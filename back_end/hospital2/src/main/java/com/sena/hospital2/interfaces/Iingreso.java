package com.sena.hospital2.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.hospital2.model.ingreso;

@Repository

public interface Iingreso extends CrudRepository<ingreso,String>{

}

