package com.oladapo.EasySchool.services;

import com.oladapo.EasySchool.constants.EasySchoolConstants;
import com.oladapo.EasySchool.model.Contact;
import com.oladapo.EasySchool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service

public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

//    public ContactService(ContactRepository contactRepository) {
//        this.contactRepository = contactRepository;
//    }

    public ContactService() {
        System.out.println("Contact Service Bean Initialized");
    }

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EasySchoolConstants.OPEN);
        contact.setCreatedBy(EasySchoolConstants.Anonymous);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    public List<Contact> findMsgWithOpenStatus(){
        return contactRepository.findMsgsWithStatus(EasySchoolConstants.OPEN);
    }

    public boolean updateMsgStatus(int id, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(id, EasySchoolConstants.CLOSE, updatedBy);
        if(result> 0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
