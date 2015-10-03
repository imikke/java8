function ArrayListFactory() {var obj = new (Java.extend(java.util.ArrayList)) {add: function(x) { print('Adding ' + x); return Java.super(obj).add(x)}}; return obj}

var arr1 = ArrayListFactory()
var arr2 = ArrayListFactory()

arr1.add('Fred')
print(arr1)

arr2.add('Bob')
print(arr2)
