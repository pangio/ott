modules = {
    application {
        resource url:'js/application.js'
    }

    bootstrap {
        dependsOn('jquery')
        resource url: 'bootstrap/css/bootstrap-responsive.css'
        resource url: 'bootstrap/css/bootstrap.css'
        resource url: 'bootstrap/js/bootstrap.js'
    }

    chosen {
        dependsOn('jquery')
        resource url: 'chosen/css/chosen.css', disposition: 'head'
        resource url: 'chosen/js/chosen.jquery.js', disposition: 'head'
    }
}