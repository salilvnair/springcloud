package com.salilvnair.springcloud.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.salilvnair.springcloud.api.reflect.ApiBaseController;
import com.salilvnair.springcloud.client.couponservice.CouponClient;
import com.salilvnair.springcloud.client.couponservice.model.Coupon;
import com.salilvnair.springcloud.model.Product;
import com.salilvnair.springcloud.repos.ProductRepo;

import brave.Tracer;

@ApiBaseController
public class ProductRestController {

    @Autowired
    ProductRepo productRepo;
    
    @Autowired
    CouponClient couponClient;
    
    @Autowired
    Tracer tracer;
    
    @Autowired
    ObjectMapper mapper;

    @HystrixCommand(fallbackMethod="createWithoutCouponApplied")
    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    @ResponseBody
    public Product create (@RequestBody Product product) throws JsonProcessingException {
    	List<Coupon> coupon = couponClient.getCoupon(product.getCouponCode());
    	BigDecimal discountedPrice = product.getPrice().multiply(coupon.get(0).getDiscount().divide(BigDecimal.valueOf(100)));
    	product.setPrice(product.getPrice().subtract(discountedPrice));
    	return productRepo.save(product);
    }
    
    public Product createWithoutCouponApplied(Product product) {
    	product.setDescription(product.getName()+ " saved without coupon discount" );
    	return productRepo.save(product);
    }

    @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> get (@PathVariable("name") String name) {
        return productRepo.findByName(name);
    }
}
