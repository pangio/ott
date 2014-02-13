package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportService {

    def List<ReportItem> buildUserReport (User user, Date dateFrom, Date dateTo) {
        List<ReportItem> userReport =  ReportItem.findAllByUserAndReleaseDateBetween(user, dateFrom, dateTo)
        return userReport
    }

    def List<ReportItem> buildProjectReport (Project project, Date dateFrom, Date dateTo) {
        List<ReportItem> projectReport =  ReportItem.findAllByProjectAndReleaseDateBetween(project, dateFrom, dateTo)
        return projectReport
    }

    def List<ReportItem> buildUserAndProjectReport (User user, Project project, Date dateFrom, Date dateTo) {
        List<ReportItem> projectAndUserReport =  ReportItem.findAllByUserAndProjectAndReleaseDateBetween(user, project, dateFrom, dateTo)
        return projectAndUserReport
    }

    def serviceMethod() {

    }
}
