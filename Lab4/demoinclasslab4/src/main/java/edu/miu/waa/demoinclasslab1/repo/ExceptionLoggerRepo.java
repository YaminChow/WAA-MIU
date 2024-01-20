package edu.miu.waa.demoinclasslab1.repo;

import edu.miu.waa.demoinclasslab1.entity.ExceptionLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLoggerRepo extends JpaRepository<ExceptionLogger,String> {
}
