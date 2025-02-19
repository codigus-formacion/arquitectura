# Arquitectura Hexagonal

Estos ejemplos muestran cómo se convierte una aplicación Spring con una web muy básica con mala calida en una app con mejor calidad hasta llegar a la arquitectura hexagonal.

## Ejemplo 1

* Ejemplo inicial que permite la gestión de anuncios.
* Los anuncios se almacenan en un mapa en memoria.
* El mapa es gestionado directamente desde el controlador (acoplado).
* Los anuncios que contienen palabras prohibidas no se pueden crear (sex, violence).

