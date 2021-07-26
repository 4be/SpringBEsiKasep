package com.sekoding.example.demo.services;


import com.sekoding.example.demo.model.entity.Clockin;
import com.sekoding.example.demo.model.repos.ClockinRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClockinService {

    @Autowired
    private ClockinRepo clockinRepo;

    public Clockin create(Clockin clockin){
        return clockinRepo.save(clockin);
    }

    public Iterable<Clockin> findAll(){
        return clockinRepo.findAll();
    }

}
