package users;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="userId")
@Table(name = Manager.nombreTabla)
public class Manager extends User {
	
	public static final String nombreTabla = "MANAGER";
	
	@OneToOne
	private Equipo equipo;

}
