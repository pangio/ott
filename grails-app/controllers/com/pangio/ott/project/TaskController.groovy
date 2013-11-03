package com.pangio.ott.project

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"])
class TaskController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count()]
    }

    @Secured(["ROLE_ADMIN"])
    def create() {
        [taskInstance: new Task(params)]
    }

    @Secured(["ROLE_ADMIN"])
    def save() {
        def taskInstance = new Task(params)
        if (!taskInstance.save(flush: true)) {
            render(view: "create", model: [taskInstance: taskInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'default.task.label', default: 'Task'), taskInstance.id])
        redirect(action: "show", id: taskInstance.id)
    }

    @Secured(["ROLE_ADMIN"])
    def edit(Long id) {
        def taskInstance = Task.get(params.id)
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "list")
            return
        }
        [taskInstance: taskInstance]
    }

    @Secured(["ROLE_ADMIN"])
    def update(Long id) {
        def taskInstance = Task.get(id)
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "list")
            return
        }
        taskInstance.properties = params
        if (!taskInstance.save(flush: true)) {
            render(view: "edit", model: [taskInstance: taskInstance])
            return
        }
        flash.message = message(code: 'default.updated.message', args: [message(code: 'default.task.label', default: 'Task'), taskInstance.id])
        redirect(action: "show", id: taskInstance.id)
    }

    def show(Long id) {
        def taskInstance = Task.get(id)
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "list")
            return
        }
        [taskInstance: taskInstance]
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        def taskInstance = Task.get(id)
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "list")
            return
        }
        try {
            taskInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'default.task.label', default: 'Task'), id])
            redirect(action: "show", id: id)
        }
    }
}
