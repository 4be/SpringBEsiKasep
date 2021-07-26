package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Clockin;
import org.springframework.data.repository.CrudRepository;

public interface ClockinRepo extends CrudRepository<Clockin,Long> {
}
