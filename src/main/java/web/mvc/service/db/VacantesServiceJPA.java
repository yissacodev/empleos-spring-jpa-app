package web.mvc.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import web.mvc.model.Vacante;
import web.mvc.repository.VacantesRepository;
import web.mvc.service.IVacantesService;

@Service
@Primary //Hay dos instancias de IVacantesService, con @Primary hago que esta sea la prioridad
class VacantesServiceJPA implements IVacantesService {
	
	@Autowired  //Inyecta dependencia de Vacantes repository
	private VacantesRepository vacantesRepo;

	public VacantesServiceJPA() {

	}

	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional = vacantesRepo.findById( idVacante );
		
		if( optional.isPresent() ) {
			return optional.get(); //Si resultados existen los retorna
		}
		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);

	}
}
