package com.boot.addressBook.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.addressBook.model.addressBook;

public interface addressBookRepo extends JpaRepository<addressBook,Integer>{

}
