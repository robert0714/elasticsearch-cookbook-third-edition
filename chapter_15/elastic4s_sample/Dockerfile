FROM docker.elastic.co/elasticsearch/elasticsearch:5.6.16 
ADD elasticsearch.yml /usr/share/elasticsearch/config/
RUN bin/elasticsearch-plugin remove x-pack --purge
USER root
RUN chown elasticsearch:elasticsearch config/elasticsearch.yml
USER elasticsearch