# Elma

El bot que te ayuda a mantenerte hidratado, recordandote que bebas agua.

## Desarrollo

### Bot de telegram
Crear fichero ``.env`` para asignarle añadir el token de telegram y los credenciales de la base de datos.
```sh
$ cd apps/elma/telegram_bot
$ cp .env.example .env
```

Para ejecutar el bot necesitamos python 3.10 y tenemos que instalar las dependencias y ejecutarlo con python
```sh
$ pip install -r requirements.txt
$ python -m elma
```

### Backend
Para ejecutar el backend debemos ejecutarlo con gradle
```sh
$ ./gradlew :elma:backend:run
```

### Ejecutar todos los servicios con docker-compose
Para ejecutar la build de los contenedores de docker y esperar a que el bot se conecte.
```sh
$ docker-compose up -d --build
```

### Ejecutar los tests de los subproyectos de kotlin
```sh
$ ./gradlew test
```
