There should be 4 files:
- a5.py
- Dockerfile
- README.txt
- requirements.txt

The python code has everything is required to provide a web application

The dockerfile initializes the required dependencies (flask) and runs the program

The requirements text file has the dependencies needed to run the docker.

additional info:
on line 14-15 is just defining the ports so it will open on 8080.

Procedure:

1. Put the files in a named folder called "flask_docker", and cd into it via CLI
2. run the command "docker image build -t flask_docker .", this will build the image file
3. run the command "docker run -p 8080:8080 -d flask_docker" this will initialize and start the docker for the image.
4. Open up a browser to the address "localhost:8080" to see the working docker