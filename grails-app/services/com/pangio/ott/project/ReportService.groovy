package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportService {

    def List<TimeSheet> buildUserReport (User user, Date dateFrom, Date dateTo) {
        List<TimeSheet> userReport =  TimeSheet.findAllByUserAndReleaseDateBetween(user, dateFrom, dateTo)
        return userReport
    }

    def List<TimeSheet> buildProjectReport (Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> projectReport =  TimeSheet.findAllByProjectAndReleaseDateBetween(project, dateFrom, dateTo)
        return projectReport
    }

    def List<TimeSheet> buildUserAndProjectReport (User user, Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> projectAndUserReport =  TimeSheet.findAllByUserAndProjectAndReleaseDateBetween(user, project, dateFrom, dateTo)
        return projectAndUserReport
    }

    def serviceMethod() {

    }
}
