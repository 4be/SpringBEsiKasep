package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Clock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface ClockRepo extends CrudRepository<Clock, Long> {

    @Query(value = "select tbl_user.nama,* from tbl_clock JOIN tbl_user ON tbl_clock.user_id_id = tbl_user.id ORDER BY tbl_clock.id DESC", nativeQuery = true)
    public List<Clock> findAllDesc();

    @Query(value = "SELECT * FROM tbl_clock WHERE tbl_clock.user_id_id=:masuk ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public List<Clock> findByIdDesc(@PathParam("masuk") Long masuk);



}
