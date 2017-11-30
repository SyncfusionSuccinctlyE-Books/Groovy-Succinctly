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
 * Queries an SQLite database and displays the result
 */

//Source file: SQLiteDatabaseAverageRainfall.groovy

@GrabConfig(systemClassLoader = true)
@Grab('org.xerial:sqlite-jdbc:3.8.11.2')

import groovy.sql.Sql

Sql.withInstance('jdbc:sqlite:../../data/weather_sqlite.db') { sql ->
    println 'Annual rainfall averages:'
    sql.eachRow('''
      SELECT strftime('%Y', recordingDate) as year, avg(rainfall) as rainfallAverage
      FROM WeatherData
      GROUP BY strftime('%Y', recordingDate)''') { row ->
        println "  $row.year: ${row.rainfallAverage.round(2)}"
    }
}
