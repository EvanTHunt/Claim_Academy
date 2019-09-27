package com.claim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.claim.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>
{

	@Query("FROM Image WHERE email=?1")
	List<Image> getImagesByEmail(String email);
	
	@Query("FROM Image WHERE lng>?1 AND lng<?2 AND lat>?3 AND lat<?4")
	List<Image> getAllImagesByLatLngNormal(double wBound, double eBound, double sBound, double nBound);
	
	@Query("FROM Image WHERE (lng>?1 OR lng<?2) AND lat>?3 AND lat<?4")
	List<Image> getAllImagesByLatLngDiscontinuity(double wBound, double eBound, double sBound, double nBound);
	
}
