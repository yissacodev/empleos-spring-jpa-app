package web.mvc.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import web.mvc.model.Categoria;
import web.mvc.model.Vacante;
import web.mvc.service.ICategoriasService;

@Service
public class CategoriasServiceImpl implements ICategoriasService{
	
	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
		
		
		try {
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setNombre("Arquitectura");
			categoria1.setDescripcion("Categoría para Arquitectura");
			
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setNombre("Contabilidad/Finanzas");
			categoria2.setDescripcion("Categoría para Finanzas");
			
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setNombre("Computación/TI");
			categoria3.setDescripcion("Categoría para Computación/TI");
			
			Categoria categoria4 = new Categoria();
			categoria4.setId(4);
			categoria4.setNombre("Ingeniería electrónica");
			categoria4.setDescripcion("Categoría para Ingeniería electr´nica");
			
			Categoria categoria5 = new Categoria();
			categoria5.setId(5);
			categoria5.setNombre("Recursos humanos");
			
			Categoria categoria6 = new Categoria();
			categoria6.setId(6);
			categoria6.setNombre("Servicio y atención al cliente");
			
			Categoria categoria7 = new Categoria();
			categoria7.setId(7);
			categoria7.setNombre("Logística y deportes");
			
			Categoria categoria8 = new Categoria();
			categoria8.setId(8);
			categoria8.setNombre("Desarrollo de software");
			
			Categoria categoria9 = new Categoria();
			categoria9.setId(9);
			categoria9.setNombre("Diseño");
			
			
			Categoria categoria10 = new Categoria();
			categoria10.setId(10);
			categoria10.setNombre("Comunicaciones");
			
			
			Categoria categoria11 = new Categoria();
			categoria11.setId(11);
			categoria11.setNombre("Mercadotecnia");
			
			
			Categoria categoria12 = new Categoria();
			categoria12.setId(12);
			categoria12.setNombre("Ventas");
			
			
			Categoria categoria13 = new Categoria();
			categoria13.setId(13);
			categoria13.setNombre("Publicidad");
			
			
			Categoria categoria14 = new Categoria();
			categoria14.setId(14);
			categoria14.setNombre("Gerencia/Administración");
			
			
			Categoria categoria15 = new Categoria();
			categoria15.setId(15);
			categoria15.setNombre("Educación");
			
			Categoria categoria16 = new Categoria();
			categoria16.setId(16);
			categoria16.setNombre("Blockchain y Bitcoin");
			
			
			lista.add( categoria1 );
			lista.add( categoria2 );
			lista.add( categoria3 );
			lista.add( categoria4 );
			lista.add( categoria5 );
			lista.add( categoria6 );
			lista.add( categoria7 );
			lista.add( categoria8 );
			lista.add( categoria9 );
			lista.add( categoria10 );
			lista.add( categoria11 );
			lista.add( categoria12 );
			lista.add( categoria13 );
			lista.add( categoria14 );
			lista.add( categoria15 );
			lista.add( categoria16 );
			
			
			
		} catch (Exception e) {
			System.out.println("Ha ocurrido el siguiente Error: " + e.getMessage());
		}
		
		
		
	}

	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
	}

}
