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
 * Uses MarkupBuilder to create a basic HTML file
 */

//Source file: ReadXmlUrl.groovy

import static java.nio.file.Paths.get as getFile
import groovy.xml.MarkupBuilder

getFile('weather_data_demo.html').withWriter('UTF-8') {
    new MarkupBuilder(outputWriter).html {
        head {
            title 'Rainfall readings'
        }
        body {
            h1 'A selection of rainfall readings'
            h2 'Year: 1990'
            table {
                tr {
                    th 'Month'
                    th 'Day'
                    th 'Reading'
                }
                tr {
                    td '01'
                    td '21'
                    td '12.7'
                }
                tr {
                    td '01'
                    td '22'
                    td '6.3'
                }
            }
        }
    }
}
