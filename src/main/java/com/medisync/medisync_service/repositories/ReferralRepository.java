package com.medisync.medisync_service.repositories;

import com.medisync.medisync_service.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Integer> {

}