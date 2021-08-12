package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.entity.Stories;
import com.sekoding.example.demo.model.repos.StoriesRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StoriesServices {

    @Autowired
    private StoriesRepo storiesRepo;

    public Stories create(Stories stories){
        return storiesRepo.save(stories);
    }

    public Iterable<Stories> findAll(){
        return storiesRepo.findAll();
    }

    public List<Stories> findAllDesc(){
        return storiesRepo.findAllDesc();
    }

    public void remmoveOne(Long id){
        storiesRepo.deleteById(id);
    }

    public Stories findById(Long id){
        return storiesRepo.findById(id).get();
    }



}
