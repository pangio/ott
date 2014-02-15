import com.pangio.ott.project.Project
import com.pangio.ott.project.Task
import com.pangio.ott.project.TimeSheet
import com.pangio.ott.user.Role
import com.pangio.ott.user.User
import com.pangio.ott.user.UserRole

class BootStrap {

    def userRole
    def adminRole
    def superAdminRole

    def init = { servletContext ->

        createRoles()
        createSuperUser()
        createAdmin()
        createUsers()
        createProjects()
        createTasks()
        submitHours()

    }

    private void createAdmin(){
        def adminOTT= new User(
                name: "admin",
                lastName: "admin",
                username: "admin",
                email: "admin@ott.com",
                password: "pass",
                enabled: true
        ).save(flush: true)

        UserRole.create(adminOTT, userRole, true)
        UserRole.create(adminOTT, adminRole, true)

    }

    private void createSuperUser(){
        def superUserOTT= new User(
                name: "super",
                lastName: "user",
                username: "super",
                email: "super@ott.com",
                password: "pass",
                enabled: true
        ).save(flush: true)

        UserRole.create(superUserOTT, userRole, true)
        UserRole.create(superUserOTT, adminRole, true)
        UserRole.create(superUserOTT, superAdminRole, true)

    }

    def createRoles() {
        userRole = new Role(authority: 'ROLE_USER').save(flush: true)
        adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        superAdminRole = new Role(authority: 'ROLE_SUPERUSER').save(flush: true)
    }


    def createUsers() {
        def user = new User(
                name: 'Paul',
                lastName: 'Peter',
                username: 'paul',
                email: 'paul.peter@gmail.com',
                password: 'pass',
                enabled: true)
                .save(flush: true)
                UserRole.create user, userRole, true

        user = new User(
                name: 'Peter',
                lastName: 'Pan',
                username: 'peter',
                email: 'peter.pan@gmail.com',
                password: 'pass',
                enabled: true)
                .save(flush: true)
                UserRole.create user, userRole, true

        user = new User(
                name: 'John',
                lastName: 'Doe',
                username: 'john',
                email: 'john.doe@gmail.com',
                password: 'pass',
                enabled: true)
                .save(flush: true)
                UserRole.create user, userRole, true
    }

    def createTasks() {
        def task = new Task(name: 'Development').save(flush: true)
        task = new Task(name: 'QA frontend', project: Project.get(1)).save(flush: true)
        task = new Task(name: 'QA backend', project: Project.get(1)).save(flush: true)
        task = new Task(name: 'Performance', project: Project.get(2)).save(flush: true)
        task = new Task(name: 'Design', project: Project.get(2)).save(flush: true)
        task = new Task(name: 'Marketing', project: Project.get(3)).save(flush: true)
        task = new Task(name: 'Administration', project: Project.get(3)).save(flush: true)
    }

    def createProjects() {
        def project = new Project(
                name: 'Website on dev',
                description: 'offshore team',
                members: [User.get(4), User.get(2), User.get(3),User.get(1)]
        ).save(flush: true)
        project = new Project(
                name: 'Website in Testing',
                description: 'onsite team',
                members: [User.get(4), User.get(5), User.get(1)]
        ).save(flush: true)
        project = new Project(
                name: 'Admin Stuff',
                description: 'accountant, financial, administration',
                tasks: [Task.get(6), Task.get(7)],
                members: [User.get(3)]
        ).save(flush: true)
    }

    def submitHours (){
        def reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(1),
                project: Project.get(1),
                user: User.get(1),
                comments: 'diseño',
                hours: 2L,
        ).save(flush: true)

        reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(2),
                project: Project.get(1),
                user: User.get(1),
                comments: 'diseño',
                hours: 2L,
        ).save(flush: true)
        reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(3),
                project: Project.get(1),
                user: User.get(1),
                comments: 'diseño',
                hours: 4L,
        ).save(flush: true)
        reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(1),
                project: Project.get(2),
                user: User.get(1),
                comments: 'diseño',
                hours: 4L,
        ).save(flush: true)
        reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(1),
                project: Project.get(2),
                user: User.get(1),
                comments: 'diseño',
                hours: 8L,
        ).save(flush: true)
        reportItem = new TimeSheet(
                releaseDate: new Date(),
                task: Task.get(2),
                project: Project.get(2),
                user: User.get(1),
                comments: 'diseño',
                hours: 8L,
        ).save(flush: true)

    }

    def destroy = {
        adminRole = null
        userRole = null
        superAdminRole = null
    }
}
