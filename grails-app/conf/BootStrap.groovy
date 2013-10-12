import com.pangio.ott.project.Project
import com.pangio.ott.project.Task
import com.pangio.ott.user.User

class BootStrap {

    def init = { servletContext ->

        createUsers()
        createTasks()
        createProjects()

    }

    def createUsers() {
        def user = new User(name: 'Paul', lastName: 'Peter', email: 'paul.peter@gmail.com').save(flush: true)
        user = new User(name: 'Peter', lastName: 'Pan', email: 'peter.pan@gmail.com').save(flush: true)
        user = new User(name: 'John', lastName: 'Doe', email: 'john.doe@gmail.com').save(flush: true)
    }

    def createTasks() {
        def task = new Task(name: 'Development').save(flush: true)
        task = new Task(name: 'QA frontend').save(flush: true)
        task = new Task(name: 'QA backend').save(flush: true)
        task = new Task(name: 'Performance').save(flush: true)
        task = new Task(name: 'Design').save(flush: true)
        task = new Task(name: 'Marketing').save(flush: true)
        task = new Task(name: 'Administration').save(flush: true)
    }

    def createProjects() {
        def project = new Project(
                name: 'Website on dev',
                description: 'offshore team',
                tasks: [Task.get(1),Task.get(5), Task.get(3)],
                members: [User.get(1), User.get(2), User.get(3)]
        ).save(flush: true)
        project = new Project(
                name: 'Website in Testing',
                description: 'onsite team',
                tasks: [Task.get(2), Task.get(2), Task.get(4)],
                members: [User.get(1), User.get(2)]
        ).save(flush: true)
        project = new Project(
                name: 'Admin Stuff',
                description: 'accountant, financial, administration',
                tasks: [Task.get(6), Task.get(7)],
                members: [User.get(2)]
        ).save(flush: true)
    }

    def destroy = {
    }
}