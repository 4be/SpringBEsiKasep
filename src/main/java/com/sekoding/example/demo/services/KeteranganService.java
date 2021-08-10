package com.sekoding.example.demo.services;


import com.sekoding.example.demo.model.entity.Clock;
import com.sekoding.example.demo.model.entity.Keterangan;
import com.sekoding.example.demo.model.repos.KeteranganRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class KeteranganService {

    @Autowired
    private KeteranganRepo keteranganRepo;

    public Keterangan create(Keterangan keterangan){
        return keteranganRepo.save(keterangan);
    }

    public Iterable<Keterangan> findAll(){
        return keteranganRepo.findAll();
    }

    public Keterangan findByid(Long id){
        return keteranganRepo.findById(id).get();
    }

    public void removeOne(Long id){
        keteranganRepo.deleteById(id);
    }

    public List<Keterangan> findKesByIdDesc(Long id){
        return keteranganRepo.findKesByIdDesc(id);
    }

    public List<Keterangan>findKesByTeam(String team){
        return keteranganRepo.findKesByTeam(team);
    }



}
