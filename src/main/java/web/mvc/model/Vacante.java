package web.mvc.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import web.mvc.enumeration.VacanteEstatus;
import web.mvc.enumeration.converter.VacanteEstatusConverter;

@Entity
@Table(name = "Vacantes")
@Comment( "Tabla en donde se almacenan las vacantes ofrecidas")
public class Vacante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment( "Id de una vacante" )
	private Integer id;
	
	@NotNull
	@Column( name = "nombre", nullable = false, length = 200)
	@Comment( "Nombre de una vacante" )
	private String nombre;
	
	@NotNull
	@Column( name = "descripcion", nullable = false, columnDefinition = "TEXT", length = 200) 
	@Comment( "Descripción de una vacante" )
	private String descripcion;
	
	@NotNull
	@Column( name = "fecha", nullable = false ) 
	@Comment( "Fecha de aparición de una vacante" )
	private Date fecha;
	
	@Valid
	@NotNull
	//@NotNull(message = "El salario es obligatorio")
	//@DecimalMin(value = "0.0", message = "El salario debe ser mayor o igual a 0")
	@Column( name = "salario", nullable = false ) 
	@Comment( "Salario ofrecido por la vacante" )
	private Double salario;
	
	@NotNull
	@Column( name = "destacado" , nullable = false) 
	@Comment( "Popularidad de la vacante" )
	private Integer destacado;

	@Column( name = "imagen", length = 250 ) 
	@Comment( "Imágen de la vacante o la empresa que la ofrece" )
	private String imagen = "no-image-png";
	
	@NotNull
	@Convert( converter = VacanteEstatusConverter.class )
	@Column( name = "estatus", nullable = false, columnDefinition = "ENUM('Aprobada', 'No Aprobada', 'Pendiente')")
	@Comment( "Estado de la vacante" )
	private VacanteEstatus estatus;
	
	@Column( name = "detalles", columnDefinition = "TEXT" )
	@Comment( "Detalles de la vacante" )
	private String detalles;
	
	/*Relaciones*/
	@OneToOne(fetch = FetchType.LAZY )
	@JoinColumn( name = "categoria", nullable = false )
	@NotNull
	@Comment( "Categoria en que se clasifica la vacante" )
	private Categoria categoria;
	
	@OneToMany(mappedBy = "vacante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Solicitud> soliictud;

	/*-Marcaciones de tiempo-*/
	@Column( name = "updated_on")
	@UpdateTimestamp
	private Date updatedOn;
	
	
	@Column( name = "created_on", columnDefinition = "DATETIME(0)")
	@ColumnDefault( value = "CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date createdOn;

	/*Constructor*/
	public Vacante( @NotNull String nombre, @NotNull String descripcion, @NotNull Date fecha,
			@NotNull Double salario, @NotNull Integer destacado, String imagen, @NotNull VacanteEstatus estatus,
			String detalles) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.salario = salario;
		this.destacado = destacado;
		this.imagen = imagen;
		this.estatus = estatus;
		this.detalles = detalles;
	}
	
	public Vacante() {
		
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getDestacado() {
		return destacado;
	}

	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public VacanteEstatus getEstatus() {
		return estatus;
	}

	public void setEstatus(VacanteEstatus estatus) {
		this.estatus = estatus;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", salario=" + salario
				+ ", descripcion=" + descripcion + ", categoria=" + categoria + ", estatus=" + estatus + ", destacado="
				+ destacado + ", detalles=" + detalles + ", imagen=" + imagen + "]";
	}

}
