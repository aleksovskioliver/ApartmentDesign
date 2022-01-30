package mk.com.apartmentdesign.repository;

import mk.com.apartmentdesign.models.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
}
