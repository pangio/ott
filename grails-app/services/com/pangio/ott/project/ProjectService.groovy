package com.pangio.ott.project

class ProjectService {

    def serviceMethod() {

    }

    def getAssignedProjects(Long userId) {
//        def assignedProjects = new ArrayList<Project>()
        List<Project> assignedProjects = Project.findAll()
//        allProjects.each {
//            assignedProjects.add(it)
//        }

        return assignedProjects
//        def projects = new ArrayList<Project>()
//        Project.findAllByMembers()

/*
        SELECT project.id
        FROM project
        INNER JOIN project_user
        ON project.id = project_user.project_members_id
        WHERE project_user.project_members_id = 2
*/
    }
}
