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

//BOOLEANS
def honest = true
def lie = false

assert true && true
assert true || false
assert !(false || false)

//NUMBERS

//A basic integer (whole) number)
def age = 24

//A negative number
def cold = -10

//A larger number
def lotteryWin = 100_000_000

//A decimal number
def pi = 3.14

//Some basic operations
assert 3 * 2 == 6
assert 3**2 == 9
assert 9 / 3 == 3

//STRINGS
def greeting = 'hello, world'
println greeting

def name = 'Maxwell'
def groovyGreeting = "hello, $name"
assert groovyGreeting == "hello, Maxwell"

def totalScore = "Total score: ${10 + 9 + 10 + 8}"
assert totalScore == "Total score: 37"

assert "Total score: ${10 + 9 + 10 + 8}" == "Total score: 37"

assert "Area: ${Math.PI * 3**2}" == "Area: 28.274333882308138"

def haiku = '''
groovy makes it easy
less boilerplate to create mess
solve the problem quick
'''

def language = 'Groovy'
def groovyHaiku = """
run $language on jvm
a multiplatform solution
for fun and profit
"""
println groovyHaiku

//Easy string concatenation
def str1 = 'hello,'
def str2 = 'world'

assert "$str1 $str2" == "hello, world"

//Some casting
def highway = 66 as String
highway = 66.toString()

def lucky = '13' as Integer
lucky = '13'.toInteger()

pi = '3.14' as BigDecimal
pi = '3.14'.toBigDecimal()

//Spaceship operator
assert 10 <=> 10 == 0
assert 9 <=> 10 == -1
assert 11 <=> 10 == 1

//Ternary operator
def witness = true ? 'honest' :'liar'
assert witness

assert 10 > 2 ? 'Ten is greater' :'Two is greater' == 'Ten is greater'
