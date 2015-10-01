function printInputStream(is) {
	var br = new java.io.BufferedReader(new java.io.InputStreamReader(is))
	try {
		var lines = new java.lang.String('')
		for (;;) {
			var line = br.readLine()
			if (line == null) {
				break
			}
			var n = java.lang.System.getProperty('line.separator')
			lines = lines.concat(line　+　n)
		}
		print(lines)
	}catch (e) {
		if (e instanceof java.lang.IOException)
			print('io exception') 
	} finally {
		br.close()
	}
}

function pipe() {
	if (arguments.length <= 0) return 0
	var cmd = new java.lang.String(arguments[0])
	for (var i = 1; i < arguments.length; i++) {
		cmd = cmd.concat(' | ' + arguments[i])
	}
	var pb = new java.lang.ProcessBuilder("/bin/sh", "-c", cmd)
	pb.redirectErrorStream(true)
	var process = pb.start()
	var is = process.getInputStream()
	process.waitFor()
	printInputStream(is)
	process.waitFor()
}

pipe()
pipe('find ..')
pipe('find ..','grep -v js')
pipe('find ..','grep -v js','sort -r')