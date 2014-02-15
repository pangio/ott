package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportItemService {

    def serviceMethod() {

    }

    def getAllReportItemsByUser(Long userId) {

        def user = User.get(userId)
        def reportItems = new ArrayList<ReportItem>()
        def allReportItems = ReportItem.findAll()
        allReportItems.each {
            if (it.user.equals(user))
                reportItems.add(it)
        }
        return reportItems

    }
}
