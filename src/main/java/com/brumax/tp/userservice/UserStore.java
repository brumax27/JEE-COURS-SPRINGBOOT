package com.brumax.tp.userservice;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.List;


@Repository
public interface UserStore extends JpaRepository<User, String> {

    List<User> findByFirstname(String firstname);

    @Query("from User WHERE firstname = :firstname ")
    List<User> findByFi(@Param("firstname") String firstname);

    @Query(nativeQuery = true, value = "SELECT u.* from User u WHERE u.firstname = :firstname ")
    List<User> findByFirst(@Param("firstname") String firstname);

    //pour les table recursive
    //@EntityGraph
    //@NamedEntityGraph

    //persiste enum
    //stoker le name et pas l'ordinal

}
