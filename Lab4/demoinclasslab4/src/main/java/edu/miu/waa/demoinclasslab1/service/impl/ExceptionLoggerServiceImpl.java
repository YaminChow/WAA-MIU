package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.entity.ExceptionLogger;
import edu.miu.waa.demoinclasslab1.repo.ExceptionLoggerRepo;
import edu.miu.waa.demoinclasslab1.service.ExceptionLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLoggerServiceImpl implements ExceptionLoggerService {
    @Autowired
    ExceptionLoggerRepo exceptionLoggerRepo;
    @Override
    public void saveExceptionLogger(ExceptionLogger exceptionLogger) {
        exceptionLoggerRepo.save(exceptionLogger);
    }
}
