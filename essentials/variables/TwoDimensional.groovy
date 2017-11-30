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

//A list of readings over 4 weeks
def dailyRainfall = [
        [ monday: 7, tuesday: 5, wednesday: 19, thursday: 6, friday: 11, saturday: 8, sunday: 0 ],
        [ monday: 8, tuesday: 7, wednesday: 4, thursday: 1, friday: 4, saturday: 18, sunday: 1 ],
        [ monday: 12, tuesday: 4, wednesday: 25, thursday: 2, friday: 17, saturday: 35, sunday: 3 ],
        [ monday: 2, tuesday: 7, wednesday: 6, thursday: 12, friday: 15, saturday: 2, sunday: 1 ],
].asImmutable()

assert dailyRainfall.size() == 4

assert dailyRainfall[1].saturday == 18

assert dailyRainfall.tuesday == [ 5, 7, 4, 7 ]

assert dailyRainfall*.values().flatten().max() == 35

def dailyRainfallLabelled = [
        week1: [ monday: 7, tuesday: 5, wednesday: 19, thursday: 6, friday: 11, saturday: 8, sunday: 0 ],
        week2: [ monday: 8, tuesday: 7, wednesday: 4, thursday: 1, friday: 4, saturday: 18, sunday: 1 ],
        week3: [ monday: 12, tuesday: 4, wednesday: 25, thursday: 2, friday: 17, saturday: 35, sunday: 3 ],
        week4: [ monday: 2, tuesday: 7, wednesday: 6, thursday: 12, friday: 15, saturday: 2, sunday: 1 ],
].asImmutable()

assert dailyRainfallLabelled.week3.friday == 17

assert dailyRainfallLabelled.values().wednesday == dailyRainfall.wednesday
