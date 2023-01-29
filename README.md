# API de formulário 

A aplicação que está sendo desenvolvida é um formulário de contato, onde os usuários podem enviar informações como nome, email, telefone e mensagem. Essas informações são armazenadas em uma entidade chamada ContactFormEntity e são gerenciadas pela classe de serviço ContactFormService.

O controlador REST, ContactFormController, é responsável por gerenciar as requisições HTTP, como as requisições do tipo POST para criar um novo contato, GET para obter uma lista de contatos ou um contato específico, DELETE para excluir um contato e GET para buscar todos os emails ou telefones.

Ao criar um novo contato, o método createContact é chamado e o status HTTP 201 (CREATED) é retornado. Ao obter uma lista de contatos, o método getContact é chamado e a lista é retornada. O método findById é chamado quando é necessário obter um contato específico, passando o ID como parâmetro e o status HTTP 200 (OK) é retornado junto com o contato encontrado. O método delete é chamado quando é necessário excluir um contato, passando o ID como parâmetro e o status HTTP 204 (NO CONTENT) é retornado.

Além disso, a aplicação também tem a capacidade de buscar todos os emails ou telefones existentes, chamando os respectivos métodos findAllEmails e findAllPhones.

Em resumo, a aplicação é uma ferramenta simples e fácil de usar para gerenciar contatos, com recursos para criar, obter, excluir e buscar contatos específicos. Além disso, também oferece a possibilidade de buscar todos os emails ou telefones existentes.



## Classe Entity

Este é o código de uma classe Java chamada "ContactFormEntity", que representa uma entidade de formulário de contato no banco de dados.

A classe utiliza anotações do Lombok (@Data, @AllArgsConstructor, @NoArgsConstructor) para gerar automaticamente getters, setters e construtores. Também usa anotações do JPA (@Entity, @Table, @Id, @GeneratedValue) para mapear a entidade para uma tabela no banco de dados.

A anotação @Entity indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados. A anotação @Table especifica o nome da tabela como "TB_CONTACTS".

A anotação @Id indica que o atributo "id" é a chave primária da entidade e a anotação @GeneratedValue especifica que o valor será gerado automaticamente pelo banco de dados.

As anotações @Column especificam que os atributos "name", "email", "phone" e "message" são colunas na tabela e não podem ser nulos.

A classe também possui um construtor sem argumentos e um construtor com todos os argumentos.



```java
package br.com.tuewebsite.apiformulario.entity;

import lombok.*;
import javax.persistence.*;

// Anotação Lombok para gerar automaticamente getters, setters e construtores
@Data
@AllArgsConstructor
@NoArgsConstructor

// Anotação JPA indicando que esta classe é uma entidade
@Entity

// Especificando o nome da tabela no banco de dados
@Table(name = "TB_CONTACTS")
public class ContactFormEntity {

    // Anotação JPA indicando que este atributo é a chave primária da entidade
    @Id
    // Especificando que o valor do atributo será gerado automaticamente pelo banco de dados
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Anotação JPA indicando que este atributo é uma coluna na tabela e não pode ser nulo
    @Column(nullable = false)
    private String name;

    // Anotação JPA indicando que este atributo é uma coluna na tabela e não pode ser nulo
    @Column(nullable = false)
    private String email;

    // Anotação JPA indicando que este atributo é uma coluna na tabela e não pode ser nulo
    @Column(nullable = false)
    private String phone;

    // Anotação JPA indicando que este atributo é uma coluna na tabela e não pode ser nulo
    @Column(nullable = false)
    private String message;
}

```



## Classe Repository

Este é o código de uma interface chamada "ContactFormRepository", que estende a interface JpaRepository do Spring Data JPA.

A anotação @Repository indica que esta interface é um repositório Spring e será gerenciada pelo Spring Data JPA.

A interface estende JpaRepository<ContactFormEntity, Long>, especificando que será um repositório para a entidade ContactFormEntity e que a chave primária é do tipo Long. Isso fornece uma série de operações CRUD (Create, Read, Update, Delete) básicas para a entidade ContactFormEntity, sem precisar implementar essas operações manualmente.

Com esta interface, é possível realizar operações de banco de dados como salvar, excluir, buscar e atualizar entidades ContactFormEntity usando o Spring Data JPA, sem precisar escrever códigos SQL ou implementar essas operações manualmente.

