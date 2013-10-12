package com.pangio.ott.user

/**
 * Created with IntelliJ IDEA.
 * User: pangio
 * @author Pablo Angiorama
 * @dateCreated january, 2013
 */
class User  {

    transient springSecurityService

    String password
    String name
    String lastName
    String email
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    /**     Relations and Constraints       */

    static constraints = {
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
