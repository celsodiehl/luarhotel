package com.celsodev.LuarHotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")  //Classe UserDetails do SpringSecurity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email é Obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Nome é Obrigatório")
    private String name;

    @NotBlank(message = "Número de Telefone é Obrigatório")
    private String phoneNumber;

    @NotBlank(message = "Senha é Obrigatório")
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public @NotBlank(message = "Nome é Obrigatório") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Nome é Obrigatório") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Número de Telefone é Obrigatório") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Número de Telefone é Obrigatório") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Email é Obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email é Obrigatório") String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank(message = "Senha é Obrigatório") String password) {
        this.password = password;
    }

}
