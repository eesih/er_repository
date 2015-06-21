curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"added":"2014-09-12T12:02:53.387","address":"5th steet","modified":"2014-09-12T12:02:53.387","name":"Matt Damon","role":"DEVELOPER"}]' localhost:8080/employeeResources/resources/employees/save

curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d  '{"days":0,"hours":0,"employee":1,"monthMM":11,"yearYYYY":2014}' localhost:8080/employeeResources/resources/timesheets/save


