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
 * Parses a CSV file and displays the data in JSON format
 */

//Source file: RainfallFormatJson.groovy
import static java.nio.file.Paths.get as getFile

@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.RFC4180

import groovy.json.JsonOutput

def data = RFC4180.withHeader()
    .parse(getFile('../../data/weather_data.csv').newReader())
    .iterator()
    .findAll { record ->
        record.Year.toInteger() == 2010 &&
        record.Month.toInteger() == 1
    }.collect { record->
        [day:"$record.Year-$record.Month-$record.Day",
         station: record.Station,
         max: record.'Maximum temparature (celcius)',
         min: record.'Minimum temparature (celcius)',
         rainfall: record.'Rainfall (millimetres)']
    }

print JsonOutput.prettyPrint(JsonOutput.toJson(data))
