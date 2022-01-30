package mk.com.apartmentdesign.repository;

import mk.com.apartmentdesign.models.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer,Long> {
}
