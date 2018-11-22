#!/bin/bash

docker run -idt \
	--name openenade-api \
	-p 8080:8080 \
	pfelipefeitosa/openenade-api:stub-data
