package com.pangio.user

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import com.pangio.ott.user.User

/**
 * Created with IntelliJ IDEA.
 * User: pangio
 * @author Pablo Angiorama
 * @dateCreated january, 2013
 */
@Secured(["ROLE_ADMIN", "ROLE_SUPER_ADMIN"])
class UserController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(["ROLE_USER","ROLE_ADMIN", "ROLE_SUPER_ADMIN"])
    def index() {
        redirect(action: "list", params: params)
    }

/*    @Secured(["ROLE_ADMIN", "ROLE_SUPER_ADMIN"])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }*/

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def register() {
        [userInstance: new User(params)]
    }

//    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "register", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'checklist.user.label', default: 'User'), userInstance.id])
        redirect(action: "profile", id: userInstance.id)
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def profile() {
        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def edit() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def update() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'checklist.user.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'checklist.user.label', default: 'User'), userInstance.id])
        redirect(action: "list")
    }

    @Secured(["ROLE_ADMIN"])
    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'checklist.user.label', default: 'User'), params.id])
            redirect(action: "profile", id: params.id)
        }
    }

    def recovery = {
        render(view: '/login/recovery')
    }

//    @Secured(['IS_AUTHENTICATED_FULLY'])
    def changepasswd = {
        def userInstance = User.get(params.id)
        render(template: 'changepasswd', model: [user: userInstance])
    }
}
