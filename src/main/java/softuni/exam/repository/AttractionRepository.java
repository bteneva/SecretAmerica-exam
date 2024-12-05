package softuni.exam.repository;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByName(@Length(min = 3, max = 40) String name);

    List<Attraction> findByCountry_Id(Long countryId);

    @Query("SELECT a FROM Attraction a " +
            "WHERE a.type = 'historical site'" +
            "OR a.type = 'archaeological site'" +
            "AND a.elevation >=300 " +
            "ORDER BY a.name ASC, a.country.name ASC")
    List<Attraction> export(long i);
}
