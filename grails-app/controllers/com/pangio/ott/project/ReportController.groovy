package com.pangio.ott.project

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.dao.DataIntegrityViolationException

class ReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(["ROLE_USER"])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(["ROLE_USER"])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    }

    @Secured(["ROLE_USER"])
    def create() {
        [reportInstance: new Report(params)]
    }

    @Secured(["ROLE_USER"])
    def save() {
        def reportInstance = new Report(params)
        if (!reportInstance.save(flush: true)) {
            render(view: "create", model: [reportInstance: reportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'report.label', default: 'Report'), reportInstance.id])
        redirect(action: "show", id: reportInstance.id)
    }

    @Secured(["ROLE_USER"])
    def show(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    @Secured(["ROLE_USER"])
    def edit(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        [reportInstance: reportInstance]
    }

    @Secured(["ROLE_USER"])
    def update(Long id, Long version) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportInstance.version > version) {
                reportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'report.label', default: 'Report')] as Object[],
                        "Another user has updated this Report while you were editing")
                render(view: "edit", model: [reportInstance: reportInstance])
                return
            }
        }

        reportInstance.properties = params

        if (!reportInstance.save(flush: true)) {
            render(view: "edit", model: [reportInstance: reportInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'report.label', default: 'Report'), reportInstance.id])
        redirect(action: "show", id: reportInstance.id)
    }

    @Secured(["ROLE_USER"])
    def delete(Long id) {
        def reportInstance = Report.get(id)
        if (!reportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
            return
        }

        try {
            reportInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'report.label', default: 'Report'), id])
            redirect(action: "show", id: id)
        }
    }
}
