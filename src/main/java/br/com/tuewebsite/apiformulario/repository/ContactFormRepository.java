package br.com.tuewebsite.apiformulario.repository;

import br.com.tuewebsite.apiformulario.entity.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactFormEntity, Long> {
}
