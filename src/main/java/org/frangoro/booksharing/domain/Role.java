package org.frangoro.booksharing.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private User user;
    private String role;

    public Role() {
        super();
    }

    public Role(final String role) {
        super();
        this.role = role;
    }

    public Role(Integer id, User user) {
        this.user = user;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role role = (Role) obj;
        if (!this.role.equals(role.role)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(role).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
}
