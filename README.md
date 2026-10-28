# Pruebas E2E ESFE Agape

Proyecto Maven para validar con Selenium WebDriver el título de la carrera de Desarrollo de Sistemas en el sitio de ESFE Agape.

## Requisitos previos

- JDK 21
- Maven 3.9 o posterior
- Node.js y npm/npx para instalar Chromium con Playwright
- Acceso a Internet para descargar dependencias y abrir el sitio

## Preparar el entorno

1. Instala y configura JDK 21. Comprueba la versión activa:

	```bash
	java -version
	```

2. Instala Maven y verifica que esté disponible:

	```bash
	mvn -version
	```

3. Instala Chromium:

	```bash
	npx --yes playwright@1.56.0 install chromium
	```

	En Linux, instala también las dependencias del sistema de Chromium para evitar errores de bibliotecas compartidas faltantes:

	```bash
	npx --yes playwright@1.56.0 install --with-deps chromium
	```

	Este comando puede requerir permisos de administrador para instalar paquetes del sistema. Selenium Manager también puede administrar el navegador y el driver al ejecutar la prueba.

## Estructura creada

- `pom.xml`: configura Java 21, JUnit 5, Selenium WebDriver, Surefire y Allure para el reporte HTML.
- `src/test/java/sv/edu/agape/e2e/CarreraDesarrolloSistemasTest.java`: contiene el caso E2E.
- `README.md`: documenta configuración y ejecución.

La suite contiene estos casos E2E:

- Abre `https://esfe.agape.edu.sv/carrera-desarrollo-de-sistemas/` y comprueba que el texto visible contenga `Titulo Obtenido: Técnico en Ingeniería de Desarrollo de Sistemas`.
- Abre el menú `INSTITUCIÓN`, selecciona `Nosotros`, espera la ruta `/nosotros/` y comprueba que la página muestre `Misión`.

Las esperas de contenido tienen un límite de 30 segundos.

## Ejecutar las pruebas

Desde la raíz del proyecto, ejecuta:

```bash
mvn clean verify
```

Maven compila con destino Java 21, ejecuta las pruebas en modo headless y genera el reporte HTML de Allure durante la fase `verify`.

## Reportes

- Reporte HTML interactivo de Allure: `target/reports/allure/index.html`
- Resultados de Allure: `target/allure-results/`
- Resultados XML de JUnit: `target/surefire-reports/`

El reporte muestra el estado, duración, etiquetas funcionales y detalles de cada caso. Para compilar sin ejecutar el navegador puedes usar `mvn -DskipTests package`.