package com.pangio.ott.user

class User {

    String name
    String lastName
    String email

    static constraints = {

    }

    @Override
    String toString() {
        return name + ' '+ lastName
    }
}
