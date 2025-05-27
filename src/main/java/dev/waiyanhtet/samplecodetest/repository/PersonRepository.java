package dev.waiyanhtet.samplecodetest.repository;

import dev.waiyanhtet.samplecodetest.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
