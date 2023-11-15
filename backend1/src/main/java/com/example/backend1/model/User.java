package com.example.backend1.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //name field
    @Column(nullable = false)
    private String name;

    //email column should be unique and not null
//    @NaturalId(mutable = true)
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    //password
    @Column(nullable = false, length = 64)
    private String password;

    //SSN
    @Column(nullable = false, length = 9)
    private String ssn;


//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "roles",
//            joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
////    private List<String> roles;
//    private String roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;




    //Overriden from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
}
