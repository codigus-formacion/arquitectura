# Arquitectura Hexagonal

Estos ejemplos muestran cómo se convierte una aplicación Spring con API REST muy básica con mala calida en una app con mejor calidad hasta llegar a la arquitectura hexagonal.

## Ejemplo 1

* Ejemplo inicial que permite la gestión de anuncios.
* Los anuncios se almacenan en un mapa en memoria.
* El mapa es gestionado directamente desde el controlador (acoplado).
* Los anuncios que contienen palabras prohibidas no se pueden crear (sex, violence).

## Ejemplo 2

* Desacopla la gestión de la API REST (AdsController) de la gestión de los anuncios en memoria (AdsService).
* Separa las clases en capas, cada una en un paquete (model, controller, service).

## Ejemplo 3

* Se desacopla el repositorio (AdsRepository) de la lógica de negocio (AdsService).
* Los datos de ejemplo se inicializan en un DemoDataLoaderService, no en un controlador (no es su responsabilidad).

## Ejemplo 4

* Se aplica el Principio de Inversión de Dependencias (Dependency Injection) de SOLID para desacoplar los servicios del repositorio y el controlador de los servicios.
* Al aplicar el principio, se crean los siguientes puertos y adaptadores:
 * Adaptador primario: AdsController, DemoDataLoaderService
 * Puerto primario: AdsService
 * Lógica de negocio: AdsServiceImpl
 * Modelo de dominio: Ad
 * Puerto secundario: AdsRepository
 * Adaptador secundario: AdsMemoryRepository

## Ejemplo 5

* Se eliminan las anotaciones de Spring del code (modelo y lógica) para que esté lo más desacoplado posible de la tecnología (y sea más reutilizable y testeable)
* Se añaden tests de la lógica de negocio (unitarios) y de la API REST (E2E).

