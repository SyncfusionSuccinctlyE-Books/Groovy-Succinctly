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
 * Uses MarkupBuilder to create a basic XML file
 */

//Source file: XmlBuilderDemo.groovy

import static java.nio.file.Paths.get as getFile

import groovy.xml.MarkupBuilder

def outputWriter = getFile('weather_data_demo.xml').newWriter('UTF-8')

def weatherData = new MarkupBuilder(outputWriter)

//In case you want to have the XML Declaration
//weatherData.mkp.xmlDeclaration(version: '1.0', encoding: 'utf-8')

weatherData.rainfall {
    year (value: 1990) {
        reading month: '01', day: '21', 12.7
        reading month: '01', day: '22', 6.3
    }
    year (value: 1995) {
        reading month: '01', day: '21', 0
        reading month: '01', day: '22', 1.8
    }
}

outputWriter.close()
