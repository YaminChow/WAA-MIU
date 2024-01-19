package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.entity.Commet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommetRepo extends JpaRepository<Commet, Long> {
}
