package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sena.hospital2.model.ingreso;

import interfaceService.IingresoService;
import interfaces.Iingreso;

public class ingresoService implements IingresoService {
	@Autowired
	private Iingreso data;
	
	
	@Override
	public String save(ingreso ingreso) {
		data.save(ingreso);
		return ingreso.getId_ingreso();
	}

	@Override
	public List<ingreso> findAll() {
		List<ingreso> listaingreso=
				(List<ingreso>) data.findAll();
		//(List<ingreso>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <ingreso>
		//- se convierte a list <ingreso>
		return listaingreso;
	}
	@Override
	public Optional<ingreso> findOne(String id_ingreso) {
		Optional<ingreso> ingreso=data.findById(id_ingreso);
		return ingreso;
	}

	@Override
	public int delete(String id_ingreso) {
		data.deleteById(id_ingreso);
		return 1;
	}
}
