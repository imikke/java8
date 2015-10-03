//インタラクティブな取り組みは間違ったときに最初に戻るのが大変

var path = java.nio.file.Paths
var file = java.nio.file.Files
var bytes = file.readAllBytes(path.get('../../../test/words2.txt'))
var contents = new java.lang.String(bytes, java.nio.charset.StandardCharsets.UTF_8)
//contents
var StringArray = Java.type('java.lang.String[]')
var array = Java.to(contents.split(new RegExp("[\\W+]")), StringArray)
var words = java.util.Arrays.asList(array)
//words

//重複を無くす
var set = new java.util.HashSet(words)
//set

var stream = set.stream()
var filter = stream.filter(function(w) {return w.length > 12})
filter.sorted().forEach(function(w) { java.lang.System.out.println(w) })