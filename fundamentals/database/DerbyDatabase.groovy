/*
 *    Copyright 2016 Duncan Dickinson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * A larger database example that demonstrates commencting to a Derby
 * database (in-memory), creating a table and loading it with data
 * from a CSV using batching in a transaction.
 * Finally, a couple of queries are run.
 */

//Source file: DerbyDatabase.groovy

import static java.nio.file.Paths.get as getFile
import static java.sql.Date.valueOf
@GrabConfig(systemClassLoader = true)
@Grab('org.apache.derby:derby:10.12.1.1')

@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.RFC4180

import groovy.sql.Sql

//Read in the CSV
def parser = RFC4180.withHeader()
        .parse(getFile('../../data/weather_data.csv').newReader())

//Get a connection to the database
def sql = Sql.newInstance('jdbc:derby:memory:myDB;create=true')

sql.execute '''
    CREATE TABLE WeatherData (
        id INT NOT NUll GENERATED ALWAYS AS IDENTITY,
        station CHAR(10) NOT NULL,
        date DATE NOT NULL,
        maxTemp SMALLINT,
        minTemp SMALLINT,
        rainfall DECIMAL
    )
'''

//Insert the CSV records into the WeatherData table
def insertCounts = 0
sql.withTransaction {
    def insertSql = '''
        INSERT INTO WeatherData(station, date, maxTemp,
                                minTemp, rainfall)
        VALUES (?,?,?,?,?)'''


    parser.each { record ->
        record.with {
            sql.executeInsert(insertSql,
                    [ Station,
                      valueOf("$Year-$Month-$Day"),
                      record."Maximum temparature (celcius)",
                      record."Minimum temparature (celcius)",
                      record."Rainfall (millimetres)"
                    ])
        }
        insertCounts++
    }

}

println "Rows created: $insertCounts"

//Query some summary data
sql.firstRow('''
  SELECT min(date) as startDate,
         max(date) as endDate,
         max(maxTemp) as highestTemp,
         min(minTemp) as lowestTemp,
         avg(maxTemp) as averageMaxTemp,
         avg(minTemp) as averageMinTemp
  FROM WeatherData''').with {
    println """Temperature extremes for $startDate to $endDate
    Highest temperature: $highestTemp (average: $averageMaxTemp)
    Lowest temperature: $lowestTemp (average: $averageMinTemp)"""
}

//Query the rainfall for a specific month.
println '\nRainfall for February 2012:'
sql.eachRow("""
  SELECT date, rainfall
  FROM WeatherData
  WHERE YEAR(date) = 2012 AND MONTH(date) = 2""") { row ->
    println "${row.date.toLocalDate().dayOfMonth}: $row.rainfall"
}

//Last thing is to close the database connection
sql.close()
