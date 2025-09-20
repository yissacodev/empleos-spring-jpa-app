package web.mvc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mvc.enumeration.VacanteEstatus;
import web.mvc.model.Vacante;

@Repository
public interface VacantesRepository extends JpaRepository<Vacante, Integer> { //<Entidad y Tipo de dato de la Llave primaria >

	List<Vacante> findByEstatus(VacanteEstatus estatus);
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, VacanteEstatus estatus);
	List<Vacante> findBySalarioBetween(double s1, double s2);
	List<Vacante> findByEstatusIn(VacanteEstatus[] estatus);
}
