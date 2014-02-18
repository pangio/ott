package com.pangio.ott.project

import com.pangio.ott.user.User
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat

class ReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def reportService

    @Secured(["ROLE_USER"])
    def index() {
        redirect(action: "build", params: params)
    }

    @Secured(["ROLE_USER"])
    def buildByUser(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    }

    @Secured(["ROLE_USER"])
    def buildByProject(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reportInstanceList: Report.list(params), reportInstanceTotal: Report.count()]
    }

    @Secured(["ROLE_USER"])
    def buildCriticalProjects(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def result = reportService.buildCritcalProjectsReport()
        render (view: 'criticalProjects', model: [result: result, resultTotal: result.size(), params: params])

    }

    @Secured(["ROLE_USER"])
    def buildReport() {
        def result = null

        if (params.user == null && params.project == null) {
            flash.message = message(code: 'error.user.project.mandatory.message')
            redirect(action: "build")
            return
        }

        if (!params.dateFrom) {
            flash.message = message(code: 'error.date.from.mandatory.message')
            redirect(action: "build")
            return
        } else {
            normalizeDates(params)
        }


        if (params.user != null && params.project != null) {
            def user = User.get(params.user)
            def project = Project.get(params.project)
            result = reportService.buildUserAndProjectReport(user, project, params.dateFrom, params.dateTo)

        } else if (params.project) {
            def project = Project.get(params.project)
            result = reportService.buildProjectReport(project, params.dateFrom, params.dateTo)

        } else if (params.user) {
            def user = User.get(params.user)
            result = reportService.buildUserReport(user, params.dateFrom, params.dateTo)
        }

        render (view: 'result', model: [result: result, resultTotal: result.size()])
    }


    private normalizeDates (params) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy")

        if (!params.dateTo){
            params.dateTo = new Date()
        } else {
            String dateToString = params.dateTo
            def Date dateTo = formatter.parse(dateToString)
            params.dateTo = dateTo
        }

        String dateFromString = params.dateFrom
        def Date dateFrom = formatter.parse(dateFromString)
        params.dateFrom = dateFrom

        params

    }
}
