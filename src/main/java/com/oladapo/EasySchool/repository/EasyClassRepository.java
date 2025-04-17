package com.oladapo.EasySchool.repository;

import com.oladapo.EasySchool.model.EasyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EasyClassRepository extends JpaRepository<EasyClass, Integer> {
}
