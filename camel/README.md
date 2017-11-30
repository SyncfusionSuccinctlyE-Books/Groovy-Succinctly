# Running the examples

This directory contains Groovy scripts that utilise [Apache Camel](http://camel.apache.org/index.html) routes.
 The Producer*.groovy scripts create data that can then be read by Consumer*.groovy scripts.

Producer      | Consumers
------------- | ------------------------------------------
ProducerFile  | ConsumerFileJson, ConsumerFileXml
ProducerQueue | ConsumerQueueStream, ConsumerQueueDatabase

## Preparing ActiveMQ

In order to work with the queue-based examples you need to get [Apache ActiveMQ](http://activemq.apache.org/) running.
The [Getting Started](http://activemq.apache.org/getting-started.html) guide is the best place to go for installation
instructions but the list below should get you going quickly:

1. Open the Apache ActiveMQ download page: <http://activemq.apache.org/download.html>
1. Download a release version (such as ActiveMQ 5.13.1 Release) for your operating system (Windows or Unix/Linux)
1. Extract the downloaded archive file
1. Change into the new directory
1. Start the server with:
    - Windows: `bin\activemq start`
    - Unix: `bin/activemq console`
1. Access the web-based administration interface: <http://0.0.0.0:8161/admin/>
    - Username: `admin`
    - Password: `admin`

If you're having problems getting the server to start, check out `data/activemq.log` for log messages.

Once the server is running, try out the following commands:

- Start the consumer in one terminal: `bin/activemq consumer --messageCount 100 --destination queue://TEST`
    - This will consume new messages in the TEST queue and cease once 100 messages have been consumed
- Send a message from a producer from another terminal: `bin/activemq producer --message "Hello,\ world" --messageCount 100 --sleep 1000 --destination queue://TEST`
    - This will generate a new "Hello, world" message every second and cease once 100 messages have been sent

To clear the queues, run `bin/activemq purge` whilst the server is running or use the admin interface.
