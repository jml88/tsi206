package users;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="userId")
public class Manager extends User {
	
	@OneToOne
	private Equipo equipo;

}
