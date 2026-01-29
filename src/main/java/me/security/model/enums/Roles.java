package me.security.model.enums;

import java.util.Set;

public enum Roles {
    ADMIN(Set.of(Permission.READ,Permission.WRITE, Permission.DELETE)),
    USER(Set.of(Permission.READ));

    private final Set<Permission> permissions;

    Roles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
