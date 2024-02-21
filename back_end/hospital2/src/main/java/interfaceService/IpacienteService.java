package interfaceService;

import java.util.List;
import java.util.Optional;

import com.sena.hospital2.model.paciente;

public interface IpacienteService {
	public String save (paciente paciente);
	public List<paciente>findAll();
	public Optional<paciente> findOne(String id_paciente);
	public int delete(String id_paciente);

}
