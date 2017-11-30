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
 * Reads weather data from a CSV, filters only readings for
 * February 2008, then sorts by rainfall (descending).
 *
 * Displays the data as: <day>: <rainfall>
 */

//Source file: RainfallDataSort.groovy

import static java.nio.file.Paths.get as getFile

@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.*

def weatherData = RFC4180.withHeader()
        .parse(getFile('../data/weather_data.csv').newReader())
        .getRecords()

def sortDescending = { n1, n2 ->
    n1.toBigDecimal() <=> n2.toBigDecimal()
}

weatherData.stream()
    .filter { it.Year == '2008' }
    .filter { it.Month == '02' }
    .sorted { day1, day2 ->
        sortDescending day2.'Rainfall (millimetres)',
                       day1.'Rainfall (millimetres)' }
    .forEach {
        println "$it.Day: ${it.'Rainfall (millimetres)'}"
    }
