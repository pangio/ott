package com.pangio.ott.project

import com.pangio.ott.user.User
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"])
class TimeSheetController {

    def springSecurityService
    def projectService
    def timeSheetService
    def grailsApplication

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

        // TODO get and send to view one list per project
        // TODO fix view to support it
        def reportItems = new ArrayList<TimeSheet>()
        reportItems = timeSheetService.getAllTimesheetsByUser(userInstance.id)

        [reportItemInstanceList: reportItems, reportItemInstanceTotal: reportItems.size(), assignedProjects: assignedProjects]
    }

    @Secured(["ROLE_USER"])
    def create() {
        [reportItemInstance: new TimeSheet(params)]
    }

    @Secured(["ROLE_USER"])
    def save() {

        Long limit = grailsApplication.config.ott.extraHoursLimit

        def reportItemInstance = new TimeSheet(params)
        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)
        reportItemInstance.user = userInstance
        reportItemInstance.project = Project.get(params.projectId)

        if (reportItemInstance.hours > limit){
            reportItemInstance.extra = new Long(params.hours) - new Long(limit)
        }

        if (!reportItemInstance.save(flush: true)) {
            render(view: "create", model: [reportItemInstance: reportItemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reportItem.label', default: 'TimeSheet'), reportItemInstance.id])
        redirect(action: "show", id: reportItemInstance.id)
    }

// TODO    ELIMINAR, AFTER CREATE REDIRECT AL LIST
    @Secured(["ROLE_USER"])
    def show(Long id) {
        def reportItemInstance = TimeSheet.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        [reportItemInstance: reportItemInstance]
    }

//    TODO SOLO DEJAR EDITAR EL COMENTARIO
    @Secured(["ROLE_USER"])
    def edit(Long id) {
        def reportItemInstance = TimeSheet.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        [reportItemInstance: reportItemInstance]
    }

//    TODO LIMPIAR CODIGO
    @Secured(["ROLE_USER"])
    def update(Long id, Long version) {
        def reportItemInstance = TimeSheet.get(id)
        if (!reportItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportItem.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportItemInstance.version > version) {
                reportItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'reportItem.label', default: 'TimeSheet')] as Object[],
                        "Another user has updated this TimeSheet while you were editing")
                render(view: "edit", model: [reportItemInstance: reportItemInstance])
                return
            }
        }

        reportItemInstance.properties = params

        if (!reportItemInstance.save(flush: true)) {
            render(view: "edit", model: [reportItemInstance: reportItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reportItem.label', default: 'TimeSheet'), reportItemInstance.id])
        redirect(action: "show", id: reportItemInstance.id)
    }

}
