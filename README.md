## 4 module Java mentoring 
### Messaging in Java: JMS, Active MQ, Rabbit MQ, Kafka
Project structure:
* Spring boot multi module project
* Used 3 spring profile for each messaging provider: activemq, rabbitmq, kafka.

Middleware systems running in docker container:
* ActiveMQ
* docker run -d -p 61616:61616 -p 8161:8161 --name activemq --restart unless-stopped webcenter/activemq
---
* RabbitMQ
* docker run -d -p 5672:5672 -p 15672:15672 --name rabbit-mq --restart unless-stopped -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
---
* Kafka running from docker-compose file which stored in resources of event-service-rest module 
* docker-compose -f <path_to_docker_compose_kafka> up -d