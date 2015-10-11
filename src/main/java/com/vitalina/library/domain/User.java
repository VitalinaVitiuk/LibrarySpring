package com.vitalina.library.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = User.FIND_USER_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")
})

@Table(name = "users")
public class User {
    public static final String FIND_USER_BY_USERNAME = "FIND_USER_BY_USERNAME";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min=5, max=15, message="{username.error.size}")
    private String username;

    @Length(min=5, max=15, message="{password.error.size}")
    private String password;

    @NotBlank(message = "{firstName.error.null}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{lastName.error.null}")
    @Column(name = "last_name")
    private String lastName;
    private Boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authority;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Issuance> issuances;

    public User() {
    }

    public List<Issuance> getIssuances() {
        return issuances;
    }

    public void setIssuances(List<Issuance> issuances) {
        this.issuances = issuances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }
}