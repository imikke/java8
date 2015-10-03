var bd = 0
if ($ENV.AGE != null) {
	bd = $ENV.AGE
} else {
	bd = readLine('How old are you? : ')
}
var nextBd = java.lang.Integer.parseInt(bd) + 1
print('Next year, you will be ' + nextBd)