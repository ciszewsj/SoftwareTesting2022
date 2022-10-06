package ee.informatyka.softwaretesting2022wut.data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String username;

	public String password;

	@Email(message = "It's not an email")
	public String email;

}
