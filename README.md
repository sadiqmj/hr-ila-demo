# hr-ila-demo
HR ILA Assigment

the project is a simple API to mange employee
it has four endpoints:
* add employee: POST /employees
* get employee by id: GET /employees/{id}
* get employee by cpr: GET /employees/cpr/{cpr}
* get employees list with filtering by name and salary range: GET /employees?name={name}&fromSalary={from-salary}&toSalary={toSalary}

the API is integrated with keycloak ([keycloak.org](https://www.keycloak.org/)), to potect it using OAuth token.
