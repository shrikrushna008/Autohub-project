package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Category;
import com.app.pojos.Products;
import com.app.pojos.User;


@Repository
public interface ProductRespository extends JpaRepository<Products, Integer>{

	@Query("delete from Products p where p.id=:productId")
	void deleteProduct(@Param("productId") int productId);

	List<Products> findByCategory(Category findByName);

	List<Products> findBySupplier(User supplier); 
}