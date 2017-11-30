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

def emptyMap = [ : ]

def weatherDetails = [ day: 'Monday', rainfall: 3.4, maxTemp: 31 ]

println "On ${weatherDetails.day} we got ${weatherDetails.rainfall}mm"

weatherDetails = [ 'day': 'Monday', 'rainfall': 3.4, 'maxTemp': 31 ]

def dailyRainfall = [ monday   : 7,
                      tuesday  : 5,
                      wednesday: 19,
                      thursday : 6,
                      friday   : 11,
                      saturday : 8,
                      sunday   : 0 ].asImmutable()

dailyRainfall.each { key, value ->
    println "$key had ${value}mm of rain"
}

assert dailyRainfall.friday == 11

def day = 'friday'
assert dailyRainfall."$day" == 11

assert dailyRainfall.size() == 7

assert dailyRainfall.keySet() == [ 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday' ] as Set

//I need to change dailyRainfall.values() to a List for the comparison to work
assert dailyRainfall.values() as List == [ 7, 5, 19, 6, 11, 8, 0 ]

assert dailyRainfall.values().max() == 19

assert dailyRainfall.values().min() == 0

//Map keys aren't limited to a single word:
def weatherMap = [ 'Maximum temperature': 32,
                   'Minimum temperature': 18 ]
