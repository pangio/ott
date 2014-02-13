package com.pangio.ott.project

import com.pangio.ott.user.User

class ProjectService {

    def serviceMethod() {

    }

    def getAssignedProjects(Long userId) {

        def user = User.get(userId)
        def assignedProjects = new ArrayList<Project>()
        def allProjects = Project.findAll()
        allProjects.each {
            if (it.members.contains(user))
                assignedProjects.add(it)
        }
        return assignedProjects

    }
}