```java
package br.com.tuewebsite.apiformulario.repository;

import br.com.tuewebsite.apiformulario.entity.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Anotação indicando que esta interface é um repositório gerenciado pelo Spring Data JPA
@Repository
public interface ContactFormRepository extends JpaRepository<ContactFormEntity, Long> {
    // A interface estende JpaRepository, fornecendo operações CRUD básicas para a entidade ContactFormEntity
    // sem precisar escrever códigos SQL ou implementar essas operações manualmente
}

```



## Classe Service

Este código representa uma classe de serviço para gerenciar formulários de contato. Ele utiliza a interface ContactFormRepository para salvar, buscar, atualizar e excluir entidades de formulários de contato. A classe está anotada com @Service, o que indica que é um componente gerenciado pelo Spring e pode ser injetado em outros componentes do aplicativo.

A classe possui métodos para salvar um novo formulário de contato, buscar todos os formulários de contato, buscar um formulário de contato específico pelo ID, excluir um formulário de contato e buscar apenas os emails e telefones de todos os formulários de contato cadastrados.

- O método saveContact() é usado para salvar um novo formulário de contato no banco de dados, ele utiliza o método save() da interface ContactFormRepository.
- O método getContact() é usado para buscar todos os formulários de contato cadastrados no banco de dados, ele utiliza o método findAll() da interface ContactFormRepository.
- O método getConactById() é usado para buscar um formulário de contato específico pelo ID, ele utiliza o método findById() da interface ContactFormRepository.
- O método deleteContact() é usado para excluir um formulário de contato específico pelo ID, ele utiliza o método deleteById() da interface ContactFormRepository.
- O método findAllEmails() é usado para buscar apenas os emails de todos os formulários de contato cadastrados. Ele primeiro busca todos os contatos usando o método findAll() da interface ContactFormRepository e, em seguida, percorre a lista de contatos adicionando cada email encontrado em uma lista de emails.
- O método findAllPhones() é usado para buscar apenas os telefones de todos os formulários de contato cadastrados. Ele primeiro busca todos os contatos usando o método findAll() da interface ContactFormRepository e, em seguida, percorre a lista de contatos adicionando cada telefone encontrado em uma lista de telefones.

```java
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

    //Injetando o repositorio
    @Autowired
    ContactFormRepository contactFormRepository;

    //Construtor
    @Autowired
    public ContactFormService(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

   // Método para salvar um novo contato no banco de dado
    public ContactFormEntity saveContact(ContactFormEntity contactFormEntity) {
        return contactFormRepository.save(contactFormEntity);
    }

    // Método para retornar todos os contatos armazenados no banco de dados
    public List<ContactFormEntity> getContact() {
        return contactFormRepository.findAll();
    }

    // Método para retornar um contato específico pelo id
    public Optional<ContactFormEntity> getConactById(Long id) {
        return contactFormRepository.findById(id);
    }
    
    // Método para deletar um contato específico pelo id
    public void deleteContact(Long id) {
        contactFormRepository.deleteById(id);
    }

    // Método para retornar todos os emails armazenados no banco de dados
    public List<String> findAllEmails() {
        List<ContactFormEntity> allContacts = contactFormRepository.findAll();
        List<String> allEmails = new ArrayList<>();
        for (ContactFormEntity contactForm : allContacts) {
            allEmails.add(contactForm.getEmail());
        }
        return allEmails;
    }

     // Método para retornar todos os telefones armazenados no banco de dados
    public List<String> findAllPhones() {
        return contactFormRepository.findAllPhones();
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

```



## Classe Controller

Este código é um controlador Spring REST que gerencia solicitações HTTP para um formulário de contato. Ele possui vários métodos, cada um deles mapeado para uma URL específica e um verbo HTTP (GET, POST, DELETE).

A anotação @RestController indica que esta classe é um controlador REST e as solicitações serão tratadas por métodos dentro dessa classe. A anotação @RequestMapping("/contact") diz que todas as solicitações para esse controlador devem começar com "/contact".

O método createUser é mapeado para uma solicitação POST com a anotação @PostMapping. Ele aceita um objeto JSON no corpo da solicitação e o salva no banco de dados usando o serviço de contato. Ele retorna o objeto salvo.

O método getContact é mapeado para uma solicitação GET com a anotação @GetMapping. Ele retorna todos os contatos existentes do banco de dados usando o serviço de contato.

O método findById é mapeado para uma solicitação GET com a anotação @GetMapping e uma variável de caminho {id}. Ele busca um contato específico pelo ID e retorna-o como uma resposta HTTP.

O método delete é mapeado para uma solicitação DELETE com a anotação @DeleteMapping e uma variável de caminho {id}. Ele exclui um contato específico pelo ID e retorna uma resposta HTTP vazia.

