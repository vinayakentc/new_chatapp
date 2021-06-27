#!/bin/bash

cd /home/ubuntu/Backend/new_chatapp/

docker build -t backendimg_1 .

docker run -v mysqlvol2:/var/lib/mysql --name mysqldb -d databaseimg_2 

docker run --name backend --link mysqldb -d backendimg_1 

docker run --name frontend --link backend -p 80:80 -d frontendimg_1 

