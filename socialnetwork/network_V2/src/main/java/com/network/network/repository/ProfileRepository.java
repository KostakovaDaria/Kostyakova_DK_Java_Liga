package com.network.network.repository;

import com.network.network.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID>, JpaSpecificationExecutor<Profile>
{

    @Query(value = "SELECT p.id, p.firstname, p.lastname, p.active, p.birthday, p.email, p.interests, " +
            "p.city, p.gender FROM profile p " +
            "INNER JOIN friends f ON p.id = f.user_id or p.id = f.friend_id" +
            " WHERE (f.friend_id = ?1 OR f.user_id = ?1) AND p.id != ?1 AND f.status = ?2", nativeQuery = true,
            countQuery = "SELECT count(*) FROM profile")
    Page<Profile> findAllByFriends(UUID Id, Boolean status, Pageable pageable);

}
