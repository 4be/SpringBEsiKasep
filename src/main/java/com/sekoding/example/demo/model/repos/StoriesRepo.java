package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Stories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface StoriesRepo extends CrudRepository<Stories,Long> {
    @Query(value = "select * from tbl_stories ORDER BY ID DESC", nativeQuery = true)
    public List<Stories> findAllDesc();

}
