package com.pangio.ott.user

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * Created with IntelliJ IDEA.
 * User: pangio
 * @author Pablo Angiorama
 * @dateCreated january, 2013
 */
class LogoutController {

    /**
     * Index action. Redirects to the Spring security logout uri.
     */
    def index = {
        // TODO put any pre-logout code here
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
    }
}
