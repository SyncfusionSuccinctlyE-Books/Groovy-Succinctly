/*
 * This is just a demonstration for different approaches to recursion
 * See: http://www.wolframalpha.com/input/?i=10!
 */

println 'Calling the factorial closure'
def factorial
factorial = { n ->
    count++
    n == 2? n: (n * factorial(n-1)).toBigInteger()
}
count = 0
(2..20).collectEntries{ n -> [ "$n": factorial(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The factorial closure was called $count times\n"


println 'Calling factorialSelf closure'
def factorialSelf = { n ->
    count++
    n == 2? n: (n * call(n-1)).toBigInteger()
}
count = 0
(2..20).collectEntries{ n -> [ "$n": factorialSelf(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The factorialSelf closure was called $count times\n"


println 'Calling the memoized factorial closure'
count = 0
factorial = factorial.memoize()
(2..20).collectEntries{ n -> [ "$n": factorial(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The memoized factorial closure was called $count times\n"


println 'Calling the memoized factorialSelf closure'
factorialSelf = factorialSelf.memoize()
count = 0
(2..20).collectEntries{ n -> [ "$n": factorialSelf(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The memoized factorialSelf closure was called $count times\n"


println 'Calling the factorialMethod method'
BigInteger factorialMethod(n) {
    count++
    n == 2? n: n * factorialMethod(n-1)
}

count = 0
(2..20).collectEntries{ n -> [ "$n": factorialMethod(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The factorialMethod was called $count times\n"


println 'Calling the memoized factorialMethodMemoized method'
@groovy.transform.Memoized
BigInteger factorialMethodMemoized(n) {
    count++
    n == 2? n: n * factorialMethodMemoized(n-1)
}
count = 0
(2..20).collectEntries{ n -> [ "$n": factorialMethodMemoized(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The factorialMethodMemoized was called $count times\n"


println 'Calling the factorator closure that refers to the factorialMethodMemoized2 method'
 @groovy.transform.Memoized
 BigInteger factorialMethodMemoized2(n) {
     count++
     n == 2? n: n * factorialMethodMemoized2(n-1)
 }

def factorator = this.&factorialMethodMemoized2
count = 0
(2..20).collectEntries{ n -> [ "$n": factorator(n) ] }
    .each{ k, v -> println "Factorial of $k is $v"}
println "The factorator closure was called $count times\n"
