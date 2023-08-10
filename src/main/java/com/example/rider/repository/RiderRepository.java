package com.example.rider.repository;

import com.example.rider.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    @Query("SELECT r FROM Rider r WHERE r.phone = :cred OR r.email = :cred")
    Optional<Rider> findByContact(@Param("cred") String cred);
}
