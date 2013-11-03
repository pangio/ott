package com.pangio.ott.project

import com.pangio.ott.user.User
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"])
class ReportItemController {

    def springSecurityService
    def projectService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(["ROLE_USER"])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(["ROLE_USER"])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)
        def assignedProjects = projectService.getAssignedProjects(userInstance.id)

        [reportItemInstanceList: ReportItem.list(params), reportItemInstanceTotal: ReportItem.count(), assignedProjects: assignedProjects]
    }

    @Secured(["ROLE_USER"])
    def create() {
        [reportItemInstance: new ReportItem(params)]
    }

    @Secured(["ROLE_USER"])
    def save() {
        def reportItemInstance = new ReportItem(params)
        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)
        reportItemInstance.user = userInstance

        if (!reportItemInstance.save(flush: true)) {
            render(view: "create", model: [reportItemInstance: reportItemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), reportItemInstance.id])
        redirect(action: "show", id: reportItemInstance.id)
    }

    @Secured(["ROLE_USER"])
    def show(Long id) {
        def reportItemInstance = ReportItem.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "list")
            return
        }

        [reportItemInstance: reportItemInstance]
    }

    @Secured(["ROLE_USER"])
    def edit(Long id) {
        def reportItemInstance = ReportItem.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "list")
            return
        }

        [reportItemInstance: reportItemInstance]
    }

    @Secured(["ROLE_USER"])
    def update(Long id, Long version) {
        def reportItemInstance = ReportItem.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportItemInstance.version > version) {
                reportItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'reportItem.label', default: 'ReportItem')] as Object[],
                        "Another user has updated this ReportItem while you were editing")
                render(view: "edit", model: [reportItemInstance: reportItemInstance])
                return
            }
        }

        reportItemInstance.properties = params

        if (!reportItemInstance.save(flush: true)) {
            render(view: "edit", model: [reportItemInstance: reportItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), reportItemInstance.id])
        redirect(action: "show", id: reportItemInstance.id)
    }

    @Secured(["ROLE_USER"])
    def delete(Long id) {
        def reportItemInstance = ReportItem.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "list")
            return
        }

        try {
            reportItemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reportItem.label', default: 'ReportItem'), id])
            redirect(action: "show", id: id)
        }
    }
}
