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