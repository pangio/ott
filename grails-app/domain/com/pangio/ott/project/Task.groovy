package com.pangio.ott.project

class Task {

    Long id
    String name

    static belongsTo = [project : Project]

    static constraints = {
    }

    @Override
    String toString() {
        return name
    }
}
