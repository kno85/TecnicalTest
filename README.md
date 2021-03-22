# TecnicalTest
Descripción del requerimiento

Se solicita la creación de la estructura de una app que muestre un listado de los personajes Marvel y permite ver el detalle de cada uno de ellos de manera individual.

Especificaciones

1. Hace uso de la API de Marvel ( https://developer.marvel.com/docs )
2. Obtener el listado de personajes
3. Obtener el detalle de un personaje

Funcionalidad

1. Listas el listado de personajes
2. Navegar al detalle de un personaje concreto

Descripción del proyecto

Proyecto creado con Android Studio haciendo uso de kotlin y aplicando Clean Architecture y persistencia de datos con Room
A nivel funcional se divide principalmente en 2 Activities (MainActivity, ItemDetailActivity) ubicadas en el paquete "ui".
El usuario al abrir la app "CharactersApp" visualizará un progressView. Este dejara de ser visible bien cuando obtenga el listado de personajes o bien cuando la api falle. En este último caso se visualizará el mensaje "Error de conexión". Si la operación se ejecuta correctamente los datos serán almacenados en un repositorio local(localRepository)  y no volverán a solicitarse a través del repositorio remoto (remoteRepository) salvo que la api falle y estos datos no sean almacenados. De ser así al cerrar y volver a abrir la app volverá a intentarse la operación previa.

MainActiviy:
A través de mvp se conecta con retrofit para invocar al listado de personajes de Marvel.
El presentador (MainPresenterImp) hace uso de corrutinas para extraer la info mapeada a capa de Dominio y mostrar únicamente los personajes que contengan descripción y además devuelve el listado ordenado alfabeticamente a la acitivity. Ësta inyectará el listado en un recyclerview pudiendo así ver el nombre de cada uno de los personajes de la lista filtrada.

ItemDetailActivity:
  Muestra el detalle del personaje seleccionado (Nombre, imagen y descripción).
  Además cuenta con un botón que se conectará al enpoint de detalle para devolver un toast con el texto:
   - Sin cambios -> Si no hay ningún cambio respecto a la información actual en contraste con la información obtenida de forma remota
   - El item ha cambiado -> Si el titulo, descripción o url de la imagen son distintas a las actuales.
  
Este proyecto hace uso de inyección de dependencias con Dagger2 y segmentación de capas a través de paquetes para aplicar los principios de Clean Architecture.
  
Se facilita además test unitarios para la parte de casos de uso, repositorios, y presenters
  
Algunas de las librerías usadas:
- Retrofit 
- Glide
- Room
- Mockito
