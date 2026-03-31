package ee.marcus.veebipood.repository;

import ee.marcus.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByEmail(String email);
}
