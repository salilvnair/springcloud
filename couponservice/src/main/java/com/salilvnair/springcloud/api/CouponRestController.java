package com.salilvnair.springcloud.api;

import com.salilvnair.springcloud.api.reflect.ApiBaseController;
import com.salilvnair.springcloud.model.Coupon;
import com.salilvnair.springcloud.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiBaseController
public class CouponRestController {

    @Autowired
    CouponRepo couponRepo;

    @RequestMapping(value = "/coupon/create", method = RequestMethod.POST)
    @ResponseBody
    public Coupon create (@RequestBody Coupon coupon) {
        return couponRepo.save(coupon);
    }

    @RequestMapping(value = "/coupons/{code}", method = RequestMethod.GET)
    @ResponseBody
    public List<Coupon> get (@PathVariable("code") String code) {
    	System.out.println("Server 8081");
        return couponRepo.findByCode(code);
    }
}
