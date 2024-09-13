package com.rootlabs.user.service;

import com.rootlabs.user.VO.Department;
import com.rootlabs.user.VO.ResponseTemplateVO;
import com.rootlabs.user.entity.User;
import com.rootlabs.user.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {

        log.info("inside saveUser of UserService");
        return userRepository.save(user);
    }

    // Name the circuit breaker and set up the fallback method
    @CircuitBreaker(name = "departmentService", fallbackMethod = "getDepartmentFallback")
    public ResponseTemplateVO getUserWithDepartment(Long userId) {

        log.info("inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;
    }

    // Fallback method if department service is unavailable
    public ResponseTemplateVO getDepartmentFallback(Long userId, Throwable t) {
        log.error("Department service is down, falling back", t);

        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department fallbackDepartment = new Department();
        fallbackDepartment.setDepartmentId(0L);
        fallbackDepartment.setDepartmentName("Department service not available");
        fallbackDepartment.setDepartmentAddress("N/A");

        vo.setUser(user);
        vo.setDepartment(fallbackDepartment);

        return vo;
    }

}
