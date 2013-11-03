package com.pangio.ott.project

import com.pangio.ott.user.User
import com.pangio.ott.project.Project

class ReportService {

    def Report getUserFullReport (User user, Date firstDate, Date secondDate) {
        Report userFullReport = new Report()
        List<ReportItem> items =  ReportItem.findAllByUserAndReleaseDateBetween(user, firstDate, secondDate)
        items.each {
            userFullReport.addToItems(it)
        }
        return userFullReport
    }

    def getProjectFullReport (Project project, Date firstDate, Date secondDate) {
        Report projectReport = new Report()
        List<ReportItem> items =  ReportItem.findAllByReleaseDateBetween(firstDate, secondDate)
        items.each {
            if (it.task.project.id == project.id) {
                projectReport.addToItems(it)
            }
        }
        return projectReport

    }

    def serviceMethod() {

    }
}
