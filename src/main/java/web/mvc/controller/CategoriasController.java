package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.mvc.model.Categoria;
import web.mvc.model.Vacante;
import web.mvc.service.ICategoriasService;

@Controller
@RequestMapping( value = "/categorias" ) //Todas las rutas de este controlador deben ser antecedidas por /categorias
public class CategoriasController {
	
	@Autowired 
	private ICategoriasService serviceCategorias;
	
	public CategoriasController() {
		// TODO Auto-generated constructor stub
	}
	
	//@RequestMapping( value="/index", method = RequestMethod.GET)
	@GetMapping("/index")
	private String mostrarIndex( Model model ) {
		
		List<Categoria> categorias = serviceCategorias.buscarTodas();		
		model.addAttribute("categorias", categorias);		
		return "categorias/listCategorias";
	}
	
	//@RequestMapping( value="/create", method = RequestMethod.GET)
	@GetMapping("/create")
	private String crear( Model model ) {
		return "categorias/formCategorias";
	}
	
	//@RequestMapping( value="/save", method = RequestMethod.POST)
	@PostMapping("/save")
	private String guardar( @RequestParam("nombre") String nombre, 
			@RequestParam("descripcion") String descripcion) {
		
		System.out.println("Categoria:" + nombre);
		System.out.println("Descripción:" + descripcion);
		
		return "categorias/listCategorias";
	}

}
