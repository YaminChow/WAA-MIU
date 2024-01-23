package edu.miu.waa.demoinclasslab1.service.impl;

import edu.miu.waa.demoinclasslab1.entity.Logger;
import edu.miu.waa.demoinclasslab1.repo.LoggerRepo;
import edu.miu.waa.demoinclasslab1.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    LoggerRepo loggerRepo;
    @Override
    public void saveLog(Logger logger) {
        loggerRepo.save(logger);
    }
}
