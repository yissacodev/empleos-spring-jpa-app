package web.mvc.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrementable
	private Integer id;
	
	@NotNull
    @Column(name = "nombre", length = 100, nullable = false, unique = false)
	private String nombre;
    
    @Column(name = "descripcion", columnDefinition = "TEXT" ,nullable = true)
    private String descripcion;
    
    /*Relaciones*/
    @OneToOne( mappedBy = "categoria", cascade = CascadeType.ALL)
    private Vacante vacante; //Este campo no aparece en la base de datos y no debe aparecer en el constructor
    
    /* Marca de tiempo */
    @Column( name = "updated_on" )
	@UpdateTimestamp
	private Date updatedOn;	
    
	@Column( name = "created_on", columnDefinition = "DATETIME(0)")
	@ColumnDefault( value = "CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date createdOn;
	
	
	public Categoria(@NotNull String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Categoria() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
