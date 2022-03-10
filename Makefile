.PHONY: \
	build-run \
	run \
	stop

build-run:
	./docker-compose.sh up -d --build

run:
	./docker-compose.sh up -d

stop:
	./docker-compose.sh stop

test:
	./docker-compose.sh run backend test
