package com.example.carsystem.models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    private Date birthday;
    
    @NotNull
    private String login;
    
    @NotNull
    private String password;
    
    @NotBlank
    @Pattern(regexp = "\\d{10}")
    private String phone;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CarEntity> cars = new ArrayList<>();

    @PastOrPresent
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    
    @PastOrPresent
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant lastLogin;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void addCar(CarEntity car) {
        cars.add(car);
        car.setUser(this);
    }

    public void removeCar(CarEntity car) {
        cars.remove(car);
        car.setUser(null);
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }



}