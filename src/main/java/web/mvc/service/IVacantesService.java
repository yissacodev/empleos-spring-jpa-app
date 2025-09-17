package web.mvc.service;

import java.util.List;

import web.mvc.model.Vacante;

public interface IVacantesService {
	List <Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
	
}
