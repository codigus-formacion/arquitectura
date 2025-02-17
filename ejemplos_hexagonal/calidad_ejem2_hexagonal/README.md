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