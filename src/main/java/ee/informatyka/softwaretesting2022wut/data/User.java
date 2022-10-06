package ee.informatyka.softwaretesting2022wut.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 6, max = 35, message = "Username should be 6-35 chars")
	public String username;

	@Size(min = 6, max = 35, message = "Password should be 6-35 chars")
	public String password;

	@Email(message = "It's not an email")
	public String email;

	public Role role;

	enum Role {
		USER, ADMIN
	}

}
