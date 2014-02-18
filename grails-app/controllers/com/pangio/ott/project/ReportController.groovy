package com.pangio.ott.project

import com.pangio.ott.user.User
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat

class ReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def reportService

    @Secured(["ROLE_ADMIN"])
    def buildByUser() {
    }

    @Secured(["ROLE_ADMIN"])
    def buildByProject() {
    }

    @Secured(["ROLE_ADMIN"])
    def buildCriticalProjects() {
        def result = reportService.buildCritcalProjectsReport()
        render (view: 'criticalProjects', model: [result: result, resultTotal: result.size(), params: params])

    }

    @Secured(["ROLE_ADMIN"])
    def buildReport() {

        def result = null

        if (!params.dateFrom) {
            flash.message = message(code: 'error.date.from.mandatory.message')
            redirect(uri: "/")
            return

        } else {
            convertDates(params)
        }

        if (params.buildBy == 'project') {
            def project = Project.get(params.project)
            result = reportService.buildProjectReport(project, params.dateFrom, params.dateTo)

        } else if (params.buildBy == 'user') {
            def user = User.get(params.user)
            result = reportService.buildUserReport(user, params.dateFrom, params.dateTo)
        }
        render (view: 'result', model: [result: result, resultTotal: result.size()])
    }


    private convertDates(params) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy")

        String dateFromString = params.dateFrom
        def Date dateFrom = formatter.parse(dateFromString)
        params.dateFrom = dateFrom

        if (!params.dateTo){
            params.dateTo = new Date()
        } else {
            String dateToString = params.dateTo
            def Date dateTo = formatter.parse(dateToString)
            params.dateTo = dateTo
        }
        params
    }
}
