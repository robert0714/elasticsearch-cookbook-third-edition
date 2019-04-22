# Usage


https://www.elastic.co/guide/en/x-pack/current/security-getting-started.html

```
user: elastic
password: changeme

```


terminal:

```bash
$ docker-compose up
$ curl -u elastic:changeme localhost:9200
{
  "name" : "qKuFB5F",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "3hLaKWRRQ6SMFKlBQ4hp6Q",
  "version" : {
    "number" : "5.6.16",
    "build_hash" : "3a740d1",
    "build_date" : "2019-03-13T15:33:36.565Z",
    "build_snapshot" : false,
    "lucene_version" : "6.6.1"
  },
  "tagline" : "You Know, for Search"
}


```

##

https://www.elastic.co/guide/en/x-pack/current/http-clients.html
https://www.elastic.co/guide/en/elasticsearch/client/java-rest/5.0/_basic_authentication.html

##  Secure your clusters » Configure the Java Transport client

https://www.elastic.co/guide/en/cloud-enterprise/current/security-transport.html

### Configure the Java Transport client

You can connect to an X-Pack or Shield protected cluster using the transport client. To learn more, see Java Client and Security and Using Elasticsearch Java Clients with Shield.

The only addition you need when using Elastic Cloud Enterprise is to add a header indicating which cluster to route the connections to.

Here is an example of how to create a transport client to connect to Elastic Cloud Enterprise:

```java

// Build the settings for our client.
String clusterId = "ac01aa2425e4a5bafdebf5100af3e9b37401055b"; // Your cluster ID here
String region = "us-east-1"; // Your region here
boolean enableSsl = true;

Settings settings = Settings.settingsBuilder()
    .put("transport.ping_schedule", "5s")
    //.put("transport.sniff", false) // Disabled by default and *must* be disabled.
    .put("cluster.name", clusterId)
    .put("action.bulk.compress", false)
    .put("shield.transport.ssl", enableSsl)
    .put("request.headers.X-Found-Cluster", clusterId)
    .put("shield.user", "username:password") // your shield username and password
    .build();

String hostname = clusterId + "." + region + ".aws.found.io";
// Instantiate a TransportClient and add the cluster to the list of addresses to connect to.
// Only port 9343 (SSL-encrypted) is currently supported.
Client client = TransportClient.builder()
        .addPlugin(ShieldPlugin.class)
        .settings(settings)
        .build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostname), 9343));

```


IndicesOperations
