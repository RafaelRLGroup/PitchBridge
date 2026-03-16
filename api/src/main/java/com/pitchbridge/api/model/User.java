package com.pitchbridge.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Data // Gera Getters, Setters, toString, Equals e HashCode automaticamente
@NoArgsConstructor // Gera o construtor vazio (exigido pelo JPA)
@AllArgsConstructor // Gera o construtor com todos os campos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio, meu nobre!")
    private String name;

    @Email(message = "Manda um e-mail válido aí!")
    @NotBlank
    @Column(unique = true) // Garante que não existam dois usuários com o mesmo e-mail
    private String email;

    @NotBlank
    @Size(min = 6, message = "A senha precisa de pelo menos 6 caracteres")
    private String password;

    // Um usuário pode ter vários sonhos postados
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Dream> dreams = new ArrayList<>();

    // Um usuário pode ter feito várias contribuições
    @OneToMany(mappedBy = "donor")
    private List<Contribution> contributions = new ArrayList<>();
}