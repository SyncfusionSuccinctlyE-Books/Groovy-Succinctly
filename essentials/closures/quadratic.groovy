//See: https://www.khanacademy.org/math/algebra/quadratics/solving-quadratics-using-the-quadratic-formula/v/using-the-quadratic-formula

def count = 0

def quadratic = {a, b, c ->
    count++
    [((-1 * b) + Math.sqrt((b**2) - (4 * a * c))) / (2 * a),
    ((-1 * b) - Math.sqrt((b**2) - (4 * a * c))) / (2 * a)]
}.memoize()

def vars = [[1, 3, -4],
[1, 3, -4],
[1, 3, -4],
[2, -4, -3],
[1, 4, -21],
[10, 8, -9]]

vars.each{ args -> println "Result for $args is ${quadratic(*args)}" }

println "${vars.size()} calls were made but the quadratic was only calculated $count times"
