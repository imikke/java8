function pipe() {
	if (arguments.length <= 0) return 0
	var input = $EXEC(arguments[0])
	for (var i = 1; i < arguments.length; i++) {
		input = $EXEC(arguments[i],input)
	}
	print(input)
}

pipe()
pipe('find ..')
pipe('find ..','grep -v js')
pipe('find ..','grep -v js','sort -r')