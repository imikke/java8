//jjs>var b = new java.math.BigInteger('1234567890987654321') 
//jjs> b
//1234567890987654400
//jjs> b.mod(java.math.BigInteger.TEN)
//1
//JavaScriptで扱える最大値えてしまったため下位3桁の値の表示がおかしい

var b = new java.math.BigInteger('1234567890987654321')
b
//余剰
b.mod(java.math.BigInteger.TEN)
//正しく表示させるには、java.lang.String.formatを使う
java.lang.String.format('%d',b)