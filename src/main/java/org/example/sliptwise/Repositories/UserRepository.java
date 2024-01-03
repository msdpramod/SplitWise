package org.example.sliptwise.Repositories;


import org.example.sliptwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findById(Long aLong);

    Optional<User> findByPhoneNumber(String phone);
//    findByFirsName(String name);

    @Override
    <S extends User> S save(S entity);
}