package com.pangio.ott

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"])
class HomeController {

    def index() {}

    def about (){
        render (view: "/about")
    }

    def home (){
        render (view: "/home")
    }
}
