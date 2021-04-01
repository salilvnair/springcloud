package com.salilvnair.springcloud.repos;

import com.salilvnair.springcloud.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    List<Coupon> findByCode(String code);
}
