# About Stylist Booking Microservices Project

This project demonstrates booking system to book stylist during the process of online shopping of clothes (esp. for Men)

### Use cases covered
1. Internal users to manage stylists readiness to work.
2. Customers to see the list of available time slots (stylist availability).
3. Customers to book a call on a specific time slot.
4. Automatic booking of calls for orders uploaded via spreadsheets (List endpoint).

### Approach

With the given fact that the stylist booking application will be internet facing and target audience are end users across the globe, it is very much required to consider all critical attributes of cloud like Scalability, Availability, Resiliency and Fault Tolerant capabilities built into the application. 

So, chosen microservices based design for implementing this project and decomposed application into microservices using business capabilities that are provided (with limited scope). Following are the business services.

 1. Customer service - Covered
 2. Stylist service - Covered 
 3. Stylist Availability service - Covered
 4. Customer Stylist Booking service - Covered
 5. Booking History service - Pending (Need to enhance)
 
### Usecase flow

 1. Customer should be able to see all stylists and pick/choose specific stylist to view their availablity.
 2. By default, the state of stylist is maintained as ROOKIE (not able to attend any customer calls), based on the state update, domain events are triggerd and notified to the Booking service. From there on stylist list will be made available to customers based on state AVAILABLE.
 3. Customer book stylist for a given time slot.
 4. To bring in async nature Replication is used so that whenever there is a change in business domain aggregate a notification will be sent to the respecitive subscribers using kafka and data gets replicated. With this approach as soon as the customer books stylist, with the available replicated data booking service will go ahead and places the order in PENDING state and return back response to the customer. In an async manner data gets validated with other dependent services using saga transactions and eventually data consistancy is maintained throughout the lifecycle of shopping.
 5. Customer gets notification as and when the state machine changes it state.
 6. Customer should be able to view their booking history
 
### Design considerations

1. Async message based communication
   - Point-to-point and publish/subsribe
   - Scaling consumers (Kafka shards)
   - Handling duplicate messages
   - Replicate data
   - Finish processing after returning response

2. Transactional messaging (Tram)
   - Updating and sending message within transaction

3. Managing transactions using Sagas (No 2PC)
   - Maintain data consistency
   - Orchestration based – state machine
     - Sagaorchestrationpackage, sagaparticipantpackage

4. Optimistic locking

### Architecture Diagram

![alt text](https://github.com/MaheshRudrachar/Stylist-Booking/edit/master/img.png)

### Technology stack

1. [SpringBoot] - Application framework
   - Starter web – embedded tomcat
   - Starter Actuator – health monitoring
   - Starter JPA

2. Eventuate Framework
   - Transaction messaging
   - Saga for orchestrated transaction management
   - Kafka based messaging
 
3. Testing
   - Rest-assured
   - Spring contract
   - Starter test

4. [Docker] - Containerization platform
   - Docker Swarm for cluster formation
   - Docker Compose for managing deployment
 
5. [Kafka] - asynchronous microservices messaging.

6. [Swagger] - API documentation

### Design and Qualty

Refer this repo: Code-Quality-and-Design

### REST APIs

- Create Customer - /v1/customers - POST
- Create Stylist - /v1/stylist - POST
- Get Stylist - /v1/stylist/{stylistId} - GET
- Get All Stylists - /v1/stylist/all - GET
- Get Stylist Availability - /v1/{stylistId}/{date} - GET
- Create Booking - /v1/booking - POST
- Create Bulk Booking - /v1/booking/bulk - POST
- Get Booking - /v1/{bookingId} - GET

### Bug Fixes

Seperate Branch is maintained for bug fixes (bug-fixes) and not to distrub the main freezed branch (as per discussion)

- Fixed the TimeRange to include even LocalDate so that it can be used to query for Bookings that are made on particular date. Same could be used for calculating stylist availability
- Fixed the LocalTime and LocalDate parser issue by registering JavaTimeModule to JSON Mapper
- Added integration test for testing end-to-end Booking flow
- Cleanup on hibernate classes

## Miscellaneous (Other items that were on low priority wanted to work on)

### Docker (Docker script is enclosed to sping docker swarm setup using virtual box.)

Microservices are deployed into containerized platfrom using Docker Swarm to take advantage of replication, volume mount, rolling update and desired cluster state retention in the production platform.

### Service Registration

During the initialization of a service, it would get registered to the discovery and registration server (which in this project is Hashicorp's Consul).

### Service Discovery

When one service (say api-gateway) needs to access a resource from another service (say microservice-a), all it has to do is ask discovery and registration server (Consul) to give one of the microservice-a's instance information.

### Zuul with Ribbon

For routing/proxying the requests to the appropriate service instances and load balancing.

### Centralized Log Management

Logstash is used to collect microservices logs and drain it into Elasticsearch for indexing and searching. For visualization Kiaban UI is used with X-Pack configured for notification, graphs and security


### Security using OAuth2

All microservices are protected using OAuth2 JWT tokens.

### Stateful Services

Stateful services like MySQL, RabbitMQ and ElasticSearch are clustered using docker swarm to enable scale, performance and resilience in the PaaS platform.

### Cloud Config

Application specific configurations which are dynamic in nature are moved to cloud config using git as the source control so that there is no need to restart app server to relfect the configuration changes.

### API Versioning

Rolling upgrades and side-by-side deployment of different microservice versions will be simpler by mainting api versioning.
