# siva-spring-repo

The application loads flight information details to an H2 in memory database.

Its saves only basic information like flightnumber,flightdate,origin,destination,arrival date,departure date uploaded through a CSV file. 

The file’s contents are validated before its saved to the database through regular expressions.

File upload: - http://localhost:8080/archive/fileupload 

Show all flights in database: - http://localhost:8080/archive/show

sample input file attached.

