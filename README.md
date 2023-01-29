# API de formulário 

A aplicação que está sendo desenvolvida é um formulário de contato, onde os usuários podem enviar informações como nome, email, telefone e mensagem. Essas informações são armazenadas em uma entidade chamada ContactFormEntity e são gerenciadas pela classe de serviço ContactFormService.

O controlador REST, ContactFormController, é responsável por gerenciar as requisições HTTP, como as requisições do tipo POST para criar um novo contato, GET para obter uma lista de contatos ou um contato específico, DELETE para excluir um contato e GET para buscar todos os emails ou telefones.

Ao criar um novo contato, o método createContact é chamado e o status HTTP 201 (CREATED) é retornado. Ao obter uma lista de contatos, o método getContact é chamado e a lista é retornada. O método findById é chamado quando é necessário obter um contato específico, passando o ID como parâmetro e o status HTTP 200 (OK) é retornado junto com o contato encontrado. O método delete é chamado quando é necessário excluir um contato, passando o ID como parâmetro e o status HTTP 204 (NO CONTENT) é retornado.

Além disso, a aplicação também tem a capacidade de buscar todos os emails ou telefones existentes, chamando os respectivos métodos findAllEmails e findAllPhones.

Em resumo, a aplicação é uma ferramenta simples e fácil de usar para gerenciar contatos, com recursos para criar, obter, excluir e buscar contatos específicos. Além disso, também oferece a possibilidade de buscar todos os emails ou telefones existentes.



## Para rodar esta aplicação em sua máquina, você precisará dos seguintes requisitos:

- Um ambiente de desenvolvimento Java configurado, incluindo o JDK (Java Development Kit) e o Eclipse, IntelliJ IDEA ou outra IDE Java de sua escolha
- Banco de dados H2, configurado e rodando na sua máquina.
- Uma instalação do Maven para gerenciar as dependências do projeto
- Certifique-se de que você tenha as dependências corretas, como spring-data-jpa e spring-web, adicionadas ao seu arquivo pom.xml
- Certifique-se de que a porta 8080 não está sendo usada por outra aplicação em sua máquina.

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
```

```java
 // Método para salvar um novo contato no banco de dado
    // Declaração de uma função/método chamado saveContact que recebe como parâmetro um objeto do 		tipo ContactFormEntity e retorna um objeto do mesmo tipo
	public ContactFormEntity saveContact(ContactFormEntity contactFormEntity) {
    // Utiliza o método save do repositório de contato para salvar o contato fornecido como parâmetro 		e retorna o objeto salvo
    return contactFormRepository.save(contactFormEntity);
}
```

Este código declara uma função/método chamado saveContact que recebe como parâmetro um objeto do tipo ContactFormEntity e retorna um objeto do mesmo tipo. Dentro da função, ele utiliza o método save do repositório de contato para salvar o contato fornecido como parâmetro e retorna o objeto salvo. Esse método pode ser usado para criar ou atualizar um contato existente no banco de dados, dependendo do estado do objeto passado como parâmetro.

```java

    // Método para retornar todos os contatos armazenados no banco de dados
    // Declaração de uma função/método chamado getContact que retorna uma lista de objetos do tipo 		ContactFormEntity
	public List<ContactFormEntity> getContact() {
    // Utiliza o método findAll do repositório de contato para retornar todos os contatos existentes 	na base de dados
    return contactFormRepository.findAll();
}
```

Este código declara uma função/método chamado getContact que retorna uma lista de objetos do tipo ContactFormEntity. Dentro da função, ele utiliza o método findAll do repositório de contato para retornar todos os contatos existentes na base de dados. Esse método é utilizado para buscar todos os contatos armazenados no banco de dados.

```java
 // Método para retornar um contato específico pelo id
    // Declaração de uma função/método chamado getConactById que retorna um objeto do tipo Optional   		de ContactFormEntity
	public Optional<ContactFormEntity> getConactById(Long id) {
    // Utiliza o método findById do repositório de contato para encontrar um contato pelo id e 			retorna o resultado
    return contactFormRepository.findById(id);
}
```

Este código declara uma função/método chamado getConactById que retorna um objeto do tipo Optional de ContactFormEntity. Dentro da função, ele utiliza o método findById do repositório de contato para encontrar um contato pelo id fornecido como parâmetro e retorna o resultado. O tipo Optional é utilizado para indicar que o método pode retornar um valor nulo, caso não seja encontrado nenhum contato com o id especificado.

```java
// Método para deletar um contato específico pelo id
    public void deleteContact(Long id) {
        contactFormRepository.deleteById(id);
    }
