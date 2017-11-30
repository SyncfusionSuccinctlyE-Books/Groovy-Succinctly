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
 * Reads weather data from a CSV and creates a map of the
 * reading date and rainfall measurement. The data is then
 * grouped by year, then by month
 *
 * Displays the data in JSON format
 */

//Source file: RainfallDataGrouping.groovy
import static groovy.json.JsonOutput.prettyPrint
import static groovy.json.JsonOutput.toJson
import static java.nio.file.Paths.get as getFile
import static java.util.stream.Collectors.groupingBy
import static java.util.stream.Collectors.toMap

@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.*

import java.time.LocalDate

def weatherData = RFC4180.withHeader()
        .parse(getFile('../data/weather_data.csv').newReader())
        .getRecords()

def getDate = { y, m, d ->
    LocalDate.of(y.toInteger(),
            m.toInteger(),
            d.toInteger())
}

def monthlyData = weatherData.stream()
    .map {
        [date: getDate(it.Year, it.Month, it.Day),
         rainfall: it.'Rainfall (millimetres)'.toBigDecimal()]
    }.collect groupingBy({ it.date.year },
                groupingBy({ it.date.month },
                    toMap({ it.date.day },{ it.rainfall })))

print prettyPrint(toJson(monthlyData))
