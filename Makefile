.PHONY: \
	build-run \
	run \
	stop \
	backend-build-run \
	backend-run \
	telegram-bot-build-run \
	telegram-bot-run

build-run:
	./docker-compose.sh up -d --build

run:
	./docker-compose.sh up -d

stop:
	./docker-compose.sh stop

test:
	./docker-compose.sh run backend ./gradlew test

backend-build-run:
	./docker-compose.sh stop backend
	./docker-compose.sh up -d --build backend

backend-run:
	./docker-compose.sh stop backend
	./docker-compose.sh up -d backend

telegram-bot-build-run:
	./docker-compose.sh stop bot
	./docker-compose.sh up -d --build bot

telegram-bot-run:
	./docker-compose.sh stop bot
	./docker-compose.sh up -d bot
