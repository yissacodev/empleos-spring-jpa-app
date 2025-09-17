package web.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import web.mvc.model.Vacante;
import web.mvc.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired //Busca en todos los paquetes una interfaz de tipo IVacantesService y la inyecta
	private IVacantesService serviceVacantes;
	
	public HomeController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/")
	public String mostrarHome( Model model ) {
		List<Vacante> lista = serviceVacantes.buscarTodas();		
		model.addAttribute("vacantes", lista);
		
		return "home"; //Nombre de la vista guardada en resources/templates
	}
	
	@GetMapping("/listado")
	public String mostrarListado( Model model ) { 
		/*Lista que solo guarda Strings*/
		List<String> lista = new LinkedList<String>();
		
		lista.add("Ingeniero de sistemas");
		lista.add("Administrador de empresas");
		lista.add("Artista de medios digitales");
		lista.add("Marketing");
		lista.add("Productor musical");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.00);
		
		model.addAttribute("vacante", vacante);
		return "detalle";
		
	}
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		//List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);		
		return "tabla";
	}	
}
