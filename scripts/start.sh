#!/bin/bash

cd /home/ubuntu/Backend/new_chatapp/

docker build -t backendimg_1 .

cd ~

cd Database/

docker build -t  databaseimg_2 .


docker login 


docker run -d -v mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=ganesh123 -e MYSQL_USER=admin --name mysqldb  databaseimg_2 

docker run --name backend --link mysqldb -d backendimg_1 

docker run --name frontend --link backend -p 80:80 -d frontendimg_1 

