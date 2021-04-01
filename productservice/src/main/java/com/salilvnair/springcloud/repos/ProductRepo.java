package com.salilvnair.springcloud.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salilvnair.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);

}