```

Este código declara uma função/método chamado deleteContact que recebe um parâmetro do tipo Long e não tem retorno. Dentro da função, ele utiliza o método deleteById do repositório de contato para remover o contato com o id fornecido como parâmetro. Esse método é utilizado para remover um contato específico armazenado no banco de dados, com base no id fornecido como parâmetro.

```java
// Método para retornar todos os emails armazenados no banco de dados
    // Declaração de uma função/método chamado findAllEmails que retorna uma lista de strings
	public List<String> findAllEmails() {
    // Cria uma variável chamada allContacts que guarda todos os contatos obtidos através do método f	 indAll do repositório de contato
    List<ContactFormEntity> allContacts = contactFormRepository.findAll();
    // Cria uma lista vazia de strings chamada allEmails
    List<String> allEmails = new ArrayList<>();
    // Itera através de cada contato na lista allContacts
    for (ContactFormEntity contactForm : allContacts) {
        // Adiciona o email do contato atual para a lista allEmails
        allEmails.add(contactForm.getEmail());
    }
    // Retorna a lista de todos os emails
    return allEmails;
	}
```

Este código declara uma função/método chamado findAllEmails que retorna uma lista de strings. Dentro da função, ele cria uma variável chamada allContacts que guarda todos os contatos obtidos através do método findAll do repositório de contato. Em seguida, ele cria uma lista vazia de strings chamada allEmails. Ele então itera através de cada contato na lista allContacts e adiciona o email do contato atual para a lista allEmails. Por fim, ele retorna a lista allEmails que contém todos os emails dos contatos.

```java
/ Declaração de uma função/método chamado findAllPhones que retorna uma lista de strings
public List<String> findAllPhones() {
    // busca todos os contatos cadastrados através do método findAll do repositório de contato
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
```

Este código declara uma função/método chamado findAllPhones que retorna uma lista de strings. Dentro da função, ele busca todos os contatos cadastrados através do método findAll do repositório de contato, armazena em uma lista chamada allContacts. Ele então cria uma nova lista vazia chamada allPhones para armazenar os telefones. Em seguida, ele usa um for loop para percorrer a lista de contatos, e adiciona o telefone de cada contato atual na lista de telefones. Por fim, a função retorna a lista de telefones.

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
```

```java
//Mapeamento para requisições do tipo POST
    @PostMapping
    //Retorna o status 201 (CREATED) quando a requisição for bem sucedida
    @ResponseStatus(HttpStatus.CREATED)
    public ContactFormEntity createUser(@RequestBody  ContactFormEntity contactFormEntity){
        //Chama o método saveContact da classe de serviço e retorna o objeto salvo
        return contactFormService.saveContact(contactFormEntity);
    }
```

-  Este é um método de mapeamento para requisições do tipo POST. Ele é anotado com @PostMapping para indicar que é um método de tratamento para requisições POST. 
- Quando a requisição for bem sucedida, ele retorna o status HTTP 201 (CREATED) usando a anotação @ResponseStatus (HttpStatus.CREATED).
-  O método recebe um parâmetro @RequestBody do tipo ContactFormEntity, que é o objeto que será salvo no banco de dados. 
- Ele chama o método saveContact da classe de serviço e retorna o objeto salvo

```java
//Mapeamento para requisições do tipo GET
    @GetMapping
    public List<ContactFormEntity> getContact(){
        //Retorna a lista de contatos retornada pelo método getContact da classe de serviço
        return contactFormService.getContact();
    }
```

- Este é um método de mapeamento para requisições do tipo GET. Ele é anotado com @GetMapping para indicar que é um método de tratamento para requisições GET. 
- Ele não recebe nenhum parâmetro, pois a requisição GET não tem corpo. 
- Ele chama o método getContact da classe de serviço e retorna a lista de contatos retornada pelo método.

```java
//Mapeamento para requisições do tipo GET com um parâmetro
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContactFormEntity>> findById(@PathVariable Long id) {
        //Chama o método getConactById da classe de serviço com o ID passado como parâmetro
        Optional<ContactFormEntity> contact = contactFormService.getConactById(id);
        //Retorna o contato encontrado com o status 200 (OK)
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
```

- Este é um método de mapeamento para requisições do tipo GET que aceita um parâmetro. Ele é anotado com @GetMapping("/{id}") para indicar que é um método de tratamento para requisições GET com um parâmetro específico, o ID do contato. 
- Ele utiliza @PathVariable para indicar que o parâmetro id na URL será usado como entrada para o método. 
- Ele chama o método getConactById da classe de serviço com o ID passado como parâmetro e retorna o contato encontrado com o status 200 (OK).

```java
//Mapeamento para requisições do tipo DELETE com um parâmetro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        //Chama o método deleteContact da classe de serviço com o ID passado como parâmetro
        contactFormService.deleteContact(id);
        //Retorna o status 204 (NO CONTENT)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
```

Este é um método para lidar com requisições do tipo DELETE na rota /{id}. Ele usa o annotation @DeleteMapping para mapear a requisição para este método. Ele aceita um parâmetro @PathVariable chamado id, que é o ID do contato a ser excluído. Dentro do método, ele chama o método deleteContact na classe de serviço ContactFormService passando o ID como parâmetro. Isso excluirá o contato correspondente do banco de dados. Finalmente, ele retorna um ResponseEntity com o status HTTP 204 (NO CONTENT), indicando que a operação foi bem-sucedida e não há conteúdo para retornar.

```java
  //Método Get para buscar todos os emails
@GetMapping("/emails")
public ResponseEntity<List<String>> findAllEmails() {
    List<String> emails = contactFormService.findAllEmails();
    return new ResponseEntity<>(emails, HttpStatus.OK);
}
```

Este código cria um método GET para buscar todos os telefones armazenados no banco de dados através da chamada ao método findAllPhones() da classe de serviço. O método retorna uma lista de strings contendo os telefones. A resposta é enviada com o status HTTP 200 (OK). Esse método é mapeado para a rota "/phones".

```java
//Método Get para buscar todos os telefones
@GetMapping("/phones")
public ResponseEntity<List<String>> findAllPhones() {
    List<String> phones = contactFormService.findAllPhones();
    return new ResponseEntity<>(phones, HttpStatus.OK);
}
}
```

Este é um método Get, mapeado para requisições do tipo GET com a rota "/phones". Ele chama o método findAllPhones() da classe de serviço ContactFormService e retorna uma lista de strings com todos os telefones de contato cadastrados. O método retorna um objeto ResponseEntity com a lista de telefones e o status HTTP 200 (OK). A anotação @GetMapping indica que este método é chamado quando uma requisição GET é feita para a rota especificada.



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

Espero que você tenha gostado do conteúdo apresentado e que esteja se sentindo mais preparado para entender e implementar esse projeto de exemplo de uma aplicação de formulário de contato. Lembre-se de que, para rodar essa aplicação em sua máquina, é necessário ter o Java 8 ou superior e o Maven instalados, além de configurar o banco H2. Caso tenha alguma dúvida ou precise de ajuda adicional, não hesite em perguntar.