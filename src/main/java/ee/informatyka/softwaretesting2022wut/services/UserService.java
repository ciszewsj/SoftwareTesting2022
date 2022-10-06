package ee.informatyka.softwaretesting2022wut.services;

import ee.informatyka.softwaretesting2022wut.data.User;
import ee.informatyka.softwaretesting2022wut.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
	@Autowired
	public UserRepository userRepository;


	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	;

}
