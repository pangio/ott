package com.pangio.ott.project

import com.pangio.ott.user.User

class Project {

    Long id
    String name
    String description

    static hasMany = [members : User, tasks : Task]

    static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
