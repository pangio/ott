package com.pangio.ott.project

import com.pangio.ott.user.User

class TimeSheetService {

    def serviceMethod() {

    }

    def findTimesheetsByUser(User user) {
        def userTimesheets = TimeSheet.findAllByUser(user)
        return userTimesheets

    }

    def findTimesheetsByUserAndProject(User user, Project project) {
        def userTimesheets = TimeSheet.findAllByUserAndProject(user, project)
        return userTimesheets
    }

}
