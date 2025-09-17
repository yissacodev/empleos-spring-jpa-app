package web.mvc.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;

@Entity
@Table( name = "Perfiles" )
public class Perfil {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Null
	@Column( name = "perfil", length = 100 )
	private String perfil;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "usuario_perfil",
			joinColumns = @JoinColumn( name = "perfil_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn( name = "usuario_id", referencedColumnName = "id")
	)
	
	private List<Usuario> usuarios;
	
	
	@Column( name = "updated_on")
	@UpdateTimestamp
	private Date updatedOn;
	
	@Column( name = "created_on", columnDefinition = "DATETIME(0)")
	@ColumnDefault( value = "CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date createdOn;
	
	public Perfil() {
		// TODO Auto-generated constructor stub
	}

}
