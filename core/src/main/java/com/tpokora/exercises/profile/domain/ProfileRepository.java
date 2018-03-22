package com.tpokora.exercises.profile.domain;

import com.tpokora.exercises.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    public Profile findByEmail(String email);
}
