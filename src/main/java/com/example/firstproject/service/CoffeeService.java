package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;


    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }


    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    public Coffee update(CoffeeDto dto, Long id) {
        Coffee coffee = dto.toEntity();
        log.info("id : {}, coffee : {}", id, coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || id != coffee.getId()) {
            log.error("잘못된 요청! id : {}, coffee : {}", id, coffee.toString());
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
