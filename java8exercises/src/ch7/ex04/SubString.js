//$ jrunscript < SubString.js 
//nashorn> nashorn> lo
//nashorn> nashorn> class java.lang.String
//nashorn> java.lang.ClassCastException: Cannot cast java.lang.Class to java.lang.String
//getClass()で取得したクラスはClass<String>なので、Stringクラスへはcastできない。

var sub = 'Hello'.slice(-2)
sub
var c = sub.getClass()
c
c.getClass()

java.lang.String.class.cast(c)