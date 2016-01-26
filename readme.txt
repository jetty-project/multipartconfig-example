Example of @MultipartConfig on Jetty
====================================

This project uses maven.
This checkout is a valid ${jetty.base} directory too.

To build:

  $ mvn clean install

  This should have created a webapps/root.war file for you

To run in jetty distribution:

  $ java -jar /path/to/jetty-distribution-9.3.6.v20151106/start.jar

To test:

  $ mkdir /tmp/upload
  $ curl -i -X POST -H "Content-Type: multipart/form-data" -F "param1=bla" -F "file=@big.tar.gz" http://localhost:8080/upload

Example results:

  $ curl -i -X POST -H "Content-Type: multipart/form-data" -F "param1=bla" -F "file=@big.tar.gz" http://localhost:8080/upload
  HTTP/1.1 100 Continue

  HTTP/1.1 200 OK
  Content-Type: text/plain;charset=iso-8859-1
  Content-Length: 65
  Server: Jetty(9.3.6.v20151106)

  Got part: name=file, size=23597555
  Got part: name=param1, size=3

  $ ls -la /tmp/upload/
  total 23052
  drwxrwxr-x.  2 joakim joakim       80 Jan 26 14:19 .
  drwxrwxrwt. 20 root   root        500 Jan 26 14:19 ..
  -rw-rw-r--.  1 joakim joakim 23597555 Jan 26 14:19 part-00.dat
  -rw-rw-r--.  1 joakim joakim        3 Jan 26 14:19 part-01.dat

  $ sha1sum /tmp/upload/part-00.dat big.tar.gz 
  13be13872d0203bae2f8a83ad457f8a0d259a2db  /tmp/upload/part-00.dat
  13be13872d0203bae2f8a83ad457f8a0d259a2db  big.tar.gz

  $ file /tmp/upload/part-01.dat 
  /tmp/upload/part-01.dat: ASCII text, with no line terminators

  $ cat /tmp/upload/part-01.dat 
  bla


