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

class WeatherStation {
    def id
    def name, url
    BigDecimal latitude, longitude, height
    final tempReadings = []

    String toString() {
        "$name(#$id): $latitude lat, $longitude long, $height height"
    }

    def addTempReading(temp) {
        tempReadings << temp
    }

    def getAverageTemperature() {
        tempReadings.sum() / tempReadings.size()
    }
}

def davisStation = new WeatherStation(id: 300000, name: 'DAVIS',
        latitude: -68.57, longitude: 77.97, height: 18.0,
        url: 'http://www.bom.gov.au/products/IDT60803/IDT60803.89571.shtml')

davisStation.addTempReading 5
davisStation.addTempReading 3
davisStation.addTempReading 7
davisStation.tempReadings << 2

println davisStation.toString()
println davisStation
println "$davisStation"
println "Station url: ${davisStation.getUrl()}"
println "Average temperature: ${davisStation.averageTemperature}"
