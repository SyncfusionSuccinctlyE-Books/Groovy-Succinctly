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
 * Generates some random data to create a fake set of weather data.
 * Exports this data into CSV, JSON, XML and an SQLite database
 */

//Source file: DataGenerator.groovy

@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.RFC4180

@GrabConfig(systemClassLoader = true)
@Grab('org.xerial:sqlite-jdbc:3.8.11.2')

import static java.nio.file.Paths.get as getFile
import groovy.json.JsonOutput
import groovy.sql.Sql
import groovy.xml.MarkupBuilder
import org.apache.commons.csv.CSVPrinter

import java.time.Year
import java.time.LocalDate

def random = new Random()

def data = [ ]

def generateDayData = { year, day ->
    //Generates random data for a single day in a year
    def date = LocalDate.ofYearDay(year, day)
    def max = random.nextInt(40)

    data << [ Station                        : 'AU_QLD_098',
              Year                           : date.year,
              Month                          : "${date.monthValue}".padLeft(2, '0'),
              Day                            : "${date.dayOfMonth}".padLeft(2, '0'),
              'Maximum temparature (celcius)': max,
              'Minimum temparature (celcius)': (max == 0)? -1 * random.nextInt(10): random.nextInt(max) - 5,
              'Rainfall (millimetres)'       : "${random.nextInt(400)}.${random.nextInt(9)}"
    ]
}

def generateAnnualData = { year ->
    //Iterates through all of the days in a given year
    (1..Year.of(year).length()).each { generateDayData year, it }
}

//This next line actually kicks off the data generation:
(2006..2015).each { generateAnnualData it }

println "Records generated: $data.size"

writeCsv data
writeJson data
writeXml data
writeSqlite data

def writeCsv(data) {
    //Write out the CSV format
    getFile('weather_data.csv').withPrintWriter { writer ->
        def printer = new CSVPrinter(writer, RFC4180)
        printer.printRecord data[0].keySet()
        data.each { record ->
            printer.printRecord record.values()
        }
    }
}

//Write out the JSON file
def writeJson(data) {
    getFile('weather_data.json').withPrintWriter { writer ->
        writer.print JsonOutput.prettyPrint(JsonOutput.toJson(data))
    }
}

//Write out the XML file
def writeXml(data) {
    getFile('weather_data.xml').withPrintWriter { writer ->
        new MarkupBuilder(writer).weather {
            data.each { record ->
                'reading' day:"$record.Year-$record.Month-$record.Day",
                        station: record.Station,
                        max: record.'Maximum temparature (celcius)',
                        min: record.'Minimum temparature (celcius)',
                        rainfall: record.'Rainfall (millimetres)'
            }
        }
    }
}

def writeSqlite(data) {
    //Write the SQLite database file
    Sql.withInstance('jdbc:sqlite:weather_sqlite.db') { sql ->
        sql.execute 'DROP TABLE IF EXISTS WeatherData'
        sql.execute '''
            CREATE TABLE WeatherData (
                station NOT NULL,
                recordingDate NOT NULL,
                maxTemp,
                minTemp,
                rainfall)'''

        sql.withTransaction {
            updateCounts = sql.withBatch('INSERT INTO WeatherData(station, recordingDate, maxTemp, minTemp, rainfall) VALUES (?,?,?,?,?)') { row ->
                data.each { record ->
                    row.addBatch([ record.Station,
                                   "$record.Year-$record.Month-$record.Day",
                                   record."Maximum temparature (celcius)".toInteger(),
                                   record."Minimum temparature (celcius)".toInteger(),
                                   record."Rainfall (millimetres)".toBigDecimal()
                    ])
                }
            }
        }
        println "\nRows created in SQLite database: ${updateCounts.sum()}\n"
    }
}
