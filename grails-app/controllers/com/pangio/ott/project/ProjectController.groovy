package com.pangio.ott.project

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"])
class ProjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [projectInstanceList: Project.list(params), projectInstanceTotal: Project.count()]
    }

    @Secured(["ROLE_ADMIN"])
    def create() {
        [projectInstance: new Project(params), tasks : Task.list()]
    }

    @Secured(["ROLE_ADMIN"])
    def save() {
        def projectInstance = new Project(params)
        if (!projectInstance.save(flush: true)) {
            render(view: "create", model: [projectInstance: projectInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'default.project.label', default: 'Project'), projectInstance.id])
        redirect(action: "show", id: projectInstance.id)
    }

    def show(Long id) {
        def projectInstance = Project.get(id)
        if (!projectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "list")
            return
        }
        [projectInstance: projectInstance]
    }

    @Secured(["ROLE_ADMIN"])
    def edit(Long id) {
        def projectInstance = Project.get(params.id)
        if (!projectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "list")
            return
        }
        [projectInstance: projectInstance]
    }

    @Secured(["ROLE_ADMIN"])
    def update(Long id) {
        def projectInstance = Project.get(id)
        if (!projectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "list")
            return
        }
        projectInstance.properties = params
        if (!projectInstance.save(flush: true)) {
            render(view: "edit", model: [projectInstance: projectInstance])
            return
        }
        flash.message = message(code: 'default.updated.message', args: [message(code: 'default.project.label', default: 'Project'), projectInstance.id])
        redirect(action: "show", id: projectInstance.id)
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        def projectInstance = Project.get(id)
        if (!projectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "list")
            return
        }
        try {
            projectInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'default.project.label', default: 'Project'), id])
            redirect(action: "show", id: id)
        }
    }
}
