package com.vinayak.contactBookApp.controllers;

import com.vinayak.contactBookApp.exception.ResourceNotFoundException;
import com.vinayak.contactBookApp.model.ContactBook;
import com.vinayak.contactBookApp.repository.ContactRepository;
import com.vinayak.contactBookApp.service.ContactBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contactBook")
public class ContactBookController {


    @Autowired
    private ContactBookService contactBookService;

    @Autowired
    private ContactRepository contactRepository;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ContactBook> getAllNotes() {
            return contactBookService.getAllNotes();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ContactBook createNote(@Valid @RequestBody ContactBook contactBook) {
        return contactBookService.createContact(contactBook);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long contactId) {
        ContactBook note = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", contactId));

        contactRepository.delete(note);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/notes/{id}")
    public ContactBook updateNote(@PathVariable(value = "id") Long contactId,
                           @Valid @RequestBody ContactBook contactBook) {

        ContactBook contactBook1 = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", contactId));

        contactBook1.setName(contactBook.getName());
        contactBook1.setEmail(contactBook.getEmail());

        ContactBook updatedContact = contactRepository.save(contactBook1);
        return updatedContact;
    }

}
