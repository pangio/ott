package com.pangio.ott.project

import com.pangio.ott.user.User

class ReportService {

//    def List<TimeSheet> buildUserReportTotal (User user, Date dateFrom, Date dateTo) {
//        List<TimeSheet> userReport =  TimeSheet.findAllByUserAndDateBetween(user, dateFrom, dateTo)
//        return userReport
//    }
//    def List<TimeSheet> buildProjectReport (Project project, Date dateFrom, Date dateTo) {
//        List<TimeSheet> projectReport =  TimeSheet.findAllByProjectAndDateBetween(project, dateFrom, dateTo)
//        return projectReport
//    }
//

    def List<TimeSheet> buildUserAndProjectReport (User user, Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> projectAndUserReport =  TimeSheet.findAllByUserAndProjectAndDateBetween(user, project, dateFrom, dateTo)
        return projectAndUserReport
    }

    def Map buildUserReport(User user, Date dateFrom, Date dateTo) {
        List<TimeSheet> timeSheets =  TimeSheet.findAllByUserAndDateBetween(user, dateFrom, dateTo)
        LinkedHashMap<String, Object> resultMap = calculateHours(timeSheets)
        return resultMap
    }

    def List<Project> buildCritcalProjectsReport () {
        List<TimeSheet> timeSheets =  TimeSheet.findAllByExtraGreaterThan(0)
        List<Project> critalProjectsReport = new ArrayList<Project>()
        timeSheets.each {
            if (!critalProjectsReport.contains(it.project)){
                critalProjectsReport.add(it.project)
            }
        }
        return critalProjectsReport
    }

    def Map buildProjectReport (Project project, Date dateFrom, Date dateTo) {
        List<TimeSheet> timeSheets =  TimeSheet.findAllByProjectAndDateBetween(project, dateFrom, dateTo)
        LinkedHashMap<String, Object> resultMap = calculateHours(timeSheets)
        return resultMap

    }

    private LinkedHashMap<String, Object> calculateHours(List<TimeSheet> timeSheets) {
        Long totalHours = 0
        Long totalExtraHours = 0

        timeSheets.each {
            totalHours += it.hours
            totalExtraHours += it.extra
        }
        def resultMap = [totalHours: totalHours, totalExtraHours: totalExtraHours, timeSheets: timeSheets]
        resultMap
    }


    def serviceMethod() {

    }
}
