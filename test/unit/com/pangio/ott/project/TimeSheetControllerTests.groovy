package com.pangio.ott.project

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(TimeSheetController)
@Mock(TimeSheet)
class TimeSheetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reportItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reportItemInstanceList.size() == 0
        assert model.reportItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reportItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reportItemInstance != null
        assert view == '/reportItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reportItem/show/1'
        assert controller.flash.message != null
        assert TimeSheet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reportItem/list'

        populateValidParams(params)
        def reportItem = new TimeSheet(params)

        assert reportItem.save() != null

        params.id = reportItem.id

        def model = controller.show()

        assert model.reportItemInstance == reportItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reportItem/list'

        populateValidParams(params)
        def reportItem = new TimeSheet(params)

        assert reportItem.save() != null

        params.id = reportItem.id

        def model = controller.edit()

        assert model.reportItemInstance == reportItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reportItem/list'

        response.reset()

        populateValidParams(params)
        def reportItem = new TimeSheet(params)

        assert reportItem.save() != null

        // test invalid parameters in update
        params.id = reportItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reportItem/edit"
        assert model.reportItemInstance != null

        reportItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reportItem/show/$reportItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reportItem.clearErrors()

        populateValidParams(params)
        params.id = reportItem.id
        params.version = -1
        controller.update()

        assert view == "/reportItem/edit"
        assert model.reportItemInstance != null
        assert model.reportItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reportItem/list'

        response.reset()

        populateValidParams(params)
        def reportItem = new TimeSheet(params)

        assert reportItem.save() != null
        assert TimeSheet.count() == 1

        params.id = reportItem.id

        controller.delete()

        assert TimeSheet.count() == 0
        assert TimeSheet.get(reportItem.id) == null
        assert response.redirectedUrl == '/reportItem/list'
    }
}
