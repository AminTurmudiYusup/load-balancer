# This project used to give illustration, how load balancer work. Using spring boot and Nginx


## How to set Nginx as reverse proxy and load balancer

This tutorial already available on my Youtube(https://youtu.be/UnzqxIC_vx4)

What the mean load balancer :refers to the process of distributing workloads across multiple computing resources, such as servers, to optimize resource utilization, maximize performance, and ensure high availability and scalability of a system.
This metric helps to ensure that no single computing resource is overwhelmed with too much work, which can lead to reduced performance, system downtime, and potential data loss or corruption

Prerequisite
1. your operating system is ubuntu(bacause, when i write this tutorial using ubuntu 20.04)
2. your laptop already installed Nginx  (if no, see previous tutorial to follow. How to install Nginx)
3. Nginx web server already run
4. You understand most command used in Nginx
5. You have two spring boot app which run on different port


Let's jump right in
1. Config context root of spring boot app(open spring boot using your favourite IDEA and both of spring boot app using the same context root)
  -server.servlet.context-path=/myapp(inside application.properties)
2. Create Controller pacakage, create IndexController class and map root into login-page.html
3. map domain name to IP address using this command (sudo gedit /etc/hosts) or you can edit using nano or vim etc
  -because i using local computer, so i map my domain(example.com) to 127.0.0.1
4. run the bellow command to configure Nginx server
  -sudo nano /etc/nginx/sites-available/default
5. define upstream
   upstream backend {
    server localhost:8080;
    server localhost:8081;
  }
5. add server name and proxy pass refer to upstream
  server_name example.com;

  location /myapp/ {//adjust the location with root spring boot context
            proxy_pass http://backend/myapp/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
  }
6. ctrl+X , press Y button and Enter
7. make sure if configuration not show the error
   - sudo nginx -t
8. reload Nginx web server
   - sudo systemctl reload nginx
9. try to access appliaction using domain twice refresh again
   - http://example.com/myapp
10. make sure if the request distribute to different backend server(check log between two spring boot app)

the trafic forward to different backend server.


Success, happy learning and happy sharing!!!

