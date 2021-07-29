package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Clock;
import org.springframework.data.repository.CrudRepository;

public interface ClockRepo extends CrudRepository<Clock,Long> {


}
