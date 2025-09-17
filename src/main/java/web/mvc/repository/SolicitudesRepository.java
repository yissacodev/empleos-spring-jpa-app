package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mvc.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
