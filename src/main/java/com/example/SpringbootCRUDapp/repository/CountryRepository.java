package com.example.SpringbootCRUDapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootCRUDapp.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long>{
//	This interface extends the JpaRepository which exposes CRUD database operations to be 
//	performed on the Country Entity
}
