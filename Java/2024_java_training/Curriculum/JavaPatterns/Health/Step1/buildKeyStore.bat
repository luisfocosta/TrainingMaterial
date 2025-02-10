cd %~dp0
if exist KeyStore del KeyStore
keytool -genkey -dname "cn=Healthcare'R'Us" -alias Capstone -keypass ccstudent -keystore KeyStore -storepass ccstudent -keyalg DSA -keysize 1024
