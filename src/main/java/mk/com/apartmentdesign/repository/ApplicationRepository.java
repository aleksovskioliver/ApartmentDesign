package mk.com.apartmentdesign.repository;

import mk.com.apartmentdesign.models.Application;
import mk.com.apartmentdesign.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    Optional<Application> findByUser(User user);
    List<Application> findAllByUser(User user);
}
