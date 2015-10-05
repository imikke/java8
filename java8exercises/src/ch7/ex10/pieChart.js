// 記法に慣れればJavaで開発する場合と難易度は変わらないと思う。
// ただ、IDEで自動補完やリファクタリングの機能が使えない点は不便に思う。
// data fromat :
// String1, double1
// String2, double2
// ...

var path = java.nio.file.Paths
var file = java.nio.file.Files
var bytes = file.readAllBytes(path.get('../../../test/population.dat'))
var contents = new java.lang.String(bytes, java.nio.charset.StandardCharsets.UTF_8)
var StringArray = Java.type('java.lang.String[]')
var array = Java.to(contents.split(new RegExp("[\\n]")), StringArray)
var dataArray = []
for (var i = 0; i < array.length; i++) {
	var data = Java.to(array[i].split(new RegExp(",")), StringArray)
	if (data.length < 2) exit(1)
	dataArray[i] = new javafx.scene.chart.PieChart.Data(data[0],java.lang.Double.parseDouble(data[1]))
}
var pieChartData =
	javafx.collections.FXCollections.observableArrayList(dataArray)
var chart = new javafx.scene.chart.PieChart(pieChartData)
chart.setTitle("Population of the Continents")

var group = new javafx.scene.Group(chart)
var scene = new javafx.scene.Scene(group)
$STAGE.setWidth(500)
$STAGE.setHeight(500)
$STAGE.setScene(scene)
$STAGE.show()