package com.rajanish.splitwise.repository;

import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.Groups;
import com.rajanish.splitwise.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
    List<Groups> findByParticipantsEquals(Users user);
    Groups save(Groups groups);
    Groups findByIdEquals(Long id);
}
