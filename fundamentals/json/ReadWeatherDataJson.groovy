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
 * Reads a JSON file and displays some of the data
 */

//Source file: ReadWeatherDataJson.groovy
import static java.nio.file.Paths.get as getFile

import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def weatherData

getFile('../../data/weather_data.json').withReader { jsonData ->
    weatherData = jsonSlurper.parse(jsonData)
}

weatherData.findAll { it.Year == 2015 && it.Month == '08' }
    .each { record ->
        def rainfall = record.'Rainfall (millimetres)'
        record.with {
            println "$Year-$Month-$Day: $rainfall"
        }
    }
