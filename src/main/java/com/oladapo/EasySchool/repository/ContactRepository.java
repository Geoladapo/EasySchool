package com.oladapo.EasySchool.repository;

import com.oladapo.EasySchool.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);
}