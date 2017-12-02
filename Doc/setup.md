# Setup
Before we start a few things need to be setup. The most important one is creating a MongoDB container in docker. This can be done by using the docker commands or if you prefer a user interface; Kitematic.

## Using Kitematic
---
1. Start Kitematic.
2. Type in "mongo" into the search bar.
3. Choose the "official mongo" and press "CREATE".
4. Select the container on the left side.
5. Press settings on the upper right side.
6. Change the "NAME" to "my_mongodb", dont forget to press "SAVE".
7. Right top corner press "Hostname/Ports".
8. Change the "PUBLISHED IP:PORT" to "localhost:37017", dont forget to press "SAVE".
9. The server will reboot and you are good to go!

## Using Docker Command Line
---
**1. The next step will pull the latest mongo image from docker.**
```sh
$ docker pull mongo
```
**2. Run the image by using the following command:**
```sh
$ docker run --name my_mongodb -d -p 37017:27017 mongo
```
Param explanation:
- --name: this will give a name to the container. The name can be used to start, restart, stop, kill, and remove a container.
- -p 37017:27017, this will bridge the standard mongo port 27017 in the docker container to port 37017 on the local machine.
- (optional parameter) -v ~/data:/data/db, this allows the user to define a local path for storing the data. The data will be stored locally instead of within the docker container.

**3. To check if the container is running, execute the following command:**

```sh
$ docker ps
```
**Additional info:**

To start, restart, stop, kill, and remove the container.

```sh
$ docker start my_mongodb
```
```sh
$ docker restart my_mongodb
```
```sh
$ docker stop my_mongodb
```
```sh
$ docker kill my_mongodb
```
```sh
$ docker rm my_mongodb
```

## Assignment
---

Okay the MongoDB container is ready to be used. The project is located at the repository ["sebivenlo/MongoDB"](https://github.com/sebivenlo/MongoDB/).

The assignment is divided into three separate parts. Each part is different framework. You are going to implement CRUD (create, read, update, and delete) using three different database clients. The chosen frameworks are:

1. [MongoDB driver](https://mongodb.github.io/mongo-java-driver/)
2. [Spring Data MongoDB](https://projects.spring.io/spring-data-mongodb/)
3. [Morphia](https://mongodb.github.io/morphia/)

Another notable framework is [Google Guice](https://github.com/google/guice), this framework is used for injecting Morphia and the MongoDB Driver. Spring Data Mongo has an own way of injecting the client.

Checkout the repository and navigate to the "assignment" directory. Now open the project, this can be done by opening it in your idea of choice (preferably notepad++, just kidding). The first thing you want to do is build it with maven dependencies, once that is done, open the TODO's and start hacking. To check if your code is valid you can run the tests thats associated to each assignment.