package com.pangio.ott.project



import org.junit.*
import grails.test.mixin.*

@TestFor(ReportItemController)
@Mock(ReportItem)
class ReportItemControllerTests {

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
        assert ReportItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reportItem/list'

        populateValidParams(params)
        def reportItem = new ReportItem(params)

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
        def reportItem = new ReportItem(params)

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
        def reportItem = new ReportItem(params)

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
        def reportItem = new ReportItem(params)

        assert reportItem.save() != null
        assert ReportItem.count() == 1

        params.id = reportItem.id

        controller.delete()

        assert ReportItem.count() == 0
        assert ReportItem.get(reportItem.id) == null
        assert response.redirectedUrl == '/reportItem/list'
    }
}
