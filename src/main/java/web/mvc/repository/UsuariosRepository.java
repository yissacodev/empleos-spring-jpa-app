package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mvc.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
