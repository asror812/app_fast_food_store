package com.example.app_fast_food.user.otp;

import com.example.app_fast_food.user.otp.entity.Otp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends CrudRepository<Otp, String> {

    Optional<Otp> findByPhoneNumber(String phoneNumber);
}
