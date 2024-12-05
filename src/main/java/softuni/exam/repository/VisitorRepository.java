package softuni.exam.repository;

import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;

import java.util.Optional;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    Optional<Visitor> findByFirstNameAndLastName(@Size(min = 2, max = 20) String firstName, @Size(min = 2, max = 20) String lastName);

    @Query("SELECT v FROM Visitor v WHERE v.personalData = :personalData")
    Optional<Visitor> findByPersonalData(@Param("personalData") PersonalData personalData);

    Optional<Visitor> findById(long id);
}
