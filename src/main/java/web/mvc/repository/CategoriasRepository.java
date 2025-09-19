package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mvc.model.Categoria;

@Repository
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> 
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	//Esta interface Extiende de JPARepository y todo objeto instanciado de esta interface 
	// E inyectado con @autowired
	//PUEDE USAR LOS MÉTODOS DE CRUD
}
