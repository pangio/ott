package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportService {

    def List<TimeSheet> buildUserReport (User user, Date dateFrom, Date dateTo) {
        List<TimeSheet> userReport =  TimeSheet.findAllByUserAndDateBetween(user, dateFrom, dateTo)
        return userReport
    }

    def List<TimeSheet> buildProjectReport (Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> projectReport =  TimeSheet.findAllByProjectAndDateBetween(project, dateFrom, dateTo)
        return projectReport
    }

    def List<TimeSheet> buildUserAndProjectReport (User user, Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> projectAndUserReport =  TimeSheet.findAllByUserAndProjectAndDateBetween(user, project, dateFrom, dateTo)
        return projectAndUserReport
    }

    def serviceMethod() {

    }
}
