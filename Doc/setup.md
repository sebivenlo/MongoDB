# Setup
Before we start a few things need to be setup. The most important one is creating a MongoDB container in docker. This is done by doing the following commands:

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

Okay the MongoDB container is ready to be used. Now the project located in repository "MongoDB"
