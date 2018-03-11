package com.tpokora.exercises.auth.domain;

import com.tpokora.exercises.auth.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
