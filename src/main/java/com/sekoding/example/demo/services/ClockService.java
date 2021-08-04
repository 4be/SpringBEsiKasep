package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.entity.Clock;
import com.sekoding.example.demo.model.repos.ClockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
public class ClockService {

    @Autowired
    private ClockRepo clockRepo;

    public Clock create(Clock clock) {
        return clockRepo.save(clock);
    }

    public Iterable<Clock> findAll() {
        return clockRepo.findAll();
    }

    public Clock findByid(Long id) {
        return clockRepo.findById(id).get();
    }

    public void removeOne(Long id) {
        clockRepo.deleteById(id);
    }

}
