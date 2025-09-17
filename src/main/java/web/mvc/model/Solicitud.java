package web.mvc.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column( name = "fecha", nullable = false )
	private Date fecha;
	
	@NotNull
	@Column( name = "archivo", length = 250, nullable = false)
	private String archivo;
	
	@NotNull
	@Column( name = "comentarios", columnDefinition = "TEXT")
	private String comentarios;
	
	/*Relaciones*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "vacante" ) //Crea el atributo llamado vacante, NO ES OBLIGATORIO
	private Vacante vacante;
	
	@ManyToOne()
	@JoinColumn( name = "usuario" )
	private Usuario usuario;
	
	/*Marca de tiempo*/
	
	@Column( name = "updated_on")
	@UpdateTimestamp
	private Date updatedOn;
	
	@Column( name = "created_on")
	@CreationTimestamp
	private Date createdOn;
	
		
	public Solicitud(@NotNull Date fecha, @NotNull String archivo, @NotNull String comentarios) {
		super();
		this.fecha = fecha;
		this.archivo = archivo;
		this.comentarios = comentarios;
	}


	public Solicitud() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getArchivo() {
		return archivo;
	}


	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}


	public String getComentarios() {
		return comentarios;
	}


	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	

}
