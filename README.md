# espenses

METHOD 1

Go to the page: https://hub.docker.com/r/shandar1997/expenses/tags?page=1&ordering=last_updated. There are docker images that are created in this program.
Pull these images from this page in own docker or insert in terminal commands:

  docker pull shandar1997/expenses:mysqldb
  
  docker pull shandar1997/expenses:app_jdk

Than create containers by these images. First run MySQL database with the command:

  docker run --name localhost -e MYSQL_ROOT_PASSWORD=1111 -e MYSQL_DATABASE=expenses -e MYSQL_PASSWORD=1111 -d shandar1997/expenses:mysqldb
  
Wait until the database is ready to use (about 30s). Then run application (app_jdk) with the command:

  docker run -d -p 8089:8080 --name app --link localhost:mysql  shandar1997/expenses:app_jdk

After open Postman, download file "endpointsFromTestTask.postman_collection_port8089.json" from this repository. Import this collection into Postman and test all required endpoints.
to see tips on how the endpoint works - right-click on the endpoint and select Edit

METHOD 2

Download archive in branch add_jarfile unpack it. Open this project in IDE. Open terminal in IDE and run command: docker-compose up

After open Postman, import collection "endpointsFromTestTask.postman_collection_port8089.json" from this project into Postman and test all required endpoints.
