# Docker maven plugin & Docker compose demo

POC to showcase how using DMP, you can build (via Dockerfile) and push a docker image to docker hub.

This project runs on JDK 11.

## Docker Maven Plugin (DMP)



1. Install Docker on your system (win/Mac/Ec2 instance).

2. To check docker setup, run the command:

	```
	docker container run hello-world
	```

3. Create an account on docker hub, and specify the credentials in Docker Mavan plugin auth section (replace PASSWORD with actual value)

4. Note the <execution> tag in the pom for this plugin, which will ensure that this plugin gets invoked everytime you run mvn package on this project.

5. Take note of the Dockerfile present under /src/main/docker/

6. Run maven package command and see the docker plugin doing its work in the logs. 
   The plugin in pom is configured to run docker build command on mvn package.

	```
	mvn package
	```

7. Check the images present and see the new image now created.

	```
	docker image ls
	```

8. Start a container using this image.

	```
	docker container run -p8080:8080 -it ramit21/docker-demo-application
	```

9. Check the application:

	```
	localhost:8080/
	```

10. To push the image to docker hub, run the package command as below. Note that just like the docker:build has been captured as part of the plugin 
as an execution step, the docker:push can also be made part of the plugin itself, but ideally push should be kept separate.

	```
	mvn package docker:push
	```

11. Check the docker hub repositories to see the new image present there.

## Docker Compose

All the services, networks and published ports are defined in a single YAML file, which is then executed by docker-compose to create the architecture. Since these files are supposed to create the entire architecture, they should be kept in a common module, and not really in 1 specific application. For simplicity, in this POC, this file is kept at same location as dockerfile above, i.e. src/main/docker/. The given docker file will create a newtork and 2 services in that network, one for the web app application container, and the other for a database (although this poc application doesn't really connects to that database).

1. Run the below command and see the two containers for the springboot app and for mysql db created.

	```
	cd /src/main/docker/
	
	docker-compose up -d
	```

2. Check logs of individual service (notice that we give service name here, not container name):
	
	```
	docker-compose logs -f my-demo-webapp
	```

3. Note that both the containers have been created in a common network. Hence if you run an exec bash to one of them, you should be able to ping the other.

	```
	docker container ls
	docker container exec -it <web-app contianer id> /bin/sh 
	ping <database container id>
	exit
	```
	
4. Tear the down the architecture by running the below command and notice that everything including the network gets pruned.

	```
	docker compose-down
	```

### Note

In this project we have used DMP plugin to build and push the image to Dockerhub.
You may not use DMP, and instead configure steps in repo commit build (teamcity/gitlab etc)
to build the image, login to remote repo (Dockerhub, AWS ECR, etc.) , and then push the image to the remote repo.