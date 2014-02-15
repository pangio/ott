package com.pangio.ott.project

import com.pangio.ott.user.User

class TimeSheetService {

    def serviceMethod() {

    }

    def getAllReportItemsByUser(Long userId) {

        def user = User.get(userId)
        def reportItems = new ArrayList<TimeSheet>()
        def allReportItems = TimeSheet.findAll()
        allReportItems.each {
            if (it.user.equals(user))
                reportItems.add(it)
        }
        return reportItems

    }
}
