package com.sekoding.example.demo.model.repos;

import com.sekoding.example.demo.model.entity.Clock;
import com.sekoding.example.demo.model.entity.Keterangan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface KeteranganRepo extends CrudRepository<Keterangan,Long> {

    @Query(value = "SELECT * FROM tbl_keterangan WHERE tbl_keterangan.user_id_id=:masuk ORDER BY id DESC LIMIT 10;", nativeQuery = true)
    public List<Keterangan> findKesByIdDesc(@PathParam("masuk") Long masuk);


    @Query(value = "SELECT * FROM tbl_keterangan JOIN tbl_user ON tbl_keterangan.user_id_id = tbl_user.id where tbl_user.nik_manger=:team ORDER BY tbl_keterangan.id DESC",nativeQuery = true)
    public List<Keterangan> findKesByTeam(@PathParam("team") String team);

    @Query(value = "SELECT COUNT(tbl_keterangan.id) from tbl_keterangan WHERE date_part('month', tbl_keterangan.start_date)=:month",nativeQuery = true)
    public List<String> findTotalKesMonth(@PathParam("month") Long month);



}
