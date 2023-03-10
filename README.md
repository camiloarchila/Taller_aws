# Taller_aws

Es un proyecto que consiste en crear un balanceador de carga de unos contenedores que estan desplegados en aws

# Iniciando

## Prerequisitos

* [Maven] - Administrador de dependencias
* [Docker] - Gestor de contenedores
* [AWS] - Servicio Cloud

## Construccion

primero debemos tener subidas las instancias en dockerhub de la siguiente forma

![image](https://user-images.githubusercontent.com/69320250/224228014-98245e9e-bc0a-41cf-b93a-24747fce7152.png)

Una vez las instancias esten arriba con el comando:

```
docker run -d -p <numeropuerto:6000> --name nombreinstacia <usuario>/<repositorio>
```
creamos en aws cada una de las instancias

![image](https://user-images.githubusercontent.com/69320250/224228121-6596fde1-aaeb-442a-af9c-47eb06a35756.png)

![image](https://user-images.githubusercontent.com/69320250/224228086-9690086a-4653-47d4-8d1e-73a19083fd5b.png)

![image](https://user-images.githubusercontent.com/69320250/224228163-0a4c8dbc-e383-428e-8881-6bce13a59e76.png)

![image](https://user-images.githubusercontent.com/69320250/224228206-5fead0ee-d22d-4f5a-882b-e6dc379e352d.png)

![image](https://user-images.githubusercontent.com/69320250/224228228-ed6f992f-ec67-4721-8387-06c5b8a5f447.png)


Ahora dentro de las reglas de seguirdad de la maquina virtual vamos a colocar los puertos que usamos, en este caso 42000, 42001, 42002 y 43000

![image](https://user-images.githubusercontent.com/69320250/224227883-1c8aef45-1955-47cf-b012-5127a05bf2d6.png)

posteriormente con la direccion del dns vamos a realizar la consulta en teste caso:

```
<direccion DNS>:43000
```

![image](https://user-images.githubusercontent.com/69320250/224227929-5a31795a-9a8a-4981-9d65-cade7e84c788.png)


![image](https://user-images.githubusercontent.com/69320250/224227846-36cb0ad0-73e6-455a-8f0a-327b5cc76101.png)

## Version 
Versión 1.0

## Autor
*[camiloarchila] Esteban Camilo Archila Bastidas 

## Descripcion Arquitectura

Se construyo un balanceador de carga con el metodo de RoundRobin que gestiona tres instancias de un servidor que almacena un registro y devuelve los ultimos 10 registros dentro de una base de datos mongo a la que esta conectado, cada una de las instancias tanto de los servidores como de la base de datos y el balanceador estan en contenedores que fueron desplegados en AWS.
