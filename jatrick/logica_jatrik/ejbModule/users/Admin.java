package users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="userId")
public class Admin extends User {

	public Admin(){
		
	}
}
