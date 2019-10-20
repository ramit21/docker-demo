# docker-demo
Docker maven plugin demo


POC to showcase how using DMP, you can build (via Dockerfile) and push a docker image to docker hub. 

1. Install Docker on your system (win/Mac/Ec2 instance).

2. To check docker setup, run the command:

	```
	docker container run hello-world
	```

3. Create an account on docker hub, and specify the credentials in Docker Mavan plugin auth section (or maven settings.xml)

4. Note the <execution> tag in the pom for this plugin, which will ensure that this plugin gets invoked everytime you run mvn package on this project.

5. Take note of the Dockerfile present under /src/main/docker/

6. Run maven package command and see the docker plugin doing its work in the logs. 

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
