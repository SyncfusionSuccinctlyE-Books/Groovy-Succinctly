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
 * Basic example of instantiating an object and calling a method
 * See: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
 */

def random = new Random()
println random.nextInt(100)

/*
 * Using class fields and methods
 */
println Math.PI
println Math.max(8, 9)
def highest = Math.max 8, 9

