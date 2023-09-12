package com.ilkinmehdiyev.unitech_test_task.model.entity;

import com.ilkinmehdiyev.unitech_test_task.model.constants.RoleConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = RoleConstants.TABLE_NAME)
@ToString
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
}
