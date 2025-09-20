package web.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.mvc.controller.HomeController;
import web.mvc.enumeration.VacanteEstatus;
import web.mvc.model.Categoria;
import web.mvc.model.Perfil;
import web.mvc.model.Usuario;
import web.mvc.model.Vacante;
import web.mvc.repository.CategoriasRepository;
import web.mvc.repository.PerfilRepository;
import web.mvc.repository.UsuariosRepository;
import web.mvc.repository.VacantesRepository;

@SpringBootApplication
public class EmpleosApplication implements CommandLineRunner{

    private final HomeController homeController;
	
	//Logger para mostrar los logs de esta clase
	Logger logger = LoggerFactory.getLogger(EmpleosApplication.class);
	
	@Autowired  //Inyecta dependencia de Vacantes repository
	private VacantesRepository vacantesRepo;
	
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Autowired
	private UsuariosRepository usuariosRepo;
	
	@Autowired
	private PerfilRepository  perfilRepo;

    EmpleosApplication(HomeController homeController) {
        this.homeController = homeController;
    }

	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//crearVacantes();
		//crearPerfilesAplicacion();
		//crearUsuarioConDosPerfil();
		//buscarUsuarios();
		//buscarVacantesPorEstatus();
		//buscarVacantesPorDestacadoEstatus();
		//buscarVacantesSalario();
		buscarVacantesVariosEstatud();
	}
	
	private void buscarVacantesVariosEstatud() {
		// TODO Auto-generated method stub
		VacanteEstatus[] estatus = new VacanteEstatus[] {VacanteEstatus.APROBADA, VacanteEstatus.PENDIENTE};
		List<Vacante> lista = vacantesRepo.findByEstatusIn(estatus);
		for (Vacante v: lista) {
			System.out.println(v.getId() + ": " + v.getNombre() + ": " + v.getEstatus());
		}
		
	}
	
	private void buscarVacantesSalario() {
		// TODO Auto-generated method stub
		List<Vacante> lista = vacantesRepo.findBySalarioBetween(12000, 15000);
		for (Vacante v: lista) {
			System.out.println(v.getId() + ": " + v.getNombre() + ": $" + v.getSalario());
		}
		
	}
	
	private void buscarVacantesPorDestacadoEstatus() {
		// TODO Auto-generated method stub
		List<Vacante> lista = vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, VacanteEstatus.APROBADA);
		for (Vacante v: lista) {
			System.out.println(v.getId() + ": " + v.getNombre() + ": " + v.getEstatus() + ":" + v.getDestacado());
		}
	}
	
	private void buscarVacantesPorEstatus() {
		// TODO Auto-generated method stub
		List<Vacante> lista = vacantesRepo.findByEstatus(VacanteEstatus.PENDIENTE);
		for (Vacante v: lista) {
			System.out.println(v.getId() + ": " + v.getNombre() + ": " + v.getEstatus());
		}
	}
	
	private void buscarUsuarios() {
		Optional<Usuario> optional = usuariosRepo.findById(1);
		if(optional.isPresent()) {
			Usuario u = optional.get();
			System.out.println("Usuario: " + u.getNombre());
			System.out.println("Perfiles asignados: ");
			for (Perfil p : u.getPerfiles()) {
				System.out.println(p.getPerfil());
			}
		}
		else {
			System.out.println("Usuario no encontrado");

		}
	}
	
	private void crearUsuarioConDosPerfil() {
		Usuario user = new Usuario();
		user.setNombre("Yessid Acosta");
		user.setEmail("yissacodev@mail.com");
		user.setCreatedOn(new Date());
		user.setUsername("yacosta");
		user.setPassword("oresama9523");
		user.setEstatus(1);
		
		Perfil per1 = new Perfil();
		per1.setId(1);
		Perfil per2 = new Perfil();
		per2.setId(2);
		
		user.agregar(per1);
		user.agregar(per2);
		
		usuariosRepo.save(user);
	}
	
	private void crearVacantes() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Vacante vacante = new Vacante();
			vacante.setId(1);
			vacante.setNombre("Ingeniero de comunicaciones");
			vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet");
			vacante.setEstatus(VacanteEstatus.APROBADA);
			vacante.setFecha(sdf.parse("05-11-23") );
			vacante.setSalario(12000.00);
			vacante.setDestacado(1);
			vacante.setImagen("empresa1.png");
			
			Categoria cat = new Categoria();
			cat.setId(3);
			vacante.setCategoria(cat);

			Vacante vacante1 = new Vacante();
			vacante1.setId(2);
			vacante1.setNombre("Ingeniero de Software");
			vacante1.setDescripcion("Se solicita ingeniero para desarrollo de microservicios");
			vacante1.setFecha( sdf.parse("18-11-23") );
			vacante1.setSalario(16000.00);
			vacante1.setDestacado(0);
			vacante1.setEstatus(VacanteEstatus.NO_APROBADA);
			vacante1.setDestacado(0);
			vacante1.setImagen("empresa2.png");
			

			cat = new Categoria();
			cat.setId(2);
			vacante1.setCategoria(cat);
			

			Vacante vacante2 = new Vacante();
			vacante2.setNombre("Artista de medios digitales");
			vacante2.setDescripcion("Se solicita Artista de medios digitales par diseño de Logos, Ilustraciones");
			vacante2.setFecha( sdf.parse("02-12-23") );
			vacante2.setSalario(15000.00);
			vacante2.setEstatus(VacanteEstatus.PENDIENTE);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa3.png");
			
			cat = new Categoria();
			cat.setId(1);
			vacante2.setCategoria(cat);
			
			
			Vacante vacante3 = new Vacante();
			vacante3.setNombre("Diseñador UI/UX");
			vacante3.setDescripcion("Buscamos diseñador UI/UX");
			vacante3.setFecha( sdf.parse("02-12-23") );
			vacante3.setSalario(15000.00);
			vacante3.setEstatus(VacanteEstatus.PENDIENTE);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa4.png");
			
			cat = new Categoria();
			cat.setId(5);
			vacante3.setCategoria(cat);
			
			this.vacantesRepo.save(vacante);
			this.vacantesRepo.save(vacante1);
			this.vacantesRepo.save(vacante2);
			this.vacantesRepo.save(vacante3);
			
			logger.info("Entidades vacantes han sido guardadas");

		} catch (Exception e) {
			logger.error("Ha ocurrido el siguiente Error: " + e.getMessage());
		}
		
	}
	
	private void crearCategorias() {
		try {
			Categoria categoria1 = new Categoria();
			categoria1.setNombre("Arquitectura");
			categoria1.setDescripcion("Categoría para Arquitectura");
			
			Categoria categoria2 = new Categoria();
			categoria2.setNombre("Contabilidad/Finanzas");
			categoria2.setDescripcion("Categoría para Finanzas");
			
			Categoria categoria3 = new Categoria();
			categoria3.setNombre("Computación/TI");
			categoria3.setDescripcion("Categoría para Computación/TI");
			
			Categoria categoria4 = new Categoria();
			categoria4.setNombre("Ingeniería electrónica");
			categoria4.setDescripcion("Categoría para Ingeniería electr´nica");
			
			Categoria categoria5 = new Categoria();
			categoria5.setNombre("Recursos humanos");
			
			Categoria categoria6 = new Categoria();
			categoria6.setNombre("Servicio y atención al cliente");
			
			Categoria categoria7 = new Categoria();
			categoria7.setNombre("Logística y deportes");
			
			Categoria categoria8 = new Categoria();
			categoria8.setNombre("Desarrollo de software");
			
			Categoria categoria9 = new Categoria();
			categoria9.setNombre("Diseño");
			
			
			Categoria categoria10 = new Categoria();
			categoria10.setNombre("Comunicaciones");
			
			
			Categoria categoria11 = new Categoria();
			categoria11.setNombre("Mercadotecnia");
			
			
			Categoria categoria12 = new Categoria();
			categoria12.setNombre("Ventas");
			
			
			Categoria categoria13 = new Categoria();
			categoria13.setNombre("Publicidad");
			
			
			Categoria categoria14 = new Categoria();
			categoria14.setNombre("Gerencia/Administración");
			
			Categoria categoria15 = new Categoria();
			categoria15.setNombre("Educación");
			
			Categoria categoria16 = new Categoria();
			categoria16.setNombre("Blockchain y Bitcoin");
			
			
			this.categoriasRepo.save( categoria1 );
			categoriasRepo.save( categoria2 );
			categoriasRepo.save( categoria3 );
			categoriasRepo.save( categoria4 );
			categoriasRepo.save( categoria5 );
			categoriasRepo.save( categoria6 );
			categoriasRepo.save( categoria7 );
			categoriasRepo.save( categoria8 );
			categoriasRepo.save( categoria9 );
			categoriasRepo.save( categoria10 );
			categoriasRepo.save( categoria11 );
			categoriasRepo.save( categoria12 );
			categoriasRepo.save( categoria13 );
			categoriasRepo.save( categoria14 );
			categoriasRepo.save( categoria15 );
			categoriasRepo.save( categoria16 );
		}  catch (Exception e) {
			logger.error("Ha ocurrido el siguiente Error: " + e.getMessage());
		}
		logger.info("Categorias han sido guardadas!");
	}
	
	private void metodosRepositorio() {
		//Aquí práctica de algunos métodos de repositorios
	}
	
	private void crearPerfilesAplicacion() {
		perfilRepo.saveAll(getPerfilesAplicacion());
	}
	
	private List<Perfil> getPerfilesAplicacion(){
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");
		
		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");
		
		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO");
		
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		
		return lista;
		
	}
	
	


}
