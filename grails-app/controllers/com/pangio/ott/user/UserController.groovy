package com.pangio.ott.user

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER, ROLE_USER"])
class UserController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(["ROLE_ADMIN", "ROLE_SUPERUSER"])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(["ROLE_ADMIN", "ROLE_SUPERUSER"])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def register() {
        [userInstance: new User(params)]
    }

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "register", model: [userInstance: userInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'default.user.label', default: 'User'), userInstance.id])
        redirect(action: "welcome", id: userInstance.id)
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def welcome(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        [userInstance: userInstance]
    }

    @Secured("IS_AUTHENTICATED_FULLY")
    def profile() {
        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)

        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        [userInstance: userInstance]
    }

//    TODO move to OTT
//    @Secured("IS_AUTHENTICATED_FULLY")
//    def publicProfile() {
//        def springUser = springSecurityService.getPrincipal()
//        def userInstance = User.get(springUser.id)
//
//        if (!userInstance) {
//            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
//            redirect(action: "list")
//            return
//        }
//        [userInstance: User.get(params.id)]
//    }

    @Secured(["ROLE_USER"])
    def edit() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        [userInstance: userInstance]
    }

    @Secured(["ROLE_USER"])
    def update() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect ("/")
            return
        }
        userInstance.properties = params
        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }
        flash.message = message(code: 'default.updated.message', args: [message(code: 'default.user.label', default: 'User'), userInstance.id])
        redirect(action: "profile")
    }

    @Secured(["ROLE_ADMIN"])
    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }
        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'default.user.label', default: 'User'), params.id])
            redirect(action: "profile", id: params.id)
        }
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def recovery() {
        render(template: '/login/recovery')
    }

    @Secured(["IS_AUTHENTICATED_FULLY"])
    def changepasswd() {
        def userInstance = User.get(params.id)
        render(template: 'changepasswd', model: [user: userInstance])
    }
}
