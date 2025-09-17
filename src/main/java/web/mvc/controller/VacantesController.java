package web.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import web.mvc.model.Vacante;
import web.mvc.service.ICategoriasService;
import web.mvc.service.IVacantesService;
import web.mvc.util.Utileria;

@Controller
@RequestMapping("vacantes")
public class VacantesController {
	
	//*Inyectar un valor desde una variqable global*/
	@Value("${empleos.ruta.imagenes}")
	private String ruta;
	
	@Autowired // Busca en todos los paquetes una interfaz de tipo IVacantesService y la inyecta
	private IVacantesService serviceVacantes;
	
	@Autowired 
	private ICategoriasService serviceCategorias;

	public VacantesController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/index")
	private String index(Model model) { 
		//Eliminar la funciona de la interface!!, la sig linea es solo para pruebas
		List<Vacante> vacantes = serviceVacantes.buscarTodas();		
		model.addAttribute("vacantes", vacantes);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("create")
	private String crear( Vacante vacante, Model model ) {
		model.addAttribute( "categorias", serviceCategorias.buscarTodas() );
		return "vacantes/formVacante";
	}

	@PostMapping("save")
	private String guardar(@Valid @ModelAttribute Vacante vacante, 
			BindingResult result, 
			RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		
		System.out.println("Vacante: " + vacante);
		System.out.println("Errores: " + result.hasErrors());
		//Errores de vinculación en la transacción, errores de formato de campos
		if( result.hasErrors() ) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		//Si multipart trae información de archivo
		if (!multiPart.isEmpty()) {
			//String ruta = "G:\\empleos\\uploadfilesspringtest\\";
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			
			if (nombreImagen != null) {
				vacante.setImagen(nombreImagen);
			}			
		}
		
		serviceVacantes.guardar(vacante);
		System.out.println("Vacante: " + vacante);
		
		//FlashAtribute para enviar un dato a otra dirección a donde se redirecciona
		attributes.addFlashAttribute("msg", "Registro guardado");		
		
		//Redirigir
		return "redirect:/vacantes/listVacantes";
	}

	@GetMapping("/view/{id}")
	private String verDetalle(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		
		// TODO Auto-generated method stub
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		System.out.println("Vacante: " + vacante);
		// Buscar los detalles de la vacante en la VD
		return "vacantes/detalle";
	}

	@GetMapping("/delete")
	private String eliminar(@RequestParam("id") int idVacante, Model model) {
		// TODO Auto-generated method stub
		System.out.println("Borrando vacante con ID: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	
	
	/*Conversor de formato de fecha*/
	@InitBinder
	public void InitBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
