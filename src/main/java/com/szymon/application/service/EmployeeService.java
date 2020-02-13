package com.szymon.application.service;

import com.szymon.application.dao.EmployeeRepository;
import com.szymon.application.dao.IEmployeeDao;
import com.szymon.application.model.Employee;
import com.szymon.application.model.MapperClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveWithoutTransaction() {
        employeeRepository.saveWithoutTransaction();
    }

    @Transactional
    public void saveWithTransaction() {
        employeeRepository.saveWithTransaction();
    }

    @Transactional
    public Employee getByIdCriteria(Long id) {
        return employeeRepository.getByIdCriteria(id);
    }

    @Transactional
    public Employee getByTimestampCriteria(Date date) {
        return employeeRepository.getByTimestampCriteria(date);
    }

    @Transactional
    public Optional<Employee> getSingleResult(Long id) {
        return employeeRepository.getSingleResult(id);
    }

    @Transactional
    public Optional<Employee> findBySingleResult(Date date) {
        return employeeRepository.findBySingleResult(date);
    }

    @Transactional
    public Map<String, String> getCityAndEmployeeNameByJoin(Long id) {
        return employeeRepository.getCityAndEmployeeNameByJoin(id);
    }

    @Transactional
    public List<MapperClass> getMapperClass() {
        return employeeRepository.getMapperClass();
    }

    @Transactional
    public List<IEmployeeDao.MapperClassExample> getMapperClassInnerClass() {
        return employeeRepository.getMapperClassInnerClass();
    }
}
