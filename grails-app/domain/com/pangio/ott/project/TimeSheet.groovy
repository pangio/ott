package com.pangio.ott.project

import com.pangio.ott.user.User

class TimeSheet {

    Task task
    User user
    Long hours
    Long extra
    Date date
    String comments
    Project project

    static constraints = {
        hours min: 0L
    }

}
