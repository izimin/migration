# APP MIGRATION
Приложение отвечающее за: 
- перенос данных из одного источника в другой;

## API
`/api/migrate/developer
`
### Request Body
`file: binary file
`
### Request Params
`typeFile: enum XLS | JSON | ... 
`
### Response
Об успешной миграции информаирует статус 200 OK<br> 
Об ошибке информирует статус 400 BAD REQUEST

## Run liquibase (create table)
    clean install -DskipTests -Dhost=localhost -Dport=5432 -Ddb=test -Dschema=test -Dlogin=postgres -Dpassword=123456

## Run App
    -DdbHost=localhost
    -DdbPortNumber=5432
    -DdbName=test
    -Dschema=test
    -DdbUser=postgres
    -DdbPassword=123456
    -DserverPort=8080