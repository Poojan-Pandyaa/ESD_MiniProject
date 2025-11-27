package com.poojan.esd_final_project.repo;

import com.poojan.esd_final_project.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

        // @Query(value = "select s.id,s.first_name,s.last_name,s.email,case when" +
        // " exists(select 1 from placement_student p where s.id = p.sid) then 'Placed'
        // else " +
        // "'Unplaced' end as placement_status" + " from students s" ,nativeQuery =
        // true)
        // public List<Object[]> showAllStudents();

        @Query("SELECT s.id, s.firstName, s.lastName, s.email, " +
                        "CASE WHEN EXISTS (SELECT p FROM PlacementStudent p WHERE p.student.id = s.id) " +
                        "THEN 'Placed' ELSE 'Unplaced' END AS placementStatus " +
                        "FROM Student s")
        public List<Object[]> showAllStudents();

        @Query(value = "select " +
                        "s.first_name, s.last_name, d.program, " +
                        "MAX(p.org) as placement_org, MAX(ao.org) as alumni_org, s.graduation_year, " +
                        "case " +
                        "when exists(select 1 from alumni a where s.id = a.sid) then 'Yes' " +
                        "else 'No' " +
                        "end as isAlumni, " +
                        "case " +
                        "when exists(select 1 from placement_student ps2 where ps2.sid = s.id) then 'Placed' " +
                        "else 'Unplaced' " +
                        "end as is_placed, " +
                        "MAX(p.ctc) as ctc " +
                        "from students s join domains d " +
                        "on s.domain = d.id " +
                        "left join placement_student ps " +
                        "on ps.sid = s.id " +
                        "left join placement p " +
                        "on p.id = ps.place_Id " +
                        "left join alumni a " +
                        "on a.sid = s.id " +
                        "left join alumni_org ao " +
                        "on a.id = ao.alumni_id " +
                        "where " +
                        "(trim(:keyword) = '' OR " +
                        "(lower(s.first_name) like concat('%',lower(:keyword),'%')) or " +
                        "(lower(s.last_name) like concat('%',lower(:keyword),'%')) or " +
                        "(lower(p.org) like concat('%',lower(:keyword),'%')) or " +
                        "(lower(ao.org) like concat('%',lower(:keyword),'%')) or " +
                        "(s.graduation_year = :keyword) or " +
                        "(lower(replace(d.program, '.', '')) like concat('%',lower(replace(:keyword, '.', '')),'%'))) "
                        +
                        "GROUP BY s.id, s.first_name, s.last_name, d.program, s.graduation_year " +
                        "order by s.first_name", nativeQuery = true)
        public List<Object[]> showStudentsByKeyword(String keyword);

}
