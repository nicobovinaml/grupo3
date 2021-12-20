package com.example.easynotes.repository;

import com.example.easynotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    /* NOTA: palabras que aceptan los Named Methods
    * NOTA: Para numeros -          greaterThan, greaterThanEquals, lessThan, lessThanEquals, beetween
    * NOTA: Para strings -          equals, like, notLike, endingWith, startingWith, containing, ignoringCase
    * NOTA: Para booleanos -        true, false
    * NOTA: Para intervalos -       in, notIn
    * NOTA: Para fechas -           before, after
    * NOTA: Operadores logicos -    or, and
    * NOTA: Otras sentencias sql -  distinct, orderBy, sort
    *  */

    //


    //@Query("Select new map(user.firstName as name, size(user.authorNotes) as notes ) from User user where user.lastName like %:lastName%")

    @Query("from User u where u.lastName like %:lastName%")
    List<?> findUserByLastNameLike(@Param("lastName") String lastName);

    @Query( "select distinct user " +
            "from User user " +
            "join user.authorNotes note " +
            "where note.title like %:title%" )
    List<User> findUserByNoteTitleLike(@Param("title") String title);

    @Query( "select distinct user " +
            "from User user " +
            "join user.authorNotes note " +
            "where note.createdAt >= :date" )
    List<User> findUserByNoteCreatedAtLessOrEqualDate(@Param("date") Date date);

    @Query( "select new map(n.created_at as date, count(n.id) as count) " +
            "from Note n " +
            "where n.author.id = :user_id and n.created_at between current_date and current_date - 3 " +
            "group by date" )
    List<HashMap<LocalDate, Integer>> findNotesBetweenThreeDaysAgo(@Param("user_id") Long userId);

    @Query( "select new map(n.created_at as date, count(n.id) as count) " +
            "from Note n " +
            "where n.author.id = :user_id and n.created_at between current_date and current_date - 21 " +
            "group by date " +
            "order by date" )
    List<HashMap<LocalDate, Integer>> findNotesBetweenThreeWeeksAgo(@Param("user_id") Long userId);


    //    // Ejemplo con like
    //    List<User> findUserByLastNameLikeAndFirstNameLike(String lastName, String firstName);
    //
    //    // Ejemplo con diferencia entre like y contains
    //    List<User> findUserByLastNameLikeAndFirstNameContains(String lastName, String firstName);

}
