package com.pitchbridge.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio, meu nobre!")
    private String name;

    @Email(message = "Manda um e-mail válido aí!")
    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, message = "A senha precisa de pelo menos 6 caracteres")
    private String password;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Dream> dreams = new ArrayList<>();

    @OneToMany(mappedBy = "donor")
    private List<Contribution> contributions = new ArrayList<>();


    // --- MÉTODOS DO CONTRATO DE SEGURANÇA (USERDETAILS) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aqui dizemos que todo usuário criado terá o papel de "USER"
        // No futuro, você pode ter ADMIN também!
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        // O Spring Security pergunta: "Qual campo eu uso como login?"
        // Nós respondemos: "Usa o e-mail!"
        return email;
    }

    @Override
    public String getPassword() {
        // O Spring pergunta onde está a senha
        return password;
    }

    // Métodos de controle de conta (Por enquanto, deixamos tudo 'true')
    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta nunca bloqueia
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As chaves nunca vencem
    }

    @Override
    public boolean isEnabled() {
        return true; // O usuário está sempre ativo
    }
}
