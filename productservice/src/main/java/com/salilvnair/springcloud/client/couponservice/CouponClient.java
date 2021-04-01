package com.salilvnair.springcloud.client.couponservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salilvnair.springcloud.client.couponservice.model.Coupon;

//@FeignClient("COUPON-SERVICE")
@FeignClient("zuul-api-gateway")
//@RibbonClient("COUPON-SERVICE") //zuul has server side Load Balancing
public interface CouponClient {
	@GetMapping("/coupon-service/api/coupons/{code}")
	List<Coupon> getCoupon(@PathVariable String code);
}
