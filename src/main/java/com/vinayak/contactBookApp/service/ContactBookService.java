package com.vinayak.contactBookApp.service;

import com.vinayak.contactBookApp.model.ContactBook;
import com.vinayak.contactBookApp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class ContactBookService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactBook> getAllNotes() {
        return contactRepository.findAll();
    }

    public ContactBook createNote(ContactBook contactBook) {
        return contactRepository.save(contactBook);
    }



}
