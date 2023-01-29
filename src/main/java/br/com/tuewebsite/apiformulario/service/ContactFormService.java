package br.com.tuewebsite.apiformulario.service;

import br.com.tuewebsite.apiformulario.entity.ContactFormEntity;
import br.com.tuewebsite.apiformulario.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactFormService {

    @Autowired
    ContactFormRepository contactFormRepository;

    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    //POST
    public ContactFormEntity saveContact(ContactFormEntity contactFormEntity) {
        return contactFormRepository.save(contactFormEntity);
    }

    //GET
    public List<ContactFormEntity> getContact() {
        return contactFormRepository.findAll();
    }

    //GET ID
    public Optional<ContactFormEntity> getConactById(Long id) {
        return contactFormRepository.findById(id);
    }
    //DELETE
    public void deleteContact(Long id) {
        contactFormRepository.deleteById(id);
    }

    // Método para buscar apenas os emails
    public List<String> findAllEmails() {
        List<ContactFormEntity> allContacts = contactFormRepository.findAll();
        List<String> allEmails = new ArrayList<>();
        for (ContactFormEntity contactForm : allContacts) {
            allEmails.add(contactForm.getEmail());
        }
        return allEmails;
    }

    // Método para buscar apenas os telefones
    public List<String> findAllPhones() {
        // busca todos os contatos cadastrados
        List<ContactFormEntity> allContacts = contactFormRepository.findAll();
        // cria uma lista vazia para armazenar os telefones
        List<String> allPhones = new ArrayList<>();
        // percorre a lista de contatos
        for (ContactFormEntity contactForm : allContacts) {
            // adiciona o telefone do contato atual na lista de telefones
            allPhones.add(contactForm.getPhone());
        }
        return allPhones;
    }

}
