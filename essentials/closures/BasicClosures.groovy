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

def noArgClosure = { -> println 'hello, world' }
noArgClosure()

def square = { it**2 }
assert square(4) == 16

def triple = { num -> num * 3 }
assert triple(3) == 9

def max = { arg1, arg2 ->
    arg1 > arg2 ? arg1 :arg2
}
assert max(8, 9) == 9

def quadratic = { a, b, c ->
    def denominator = 2 * a
    def partialNumerator = Math.sqrt((b**2) - (4 * a * c))
    def answer1 = ((-1 * b) + partialNumerator) / denominator
    def answer2 = ((-1 * b) - partialNumerator) / denominator
    return [ answer1, answer2 ]
}

assert quadratic(1, 3, -4) == [ 1, -4 ]

def quadraticPartialNumerator = { a, b, c ->
    Math.sqrt((b**2) - (4 * a * c))
}.memoize()

def quadratic2 = { a, b, c ->
    [ ((-1 * b) + quadraticPartialNumerator(a, b, c)) / 2 * a,
      ((-1 * b) - quadraticPartialNumerator(a, b, c)) / 2 * a ]
}

assert quadratic2(1, 3, -4) == [ 1, -4 ]
