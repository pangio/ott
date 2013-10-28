package com.pangio.ott.project

class Task {

    Long id
    String name
    Project project

//    static belongsTo = [project : Project]

    static constraints = {
        project nullable: true
    }

    @Override
    String toString() {
        return name
    }
}
