package ee.informatyka.softwaretesting2022wut.data;


import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	public List<Product> product;

	@OneToOne
	public User user;

	public Status status;


	enum Status {
		UNPAID, INPREPARATION, SENT, DELIVERED, CANCELLED
	}
}
