package users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@PrimaryKeyJoinColumn(name="userId")
@Table(name = Admin.nombreTabla)
public class Admin extends User {

	public static final String nombreTabla = "ADMIN";
	
	public Admin(){
		
	}
}
