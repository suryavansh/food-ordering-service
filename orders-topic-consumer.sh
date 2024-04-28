#!/usr/bin/env bash
docker run --net=host confluentinc/cp-enterprise-kafka:5.2.2  kafka-console-consumer --bootstrap-server localhost:9092  --topic orders-topic --from-beginning --group console-group