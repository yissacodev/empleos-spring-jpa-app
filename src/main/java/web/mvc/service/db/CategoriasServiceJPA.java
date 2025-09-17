package web.mvc.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import web.mvc.model.Categoria;
import web.mvc.repository.CategoriasRepository;
import web.mvc.service.ICategoriasService;

@Service
@Primary
public class CategoriasServiceJPA implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepo;

	public CategoriasServiceJPA() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriasRepo.findById( idCategoria);
		if( optional.isPresent() ) {
			optional.get(); //Si resultados existen los retorna
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save( categoria ); 
	}

}
