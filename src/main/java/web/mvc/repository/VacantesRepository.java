package web.mvc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mvc.model.Vacante;

@Repository
public interface VacantesRepository extends JpaRepository<Vacante, Integer> { //<Entidad y Tipo de dato de la Llave primaria >


}
