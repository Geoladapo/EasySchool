package com.oladapo.EasySchool.services;

import com.oladapo.EasySchool.constants.EasySchoolConstants;
import com.oladapo.EasySchool.model.Person;
import com.oladapo.EasySchool.model.Roles;
import com.oladapo.EasySchool.repository.PersonRepository;
import com.oladapo.EasySchool.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;

    public PersonService(PersonRepository personRepository,
                         RolesRepository rolesRepository) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
    }

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(EasySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if(null != person && person.getPersonId() > 0){
            isSaved = true;
        }
        return isSaved;
    }
}
