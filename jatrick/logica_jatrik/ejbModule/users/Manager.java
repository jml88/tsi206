package users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import campeonato.Torneo;
import datatypes.DatosManager;
import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="userId")
@Table(name = Manager.nombreTabla)
public class Manager extends User {
	
	public static final String nombreTabla = "MANAGER";
	
	@OneToOne
	private Equipo equipo;
	
	@OneToOne
	private Torneo torneo;

	public Manager(){
		
	}
	
	public Manager(DatosManager datosManager, String password){
		this.username = datosManager.getUsername();
		this.password = password;
		this.name = datosManager.getName();
		this.email = datosManager.getEmail();
		this.roles = new HashSet<Role>();
		
		Set<String> roles = datosManager.getRoles();
		for (String role : roles) {
			if (role.equals("MANAGER"))
				this.roles.add(Role.MANAGER);
			else if (roles.equals("ADMIN"))
				this.roles.add(Role.ADMIN);			
		}
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	
}
