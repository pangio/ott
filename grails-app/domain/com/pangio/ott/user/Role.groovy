package com.pangio.ott.user

/**
 * Created with IntelliJ IDEA.
 * User: pangio
 * @author Pablo Angiorama
 * @dateCreated january, 2013
 */
class Role {

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
