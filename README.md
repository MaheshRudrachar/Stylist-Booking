# About Microservices Project

### Use case : Tasks
1. internal users to manage stylists readiness to work;
2. customers to see the list of available time slots (as per previous screenshot);
3. customers to book a call on a specific time slot;
4. automatic booking of calls for orders uploaded via spreadsheets; think of it as an “add many” endpoint;

Based on the business capability that is mentioned, will be decomposing application services by business capability since it is straight forward with limited scope of assignment.

So, with that came up with 5 -6 Services viz.
Customer service - Covered
Stylist service - Covered 
Stylist Availability service - Covered
Customer Stylist Booking service - Covered
Booking History service - Pending (Need to enhance)

Design considerations:
- Stylist Call Booking - User will see all stylists and pick/choose specific stylist to view their availablity. By default state is maintained as ROOKIE, based on the state update, domain events are triggerd and notified to Booking service. From there on stylist list will be made available to end users based on state=AVAILABLE
- Replicating the events using kafka and to enable scalability
- Saga - Started with one module but need enhancements
- Domain Events, Event Sourcing, CQRS and Streaming - Kafka could be used, but not utilized fully. Instead of re-inventing the wheel, made use of eventuate framework.
- Thought of using Redis for view/query of stylists and availability but will take it as enhancement

Test Cases -  Added few testcases and cleanup

## To support Resilient, Faut Tolerant and Scalable design, following approach is followed:

The Customer and Stylist services publish events whenever their data changes. The Booking service subscribes to those events and updates its replica. So that way each and every service is losely coupled and can be scaled based on need basis.




# Miscellaneous
# Other items that were on low priority wanted to work on.

#### Docker (Docker script is enclosed to sping docker swarm setup using virtual box.)

Microservices are deployed into containerized platfrom using Docker Swarm to take advantage of replication, volume mount, rolling update and desired cluster state retention in the production platform.

#### Service Registration

During the initialization of a service, it would get registered to the discovery and registration server (which in this project is Hashicorp's Consul).

#### Service Discovery

When one service (say api-gateway) needs to access a resource from another service (say microservice-a), all it has to do is ask discovery and registration server (Consul) to give one of the microservice-a's instance information.

#### Zuul with Ribbon

For routing/proxying the requests to the appropriate service instances and load balancing.

#### Centralized Log Management

Logstash is used to collect microservices logs and drain it into Elasticsearch for indexing and searching. For visualization Kiaban UI is used with X-Pack configured for notification, graphs and security


#### Security using OAuth2

All microservices are protected using OAuth2 JWT tokens.

#### Stateful Services

Stateful services like MySQL, RabbitMQ and ElasticSearch are clustered using docker swarm to enable scale, performance and resilience in the PaaS platform.

#### Cloud Config

Application specific configurations which are dynamic in nature are moved to cloud config using git as the source control so that there is no need to restart app server to relfect the configuration changes.

#### API Versioning

Rolling upgrades and side-by-side deployment of different microservice versions will be simpler by mainting api versioning.

### Architecture

Enclosed high level architecture

### Technology

* [SpringBoot] - Application framework
* [Docker] - Containerization platform
* [Kafka] - asynchronous microservices messaging.
* [Swagger] - API documentation

Design and Qualty
================
Refer this repo: Code-Quality-and-Design

