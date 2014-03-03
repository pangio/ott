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
        def user = User.get(springUser.id)
        def assignedProjects = projectService.getAssignedProjects(user.id)

        LinkedHashMap<String, Object> map = new LinkedHashMap<>()

        assignedProjects.each {
            map.put(it.id, timeSheetService.findTimesheetsByUserAndProject(user, it))
        }
        [map: map]
    }

    @Secured(["ROLE_USER"])
    def create() {
        [timeSheetInstance: new TimeSheet(params)]
    }

    @Secured(["ROLE_USER"])
    def save() {

        Long limit = grailsApplication.config.ott.extraHoursLimit

        def timeSheetInstance = new TimeSheet(params)
        def springUser = springSecurityService.getPrincipal()
        def userInstance = User.get(springUser.id)
        timeSheetInstance.user = userInstance
        timeSheetInstance.project = Project.get(params.projectId)

        if (timeSheetInstance.hours > limit){
            timeSheetInstance.extra = new Long(params.hours) - new Long(limit)
        }

        if (!timeSheetInstance.save(flush: true)) {
            render(view: "create", model: [timeSheetInstance: timeSheetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ott.timesheet.label', default: 'TimeSheet'), timeSheetInstance.id])
        redirect(action: "show", id: timeSheetInstance.id)
    }

// TODO    ELIMINAR, AFTER CREATE REDIRECT AL LIST
    @Secured(["ROLE_USER"])
    def show(Long id) {
        def timeSheetInstance = TimeSheet.get(id)
        if (!timeSheetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ott.timesheet.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        [timeSheetInstance: timeSheetInstance]
    }

//    TODO SOLO DEJAR EDITAR EL COMENTARIO
    @Secured(["ROLE_USER"])
    def edit(Long id) {
        def timeSheetInstance = TimeSheet.get(id)
        if (!timeSheetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ott.timesheet.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        [timeSheetInstance: timeSheetInstance]
    }

//    TODO LIMPIAR CODIGO
    @Secured(["ROLE_USER"])
    def update(Long id, Long version) {
        def timeSheetInstance = TimeSheet.get(id)
        if (!timeSheetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ott.timesheet.label', default: 'TimeSheet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (timeSheetInstance.version > version) {
                timeSheetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ott.timesheet.label', default: 'TimeSheet')] as Object[],
                        "Another user has updated this TimeSheet while you were editing")
                render(view: "edit", model: [timeSheetInstance: timeSheetInstance])
                return
            }
        }

        timeSheetInstance.properties = params

        if (!timeSheetInstance.save(flush: true)) {
            render(view: "edit", model: [timeSheetInstance: timeSheetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ott.timesheet.label', default: 'TimeSheet'), timeSheetInstance.id])
        redirect(action: "show", id: timeSheetInstance.id)
    }

}
