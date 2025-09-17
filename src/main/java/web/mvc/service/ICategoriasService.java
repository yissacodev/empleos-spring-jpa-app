package web.mvc.service;

import java.util.List;

import web.mvc.model.Categoria;
import web.mvc.model.Vacante;

public interface ICategoriasService {

	List <Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	void guardar(Categoria categoria);

}
