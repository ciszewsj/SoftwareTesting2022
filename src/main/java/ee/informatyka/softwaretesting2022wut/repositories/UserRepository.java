package ee.informatyka.softwaretesting2022wut.repositories;

import ee.informatyka.softwaretesting2022wut.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
