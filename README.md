## Open Time Tracker Management Tool
===
## FEATURES:

* Login / Signup / Profile
* CRUD of Tasks
* CRUD of Projects
* 'Admin Role' Users able to asign Projects to Users
* 'Standard Role' Users able to submit Timesheet to assigned Projects
* Create Reports by User and date between
* Create Reports by Project and date between
* Create Reports by Critical Projects (extra hours submitted)


# Stack
*  Groovy / Grails 2.2.2
*  Twitter Bootstrap v2.3.2
*  MySql
  

## INSTALL:

* clone repository
```
https://github.com/pangio/ott.git
```
* create MySQL schema on your local env (previously MySql installed)
```
schema name: 'ott' 
user: 'root'
pass: ''
```
* setup DataSource.groovy with schema/user/pass if you've created with different connection details
* run the Time Tracker tool by executing 
```
grails run-app
```


