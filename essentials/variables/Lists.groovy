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

//Some list examples

def emptyList = [ ]

def dailyRainfall = [ 0, 4, 3, 7, 2, 4, 8 ].asImmutable()

dailyRainfall.each ({ println it })
dailyRainfall.each (){ println it }
dailyRainfall.each { println it }

def printer = { println it }
dailyRainfall.each printer

assert dailyRainfall.size() == 7

assert dailyRainfall[4] == 2

assert dailyRainfall[-2] == 4

assert dailyRainfall.min() == 0

assert dailyRainfall.max() == 8

assert dailyRainfall.sort(false) == [ 0, 2, 3, 4, 4, 7, 8 ]

assert dailyRainfall.unique(false).sort(false) == [ 0, 2, 3, 4, 7, 8 ]

assert dailyRainfall.sum() == 28

//Calculate the mean (average)
assert dailyRainfall.sum() / dailyRainfall.size() == 4

//Just to make sure nothing changed the list
assert dailyRainfall == [ 0, 4, 3, 7, 2, 4, 8 ]

//A list of strings
def measurements = [ 'temperature', 'rainfall', 'humidity' ]

//A range
def countdown = 10..0

//A Set
def days = [ 'monday', 'tuesday', 'monday' ] as Set
assert days == [ 'monday', 'tuesday' ] as Set
