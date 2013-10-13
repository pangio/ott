package com.pangio.ott.user

class User {

    transient springSecurityService

    String username
    String password
    boolean enabled = true
    String name
    String lastName
    String email
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true
        email blank: false, unique: true
        password blank: false
    }

    static mapping = {
        version false
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    @Override
    String toString() {
        return name + ' ' + lastName
    }

}
