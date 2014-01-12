package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportItem {

    Task task
    User user
    Long hours
    Date releaseDate
    String comments
    Project project

    static constraints = {
        hours min: 0L
    }

}
