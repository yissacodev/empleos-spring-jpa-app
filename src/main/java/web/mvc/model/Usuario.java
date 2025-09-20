package web.mvc.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "Usuarios" )
@Comment("Tabla que guarda los usuarios que pueen acceder al sistema")
public class Usuario {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Comment("Id de un usuario")
	private Integer id;
	
	@NotNull
	@Column( name = "nombre", length = 50, nullable = false )
	@Comment("Nombre de un usuario")
	private String nombre;
	
	@NotNull
	@Column( name = "email", length = 200, nullable = false )
	@Comment("Email de un usuario")
	private String email;
	
	@NotNull
	@Column( name = "username", length = 45, nullable = false )
	@Comment("Username de un usuario")
	private String username;
	
	@NotNull
	@Column( name = "password", length = 100, nullable = false )
	@Comment("Contraseña de un usuario")
	private String password;
	
	@NotNull
	@Column( name = "estatus" )
	@Comment("Estatus de un usuario")
	private Integer estatus;
	
	/* Relaciones */
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Solicitud> solicitud;
	
	
	//@ManyToMany( mappedBy = "usuarios" )
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "usuario_perfil",
			joinColumns = @JoinColumn( name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn( name = "perfil_id", referencedColumnName = "id")
	)
	private List<Perfil> perfiles;
	
	/*Marcaciones de tiempo*/
	
	@Column( name = "updated_on")
	@UpdateTimestamp
	private Date updatedOn;

	@Column( name = "created_on", columnDefinition = "DATETIME(0)")
	@ColumnDefault( value = "CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date createdOn;

	
	
	
	public Usuario(Integer id, @NotNull String nombre, @NotNull String email, @NotNull String username,
			@NotNull String password, @NotNull Integer estatus, List<Solicitud> soliictud, List<Perfil> perfiles,
			Date updatedOn, Date createdOn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.username = username;
		this.password = password;
		this.estatus = estatus;
		this.solicitud = soliictud;
		this.perfiles = perfiles;
		this.updatedOn = updatedOn;
		this.createdOn = createdOn;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void agregar(Perfil tempPerfil) {
		if(perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}
		perfiles.add(tempPerfil);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", estatus=" + estatus + ", soliictud=" + solicitud + ", perfiles="
				+ perfiles + ", updatedOn=" + updatedOn + ", createdOn=" + createdOn + "]";
	}
	
	
}
