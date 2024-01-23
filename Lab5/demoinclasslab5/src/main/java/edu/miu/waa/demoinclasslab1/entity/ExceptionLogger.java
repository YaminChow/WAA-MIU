package edu.miu.waa.demoinclasslab1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transaction_Id;
    private LocalDate transaction_date;
    private LocalTime transaction_time;
    private String principle;
    private String operation;
    private String exceptionType;
}
