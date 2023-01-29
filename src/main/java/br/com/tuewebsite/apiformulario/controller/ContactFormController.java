package br.com.tuewebsite.apiformulario.controller;

import br.com.tuewebsite.apiformulario.entity.ContactFormEntity;
import br.com.tuewebsite.apiformulario.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/contact")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    //Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactFormEntity createContact(@RequestBody  ContactFormEntity contactFormEntity){
        return contactFormService.saveContact(contactFormEntity);
    }

    //Get
    @GetMapping
    public List<ContactFormEntity> getContact(){
        return contactFormService.getContact();
    }

    // GET request to search a specific contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContactFormEntity>> findById(@PathVariable Long id) {
        Optional<ContactFormEntity> contact = contactFormService.getConactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }


    // DELETE request to delete a specific contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactFormService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // GET request to search all emails
    @GetMapping("/emails")
    public ResponseEntity<List<String>> findAllEmails() {
        List<String> emails = contactFormService.findAllEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    // GET request to search all phones
    @GetMapping("/phones")
    public ResponseEntity<List<String>> findAllPhones() {
        List<String> phones = contactFormService.findAllPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
}
