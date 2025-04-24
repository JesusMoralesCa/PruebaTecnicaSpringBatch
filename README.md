# PruebaTecnicaSpringBatch


Este proyecto incluye pruebas unitarias para verificar el correcto funcionamiento de un proceso de extracción de facturas en un archivo CSV. El proceso está diseñado para leer facturas desde una base de datos, procesarlas y escribirlas en un archivo CSV.



## Descripción

El sistema extrae facturas de una base de datos, las procesa y las escribe en un archivo CSV utilizando el componente FacturaItemWriter. Las pruebas unitarias están enfocadas en asegurarse de que las facturas se escriben correctamente en el archivo CSV, con el formato adecuado.
Componentes del Proyecto

  1.FacturaItemReader: Lee las facturas desde la base de datos.

  2.FacturaProcessor: Procesa las facturas leídas (por ejemplo, actualiza su estado).

  3.FacturaItemWriter: Escribe las facturas procesadas en un archivo CSV.

  4.Pruebas Unitarias: Aseguran que los componentes interactúan correctamente, especialmente verificando que las facturas se escriben en el archivo con el formato correcto.


  ## Tecnologías Utilizadas

  - Spring Batch: Para la gestión de trabajos y pasos batch.
  - JUnit: Para las pruebas unitarias.
  - Mockito: Para la simulación de dependencias.
  - Spring Boot: Como marco para la ejecución del proyecto.


  ## Requisitos

  Para ejecutar el proyecto y las pruebas unitarias, asegúrate de tener lo siguiente instalado:

    - Java 21.
    - Maven para gestionar dependencias y compilar el proyecto.
    - JUnit 5 para ejecutar las pruebas.


  # Cómo Ejecutar las Pruebas
  
  1. Clonar el Repositorio
  Clona el repositorio del proyecto en tu máquina local:  https://github.com/JesusMoralesCa/PruebaTecnicaSpringBatch.git

  2. Construir el Proyecto
  
  Navega al directorio del proyecto y construye el proyecto con Maven:
  
  mvn clean install   
  
  //  
  
  mvn clean package

  3. Arranca el proyecto junto a un parametro de fecha para extraer la factura que quieras.

  java -jar target/demo-0.0.1-SNAPSHOT.jar --fecha=2025-06-15



 # Conclusión
  
  Este proyecto proporciona una implementación completa del proceso de extracción de facturas en formato CSV. Las pruebas unitarias verifican que los datos se manejan correctamente. Para ejecutar las pruebas, solo se necesita tener configurado el entorno adecuado y ejecutar los comandos de Maven y Java.