O método findAllEmails é mapeado para uma solicitação GET com a anotação @GetMapping("/emails"). Ele retorna uma lista de todos os e-mails dos contatos existentes no banco de dados usando o serviço de contato.

O método findAllPhones é mapeado para uma solicitação GET com a anotação @GetMapping("/phones"). Ele retorna uma lista de todos os telefones dos contatos existentes no banco de dados usando o serviço de contato.

O Autowired é usado para injetar uma instância de ContactFormService no controlador, que é usado para acessar os métodos de persistência de dados.

```java
package br.com.tuewebsite.apiformulario.controller;

import br.com.tuewebsite.apiformulario.entity.ContactFormEntity;
import br.com.tuewebsite.apiformulario.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Indica que essa classe é um controlador REST
@RestController
//Indica que todas as requisições para esse controlador vão começar com /contact
@RequestMapping(value = "/contact")
public class ContactFormController {

    //Injeta a classe de serviço
    @Autowired
    private ContactFormService contactFormService;

    //Mapeamento para requisições do tipo POST
    @PostMapping
    //Retorna o status 201 (CREATED) quando a requisição for bem sucedida
    @ResponseStatus(HttpStatus.CREATED)
    public ContactFormEntity createUser(@RequestBody  ContactFormEntity contactFormEntity){
        //Chama o método saveContact da classe de serviço e retorna o objeto salvo
        return contactFormService.saveContact(contactFormEntity);
    }

    //Mapeamento para requisições do tipo GET
    @GetMapping
    public List<ContactFormEntity> getContact(){
        //Retorna a lista de contatos retornada pelo método getContact da classe de serviço
        return contactFormService.getContact();
    }

    //Mapeamento para requisições do tipo GET com um parâmetro
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContactFormEntity>> findById(@PathVariable Long id) {
        //Chama o método getConactById da classe de serviço com o ID passado como parâmetro
        Optional<ContactFormEntity> contact = contactFormService.getConactById(id);
        //Retorna o contato encontrado com o status 200 (OK)
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    //Mapeamento para requisições do tipo DELETE com um parâmetro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        //Chama o método deleteContact da classe de serviço com o ID passado como parâmetro
        contactFormService.deleteContact(id);
        //Retorna o status 204 (NO CONTENT)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //Método Get para buscar todos os emails
@GetMapping("/emails")
public ResponseEntity<List<String>> findAllEmails() {
    List<String> emails = contactFormService.findAllEmails();
    return new ResponseEntity<>(emails, HttpStatus.OK);
}

//Método Get para buscar todos os telefones
@GetMapping("/phones")
public ResponseEntity<List<String>> findAllPhones() {
    List<String> phones = contactFormService.findAllPhones();
    return new ResponseEntity<>(phones, HttpStatus.OK);
}
}
```

## Properies.yml

Esse código é uma configuração do banco de dados para uma aplicação Spring. Ele está configurando o uso do banco de dados H2 em memória, o que significa que os dados serão perdidos quando a aplicação for fechada. Ele também está configurando o uso do JPA (Java Persistence API) para o gerenciamento das entidades e o Hibernate como o provedor de implementação. Ele especifica a URL de conexão, o nome de usuário e a senha, bem como o driver JDBC para acessar o banco de dados. Além disso, as configurações JPA são fornecidas para especificar o dialeto de SQL utilizado pelo banco de dados H2 e para exibir e formatar as consultas SQL no console. Também há configurações para acesso à console do H2, que permite visualizar e gerenciar o banco de dados diretamente através de uma interface web.

```sql
# Configurações do Spring para conexão com o banco de dados
spring:
  # Configurações para acessar o banco de dados H2
  datasource:
    # URL de conexão com o banco de dados H2
    url: jdbc:h2:mem:testdb
    # Nome de usuário para conexão com o banco de dados
    username: sa
    # Senha de acesso ao banco de dados (não há senha configurada)
    password:
    # Classe do driver JDBC para conexão com o H2
    driverClassName: org.h2.Driver

  # Configurações JPA para acesso ao banco de dados
  jpa:
    # Dialeto de SQL utilizado pelo banco de dados H2
    database-platform: org.hibernate.dialect.H2Dialect
    # Exibir as consultas SQL no console
    properties.hibernate.show_sql: true
    # Formatar as consultas SQL no console
    properties.hibernate.format_sql: true

  # Configurações para acesso à console do H2
  h2:
    console:
      # Ativar acesso à console do H2
      enabled: true
      # Caminho para acessar a console (ex: http://localhost:8080/h2)
      path: /h2

```

